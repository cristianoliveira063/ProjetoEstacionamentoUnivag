<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Lista  Movimento</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
        <!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
        <!--script src="js/less-1.3.3.min.js"></script-->
        <!--append ‘#!watch’ to the browser URL, then refresh the page. -->

        <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">


        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="js/html5shiv.js"></script>
        <![endif]-->

        <!-- Fav and touch icons -->
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="img/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="img/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="img/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-57-precomposed.png">
        <link rel="shortcut icon" href="img/favicon.png">

        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
    </head>

    <body>
        <jsp:include page="../menu.jsp"></jsp:include>

            <div class="container" style="margin-top: 1px">
                <div class="page-header">
                    <h3 style="text-align:center;font-weight: bold">Registro de ve�culo</h3>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                    <c:choose>  
                        <c:when test="${not empty listMovimento}">  

                            <table class="table table-hover table-bordered">
                                <thead>
                                    <tr>
                                        <th>
                                            #
                                        </th>
                                        <th>
                                            DATA
                                        </th>
                                        <th>
                                            VEICULO
                                        </th>
                                        <th>
                                            USUARIO
                                        </th>
                                        <th>
                                            GUARITA
                                        </th>
                                        <th>
                                            STATUS
                                        </th>
                                        <th>
                                            REGISTRO
                                        </th>
                                        <th>
                                            DAR SA�DA
                                        </th>

                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="lista" items="${listMovimento}">  
                                        <tr >
                                            <td>
                                                ${lista.codigo} 
                                            </td>
                                            <td>

                                                <fmt:formatDate value="${lista.dataRegistro.time}"
                                                                type="both" 
                                                                dateStyle="short" timeStyle="short" />


                                            </td>
                                            <td >
                                                ${lista.veiculo.placa}

                                            </td>
                                            <td >
                                                ${lista.usuario.nome}
                                            </td>
                                            <td>

                                                ${lista.guarita.nome}

                                            </td>
                                            <td>
                                                ${lista.status ==1? "ENTRADA" : "SAIDA"}


                                            </td>
                                            <td>

                                                ${lista.status_movimento == 1? "ATIVO" : "INATIVO"}


                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${lista.status_movimento==1}">
                                                        <a href="Controller?acao=saidalist&logica=registrarSaidaList&codigo=${lista.codigo}&placa= ${lista.veiculo.placa}">Sa�da</a>
                                                    </c:when>
                                                    <c:otherwise>

                                                        INATIVO

                                                    </c:otherwise>      

                                                </c:choose>  


                                            </td>

                                        </tr>
                                    </c:forEach> 

                                </tbody>
                            </table>
                        </c:when>  
                        <c:otherwise>  
                            <h5 style="text-align: center;color: red"><c:out value="....Nenhum registro para ser exibido...."/></h5>    
                        </c:otherwise>  
                    </c:choose>    


                </div>
            </div>
        </div>

    </div>














</body>
<jsp:include page="../rodape.jsp"></jsp:include>
</html>
