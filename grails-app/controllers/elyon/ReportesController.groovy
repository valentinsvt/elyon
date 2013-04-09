package elyon
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter
import com.lowagie.text.PageSize;

class ReportesController {

    def index() { }

    def buscadorService
    def dbConnectionService

    def archivo = {
        def cn = dbConnectionService.getConnection()
        def tx = 0;
        def sp =";"
        def tx_sql = "select data.*, tpidcdgo from data, tpid where tpid.tpid__id = data.tpid__id " +
                "order by dataap01, dataap02"
        println tx_sql
        cn.eachRow(tx_sql.toString()) {d ->
            tx  = d.tpidcdgo
            tx += sp + completa(d.datanmid, 10)
            tx += sp + completa(d.dataap01, 20)
            tx += sp + completa(d.dataap02, 20)
            tx += sp + completa(d.datanb01, 20)
            tx += sp + completa(d.databn02, 20)
            tx += sp + completa((d.datadrrs != null ?: " "), 140)
            tx += sp + completa((d.datadrtb != null ?: " "), 150)
            tx += sp + completa((d.ruta__id ? Ruta.get(d.ruta__id).codigo : " "), 1)
            tx += sp + completa((d.rutaedcn ? Ruta.get(d.rutaedcn).codigo : " "), 1)
            tx += sp + completa((d.datatelf != null ?: " "), 20)
            tx += sp + completa((d.datamail != null ?: " "), 50)
            tx += sp + completa((d.ofic__id ? Sucursal.get(Oficina.get(d.ofic__id)).codigo : " "), 3)
            tx += sp + completa((d.ofic__id ? Oficina.get(d.ofic__id).codigo : " "), 3)
            tx += sp + completa((d.parr__id ? Ciudad.get(Parroquia.get(d.parr__id)).codigo : " "), 8)
            tx += sp + completa((d.parr__id ? Parroquia.get(d.parr__id).codigo : " "), 8)
            tx += sp + completa((d.ncnl__id ? Nacionalidad.get(d.ncnl__id).codigo : " "), 3)
            tx += sp + completa((d.datafcna != null ?: " "), 8)
            tx += sp + completa((d.sexo__id ? Sexo.get(d.sexo__id).codigo : " "), 1)
            tx += sp + completa ((d.edcv__id ? EstadoCivil.get(d.edcv__id).codigo: " "), 1)
            tx += sp + completa ((d.nves__id ? NivelEstudios.get(d.nves__id).codigo: " "), 2)
            tx += sp + completa ((d.prof__id ? Profesion.get(d.prof__id).codigo: " "),3)
            tx += sp + completa ((d.acec__id ? ActividadEconomica.get(d.acec__id).codigo : " "),7)
            tx += sp + completa ((d.orig__id ? OrigenIngresos.get(d.orig__id).codigo : " "), 2)
            tx += sp + completa((d.rgig__id ? RangoIngresos.get(d.rgig__id).codigo : " "),2)
            tx += sp + completa((d.datapatr != null ?: " "),12)
            tx += sp + completa ((d.tpvv__id ? TipoVivienda.get(d.tpvv__id).codigo : " "),2)
            tx += sp + completa ((d.datavlvv != null ?: " "),12)
//            tx += sp + completa ((d.))       //tiempo residencia
            tx += sp +  completa ((d.rldp__id ? RelacionDependenciaLaboral.get(d.rldp__id).codigo : " "), 2)
//            tx += sp + completa()   //tiempo ultimoTrabajo
            tx += sp + completa((d.datafcit != null ?: " "),8)
            tx += sp + completa((d.datafcft != null ?: " "),8)
            tx += sp + completa((d.datacrga != null ?: " "), 2)
            tx += sp + completa((d.bins__id ? Bins.get(d.bins__id).codigo : " "),6)
            tx += sp + completa((d.afnd__id ? Afinidad.get(d.afnd__id).codigo : " "),3)
            tx += sp + completa((d.datacupo != null ?: " "),7)
            tx += sp + completa((d.datanmbr != null ?: " "),19)
            tx += sp + completa((d.vend__id ? Vendedor.get(d.vend__id).codigo: " "),5)
            tx += sp + completa((d.rfprtpid ? TipoDeIdentificacion.get(d.rfprtpid).codigo: " "),1)
            tx += sp + completa((d.datarfpr != null ?: " "),10)
            tx += sp + completa ((d.dataa1rp != null ?: " "),20)
            tx += sp + completa ((d.dataa2rf != null ?: " "),20)
            tx += sp + completa ((d.datan1rp != null ?: " "),20)
            tx += sp + completa ((d.datan2rp != null ?: " "),20)
            tx += sp + completa ((d.datadrrp != null ?: " "),150)
            tx += sp + completa ((d.datatfrp != null ?: " "),20)
            tx += sp + completa ((d.tptj__id ? TipoTarjeta.get(d.tptj__id).codigo: " "),1)

            if(TipoTarjeta.get(d.tptj__id).codigo != 'P'){
                tx += sp + completa ((d.rfprtpid ? TipoTarjeta.get(d.rfprtpid).codigo: " "),1)
            }else {
                tx +=sp + completa(" ",1)
            }
            if(TipoTarjeta.get(d.tptj__id).codigo != 'P'){

                tx += sp + completa ((d.datanmid != null ?: " "),10)

            }else {

                 tx += sp + completa ("0000000000",10)
            }
            if(TipoTarjeta.get(d.tptj__id).codigo != 'P'){

                tx += sp + completa((d.bins__id ? Bins.get(d.bins__id).codigo : " "), 6)

            } else {

               tx += sp + completa(" ", 6)
            }
            if (TipoTarjeta.get(d.tptj__id).codigo != 'P'){

                tx += sp + completa((d.prnt__id ? Parentesco.get(d.prnt__id).codigo: " "),2)


            }else {

                tx += sp + completa(" ", 2)
            }





        }
        cn.close()
        println tx
        render(tx)
    }

