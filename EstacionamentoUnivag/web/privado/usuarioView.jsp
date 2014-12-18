<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Cadastro de usuários</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">



        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/bootstrapValidator.css"/>

        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrapValidator/vendor/jquery/jquery-1.10.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrapValidator/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrapValidator/dist/js/bootstrapValidator.js"></script>



    </head>
    <body>
        <jsp:include page="../menu.jsp"></jsp:include>

            <div class="container">

                <div class="row">

                    <!-- form: -->
                    <section>
                        <div class="col-lg-8 col-lg-offset-2">
                            <div class="page-header">
                                <h3 style="text-align:center;;font-weight: bold">Cadastro de usuários</h3>
                            </div>

                        <c:if test="${msg!=null}"> 
                            <!-- mensagens do sistema: -->
                            <div class="alert alert-${msg.tipoMsg} alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                <h4 style="text-align: center">
                                     ${msg.msg}.

                            </div>

                        </c:if> 




                        <form id="defaultForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/Controller"
                              data-bv-message="Valor informado inválido"
                              data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
                              data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
                              data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
                            <div class="form-group">
                                <label class="col-lg-4 control-label">Nome</label>
                                <div class="col-lg-4">
                                    <input type="text" class="form-control" name="firstName" placeholder="Primeiro nome" required data-bv-notempty-message="Informe o seu Primeiro nome" />
                                </div>
                                <div class="col-lg-4">
                                    <input type="text" class="form-control" name="lastName" placeholder="Segundo nome" required data-bv-notempty-message="Informe o seu Segundo nome" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-4 control-label">CPF</label>
                                <div class="col-lg-5">
                                    <input type="text" class="form-control" name="cpf"
                                           data-bv-message="CPF inválido"
                                           required data-bv-notempty-message="CPF é obrigatório"
                                           pattern="[0-9]{11}" data-bv-regexp-message="Faltam digitos no CPF informado"
                                           data-bv-stringlength="true" data-bv-stringlength-min="11" data-bv-stringlength-max="11" data-bv-stringlength-message="CPF deve possuir 11 digitos"

                                           />
                                </div>
                            </div>



                            <div class="form-group">
                                <label class="col-lg-4 control-label">Email </label>
                                <div class="col-lg-5">
                                    <input class="form-control" name="email"  type="email" data-bv-emailaddress-message="Informe um email válido" 
                                           required data-bv-notempty-message="Email é obrigatório"
                                          />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-4 control-label">Perfil de acesso</label>
                                <div class="col-lg-5">
                                    <select class="form-control" name="perfil" data-bv-notempty data-bv-notempty-message="Selecione um Perfil de  acesso">
                                        <option value="">-- Selecione um Perfil --</option>

                                        <c:forEach var="lista" items="${perfil}">  
                                            <option value="${lista.valor}"> ${lista}</option>  
                                        </c:forEach> 

                                    </select>
                                </div>
                            </div>

                            <input type="hidden" name="acao" value="usuario" />
                            <input type="hidden" name="logica" value="cadastrar" />

                            <div class="form-group">
                                <div class="col-lg-9 col-lg-offset-4">
                                    <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-user"></span>Salvar</button>
                                    <button type="reset" class="btn btn-primary btn-warning"><span class="glyphicon glyphicon-remove"></span>Limpar</button>



                                </div>
                            </div>
                        </form>
                    </div>
                </section>
                <!-- :form -->
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#defaultForm').bootstrapValidator();
            });
        </script>
        <jsp:include page="../rodape.jsp"></jsp:include>
    </body>
</html>