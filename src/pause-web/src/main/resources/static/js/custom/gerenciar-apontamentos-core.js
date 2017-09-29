var indicadorMesFechado = false;

$(document).ready(function() {
	calcularTotal();
});

function dialogApontamentoHora(td, idApontamento) {
	var infoDia = $(td).parent().find('input[id^="infoDia"]');
	var id = $(td).attr('id');
	

	verificarMesFechado(infoDia.val());
	
	clearForm(0);
	$( ".help-block" ).remove();
	
	if (typeof(idApontamento) != "undefined"){
		modalEditarApontamento(idApontamento);
	}
	
	$('#btn-cancelar-apontamento').text('Cancelar');
	$('#btn-cancelar-apontamento').attr('onclick',"cancelarApontamento()");
	$('#title-modal-apontamento').text(infoDia.val());
	$('#apontamento-id').val(id)
	$('#demo-default-modal').modal();
}

function cancelarApontamento() {
	$('#demo-default-modal').modal('hide');
}

function verificarMesFechado(dataApontamento){
	$.ajax({
		url: 'gerenciar-apontamento/verificar-mes-fechado',
		type : 'GET',
		contentType : 'application/json',
		data: {dataApontamento : dataApontamento},
		cache: false,
		async: false,
		success: function(data){
			
			indicadorMesFechado =  data;
		},
		error: function(erro){
			
		}
	});
	
}

function informarHorario() {
	var id = $('#apontamento-id').val();
	var horario = $('#apontamento-time').val();
	var dtApontamento = $('#title-modal-apontamento').text().split(',');
	
	apontar(horario,dtApontamento[0],id);
	
	var tr = $("#"+id).parent();
	var horarios = [];
	$("#"+id).text(horario);
	
	$(tr).find('td[id^="apontamento"], span[id^="apontamento"]').each (function() {
		if ($(this).html() != '--:--') {
			
			var horarioSetado = $(this).html();
			var pad = "00:00";
			var ans = pad.substring(0, pad.length - horarioSetado.length) + horarioSetado;
			horarios.push(ans);
		}
	});
	
	horarios.sort();
	
	$(tr).find('td[id^="apontamento"], span[id^="apontamento"]').each (function(index) {
		if (typeof horarios[index] == 'undefined') {
			$(this).html('--:--');
			$(this).attr("class", "");
			$(this).attr("style", "cursor:pointer;");
			$(this).attr("onclick", "dialogApontamentoHora(this);");
		}
		else {
			var horarioAlterado = horarios[index];
			
			$(this).html(horarioAlterado);
			if (horarioAlterado.indexOf('E') > -1) {
				$(this).attr("class", "text-muted demo-pli-clock");
				$(this).attr("style", "");
				$(this).attr("onclick", "");
			}
			else {
				$(this).attr("class", "");
				$(this).attr("style", "cursor:pointer;");
			}                
		}
	});
	calcularTotal();
	$('#demo-default-modal').modal("hide");
}
function apontar(horario, data, idTd){
	var apontamento = {
		id : $('#idApontamento').val(),
		horarioJson : horario,
		dataJson : data,
		observacao : $('#apontamento-obs').val(),
		idFuncionario : $('#apontamento-funcionario').val(),
		tpJustificativa : {
			id : $('#apontamento-jus').val()
		}
	};
	
	$.ajax({
		url: 'gerenciar-apontamento/apontar',
		type : 'POST',
		contentType : 'application/json',
		data: JSON.stringify(apontamento),
		cache: false,
		success: function(data){
			$("#"+idTd).attr('onclick',"dialogApontamentoHora(this,"+ data.id +")");
		},
		error: function(erro){
			$('#erro-label').text(erro.responseText);
			$('#erro-sm-modal').modal();
			$("#"+idTd).html("--:--");
		}
	});
}
function calcularTotal() {
	
	$("#dt-apontamentos").find('tr').each (function() {
		var horaTotal = 0;
		var entrada = 0;
		var saida = 0;
		
		$(this).find('td[id^="apontamento"], span[id^="apontamento"]').each (function() {
			if ($(this).html() != '--:--') {
				var apontamento = $(this).html().split(':');
				if (entrada==0) {
					entrada = (parseInt(apontamento[0])*60)+parseInt(apontamento[1].replace('E', ''));	 
				}
				else {
					saida = (parseInt(apontamento[0])*60)+parseInt(apontamento[1].replace('E', ''));
					horaTotal += (saida - entrada)/60;
					entrada=0;
					saida=0;
				}
			}
		});
		
		$(this).find('td[id^="total-hora"]').text(Math.round(horaTotal*100)/100);
		horaTotal = 0;
	});	
}
function modalEditarApontamento(id){
	$.ajax({
		url: 'gerenciar-apontamento/obter',
		type : 'GET',
		contentType : 'application/json',
		data: {'id' :id},
		cache: true,
		success: function(data){
			$('#btn-cancelar-apontamento').text('Remover');
			$('#btn-cancelar-apontamento').attr('onclick',"confirmarRemover()");
			$('#btn-remover-apontamento').attr('onclick',"removerApontamento("+ data.id +")");
			$('#idApontamento').val(data.id);
			$('#apontamento-obs').val(data.observacao);
			$('#apontamento-time').timepicker('setTime', data.horario.substring(0,5));
			$('#apontamento-jus').prop('selectedIndex',data.tpJustificativa.id);
			$('#apontamento-jus').selectpicker('refresh');
		}
	});
}
function confirmarRemover(){
	$('#remover-sm-modal').modal('show');
}
function removerApontamento(id){
	$('#remover-sm-modal').modal('hide');
	$.ajax({
		url: 'gerenciar-apontamento/remover',
		type : 'GET',
		contentType : 'application/json',
		data: {'id' : id},
		cache: true,
		success: function(data){
			tdRemove = $('#apontamento-id').val();
			$("#idApontamento").val("");
			$("#"+tdRemove).attr('onclick',"dialogApontamentoHora(this)");
			$("#"+tdRemove).text('--:--')
		},
		error: function(erro){
			$('#remover-sm-modal').modal('hide');
			$('#erro-label').text(erro.responseText);
			$('#erro-sm-modal').modal();
		}
	});
}