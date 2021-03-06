var url = window.document.URL;

$(document).ready(function() {
	var dataAtual = new Date();

	$("#periodoDe").attr("max", dataAtual.toISOString().substring(0, 10));
	$("#periodoAte").attr("max", dataAtual.toISOString().substring(0, 10));

});

$(".periodo").change(function() {
	var date = new Date();

	var startDate = document.getElementById("periodoDe").value;
	var endDate = document.getElementById("periodoAte").value;

	if ((Date.parse(endDate) < Date.parse(startDate))) {
		$("#filtrar-bt").attr("disabled", true);
	} else if (Date.parse(endDate) > date.toISOString().substring(0, 10)) {
		$("#filtrar-bt").attr("disabled", true);
	} else if (Date.parse(startDate) > date.toISOString().substring(0, 10)) {
		$("#filtrar-bt").attr("disabled", true);
	} else {
		$("#filtrar-bt").attr("disabled", false);
	}
});

if (!url.includes("filtrar")) {
	var dias = 7; // Quantidade de dias que você quer subtrair.
	var dataAtual = new Date();

	$("#periodoAte").val(dataAtual.toISOString().substring(0, 10));

	var dataAnterior = new Date(dataAtual.getTime()
			- (dias * 24 * 60 * 60 * 1000));

	$("#periodoDe").val(dataAnterior.toISOString().substring(0, 10));
	$("#periodoAte").val(dataAtual.toISOString().substring(0, 10));

	$("#bv-form").submit();
}

function linkGerenciar (idFuncionario) {
	var link = window.location.href;
	
	link = link.substring(0, link.indexOf('pause'));
	
	link += 'pause/gerenciar-apontamento?idFuncionario=' + idFuncionario + '&periodo=&periodo=';
	
	window.open(link);
}

function gerarRelatorio() {
	var dataDe;
	var dataAte;
	var idFuncionario = $("#select-funcionario").val();
	if (url.includes("filtrar")) {
		var deAno = $("#periodoDe").val().substring(0, 4);
		var deMes = $("#periodoDe").val().substring(5, 7);
		var deDia = $("#periodoDe").val().substring(8, 10);

		dataDe = new Date(deAno, deMes - 1, deDia);

		var ateAno = $("#periodoAte").val().substring(0, 4);
		var ateMes = $("#periodoAte").val().substring(5, 7);
		var ateDia = $("#periodoAte").val().substring(8, 10);

		dataAte = new Date(ateAno, ateMes - 1, ateDia);
	} else {
		dataAte = new Date();
		dataDe = new Date(dataAte.getYear() + 1900, dataAte.getMonth(), 01);
	}
	$("#download").attr("href", "/pause/consultar-apontamento/gerar-relatorio-consulta?idFuncionario="+idFuncionario+
			"&de="+dataDe+"&ate="+dataAte);
	window.open($("#download").attr("href"),'_blank');
}