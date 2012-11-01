<table width="100%" id="tablaBuscador"  class="table table-bordered table-striped table-condensed table-hover" style="font-size: 10px !important;">
    <thead>
    <tr>
        <th>Cédula</th>
        <th>Nombre</th>
        <th>Ciudad</th>
        <th>Estado de gestión</th>
    </tr>
    </thead>
    <tbody id="paginate">
    <g:each in="${lote}" var="l">
        <tr>
            <td>${l.cedula}</td>
            <td>${l.nombre}</td>
            <td>${l.ciudad}</td>
            <td>${l.estadoGestion}</td>
        </tr>
    </g:each>
    </tbody>
</table>
<script type="text/javascript">

    function paginar(id,mostrar){

        var tbody=$("#"+id)
        var num = mostrar
        var rows = tbody.find("tr")
        var cant = rows.size()
        var paginas = Math.ceil(cant/num)
        var i=0
        var fila
        var padre
        var show = function (){
            var pag=$(this).html()
            var body =$("#"+$(this).attr("body"))
            body.find("tr").addClass("hiden")
            body.find("."+pag).removeClass("hiden").show()
            $(".paginateButon").css("background","none")
            $(".b"+pag).css("background","#B2D1FF")
            $(".hiden").hide()

        }

        rows.each(function(i){
            if(i>=num){
                $(this).hide().addClass("hiden "+(Math.ceil((i+1)/num)))
            }else{
                $(this).addClass("1")
            }

        });

        padre=tbody.parent().parent()
        fila=$("<div>")
        fila.width("100%")
        fila.height(20)
        fila.css("padding-left",5).css("padding-rigth",5).css("padding-top",2)

        for(i=0;i<paginas;i++){
            var boton = $("<div>")
            boton.css({
                cursor:"pointer",
                width:15,
                height:20,
                float:"left",
                marginLeft:5,
                border:"1px solid black",
                lineHeight:"20px",
                paddingLeft:"7px"

            }).html(i+1).bind("click",show).attr("body",id).addClass("b"+(i+1)+" paginateButon");
            if(i==0)
                boton.css("background","#B2D1FF")
            fila.append(boton)
        }

        padre.append(fila)


    }



    paginar("paginate",10)

</script>