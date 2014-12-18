<!DOCTYPE html>
<html>
    <head>
        <title>Data relatório</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrapValidator/dist/css/bootstrapValidator.css"/>

        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrapValidator/vendor/jquery/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrapValidator/dist/js/bootstrapValidator.js"></script>

        <!-- Support datetime picker plugin: http://eonasdan.github.io/bootstrap-datetimepicker/ -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/datepicker/bootstrap-datetimepicker.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/datepicker/moment.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/datepicker/bootstrap-datetimepicker.js"></script>


    </head>
    <body>
        <jsp:include page="../menu.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <!-- form: -->

                    <div class="col-lg-10 col-lg-offset-2">
                        <div class="page-header">
                            <h3 style="margin-left: 280px;font-weight: bold">Selecione uma data</h3>
                        </div>



                        <form id="defaultForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/Controller">


                            <div class="row" >
                                <div class='col-sm-4 col-lg-offset-3'>
                                    <div class="form-group">
                                        <div class='input-group date' id='datetimepicker5'>
                                            <input type='text' class="form-control input-lg" name="data" required="required" style="padding-left: 100px" data-date-format="DD/MM/YYYY"/>
                                            <span class="input-group-addon">
                                                <span class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-9 col-lg-offset-1">
                                            <input type="hidden" value="relatorio" name="acao"   />
                                             <input type="hidden" value="listRelatorio" name="logica"   />

                                            <button class="btn btn-lg btn-primary btn-block" type="submit">Pesquisar</button>

                                        </div>
                                    </div>

                                    <script type="text/javascript">
                                        $(function () {
                                            $('#datetimepicker5').datetimepicker({
                                                pickTime: false
                                            });
                                        });
                                    </script>
                                </div>
                            </div>
                        </form>
                    </div>

                    <!-- :form -->
                </div>
            </div>

        <jsp:include page="../rodape.jsp"></jsp:include>
    </body>
</html>