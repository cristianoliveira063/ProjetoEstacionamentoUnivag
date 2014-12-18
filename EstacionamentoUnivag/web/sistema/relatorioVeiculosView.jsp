
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="pt">
    <head>
        <meta charset="utf-8">
        <title>Relatório de veículos</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
        <!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
        <!--script src="js/less-1.3.3.min.js"></script-->
        <!--append â€˜#!watchâ€™ to the browser URL, then refresh the page. -->

        <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="js/html5shiv.js"></script>
        <![endif]-->




        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>

    </head>

    <body>
        <jsp:include page="../menu.jsp"></jsp:include>

            <div class="container" >

                <div class="page-header">

                    <h3 style="text-align:center;font-weight: bold">Saldo de veículos</h3>
                </div>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-striped">
                            <thead>
                                <tr>DATA:  <fmt:formatDate value="${list.dataAtual.time}"
                                            type="both" 
                                            dateStyle="short" timeStyle="short" />
                            </tr>

                            <tr>
                                <th>
                                    #
                                </th>
                                <th>
                                    DATA MOVIMENTO
                                </th>
                                <th>
                                    SALDO ENTRADA
                                </th>
                                <th>
                                    SALDO SAÍDA
                                </th>
                                <th>
                                    SALDO ATUAL
                                </th>

                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>

                                </td>
                                <td>
                                    <fmt:formatDate pattern="dd/MM/yyyy" 
                                                    value="${list.dataMovimento.time}" />
                                </td>
                                <td>

                                    ${list.saldoEntrada}

                                </td>
                                <td>

                                    ${list.saldoSaida}

                                </td>
                                <td>

                                    ${list.ativo}

                                </td>


                            </tr>




                        </tbody>
                    </table>
                </div>
            </div>
        </div>


    </body>
    <jsp:include page="../rodape.jsp"></jsp:include>a
</html>
