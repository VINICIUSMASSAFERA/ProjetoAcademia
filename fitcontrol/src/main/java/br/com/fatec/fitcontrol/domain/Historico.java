package br.com.fatec.fitcontrol.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Historico {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Treino treino;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataTreino;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataTreino() {
		return dataTreino;
	}

	public void setDataTreino(Date dataTreino) {
		this.dataTreino = dataTreino;
	}

}
