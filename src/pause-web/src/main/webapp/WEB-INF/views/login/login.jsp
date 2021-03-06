<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Pause</title>

	<!--STYLESHEET-->
    <!--=================================================-->
    <!--Open Sans Font [ OPTIONAL ]-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700' rel='stylesheet' type='text/css'>

    <!--Bootstrap Stylesheet [ REQUIRED ]-->
    <link href='<c:url value="/css/bootstrap.min.css"/>' rel="stylesheet">

    <!--Nifty Stylesheet [ REQUIRED ]-->
    <link href='<c:url value="/css/nifty.min.css"/>' rel="stylesheet">
    
    <link href='<c:url value="/plugins/ionicons/css/ionicons.min.css"/>' rel="stylesheet">
    
    <!--Bootstrap Validator [ OPTIONAL ]-->
    <link href='<c:url value="/plugins/bootstrap-validator/bootstrapValidator.min.css"/>' rel="stylesheet">
    
    <!--jQuery [ REQUIRED ]-->
    <script src='<c:url value="/js/jquery.min.js"/>'></script>
    
    <!--Bootstrap Validator [ OPTIONAL ]-->
    <script src='<c:url value="/plugins/bootstrap-validator/bootstrapValidator.min.js"/>'></script>
    <script src='<c:url value="/js/custom/login.js"/>'></script>

</head>
<body>
	<div class="cls-container">
		<!-- LOGIN FORM -->
		<!--===================================================-->
		<div class="cls-content">
		    <div class="cls-content-sm panel">
	            <div class="pad-btm">
	            	<img src='<c:url value="/img/logo/LOGO.png"/>'alt="" class="">
	            </div>
	            <form action='<c:url value="/login"/>' method="post" id="bv-form">
					<div class="panel">
						<div class="">
							<h4 class="panel-title">Acessar</h4>
						</div>
						<div class="panel-body">
							<c:if test="${param.error != null}">
                                <div class="alert alert-danger">
                                    <p>Usuário ou senha inválidos. Por favor, digite novamente.</p>
                                </div>
                           	</c:if>
                           	<c:if test="${param.logout != null}">
                                <div class="alert alert-success">
                                    <p>Você foi desconectado com sucesso.</p>
                                </div>
                           	</c:if>
							<div class="form-group has-feedback">
								<i class="ion-person icon-lg form-control-feedback"></i>
				               	<input type="text" id="#" class="form-control" name="user" placeholder="Usuário" autofocus>
				            </div>
				            <div class="form-group has-feedback">
								<i class="ion-locked icon-lg form-control-feedback"></i>
				               	<input type="password" id="#" class="form-control" name="senha" placeholder="Senha 	" autofocus>
				            </div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<div class="pad-top">
                               	<input type="submit"
                                   	class="btn btn-block btn-info btn-rounded" value="Acessar">
                           	</div>
						</div>
					</div>
					
	            </form>
		    </div>

		<!--===================================================-->
	</div>
	<!--===================================================-->
	<!-- END OF CONTAINER -->        
</body>
</html>