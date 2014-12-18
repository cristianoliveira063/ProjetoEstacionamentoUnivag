<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <title>Sair do sistema</title>


        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/jasny-bootstrap.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrapValidator/vendor/jquery/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jasny-bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/scripts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>

    </head>
    <body >
        <jsp:include page="../menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="page-header">
                        <h3 style="text-align: center;font-weight: bold">Confirmar sa�da do sistema</h3>

                    </div>

                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="alert alert-info" style="text-align: center">
                            <h4 style="text-align: center">Ol� <strong style="font-style: italic"><c:out value="${usuarioLogado.nomeUsuario}"/></strong>,deseja sair do sistema?</h4> 
                        <a href="Controller?acao=sairSistema&logica=sair" class="btn btn-danger " ><span class="glyphicon glyphicon-ban-circle"></span>Sim</a>  
                        <a href="Controller?acao=veiculo" class="btn btn-primary "><span class="glyphicon glyphicon-exclamation-sign"></span>N�o</a>
                    </div>        

                </div>

            </div>

            <jsp:include page="../rodape.jsp"></jsp:include>
    </body>
</html>