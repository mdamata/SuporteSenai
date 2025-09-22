package br.com.suporteSenai.suporte.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Paineltecnico implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long idSolicitacao;
	private String status;
	private String acao;
	private String tecnicoResponsavel;
	private String observacoes;	
		public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(long idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}


	public String getTecnicoResponsavel() {
		return tecnicoResponsavel;
	}
	public void setTecnicoResponsavel(String tecnicoResponsavel) {
		this.tecnicoResponsavel = tecnicoResponsavel;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
		private static final long serialVersionUID = 1L;
	

}
