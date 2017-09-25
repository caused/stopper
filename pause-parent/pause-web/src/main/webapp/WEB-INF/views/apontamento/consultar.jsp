<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<layout:extends name="../shared/base.jsp">
	<layout:put block="css">
		<link href='<c:url value="/plugins/datatables/media/css/dataTables.bootstrap.css"/>' rel="stylesheet">
	</layout:put>
	<layout:put block="contents">
		<!--Page Title-->
		<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
		<div id="page-title">
			<h1 class="page-header text-overflow">Consultar apontamentos</h1>
		</div>
		<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
		<!--End page title-->
		<!--Breadcrumb-->
		<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
		<ol class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li class="active">Consultar apontamentos</li>
		</ol>
		<!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
		<!--End breadcrumb-->

		<!--Page content-->
		<!--===================================================-->
		<div id="page-content">
			<div class="panel">

				<!--Data Table-->
				<!--===================================================-->
				<div class="panel-body">
					<div class="row">
						<form id="bv-form" action="/pause/consultar-apontamento/filtrar-consulta" method="POST">
							<input type="hidden" id="token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<div class="col-sm-3">
								<div class="form-group">
									<label class="control-label">Nome do funcionário</label>
									<select class="selectpicker" data-live-search="true" data-width="100%" id="select-funcionario" name="idFuncionario">
										<option value="">Selecione</option>
										<c:forEach items="${funcionariosBusca}" var="funcionario">
											<option value="${funcionario.id }">${funcionario.nome }</option>
										</c:forEach>
									</select>
									<input type="hidden" id="funcSel" name="idFuncionario" val="${idFuncBusca }"/>
								</div>
							</div>
							<div class="col-sm-5 pad-btm">
								<label class="control-label">Período</label>
								<div class="input-daterange input-group" id="datepicker">
									<input type="date" class="form-control periodo" name="periodo" id="periodoDe" value="${de }" min="2010-03-01" max=""/>
									<span class="input-group-addon">até</span>
									<input type="date" class="form-control periodo" name="periodo" id="periodoAte" value="${ate }" min="2010-03-01" max=""/>
								</div>
							</div>
							<div class="col-sm-1" style="margin-top: 23px; ">
								<button class="btn btn-info" type="submit" id="filtrar-bt">Filtrar</button>
							</div>
						</form>
						<div class="col-sm-2 pull-right" style="margin-top: 23px;">
							<button type="button" class="btn btn-success pull-right" onclick="gerarRelatorio()">
								<i class="fa fa-file-excel-o"></i> Gerar relatório
							</button>
							<a href="${url }" target="_blank" id="download" class="hide"></a>
						</div>
					</div>
					<div class="table-respon">
						<table id="dt-basic" class="table table-striped table-bordered dt-pag">
							<thead>
								<tr>
									<th class="text-center">Funcionário</th>
									<th class="text-center">Total de horas</th>
									<th class="text-center">Banco de horas</th>
									<th class="text-center">Adicional noturno</th>
									<th class="text-center">Horas SA</th>
									<th class="text-center">Horas ST</th>
								</tr>
							</thead>
							<tbody class="text-center">
								<c:forEach items="${funcionarios }" var="funcionario">
									<tr>
										<td>${funcionario.nmFuncionario }</td>
										<td>${funcionario.controleDiario.horaTotal }</td>
										<td>${funcionario.controleDiario.bancoHora }</td>
										<td>${funcionario.controleDiario.adicNoturno }</td>
										<td>${funcionario.controleDiario.sobreAviso }</td>
										<td>${funcionario.controleDiario.sobreAviso }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!--===================================================-->
				<!--End Data Table-->
			</div>
		</div>
		<!--===================================================-->
		<!--End page content-->
		
	</layout:put>
	<layout:put block="scripts">
		<script src='<c:url value="/plugins/datatables/media/js/jquery.dataTables.js"/>'></script>
		<script src='<c:url value="/plugins/datatables/media/js/dataTables.bootstrap.js"/>'></script>
		<script src='<c:url value="/plugins/bootstrap-validator/bootstrapValidator.min.js"/>'></script>
		<script src='<c:url value="/js/custom/bootstrap-validator-data-periodo.js"/>'></script>
		<script src='<c:url value='/js/custom/datatable-custom.js'/>'></script>
		<script src='<c:url value="/js/custom/send-ajax.js"/>'></script>
		<script>
		var url = window.document.URL;
		$(document).ready(function() {
			/* debugger;
		     $('#filtrar-bt').on('click', function(){
		    	if(url.includes("filtrar")){
		    		$("#bv-form").submit();
		    	}else if($('#periodoAte').val() == "" && $('#periodoDe').val() == ""){
					table
					.columns(0).search($('#select-funcionario option:selected').text())
					.draw();
					$("#funcSel").val($("#select-funcionario").val());
		    	}else{
		    		$("#bv-form").submit();
		    	}
		    }); */
		    
/* 	    	var date = new Date();
	    	date = date.toLocaleDateString();
	    	var dia = date.substring(0, 2);
	    	var mes = date.substring(3, 5);
	    	var ano = date.substring(6, 10);
	    	
	    	 $("#de").attr("max", ano+"-"+mes+"-"+dia);
	    	 $("#ate").attr("max", ano+"-"+mes+"-"+dia);
	    	 $("#ate").attr("min", ano+"-"+mes+"-"+dia);  */
		 
    	 
    	 	$('.periodo').prop('max',function(){
		        return new Date().toJSON().split('T')[0];
		    });
			
			$('#bv-form').bootstrapValidator({
				excluded : [ ':disabled' ],
				fields : {
					periodo : {
						validators : {
							range : {
								message : 'A segunda data deve ser superior ou igual à primeira.'
							},
							date: {
								min: $("#periodoAte").prop('min'),
								max: $("#periodoAte").prop('max'),
								message:'Data inválida.'
							}
						}
					}
				}
			});
		});
		
		function gerarRelatorio(){
			var dataDe;
			var dataAte;
			var idFuncionario = $("#select-funcionario").val();
			if(url.includes("filtrar")){
				var deAno = $("#periodoDe").val().substring(0, 4);
				var deMes = $("#periodoDe").val().substring(5, 7);
				var deDia = $("#periodoDe").val().substring(8, 10);
				
				dataDe = new Date(deAno, deMes-1, deDia);
				alert(dataDe);
				
				var ateAno = $("#periodoAte").val().substring(0, 4);
				var ateMes = $("#periodoAte").val().substring(5, 7);
				var ateDia = $("#periodoAte").val().substring(8, 10);
				
				dataAte = new Date(ateAno, ateMes-1, ateDia);
				alert(dataAte);
	    	}else{
	    		dataAte = new Date();
	    		dataDe = new Date(dataAte.getYear()+1900, dataAte.getMonth(), 01);
	    	}
			
			$.ajax({
				url: "/pause/consultar-apontamento/gerar-relatorio-consulta",
				type: "POST",
				data: {'idFuncionario' : idFuncionario,
						'ate' : dataAte,
						'de' : dataDe},
				success: function(data){
					$("#download").attr("href", "/pause/relatorio/download?caminho="+data);
					window.open($("#download").attr("href"),'_blank');
				}
			});
		}
		</script>
	</layout:put>
</layout:extends>