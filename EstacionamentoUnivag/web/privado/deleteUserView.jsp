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

        <title>Excluir Usuario</title>


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
                        <h3 style="text-align: center;;font-weight: bold">Confirmar Exclusão do usuário</h3>

                    </div>

                    <div class="col-lg-8 col-lg-offset-2">
                        <div class="alert alert-danger" style="text-align: center">
                            <h4 style="text-align: center">Deseja excluir <strong style="font-style: italic"><c:out value="${usuario.nome}"/></strong>   do sistema.</h4> 
                        <a href="Controller?acao=deletarUsr&logica=excluirUsr&codigo=${usuario.codigo}" class="btn btn-danger " ><span class="glyphicon glyphicon-ban-circle"></span> Excluir</a>  
                        <a href="Controller?acao=listagemUsr" class="btn btn-warning "><span class="glyphicon glyphicon-exclamation-sign"></span> Cancelar</a>
                    </div>        

                </div>

            </div>

            <jsp:include page="../rodape.jsp"></jsp:include>
    </body>
</html>