    def completa(tx, n) {
        return tx.toString() + " " * (n - (tx?tx.size():0).toInteger()).toInteger()
    }

    def reporteBuscador= {

        // println "reporte buscador params !! "+params
        if (!session.dominio)
            response.sendError(403)
        else{
            def listaTitulos = params.listaTitulos
            def listaCampos = params.listaCampos
            def lista = buscadorService.buscar(session.dominio, params.tabla, "excluyente", params, true,params.extras)
            def funciones = session.funciones
            session.dominio=null
            session.funciones=null
            lista.pop()

            def baos = new ByteArrayOutputStream()
            def name = "reporte_de_"+params.titulo.replaceAll(" ","_")+"_"+new Date().format("ddMMyyyy_hhmm")+".pdf";
//            println "name "+name
            Font catFont = new Font(Font.TIMES_ROMAN, 10,Font.BOLD);
            Font info = new Font(Font.TIMES_ROMAN, 8,Font.NORMAL)
            Document document
            if(params.landscape)
               document = new Document(PageSize.A4.rotate());
            else
               document = new Document();

            def pdfw= PdfWriter.getInstance(document,baos);

            document.open();
            document.addTitle("Reporte de "+params.titulo+" "+new Date().format("dd_MM_yyyy"));
            document.addSubject("Generado por el sistema Elyon");
            document.addKeywords("reporte, elyon,"+params.titulo);
            document.addAuthor("Elyon");
            document.addCreator("Tedein SA");
            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Reporte de "+params.titulo, catFont));
            preface.add(new Paragraph("Generado por el usuario: "+session.usuario+"   el: "+new Date().format("dd/MM/yyyy hh:mm"),info))
            addEmptyLine(preface, 1);
            document.add(preface);
//        Start a new page
//        document.newPage();
            //System.getProperty("user.name")
            addContent(document,catFont,listaCampos.size(),listaTitulos,params.anchos, listaCampos, funciones, lista);            // Los tamaños son porcentajes!!!!
            document.close();
            pdfw.close()
            byte[] b = baos.toByteArray();
            response.setContentType("application/pdf")
            response.setHeader("Content-disposition", "attachment; filename=" + name)
            response.setContentLength(b.length)
            response.getOutputStream().write(b)
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }


    private static void addContent(Document document,catFont,columnas,headers,anchos,campos,funciones,datos) throws DocumentException {
        Font small= new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
        def parrafo =  new Paragraph("")
        createTable(parrafo,columnas,headers,anchos,campos,funciones,datos);
        document.add(parrafo);



    }


    private static void createTable(Paragraph subCatPart,columnas,headers,anchos,campos,funciones,datos) throws BadElementException {
        PdfPTable table = new PdfPTable(columnas);
        table.setWidthPercentage(100);
        table.setWidths(arregloEnteros(anchos))
        Font small= new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
        headers.eachWithIndex{h,i->
            PdfPCell c1 = new PdfPCell(new Phrase(h,small));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);
        def tagLib = new BuscadorTagLib()
        datos.each{d->
            campos.eachWithIndex{c,j->
                def campo
                if(funciones){
                    if(funciones[j])
                        campo = tagLib.operacion([propiedad:c,funcion:funciones[j],registro:d]).toString()
                    else
                        campo = d.properties[c].toString()
                }else{
                    campo = d.properties[c].toString()
                }

                table.addCell(new Phrase(campo,small));

            }

        }

        subCatPart.add(table);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }


    static arregloEnteros(array){
        int[] ia= new int [array.size()]
        array.eachWithIndex{it,i->
            ia[i]=it.toInteger()
        }

        return ia
    }


}
