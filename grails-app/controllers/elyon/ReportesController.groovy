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
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.write.*

import java.text.SimpleDateFormat

//import java.awt.Label


class ReportesController {

    def index() {}

    def buscadorService
    def dbConnectionService

    def crearTxt (tx_sql) {


        def cn = dbConnectionService.getConnection()
        def tx = "";
        def sp = ";"

        println tx_sql
        cn.eachRow(tx_sql.toString()) { d ->
            tx += d.tpidcdgo
            tx += sp + completa(d.datanmid, 10)
            tx += sp + completa(d.dataap01, 20)
            tx += sp + completa(d.dataap02, 20)
            tx += sp + completa(d.datanb01, 20)
            tx += sp + completa(d.databn02, 20)
            tx += sp + completa(((d.datadrrs != null)? d.datadrrs : " "), 150)
            tx += sp + completa(((d.datadrtb != null) ? d.datadrtb: " "), 150)
            tx += sp + completa((d.ruta__id ? Ruta.get(d.ruta__id).codigo : " "), 1)
            tx += sp + completa((d.rutaedcn ? Ruta.get(d.rutaedcn).codigo : " "), 1)
            tx += sp + completa(((d.datatelf != null) ? d.datatelf: " "), 20)
            tx += sp + completa(((d.datatltr != null) ? d.datatltr: " "), 20)
            tx += sp + completa(((d.datacllr != null) ? d.datacllr : " "), 20)
            tx += sp + completa(((d.datamail != null) ? d.datamail: " "), 50)
            tx += sp + numeros((d.ofic__id ? Oficina.get(d.ofic__id).sucursal.codigo : "0"), 3)
            tx += sp + numeros((d.ofic__id ? Oficina.get(d.ofic__id).codigo : "0"), 3)
            tx += sp + completa((d.parr__id ? Parroquia.get(d.parr__id).ciudad.codigo : " "), 8)
            tx += sp + completa((d.parr__id ? Parroquia.get(d.parr__id).codigo : " "), 8)
            tx += sp + completa((d.ncnl__id ? Nacionalidad.get(d.ncnl__id).codigo : " "), 3)
            tx += sp + completa(((d.datafcna != null) ? new SimpleDateFormat("yyyyMMdd").format(d.datafcna) : " "), 8)
            tx += sp + completa((d.sexo__id ? Sexo.get(d.sexo__id).codigo : " "), 1)
            tx += sp + completa((d.edcv__id ? EstadoCivil.get(d.edcv__id).codigo : " "), 1)
            tx += sp + completa((d.nves__id ? NivelEstudios.get(d.nves__id).codigo : " "), 2)
            tx += sp + completa((d.prof__id ? Profesion.get(d.prof__id).codigo : " "), 3)
            tx += sp + numeros((d.acec__id ? ActividadEconomica.get(d.acec__id).codigo : "0"), 7)
            tx += sp + numeros((d.orig__id ? OrigenIngresos.get(d.orig__id).codigo : "0"), 2)
            tx += sp + numeros((d.rgig__id ? RangoIngresos.get(d.rgig__id).codigo : "0"), 2)
            tx += sp + numerosf((d.datapatr * 100 ?: "0"), 12)
            tx += sp + completa((d.tpvv__id ? TipoVivienda.get(d.tpvv__id).codigo : " "), 2)
            tx += sp + numerosf((d.datavlvv * 100 ?: "0"), 12)
            tx += sp + completa(((d.datafirs != null) ? new SimpleDateFormat("yyyyMMdd").format(d.datafirs) : " "), 8)
            tx += sp + completa((d.rldp__id ? RelacionDependenciaLaboral.get(d.rldp__id).codigo : " "), 2)
            tx += sp + completa(((d.datafita != null )? new SimpleDateFormat("yyyyMMdd").format(d.datafita) : " "), 8)
            tx += sp + completa(((d.datafcit != null) ? new SimpleDateFormat("yyyyMMdd").format(d.datafcit) : " "), 8)
            tx += sp + completa(((d.datafcft != null) ? new SimpleDateFormat("yyyyMMdd").format(d.datafcft) : " "), 8)
            tx += sp + numeros((d.datacrga ?: "0"), 2)
            tx += sp + completa((d.bins__id ? Bins.get(d.bins__id).codigo : " "), 6)
            //tx += sp + completa((d.afnd__id ? Afinidad.get(d.afnd__id).codigo : " "), 3)
            tx += sp + "00 "   /** se pone siempre como afinidad '00 ' */
            tx += sp + numerosf((d.datacupo *100 ?: "0"), 7)
            tx += sp + completa((d.datanmbr ?: " "), 19)
            tx += sp + completa((d.vend__id ? Vendedor.get(d.vend__id).codigo : " "), 5)
            tx += sp + completa((d.rfprtpid ? TipoDeIdentificacion.get(d.rfprtpid).codigo : " "), 1)
            tx += sp + completa((d.datarfpr ?: " "), 10)
            tx += sp + completa(((d.dataa1rp != null) ? d.dataa1rp : " "), 20)
            tx += sp + completa(((d.dataa2rf != null) ? d.dataa2rf : " "), 20)
            tx += sp + completa(((d.datan1rp != null) ? d.datan1rp : " "), 20)
            tx += sp + completa(((d.datan2rp != null) ? d.datan2rp : " "), 20)
            tx += sp + completa(((d.datadrrp != null) ? d.datadrrp : " "), 150)
            tx += sp + completa(((d.datatfrp != null) ? d.datatfrp : " "), 20)

            tx += sp + completa((d.prnt__id ? Parentesco.get(d.prnt__id).codigo : " "), 2)




//            tx += sp + completa((d.tptj__id ? TipoTarjeta.get(d.tptj__id).codigo : " "), 1)

            tx += sp + completa("P", 1)


//            if (d.tptj__id != null) {
//
//                if (TipoTarjeta.get(d.tptj__id).codigo != 'P') {
//                    tx += sp + completa((d.rfprtpid ? TipoTarjeta.get(d.rfprtpid).codigo : " "), 1)
//                } else {
            tx += sp + completa(" ", 1)
//                }
//                if (TipoTarjeta.get(d.tptj__id).codigo != 'P') {

//                    tx += sp + completa((d.datanmid != null ?: " "), 10)
//
//                } else {

            tx += sp + completa("0000000000", 10)
//                }
//                if (TipoTarjeta.get(d.tptj__id).codigo != 'P') {

//                    tx += sp + completa((d.bins__id ? Bins.get(d.bins__id).codigo : " "), 6)

//                } else {

            tx += sp + completa(" ", 6)
//                }
//                if (TipoTarjeta.get(d.tptj__id).codigo != 'P') {
//
//                    tx += sp + completa((d.prnt__id ? Parentesco.get(d.prnt__id).codigo : " "), 2)


//                } else {

            tx += sp + completa(" ", 2)

            tx += sp + "\n"
//                }
//            }
        }
        cn.close()


      return tx
    }


