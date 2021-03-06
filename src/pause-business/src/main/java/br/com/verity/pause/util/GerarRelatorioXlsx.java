package br.com.verity.pause.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.verity.pause.bean.ConsultaApontamentosBean;
import br.com.verity.pause.bean.ConsultaCompletaBean;
import br.com.verity.pause.bean.ControleMensalBean;
import br.com.verity.pause.bean.FuncionarioBean;
import br.com.verity.pause.bean.SobreAvisoBean;
import br.com.verity.pause.business.AfastamentoBusiness;
import br.com.verity.pause.enumeration.DiaSemanaEnum;
import br.com.verity.pause.enumeration.MesEnum;

@Component
public class GerarRelatorioXlsx {
	
	@Autowired
	private AfastamentoBusiness afastamentoBusiness;

	/**
	 * @author guilherme.oliveira XSSF gera um arquivo .xlsx e HSSF gera um
	 *         arquivo .xls WorkBook é um arquivo Excel, Sheet é para gerar um
	 *         planilha, row gera/seleciona uma linha, Cell gera/seleciona uma
	 *         celula da linha. WoorkBook.write(FileOutputStram) salva os dados
	 *         na planilha
	 * @param sobreAvisos 
	 */
	@SuppressWarnings("deprecation")
	public byte[] relatorioFuncionarioPeriodo(List<ConsultaCompletaBean> consultaCompleta, List<ControleMensalBean> saldoDeHoras ,List<SobreAvisoBean> sobreAvisos, FuncionarioBean funcionario,
			String de, String ate) {
		String empresa = (funcionario.getEmpresa().getId() == 2) ? "Verity" : "QA360";
		ClassPathResource resourceModelo = new ClassPathResource(empresa+"Relatorio.xlsx");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL, new Locale("pt", "BR"));
		DateFormat formatDt = new SimpleDateFormat("dd/MM/yyyy");
		int linha = 1;
		int dia = 1;
		int mesmoDia = 1;
		int aux = 0;
		try {
			InputStream fileModelo = resourceModelo.getInputStream();
			XSSFWorkbook workbook = new XSSFWorkbook(fileModelo);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.getRow(linha);
			
			row.getCell(1).setCellValue(funcionario.getPis());
			row.getCell(6).setCellValue(funcionario.getMatricula());
			row.getCell(16).setCellValue(de.replace('-', '/') + " a " + ate.replace('-', '/'));
			linha++;
			
			row = sheet.getRow(linha);
			row.getCell(1).setCellValue(funcionario.getNome());
			row.getCell(16).setCellValue(formatador.format(new Date())+" às "+LocalTime.now().format(formatter));
			linha+= 4;

			row = sheet.createRow(linha);
			int cont = 0;
			for (ConsultaCompletaBean consulta : consultaCompleta) {
				row.setHeight((short) 500);
				Integer idFuncionario = consulta.getIdFuncionario();
				Boolean afastado = false;
				
				if (consulta.getData().getDate() == dia) {
					mesmoDia += 1;
				} else {
					linha++;
					dia++;
					mesmoDia = 2;
					row = sheet.createRow(linha);
					aux = 0;
				}
				if(idFuncionario != null){
					afastado = afastamentoBusiness.existeAfastamentoPara(consulta.getIdFuncionario(), new java.sql.Date(consulta.getData().getTime()));
				}
				String dtApontamento = formatDt.format(consulta.getData());
				row.createCell(0).setCellValue(dtApontamento);
				row.createCell(1).setCellValue(DiaSemanaEnum.valueOf(consulta.getData().getDay()).getDiaSimples());
				
				if (consulta.getApontamentoHorario() != null) {
					String hora = formatter.format(consulta.getApontamentoHorario());
					hora += consulta.getApontamentoTpImportacao() == true ? " E" : " M";
					row.createCell(mesmoDia).setCellValue(hora);
					aux++;
				}
				SobreAvisoBean sa = sobreAvisos.stream().filter(x -> x.getData().getTime()== consulta.getData().getTime()).findFirst().orElse(null);
				if(sa != null){
					row.createCell(10).setCellValue(sa.getEntrada().format(formatter));
					row.createCell(11).setCellValue(sa.getSaida().format(formatter));
				}
				row.createCell(12).setCellValue(
						(consulta.getAtestadoQuantidadeHora() != null) ? round(consulta.getAtestadoQuantidadeHora(),2) : 0);
				row.createCell(13).setCellValue(
						(consulta.getControleDiarioHoraTotal() != null) ? round(consulta.getControleDiarioHoraTotal(),2) : 0);
				if(consulta.getControleDiarioBancoHora() != null && consulta.getControleDiarioBancoHora() > 0){
					row.createCell(14).setCellValue(round(consulta.getControleDiarioBancoHora(),2));
					row.createCell(15).setCellValue(0);
				}else if(consulta.getControleDiarioBancoHora() != null && consulta.getControleDiarioBancoHora()<0){
					row.createCell(14).setCellValue(0);
					row.createCell(15).setCellValue(round(consulta.getControleDiarioBancoHora(),2));
				}else{
					row.createCell(14).setCellValue(0);
					row.createCell(15).setCellValue(0);
				}
				row.createCell(16).setCellValue(
						(consulta.getControleDiarioAdcNoturno() != null) ? round(consulta.getControleDiarioAdcNoturno(),2) : 0);
				row.createCell(17)
						.setCellValue((consulta.getControleDiarioSA() != null) ? round(consulta.getControleDiarioSA(),2) : 0);
				row.createCell(18).setCellValue(
						(consulta.getControleDiarioST() != null) ? round(consulta.getControleDiarioST(),2) : 0);
				row.createCell(19).setCellValue(consulta.getApontamentoObs());
				if(cont == consultaCompleta.size()-1){
					mesmoDia = (aux == 0) ? 2 : mesmoDia + 2;
				}
				cont++;
			}
			linha++;
			row = sheet.createRow(linha);
			row.createCell(13).setCellFormula("SUM(N6:N" + (linha) + ")");
			row.createCell(14).setCellFormula("SUM(O6:O" + (linha) + ")");
			row.createCell(15).setCellFormula("SUM(P6:P" + (linha) + ")");
			row.createCell(16).setCellFormula("SUM(Q6:Q" + (linha) + ")");
			row.createCell(17).setCellFormula("SUM(R6:R" + (linha) + ")");
			row.createCell(18).setCellFormula("SUM(S6:S" + (linha) + ")");
			linha++;
			
			int linhaSaldo;
			row = sheet.createRow(linha);
			row.createCell(11).setCellValue("Saldo Final: ");
			row.createCell(13).setCellFormula("O" + (linha) + "+P" + (linha));
			row.createCell(14).setCellFormula("TEXT(ABS(N" + (linha + 1) + ")/24,\"([h]:mm)\")");
			linhaSaldo = linha;
			linha++;

			row = sheet.createRow(linha);
			row.createCell(0).setCellValue("Controle de Saldo de Horas");
			linha++;
			
			row = sheet.createRow(linha);
			row.createCell(0).setCellValue("Trimestre");
			row.createCell(1).setCellValue("Ano");
			row.createCell(2).setCellValue("Mês");
			row.createCell(3).setCellValue("Banco");
			row.createCell(4).setCellValue("Saldo");
			linha++;
			
			double saldoAnterior = 0.0d;
			for(ControleMensalBean cm: saldoDeHoras){
				if(cm != null){
					row = sheet.createRow(linha);
					row.createCell(0)
							.setCellValue(MesEnum.valueOf((cm.getMes())).getSemestre());
					row.createCell(1).setCellValue(cm.getAno());
					row.createCell(2).setCellValue(cm.getMes());
					row.createCell(3).setCellValue(round(cm.getBancoHora(),2));
					row.createCell(4).setCellValue(round(cm.getBancoHora()
							+ saldoAnterior,2));
					
					saldoAnterior = cm.getBancoHora() + saldoAnterior;
					linha++;
				}
			}
			row.createCell(3).setCellFormula("O" + (linhaSaldo) + "+P" + (linhaSaldo));
			if(saldoDeHoras.size()>1)
				row.createCell(4).setCellFormula("D"+ (linha) + "+E" + (linha-1));
			else
				row.createCell(4).setCellFormula("O" + (linhaSaldo) + "+P" + (linhaSaldo));
			
			row = sheet.createRow(linha);
			row.createCell(11).setCellValue("__________________________________________________________");
			linha++;

			row = sheet.createRow(linha);
			row.createCell(11).setCellValue("Declaro que analisei as informações deste relatório e reconheço a");
			linha++;

			row = sheet.createRow(linha);
			row.createCell(11).setCellValue("veracidade dos registros.");
			/*linha++;
			linha++;

			row = sheet.createRow(linha);
			row.createCell(0).setCellValue(
					"Legenda : Origem Marcação: M- Manual / E- Eletrônico / SA = Sobre Aviso (Plantão); SA = Sobre Aviso sem Acionamento / ST = Sobre Aviso Trabalhado / TP = Tipo do Ponto");
			linha++;

			row = sheet.createRow(linha);
			Date hoje = new Date();
			String dtHoje = formatDt.format(hoje);
			row.createCell(0).setCellValue(dtHoje);*/
			
			//FileOutputStream out = new FileOutputStream(new File(arquivo));
			sheet.setColumnWidth(4, 2000);
			
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			
			byte [] outArray = outByteStream.toByteArray();
			outByteStream.close();
			return outArray;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public byte[] relatorioConsulta(List<ConsultaApontamentosBean> consultaApontamentosBean, Date de, Date ate, Integer idEmpresa) {
		String empresa = (idEmpresa == 2)?"Verity":"QA360";
		ClassPathResource resourceModelo = new ClassPathResource(empresa+".xlsx");
		DateFormat formatDt = new SimpleDateFormat("dd/MM/yyyy");
		int linha = 5;
		try {
			InputStream fileModelo = resourceModelo.getInputStream();
			XSSFWorkbook workbook = new XSSFWorkbook(fileModelo);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row = sheet.createRow(linha);

			String data = formatDt.format(de) + " até " + formatDt.format(ate);
			
			row = sheet.getRow(3);
			row.getCell(2).setCellValue(data);
			
			for (ConsultaApontamentosBean bean : consultaApontamentosBean) {
				row = sheet.createRow(linha);
				row.createCell(0).setCellValue(bean.getNmFuncionario());
				row.createCell(1).setCellValue(bean.getControleDiario().getHoraTotal());
				row.createCell(2).setCellValue(bean.getControleDiario().getBancoHora());
				row.createCell(3).setCellValue(bean.getControleDiario().getAdicNoturno());
				row.createCell(4).setCellValue(bean.getControleDiario().getSobreAviso());
				row.createCell(5).setCellValue(bean.getControleDiario().getSobreAvisoTrabalhado());
				linha++;
			}
			
			//FileOutputStream out = new FileOutputStream(new File(consolidado));
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			
			byte [] outArray = outByteStream.toByteArray();
			outByteStream.close();
			return outArray;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}