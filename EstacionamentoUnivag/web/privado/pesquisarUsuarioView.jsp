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

        <title>Pesquisa</title>


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
                        <h3 style="text-align: center;;font-weight: bold">Pesquisa de Usuário</h3>
                    </div>
                    <h5 style="text-align: center">*Pesquisa pode ser feita com CPF ou EMAIL
                   </h5>
                <c:if test="${msg!=null}"> 
                    <!-- mensagens do sistema: -->
                    <div class="alert alert-${msg.tipoMsg} alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4 style="text-align: center">
                            Mensagem! ${msg.msg}.

                    </div>

                </c:if> 

                <div class="col-lg-9 col-lg-offset-2">
                    <form id="defaultForm" method="post" class="form-horizontal" style="margin-top:50px" >
                        <div class="form-group">
                            <label class="col-lg-3 control-label"> Pesquisa:</label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="pesquisar"  required="required" autocomplete="off" />
                                <input type="hidden" name="acao" value="pesquisarUsr"/>
                                <input type="hidden" name="logica" value="parametroPesquisa"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-lg-5 col-lg-offset-3">


                                <button class="btn btn-lg btn-primary btn-block" type="submit">Pesquisar</button>

                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <jsp:include page="../rodape.jsp"></jsp:include>
    </body>
</html>