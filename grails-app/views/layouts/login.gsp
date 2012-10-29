<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>
            <g:layoutTitle default="Elyon"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <script src="${resource(dir: 'js/jquery/js', file: 'jquery-1.8.2.js')}"></script>
        <script src="${resource(dir: 'js/jquery/js', file: 'jquery-ui-1.9.1.custom.min.js')}"></script>

        <!-- Le styles -->
        <link href="${resource(dir: 'css/bootstrap/css', file: 'bootstrap.css')}" rel="stylesheet">

        <link href="${resource(dir: 'css/bootstrap/css', file: 'bootstrap-responsive.css')}" rel="stylesheet">

        <link href="${resource(dir: 'js/jquery/css/twitBoot', file: 'jquery-ui-1.9.1.custom.min.css')}" rel="stylesheet">

        <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

        <!-- Le fav and touch icons -->
        %{--<link rel="shortcut icon" href="${resource(dir: 'images/ico', file: 'janus_16.png')}">--}%
        <g:layoutHead/>
    </head>

    <body>

        <div class="container" style="margin-top: 40px;">

            <g:layoutBody/>

        </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
        <script src="${resource(dir: 'css/bootstrap/js', file: 'bootstrap.js')}"></script>
    </body>
</html>
