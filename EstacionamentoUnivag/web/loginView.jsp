
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">


        <title>Login no Sistema</title>

        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}//bootstrap/css/signin.css" rel="stylesheet">

        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="${pageContext.request.contextPath}/assets/js/ie-emulation-modes-warning.js"></script>


        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="${pageContext.request.contextPath}/assets/js/ie10-viewport-bug-workaround.js"></script>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>



        <div class="container">
            <c:if test="${msg!=null}"> 
                <!-- mensagens do sistema: -->
                <div class="alert alert-${msg.tipoMsg} alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    <h4 style="text-align: center">
                         ${msg.msg}.

                </div>

            </c:if> 

            <form class="form-signin"  role="form" method="post" action="${pageContext.request.contextPath}/Controller" >
                <h2 class="form-signin-heading" style="text-align: center">Login no Sistema</h2>
                <input type="email" class="form-control" name="emailLogin" value="" placeholder="Email address" required autofocus>
                <input type="password" class="form-control" name="senha" placeholder="Password" required>
                <input type="hidden" name="acao" value="loginUser" /> 
                <input type="hidden" name="logica" value="verificarUser"  />


                <!-- Select Basic -->


                <div class="col-md-">
                    <select id="selectbasic" name="guarita" class="form-control" style="width: 300px"  >
                        <option  value="">----Selecione uma portaria----</option>
                        <c:forEach var="guarita" items="${list}">

                            <option value="${guarita.codigo}">${guarita.nome}</option>

                        </c:forEach>


                    </select>
                    </br>

                    <p style="text-align: center"> <a href="Controller?acao=loginUser&logica=esqueceuSenha">Esqueci minha senha </a> </p>
                </div>
                </br>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            </form>

        </div> <!-- /container -->


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <jsp:include page="/rodape.jsp"></jsp:include>
    </body>

</html>
