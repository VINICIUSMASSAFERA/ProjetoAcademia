package br.com.fatec.fitcontrol.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DadosObjetivoUsuario {
	@Id
	@Column(nullable = false)
	private String usuario;

	@Column(nullable = false)
	private String objetivo;

	@Column(nullable = false)
	private String tempoDisponivel;

	@Column(nullable = false)
	private String diasDisponiveis;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getTempoDisponivel() {
		return tempoDisponivel;
	}

	public void setTempoDisponivel(String tempoDisponivel) {
		this.tempoDisponivel = tempoDisponivel;
	}

	public String getDiasDisponiveis() {
		return diasDisponiveis;
	}

	public void setDiasDisponiveis(String diasDisponiveis) {
		this.diasDisponiveis = diasDisponiveis;
	}

}