    def archivo = {
//        def cn = dbConnectionService.getConnection()
////        def tx = 0;
//        def sp = ";"
        def tx_sql = "select data.*, tpidcdgo from data, lote, tpid where tpid.tpid__id = data.tpid__id and " +
                "lote.lote__id = data.lote__id and edgs__id = 5 order by dataap01, dataap02"

/*
        def tx_sql = "select data.tpidcdgo, data.nmid, data.dataap01, data.dataap02, data.datanb01, data.datanb02," +
                "data.datadrrs, data.datadrtb, data.ruta__id, data.rutaedcn, data.datatelf, data.datatltr" +
                "data.datacllr, data.datamail, data.ofic__id, data.parr__id, data.ncnl__id, " +
                "cast(data.datafcna as date) datafcha, data.sexo__id, data.edcv__id, data.nves__id, data.prof__id, " +
                "data.acec__id, data.orig__id, data.datapatr, data.tpvv__id, data.datavlvv, data.datafirs, " +
                "data.rldp__id, data.datafita, cast(data.datafcit as date) datafcit, cast(data.datafcft as date) datafcft, " +
                "data.datacrga, data.bins__id, data.afnd__id, data.datacupo, data.datanmbr, data.vend__id, " +
                "data.rfprtpid, data.datarfpr, data.dataa1rp, data.dataa2rf, data.datan1rp, data.datan2rp, " +
                "data.datadrrp, data.datatfrp, tpidcdgo from data, lote, tpid where tpid.tpid__id = data.tpid__id and " +
                "lote.lote__id = data.lote__id and edgs__id = 5 order by dataap01, dataap02"
*/

        def tx = crearTxt(tx_sql)



        /*println tx_sql
        cn.eachRow(tx_sql.toString()) { d ->
            tx = d.tpidcdgo
            tx += sp + completa(d.datanmid, 10)
            tx += sp + completa(d.dataap01, 20)
            tx += sp + completa(d.dataap02, 20)
            tx += sp + completa(d.datanb01, 20)
            tx += sp + completa(d.databn02, 20)
            tx += sp + completa((d.datadrrs != null ?: " "), 150)
            tx += sp + completa((d.datadrtb != null ?: " "), 150)
            tx += sp + completa((d.ruta__id ? Ruta.get(d.ruta__id).codigo : " "), 1)
            tx += sp + completa((d.rutaedcn ? Ruta.get(d.rutaedcn).codigo : " "), 1)
            tx += sp + completa((d.datatelf != null ?: " "), 20)
            tx += sp + completa((d.datatltr != null ?: " "), 20)
            tx += sp + completa((d.datacllr != null ?: " "), 20)
            tx += sp + completa((d.datamail != null ?: " "), 50)
            tx += sp + completa((d.ofic__id ? Sucursal.get(Oficina.get(d.ofic__id).id ?: 0)?.codigo : " "), 3)
            tx += sp + completa((d.ofic__id ? Oficina.get(d.ofic__id).codigo : " "), 3)
            tx += sp + completa((d.parr__id ? Ciudad.get(Parroquia.get(d.parr__id).id ?: 0)?.codigo : " "), 8)
            tx += sp + completa((d.parr__id ? Parroquia.get(d.parr__id).codigo : " "), 8)
            tx += sp + completa((d.ncnl__id ? Nacionalidad.get(d.ncnl__id).codigo : " "), 3)
            tx += sp + completa((d.datafcna != null ?: " "), 8)
            tx += sp + completa((d.sexo__id ? Sexo.get(d.sexo__id).codigo : " "), 1)
            tx += sp + completa((d.edcv__id ? EstadoCivil.get(d.edcv__id).codigo : " "), 1)
            tx += sp + completa((d.nves__id ? NivelEstudios.get(d.nves__id).codigo : " "), 2)
            tx += sp + completa((d.prof__id ? Profesion.get(d.prof__id).codigo : " "), 3)
            tx += sp + completa((d.acec__id ? ActividadEconomica.get(d.acec__id).codigo : " "), 7)
            tx += sp + completa((d.orig__id ? OrigenIngresos.get(d.orig__id).codigo : " "), 2)
            tx += sp + completa((d.rgig__id ? RangoIngresos.get(d.rgig__id).codigo : " "), 2)
            tx += sp + completa((d.datapatr ?: "0"), 12)
            tx += sp + completa((d.tpvv__id ? TipoVivienda.get(d.tpvv__id).codigo : " "), 2)
            tx += sp + completa((d.datavlvv ?: "0"), 12)
            tx += sp + completa((d.datafirs != null ?: " "), 8)
            tx += sp + completa((d.rldp__id ? RelacionDependenciaLaboral.get(d.rldp__id).codigo : " "), 2)
            tx += sp + completa((d.datafita != null ?: " "), 8)
            tx += sp + completa((d.datafcit != null ?: " "), 8)
            tx += sp + completa((d.datafcft != null ?: " "), 8)
            tx += sp + completa((d.datacrga ?: "0"), 2)
            tx += sp + completa((d.bins__id ? Bins.get(d.bins__id).codigo : " "), 6)
            tx += sp + completa((d.afnd__id ? Afinidad.get(d.afnd__id).codigo : " "), 3)
            tx += sp + completa((d.datacupo ?: "0"), 7)
            tx += sp + completa((d.datanmbr ?: " "), 19)
            tx += sp + completa((d.vend__id ? Vendedor.get(d.vend__id).codigo : " "), 5)
            tx += sp + completa((d.rfprtpid ? TipoDeIdentificacion.get(d.rfprtpid).codigo : " "), 1)
            tx += sp + completa((d.datarfpr ?: " "), 10)
            tx += sp + completa((d.dataa1rp != null ?: " "), 20)
            tx += sp + completa((d.dataa2rf != null ?: " "), 20)
            tx += sp + completa((d.datan1rp != null ?: " "), 20)
            tx += sp + completa((d.datan2rp != null ?: " "), 20)
            tx += sp + completa((d.datadrrp != null ?: " "), 150)
            tx += sp + completa((d.datatfrp != null ?: " "), 20)



//            tx += sp + completa((d.tptj__id ? TipoTarjeta.get(d.tptj__id).codigo : " "), 1)

            tx += sp + completa("P", 1)


//            if (d.tptj__id != null) {
//
//                if (TipoTarjeta.get(d.tptj__id).codigo != 'P') {
//                    tx += sp + completa((d.rfprtpid ? TipoTarjeta.get(d.rfprtpid).codigo : " "), 1)
//                } else {
                    tx += sp + completa(" ", 1)
//                }
//                if (TipoTarjeta.get(d.tptj__id).codigo != 'P') {

//                    tx += sp + completa((d.datanmid != null ?: " "), 10)
//
//                } else {

                    tx += sp + completa("0000000000", 10)
//                }
//                if (TipoTarjeta.get(d.tptj__id).codigo != 'P') {

//                    tx += sp + completa((d.bins__id ? Bins.get(d.bins__id).codigo : " "), 6)

//                } else {

                    tx += sp + completa(" ", 6)
//                }
//                if (TipoTarjeta.get(d.tptj__id).codigo != 'P') {
//
//                    tx += sp + completa((d.prnt__id ? Parentesco.get(d.prnt__id).codigo : " "), 2)


//                } else {

                    tx += sp + completa(" ", 2)
//                }
//            }
        }
        cn.close()*/
        //println tx
        //render(tx)
        def output = response.getOutputStream()


        def header = "attachment; filename=" + "ventas.txt"
        response.setContentType("text/txt")
        response.outputStream << tx
        response.outputStream.flush()

    }


