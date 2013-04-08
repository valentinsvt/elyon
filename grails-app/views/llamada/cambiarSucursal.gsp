<g:select from="${sucs}" name="sucursal.id" class="span2"   noSelection="['': '-Seleccione-']" optionKey="id" id="suc"/>
<script type="text/javascript">

    $("#suc").change(function(){
        var valor=$(this).val()

        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'cambiarOficina')}",
            data    : "sucursal=" + valor,
            success : function (msg) {
                $("#div_ofi").html(msg)
            }
        });
    })
</script>