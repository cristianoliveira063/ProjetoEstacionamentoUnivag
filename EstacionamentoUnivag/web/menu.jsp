<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
        <!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
        <!--script src="js/less-1.3.3.min.js"></script-->
        <!--append â€˜#!watchâ€™ to the browser URL, then refresh the page. -->
        <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">



        <!-- 
          <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
           <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/scripts.js"></script>
         <script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
      
        
        
        -->



        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="js/html5shiv.js"></script>
        <![endif]-->


        <!-- <script type="text/javascript" src="bootstrap/js/jquery.min.js"></script>
        -->
    </head>

    <body style="background-color:#EBF5FF">
        <div class="container">

            <div class="row clearfix" style="margin-top: 50px">
                <div class="col-md-12 column">
                    <div class="carousel slide" id="carousel-105034">
                        <ol class="carousel-indicators">
                            <li class="active" data-slide-to="0" data-target="#carousel-105034">
                            </li>
                            <li data-slide-to="1" data-target="#carousel-105034">
                            </li>
                            <li data-slide-to="2" data-target="#carousel-105034">
                            </li>
                        </ol>
                        <div class="carousel-inner">
                            <div class="item active">
                                <img alt="" src="${pageContext.request.contextPath}/imagens/img01.jpg">
                                <div class="carousel-caption">
                                    <h4>

                                    </h4>
                                    <p>

                                    </p>
                                </div>
                            </div>
                            <div class="item">
                                <img alt="" src="${pageContext.request.contextPath}/imagens/img02.jpg">
                                <div class="carousel-caption">
                                    <h4>

                                    </h4>
                                    <p>

                                    </p>
                                </div>
                            </div>
                            <div class="item">
                                <img alt="" src="${pageContext.request.contextPath}/imagens/img02.jpg">
                                <div class="carousel-caption">
                                    <h4>
                                    </h4>
                                    <p>

                                    </p>
                                </div>
                            </div>
                        </div> <a class="left carousel-control" href="#carousel-105034" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#carousel-105034" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
                    </div>
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#">Principal</a>
                        </li>
                        <li class="disabled">
                            <a href="#">Você está na <c:out value="${usuarioLogado.nomePortaria}"/></a>
                        </li>
                          <li>
                            <a href="Controller?acao=registroveiculo">Consultar Veículo</a>
                        </li>
                           <li>
                            <a href="Controller?acao=relatoriodata">Listagem Veículo Por Data</a>
                        </li>
                          <li >
                            <a href="Controller?acao=perfilusuario&logica=visualizarPerfil">Perfil de Usuário</a>
                        </li>

                        <li class="dropdown pull-right">
                            <a href="#" data-toggle="dropdown" class="dropdown-toggle">Administrador<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="Controller?acao=usuario">Cadastrar usuário</a>
                                </li>
                                <li>
                                    <a href="Controller?acao=listagemUsr">Listagem de usuários</a>
                                </li>
                                <li>
                                    <a href="Controller?acao=pesquisarUsr">Pesquisar usuário</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Administrador</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="#" style="font-style: italic">Olá <c:out value="${usuarioLogado.nomeUsuario}"/></a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="active">
                                    <a href="Controller?acao=veiculo">| Registrar Entrada/Saída Veículos |</a>
                                </li>
                                <li>
                                    <a href="Controller?acao=listagemMov">| Listagem Veículos Ativos |</a>
                                </li>
                                 <li>
                                    <a href="Controller?acao=relatorio">| Total Entrada/Saida Veículos |</a>
                                </li>

                            </ul>

                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                <li >
                                    <a href="Controller?acao=sairSistema&logica=confirmarSaida" >| Sair do sistema | </a>
                                </li>
                                <li>

                                </li>
                                </li>
                                <li class="dropdown">
                                <li >
                                    <a href="" ></a>
                                </li>
                                

                            </ul>
                            </li>
                            </ul>
                        </div>

                    </nav>
                </div>
            </div>
        </div>
    </body>
</html>