    def crearExcel (tx_sql) {


        def cn = dbConnectionService.getConnection()
        def tx = 0;
        def sp = ";"
//        def tx_sql = "select data.*, tpidcdgo from data, tpid where tpid.tpid__id = data.tpid__id " +
//                "order by dataap01, dataap02"

//        println tx_sql
        WorkbookSettings workbookSettings = new WorkbookSettings()
        workbookSettings.locale = Locale.default

        def file = File.createTempFile('myExcelDocument', '.xls')
        file.deleteOnExit()
        WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)

        WritableFont font = new WritableFont(WritableFont.ARIAL, 12)
        WritableCellFormat formatXls = new WritableCellFormat(font)


        def row = 0
        WritableSheet sheet = workbook.createSheet('MySheet', 0)

        WritableFont times16font = new WritableFont(WritableFont.TIMES, 11, WritableFont.BOLD, true);
        WritableCellFormat times16format = new WritableCellFormat(times16font);
        sheet.setColumnView(0, 15)
        sheet.setColumnView(1, 25)
        sheet.setColumnView(2, 25)
        sheet.setColumnView(3, 25)
        sheet.setColumnView(4, 25)
        sheet.setColumnView(5, 25)
        sheet.setColumnView(6, 25)
        sheet.setColumnView(7, 15)
        sheet.setColumnView(8, 25)
        sheet.setColumnView(9, 15)
//        sheet.setColumnView(10, 15)
//        sheet.setColumnView(11, 15)

