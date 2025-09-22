package br.com.suporteSenai.suporte.model;

import java.io.Serializable;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Solicitacao implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	private String nif;
	private String nomeSolicitante;
	private String numeroSala;
	private String codigoPatrimonio;
	private String descricaoProblema;
	private String tipoProblema;
	private String status = "pendente";
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(String numeroSala) {
		this.numeroSala = numeroSala;
	}
	public String getCodigoPatrimonio() {
		return codigoPatrimonio;
	}
	public void setCodigoPatrimonio(String codigoPatrimonio) {
		this.codigoPatrimonio = codigoPatrimonio;
	}
	public String getDescricaoProblema() {
		return descricaoProblema;
	}
	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}
	public String getTipoProblema() {
		return tipoProblema;
	}
	public void setTipoProblema(String tipoProblema) {
		this.tipoProblema = tipoProblema;
	}
	
	
	
}
