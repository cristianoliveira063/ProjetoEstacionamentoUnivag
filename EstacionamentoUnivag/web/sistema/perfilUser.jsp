<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Visualizar perfil</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrapValidator/vendor/bootstrap/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrapValidator/dist/css/bootstrapValidator.css"/>

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
           
            <div class="col-lg-10 col-lg-offset-2">
                <div class="page-header">
                   <h3 style="margin-left: 300px;font-weight: bold">Visualizar Perfil</h3>
                </div>
                  <c:if test="${msg!=null}"> 
                    <!-- mensagens do sistema: -->
                    <div class="alert alert-${msg.tipoMsg} alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4 style="text-align: center">
                             ${msg.msg}.

                    </div>

                </c:if> 

                <form id="contactForm" method="post" class="form-horizontal" action="${pageContext.request.contextPath}/Controller"
                      data-bv-message="This value is not valid"
                      data-bv-feedbackicons-valid="glyphicon glyphicon-ok"
                      data-bv-feedbackicons-invalid="glyphicon glyphicon-remove"
                      data-bv-feedbackicons-validating="glyphicon glyphicon-refresh">
                    <div class="form-group">
                        <label class="col-lg-3 control-label">Nome <sup>*</sup></label>
                        <div class="col-lg-4">
                            <input type="text"  value="${usuario.nome}" class="form-control" name="firstName" placeholder="First name" required data-bv-notempty-message="Campo obrigatótio" />
                        </div>
                        <div class="col-lg-4">
                            <input type="text" value="${usuario.sobrenome}" class="form-control" name="lastName" placeholder="Last name" required data-bv-notempty-message="Campo obrigatório" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">Email <sup>*</sup></label>
                        <div class="col-lg-4">
                            <input type="text" readonly="true" value="${usuario.email}" class="form-control" name="email"
                                   required data-bv-notempty-message="The company name is required" />
                        </div>
                    </div>
                          <div class="form-group">
                        <label class="col-lg-3 control-label">Perfil<sup>*</sup></label>
                        <div class="col-lg-4">
                            <input type="text" readonly="true" value="${usuario.perfil == 1 ? "ADMINISTRADOR" : "USUARIO"}" class="form-control" name="Mperfil"
                                   required data-bv-notempty-message="The company name is required" />
                            <input type="hidden" name="perfil" value="${usuario.perfil}"/>
                        </div>
                    </div>
					
					
					
                    <div id="jobInfo" style="display: none;">
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><sup>*</sup></label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="job"
                                       required data-bv-notempty-message="The job title is required" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Department <sup>*</sup></label>
                            <div class="col-lg-5">
                                <input type="text" class="form-control" name="department"
                                       required data-bv-notempty-message="The department name is required" />
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label">CPF<sup>*</sup></label>
                        <div class="col-lg-4">
                            <input type="text" readonly="true" class="form-control" name="cpf" value="${usuario.cpf}"
                                   required data-bv-notempty-message="The mobile phone number is required"
                                   data-bv-digits data-bv-digits-message="The mobile phone number is not valid" />
                        </div>
                        <div class="col-lg-2">
                            <button type="button" class="btn btn-link" data-toggle="#Info">Alterar senha</button>
                        </div>
                    </div>

                    <div id="Info" style="display: none;">
                        <div class="form-group">
                            <label class="col-lg-3 control-label">Nova senha</label>
                            <div class="col-lg-3">
                                 <input type="password" class="form-control" name="password"
                                   required data-bv-notempty-message="Informe a nova senha de login"
                                   data-bv-identical="true" data-bv-identical-field="confirmPassword" data-bv-identical-message="Senha login"
                                   data-bv-different="true" data-bv-different-field="username" data-bv-different-message="senha login"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label">Repita a nova senha</label>
                            <div class="col-lg-3">
                               <input type="password" class="form-control" name="confirmPassword"
                                   required data-bv-notempty-message="Confirmar a nova senha escolhida"
                                   data-bv-identical="true" data-bv-identical-field="password" data-bv-identical-message="Senha deve ser idêntica a informada acima"
                                   data-bv-different="true" data-bv-different-field="username" data-bv-different-message="Senha informada inválida"/>
                            </div>
                        </div>
                    </div>
                    
                   
	     <div class="form-group">
                        <label class="col-lg-3 control-label">Senha atual <sup>*</sup></label>
                        <div class="col-lg-4">
                            <input type="hidden" value="perfilusuario" name="acao"/>
                             <input type="hidden" value="alterarPerfilUser" name="logica"/>
                            <input type="password" class="form-control" name="senhaAtual"
                                   required data-bv-notempty-message="Informar senha usada para fazer login" />
                        </div>
                    </div>
					
                    <div class="form-group">
                        <div class="col-lg-4 col-lg-offset-3">
                              <button class="btn btn-lg btn-primary btn-block" type="submit">Salvar</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- :form -->
    </div>
</div>

<script type="text/javascript">
$(document).ready(function() {
    $('button[data-toggle]').on('click', function() {
        var $target = $($(this).attr('data-toggle'));
        $target.toggle();
        if (!$target.is(':visible')) {
            // Enable the submit buttons in case additional fields are not valid
            $('#contactForm').data('bootstrapValidator').disableSubmitButtons(false);
        }
    });

    $('#contactForm').bootstrapValidator();
});
</script>
  <jsp:include page="../rodape.jsp"></jsp:include>
</body>
</html>