        def label
        def nmro

        def fila = 3;

        label = new jxl.write.Label(0, 2, "CÉDULA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(1,2, "NOMBRES", times16format); sheet.addCell(label);

//        label = new jxl.write.Label(1, 2, "APELLIDO", times16format); sheet.addCell(label);
//        label = new jxl.write.Label(2, 2, "APELLIDO 2", times16format); sheet.addCell(label);
//        label = new jxl.write.Label(3, 2, "NOMBRE", times16format); sheet.addCell(label);
//        label = new jxl.write.Label(4, 2, "NOMBRE 2", times16format); sheet.addCell(label);
        label = new jxl.write.Label(2, 2, "EMPRESA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(3, 2, "DIRECCIÓN DOMICILIO", times16format); sheet.addCell(label);
        label = new jxl.write.Label(4, 2, "TELÉFONO DOMICILIO", times16format); sheet.addCell(label);
        label = new jxl.write.Label(5, 2, "DIRECCIÓN OFICINA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(6, 2, "TELÉFONO OFICINA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(7, 2, "CELULAR", times16format); sheet.addCell(label);
        label = new jxl.write.Label(8, 2, "CIUDAD", times16format); sheet.addCell(label);
        label = new jxl.write.Label(9, 2, "OPERADOR", times16format); sheet.addCell(label);


        cn.eachRow(tx_sql.toString()) { d ->
//              println("\t" + d)


            if (d.datanmid){

                label = new jxl.write.Label(0, fila, d.datanmid.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(0, fila, " "); sheet.addCell(label);
            }
            if (d.datanb01 || d.databn02 || d.dataap01 || d.dataap02){


                label = new jxl.write.Label(1, fila, d.datanb01.toString() + " " + d.databn02.toString() + " " + d.dataap01.toString() + " " + d.dataap02.toString()); sheet.addCell(label);

            }else {



                label = new jxl.write.Label(1, fila, " "); sheet.addCell(label);
            }

            if (d.dataempr){

                label = new jxl.write.Label(2, fila, d.dataempr.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(2, fila, " "); sheet.addCell(label);
            }

            if (d.datadrrs){

                label = new jxl.write.Label(3, fila, d.datadrrs.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(3, fila, " "); sheet.addCell(label);
            }

            if (d.datatelf){

                label = new jxl.write.Label(4, fila, d.datatelf.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(4, fila, " "); sheet.addCell(label);
            }
            if (d.datadrtb){

                label = new jxl.write.Label(5, fila, d.datadrtb.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(5, fila, " "); sheet.addCell(label);
            }
            if (d.datatltr){

                label = new jxl.write.Label(6, fila, d.datatltr.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(6, fila, " "); sheet.addCell(label);
            }
            if (d.datacllr){

                label = new jxl.write.Label(7, fila, d.datacllr.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(7, fila, " "); sheet.addCell(label);
            }

            if (d.parr__id){

                label = new jxl.write.Label(8, fila, (Parroquia.get(d.parr__id).ciudad).toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(8, fila, " "); sheet.addCell(label);
            }
            if(d.lote__id){

                label = new jxl.write.Label(9, fila, (Lote.get(d.lote__id).ordenDeTrabajo.usro.nombre.toString() + " " + Lote.get(d.lote__id).ordenDeTrabajo.usro.apellido.toString())); sheet.addCell(label);


            }else {

                label = new jxl.write.Label(9, fila, " "); sheet.addCell(label);


            }



            fila++

        }

        cn.close()



        workbook.write();
        workbook.close();


        return file

//        def output = response.getOutputStream()
//        def header = "attachment; filename=" + "Excel.xls";
//        response.setContentType("application/octet-stream")
//        response.setHeader("Content-Disposition", header);
//        output.write(file.getBytes());


    }




    def completa(tx, n) {
//        println "competa:" + tx.toString() + "  n " + n
//
/*
        println "!********************************************"
        println tx?.toString()
        println n
        println tx?.toString()?.size()
        println tx?.toString() + " " * (n - (tx ? tx.toString().size() : 0).toInteger()).toInteger()
*/

        return tx?.toString() + " " * (n - (tx ? tx.toString().size() : 0).toInteger()).toInteger()
    }

    def numeros(tx, n) {
//        println "competa:" + tx.toString() + "  n " + n
//
/*
        println "!********************************************"
        println tx?.toString()
        println n
        println tx?.toString()?.size()
        println tx?.toString() + "0" * (n - (tx ? tx.toString().size() : 0).toInteger()).toInteger()
*/

        return "0" * (n - (tx ? tx.toString().size() : 0).toInteger()).toInteger() + tx?.toString()
    }

    def numerosf(nmro, n) {
        println nmro
        def tx = nmro.toInteger().toString()
        println tx

        println "0" * (n - (tx ? tx.toString().size() : 0).toInteger()).toInteger() + tx?.toString()

        return "0" * (n - (tx ? tx.toString().size() : 0).toInteger()).toInteger() + tx?.toString()
    }


    def ventasReporte () {

        def fechaInicio = new Date().parse("dd-MM-yyyy",params.fechaInicio).format("yyyy-MM-dd");
        def fechaFin = new Date().parse("dd-MM-yyyy",params.fechaFin)
//        println "fecha "+fechaFin
        fechaFin=fechaFin.plus(1)
        fechaFin=fechaFin.format("yyyy-MM-dd")



       def tx_sql = "select data.*, tpidcdgo from data, lote, tpid where tpid.tpid__id = data.tpid__id and lote.lote__id = data.lote__id and edgs__id=5 " +
               " and datafcha >= '${fechaInicio}' and datafcha <= '${fechaFin}' " +
                "order by dataap01, dataap02"

        println tx_sql


        def tx = crearTxt(tx_sql)

        def output = response.getOutputStream()

        def header = "attachment; filename=" + "ventas.txt"
        response.setContentType("text/txt")
        response.outputStream << tx
        response.outputStream.flush()



    }


    def ventasReporteExcel () {

        def fechaInicio = new Date().parse("dd-MM-yyyy",params.fechaInicio).format("yyyy-MM-dd");
        def fechaFin = new Date().parse("dd-MM-yyyy",params.fechaFin);

        fechaFin=fechaFin.plus(1)
        fechaFin=fechaFin.format("yyyy-MM-dd")


        def tx_sql = "select data.*, tpidcdgo from data, lote, tpid where tpid.tpid__id = data.tpid__id and lote.lote__id = data.lote__id and edgs__id = 5  " +
                " and datafcha >= '${fechaInicio}' and datafcha <= '${fechaFin}' " +
                "order by dataap01, dataap02"

        def tx = crearExcel(tx_sql)

        def output = response.getOutputStream()
        def header = "attachment; filename=" + "Excel.xls";
        response.setContentType("application/octet-stream")
        response.setHeader("Content-Disposition", header);

        output.write(tx.getBytes());


    }


    def ventas () {



    }


    def reporteDetallado () {

        def campana = Campana.get(params.id);

        def orden = OrdenDeTrabajo.findByCampana(campana)

        def lote = Lote.findAllByCampana(campana)

        def usuario = session.usuario

        WorkbookSettings workbookSettings = new WorkbookSettings()
        workbookSettings.locale = Locale.default

        def file = File.createTempFile('myExcelDocument', '.xls')
        file.deleteOnExit()
        WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)

        WritableFont font = new WritableFont(WritableFont.ARIAL, 12)
        WritableCellFormat formatXls = new WritableCellFormat(font)

        def row = 0
        WritableSheet sheet = workbook.createSheet('MySheet', 0)

        WritableFont times16font = new WritableFont(WritableFont.TIMES, 11, WritableFont.BOLD, true);
        WritableCellFormat times16format = new WritableCellFormat(times16font);
        sheet.setColumnView(0, 20)
        sheet.setColumnView(1, 15)
        sheet.setColumnView(2, 15)
        sheet.setColumnView(3, 20)
        sheet.setColumnView(4, 20)
        sheet.setColumnView(5, 40)
        sheet.setColumnView(6, 25)
        sheet.setColumnView(7, 20)
        sheet.setColumnView(8, 18)
        sheet.setColumnView(9, 18)
        sheet.setColumnView(10, 18)
        sheet.setColumnView(11, 18)
        sheet.setColumnView(12, 18)
        sheet.setColumnView(13, 18)
        sheet.setColumnView(14, 18)
        sheet.setColumnView(15, 18)
        sheet.setColumnView(16, 18)
        sheet.setColumnView(17, 18)
        sheet.setColumnView(18, 18)
        sheet.setColumnView(19, 18)
        sheet.setColumnView(20, 18)
        sheet.setColumnView(21, 20)
        sheet.setColumnView(22, 15)
        sheet.setColumnView(23, 20)


        def label
        def number
        def fila = 8;

        label = new jxl.write.Label(2, 1, "Sistema de Gestión Telefónica".toUpperCase(), times16format); sheet.addCell(label);
        label = new jxl.write.Label(2, 2, "Reporte Detallado", times16format); sheet.addCell(label);

        label = new jxl.write.Label(1, 4, "", times16format); sheet.addCell(label);
        label = new jxl.write.Label(4, 4, "", times16format); sheet.addCell(label);


        label = new jxl.write.Label(0, 6, "CAMPAÑA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(1, 6, "ORDEN", times16format); sheet.addCell(label);
        label = new jxl.write.Label(2, 6, "CANTIDAD", times16format); sheet.addCell(label);
        label = new jxl.write.Label(3, 6, "OPERADOR", times16format); sheet.addCell(label);
        label = new jxl.write.Label(4, 6, "CODIGO CLIENTE", times16format); sheet.addCell(label);
        label = new jxl.write.Label(5, 6, "CLIENTE", times16format); sheet.addCell(label);
        label = new jxl.write.Label(6, 6, "ESTADO DEL INTENTO", times16format); sheet.addCell(label);
        label = new jxl.write.Label(7, 6, "FECHA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(8, 6, "TELÉFONO 1", times16format); sheet.addCell(label);
        label = new jxl.write.Label(9, 6, "ESTADO TELÉFONO 1", times16format); sheet.addCell(label);
        label = new jxl.write.Label(10, 6, "TELÉFONO 2", times16format); sheet.addCell(label);
        label = new jxl.write.Label(11, 6, "ESTADO TELÉFONO 2", times16format); sheet.addCell(label);
        label = new jxl.write.Label(12, 6, "TELÉFONO 3", times16format); sheet.addCell(label);
        label = new jxl.write.Label(13, 6, "ESTADO TELÉFONO 3", times16format); sheet.addCell(label);
        label = new jxl.write.Label(14, 6, "TELÉFONO 4", times16format); sheet.addCell(label);
        label = new jxl.write.Label(15, 6, "ESTADO TELÉFONO 4", times16format); sheet.addCell(label);
        label = new jxl.write.Label(16, 6, "TELÉFONO 5", times16format); sheet.addCell(label);
        label = new jxl.write.Label(17, 6, "ESTADO TELÉFONO 5", times16format); sheet.addCell(label);
        label = new jxl.write.Label(18, 6, "TELÉFONO 6", times16format); sheet.addCell(label);
        label = new jxl.write.Label(19, 6, "ESTADO TELÉFONO 6", times16format); sheet.addCell(label);
        label = new jxl.write.Label(20, 6, "OBSERVACIONES", times16format); sheet.addCell(label);
        label = new jxl.write.Label(21, 6, "CIUDAD", times16format); sheet.addCell(label);
        label = new jxl.write.Label(22, 6, "CUPO", times16format); sheet.addCell(label);
        label = new jxl.write.Label(23, 6, "TIPO TARJETA", times16format); sheet.addCell(label);


        lote.each {

            label = new jxl.write.Label(0, fila, it?.campana?.descripcion.toString()); sheet.addCell(label);
            label = new jxl.write.Label(1, fila, OrdenDeTrabajo.findByCampana(it?.campana).id.toString()); sheet.addCell(label);
            label = new jxl.write.Label(2, fila, lote.size().toString()); sheet.addCell(label);
            label = new jxl.write.Label(3, fila, usuario.toString()); sheet.addCell(label);
            label = new jxl.write.Label(4, fila, it?.cedula); sheet.addCell(label);
            label = new jxl.write.Label(5, fila, it?.nombre?.toString()); sheet.addCell(label);
            label = new jxl.write.Label(6, fila, it?.estadoGestion?.descripcion.toString()); sheet.addCell(label);
            if (it?.loteFecha){
                label = new jxl.write.Label(7, fila, it?.loteFecha.format("dd-MM-yyyy")); sheet.addCell(label);

            } else {

                label = new jxl.write.Label(7, fila," "); sheet.addCell(label);
            }
            label = new jxl.write.Label(8, fila, it?.telefono1); sheet.addCell(label);

            if(GestionTelefonica.findByLoteAndTelefono(it,it?.telefono1)?.estadoLlamada?.descripcion){
                label = new jxl.write.Label(9, fila, GestionTelefonica.findByLoteAndTelefono(it,it?.telefono1)?.estadoLlamada?.descripcion.toString()); sheet.addCell(label);

            } else {

                label = new jxl.write.Label(9, fila," "); sheet.addCell(label);
            }


            label = new jxl.write.Label(10, fila, it?.telefono2); sheet.addCell(label);

            if(GestionTelefonica.findByLoteAndTelefono(it,it?.telefono2)?.estadoLlamada?.descripcion){
                label = new jxl.write.Label(11, fila, GestionTelefonica.findByLoteAndTelefono(it,it?.telefono2)?.estadoLlamada?.descripcion.toString()); sheet.addCell(label);
            }   else {

                label = new jxl.write.Label(11, fila, " "); sheet.addCell(label);
            }


            label = new jxl.write.Label(12, fila, it?.telefono3); sheet.addCell(label);

            if( GestionTelefonica.findByLoteAndTelefono(it,it?.telefono3)?.estadoLlamada?.descripcion){

                label = new jxl.write.Label(13, fila, GestionTelefonica.findByLoteAndTelefono(it,it?.telefono3)?.estadoLlamada?.descripcion.toString()); sheet.addCell(label);
            } else {

                label = new jxl.write.Label(13, fila, " "); sheet.addCell(label);
            }


            label = new jxl.write.Label(14, fila, it?.telefono4); sheet.addCell(label);

            if(GestionTelefonica.findByLoteAndTelefono(it,it?.telefono4)?.estadoLlamada?.descripcion){

                label = new jxl.write.Label(15, fila, GestionTelefonica.findByLoteAndTelefono(it,it?.telefono4)?.estadoLlamada?.descripcion.toString()); sheet.addCell(label);
            }else {
                label = new jxl.write.Label(15, fila, " "); sheet.addCell(label);

            }

            label = new jxl.write.Label(16, fila, it?.telefono5); sheet.addCell(label);

            if(GestionTelefonica.findByLoteAndTelefono(it,it?.telefono5)?.estadoLlamada?.descripcion){
                label = new jxl.write.Label(17, fila, GestionTelefonica.findByLoteAndTelefono(it,it?.telefono5)?.estadoLlamada?.descripcion.toString()); sheet.addCell(label);
            } else {

                label = new jxl.write.Label(17, fila, " "); sheet.addCell(label);
            }


            label = new jxl.write.Label(18, fila, it?.telefono6); sheet.addCell(label);

            if(GestionTelefonica.findByLoteAndTelefono(it,it?.telefono6)?.estadoLlamada?.descripcion){

                label = new jxl.write.Label(19, fila, GestionTelefonica.findByLoteAndTelefono(it,it?.telefono6)?.estadoLlamada?.descripcion.toString()); sheet.addCell(label);
            } else {

                label = new jxl.write.Label(19, fila, " "); sheet.addCell(label);
            }

            if(it?.observaciones){
                label = new jxl.write.Label(20, fila, it?.observaciones.toString()); sheet.addCell(label);

            }else {

                label = new jxl.write.Label(20, fila, " "); sheet.addCell(label);
            }
            if(it?.ciudad){

                label = new jxl.write.Label(21, fila, it?.ciudad.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(21, fila, " "); sheet.addCell(label);
            }

            label = new jxl.write.Label(22, fila, it?.cupoNormal.toString()); sheet.addCell(label);
            label = new jxl.write.Label(23, fila, it?.tipoTarjeta.toString()); sheet.addCell(label);


            fila++

        }


        workbook.write();
        workbook.close();
        def output = response.getOutputStream()
        def header = "attachment; filename=" + "reporteDetalladoExcel.xls";
        response.setContentType("application/octet-stream")
        response.setHeader("Content-Disposition", header);
        output.write(file.getBytes());

    }


    def ventasExcel () {



    }


    def aExcel = {
        def cn = dbConnectionService.getConnection()
        def tx = 0;
        def sp = ";"
        def tx_sql = "select data.*, tpidcdgo from data, lote, tpid where tpid.tpid__id = data.tpid__id and " +
                "lote.lote__id = data.lote__id and edgs__id = 5 order by dataap01, dataap02"
//        println tx_sql
        WorkbookSettings workbookSettings = new WorkbookSettings()
        workbookSettings.locale = Locale.default

        def file = File.createTempFile('myExcelDocument', '.xls')
        file.deleteOnExit()
        WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)

        WritableFont font = new WritableFont(WritableFont.ARIAL, 12)
        WritableCellFormat formatXls = new WritableCellFormat(font)


        def row = 0
        WritableSheet sheet = workbook.createSheet('MySheet', 0)

        WritableFont times16font = new WritableFont(WritableFont.TIMES, 11, WritableFont.BOLD, true);
        WritableCellFormat times16format = new WritableCellFormat(times16font);
        sheet.setColumnView(0, 15)
        sheet.setColumnView(1, 25)
        sheet.setColumnView(2, 25)
        sheet.setColumnView(3, 25)
        sheet.setColumnView(4, 25)
        sheet.setColumnView(5, 25)
        sheet.setColumnView(6, 25)
        sheet.setColumnView(7, 15)
        sheet.setColumnView(8, 25)
        sheet.setColumnView(9, 15)
//        sheet.setColumnView(10, 15)
//        sheet.setColumnView(11, 15)

        def label
        def nmro

        def fila = 3;

        label = new jxl.write.Label(0, 2, "CÉDULA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(1,2, "NOMBRES", times16format); sheet.addCell(label);

//        label = new jxl.write.Label(1, 2, "APELLIDO", times16format); sheet.addCell(label);
//        label = new jxl.write.Label(2, 2, "APELLIDO 2", times16format); sheet.addCell(label);
//        label = new jxl.write.Label(3, 2, "NOMBRE", times16format); sheet.addCell(label);
//        label = new jxl.write.Label(4, 2, "NOMBRE 2", times16format); sheet.addCell(label);
        label = new jxl.write.Label(2, 2, "EMPRESA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(3, 2, "DIRECCIÓN DOMICILIO", times16format); sheet.addCell(label);
        label = new jxl.write.Label(4, 2, "TELÉFONO DOMICILIO", times16format); sheet.addCell(label);
        label = new jxl.write.Label(5, 2, "DIRECCIÓN OFICINA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(6, 2, "TELÉFONO OFICINA", times16format); sheet.addCell(label);
        label = new jxl.write.Label(7, 2, "CELULAR", times16format); sheet.addCell(label);
        label = new jxl.write.Label(8, 2, "CIUDAD", times16format); sheet.addCell(label);
        label = new jxl.write.Label(9, 2, "OPERADOR", times16format); sheet.addCell(label);


        cn.eachRow(tx_sql.toString()) { d ->
//              println("\t" + d)


            if (d.datanmid){

                label = new jxl.write.Label(0, fila, d.datanmid.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(0, fila, " "); sheet.addCell(label);
            }
             if (d.datanb01 || d.databn02 || d.dataap01 || d.dataap02){


                 label = new jxl.write.Label(1, fila, d.datanb01.toString() + " " + d.databn02.toString() + " " + d.dataap01.toString() + " " + d.dataap02.toString()); sheet.addCell(label);

             }else {



                 label = new jxl.write.Label(1, fila, " "); sheet.addCell(label);
             }

            if (d.dataempr){

                label = new jxl.write.Label(2, fila, d.dataempr.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(2, fila, " "); sheet.addCell(label);
            }

            if (d.datadrrs){

                label = new jxl.write.Label(3, fila, d.datadrrs.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(3, fila, " "); sheet.addCell(label);
            }

            if (d.datatelf){

                label = new jxl.write.Label(4, fila, d.datatelf.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(4, fila, " "); sheet.addCell(label);
            }
            if (d.datadrtb){

                label = new jxl.write.Label(5, fila, d.datadrtb.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(5, fila, " "); sheet.addCell(label);
            }
            if (d.datatltr){

                label = new jxl.write.Label(6, fila, d.datatltr.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(6, fila, " "); sheet.addCell(label);
            }
            if (d.datacllr){

                label = new jxl.write.Label(7, fila, d.datacllr.toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(7, fila, " "); sheet.addCell(label);
            }

            if (d.parr__id){

                label = new jxl.write.Label(8, fila, (Parroquia.get(d.parr__id).ciudad).toString()); sheet.addCell(label);
            }else {

                label = new jxl.write.Label(8, fila, " "); sheet.addCell(label);
            }
            if(d.lote__id){

                label = new jxl.write.Label(9, fila, (Lote.get(d.lote__id).ordenDeTrabajo.usro.nombre.toString() + " " + Lote.get(d.lote__id).ordenDeTrabajo.usro.apellido.toString())); sheet.addCell(label);


            }else {

                label = new jxl.write.Label(9, fila, " "); sheet.addCell(label);


            }



            fila++

        }

        cn.close()

        workbook.write();
        workbook.close();
        def output = response.getOutputStream()
        def header = "attachment; filename=" + "Excel.xls";
        response.setContentType("application/octet-stream")
        response.setHeader("Content-Disposition", header);
        output.write(file.getBytes());

//        render(tx)
    }


    def reporteBuscador = {

        // println "reporte buscador params !! "+params
        if (!session.dominio)
            response.sendError(403)
        else {
            def listaTitulos = params.listaTitulos
            def listaCampos = params.listaCampos
            def lista = buscadorService.buscar(session.dominio, params.tabla, "excluyente", params, true, params.extras)
            def funciones = session.funciones
            session.dominio = null
            session.funciones = null
            lista.pop()

            def baos = new ByteArrayOutputStream()
            def name = "reporte_de_" + params.titulo.replaceAll(" ", "_") + "_" + new Date().format("ddMMyyyy_hhmm") + ".pdf";
//            println "name "+name
            Font catFont = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
            Font info = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL)
            Document document
            if (params.landscape)
                document = new Document(PageSize.A4.rotate());
            else
                document = new Document();

            def pdfw = PdfWriter.getInstance(document, baos);

            document.open();
            document.addTitle("Reporte de " + params.titulo + " " + new Date().format("dd_MM_yyyy"));
            document.addSubject("Generado por el sistema Elyon");
            document.addKeywords("reporte, elyon," + params.titulo);
            document.addAuthor("Elyon");
            document.addCreator("Tedein SA");
            Paragraph preface = new Paragraph();
            addEmptyLine(preface, 1);
            preface.add(new Paragraph("Reporte de " + params.titulo, catFont));
            preface.add(new Paragraph("Generado por el usuario: " + session.usuario + "   el: " + new Date().format("dd/MM/yyyy hh:mm"), info))
            addEmptyLine(preface, 1);
            document.add(preface);
//        Start a new page
//        document.newPage();
            //System.getProperty("user.name")
            addContent(document, catFont, listaCampos.size(), listaTitulos, params.anchos, listaCampos, funciones, lista);            // Los tamaños son porcentajes!!!!
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


    private static void addContent(Document document, catFont, columnas, headers, anchos, campos, funciones, datos) throws DocumentException {
        Font small = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
        def parrafo = new Paragraph("")
        createTable(parrafo, columnas, headers, anchos, campos, funciones, datos);
        document.add(parrafo);


    }


    private static void createTable(Paragraph subCatPart, columnas, headers, anchos, campos, funciones, datos) throws BadElementException {
        PdfPTable table = new PdfPTable(columnas);
        table.setWidthPercentage(100);
        table.setWidths(arregloEnteros(anchos))
        Font small = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
        headers.eachWithIndex { h, i ->
            PdfPCell c1 = new PdfPCell(new Phrase(h, small));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);
        def tagLib = new BuscadorTagLib()
        datos.each { d ->
            campos.eachWithIndex { c, j ->
                def campo
                if (funciones) {
                    if (funciones[j])
                        campo = tagLib.operacion([propiedad: c, funcion: funciones[j], registro: d]).toString()
                    else
                        campo = d.properties[c].toString()
                } else {
                    campo = d.properties[c].toString()
                }

                table.addCell(new Phrase(campo, small));

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


    static arregloEnteros(array) {
        int[] ia = new int[array.size()]
        array.eachWithIndex { it, i ->
            ia[i] = it.toInteger()
        }

        return ia
    }


}
