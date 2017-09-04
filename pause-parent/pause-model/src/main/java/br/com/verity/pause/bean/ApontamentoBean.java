package br.com.verity.pause.bean;

import java.sql.Time;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author guilherme.oliveira
 *
 */
@Component
public class ApontamentoBean {

	private Integer id;
	private String pis;
	private Date data;
	private Time horario;
	private Boolean tipoImportacao = true;
	private Date dataInclusao;
	private String observacao;
	private TipoJustificativaBean tpJustificativa;
	private ControleDiarioBean cntrDiario;
	private Integer idEmpresa;
	private Integer idUsuarioInclusao;
	private ArquivoApontamentoBean arqApontamento;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHorario() {
		return horario;
	}

	public void setHorario(Time horario) {
		this.horario = horario;
	}

	public Boolean getTipoImportacao() {
		return tipoImportacao;
	}

	public void setTipoImportacao(Boolean tipoImportacao) {
		this.tipoImportacao = tipoImportacao;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoJustificativaBean getTpJustificativa() {
		return tpJustificativa;
	}

	public void setTpJustificativa(TipoJustificativaBean tpJustificativa) {
		this.tpJustificativa = tpJustificativa;
	}

	public ControleDiarioBean getCntrDiario() {
		return cntrDiario;
	}

	public void setCntrDiario(ControleDiarioBean cntrDiario) {
		this.cntrDiario = cntrDiario;
	}

	public Integer getIdUsuarioInclusao() {
		return idUsuarioInclusao;
	}

	public void setIdUsuarioInclusao(Integer idUsuarioInclusao) {
		this.idUsuarioInclusao = idUsuarioInclusao;
	}

	public ArquivoApontamentoBean getArqApontamento() {
		return arqApontamento;
	}

	public void setArqApontamento(ArquivoApontamentoBean arqApontamento) {
		this.arqApontamento = arqApontamento;
	}

}