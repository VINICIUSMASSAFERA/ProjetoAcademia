package br.com.fatec.fitcontrol.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import br.com.fatec.fitcontrol.exception.EmailInvalidoException;

@Entity
public class Usuario {
	@Id
	@Column(nullable = false, unique = true)
	private String cpf;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private Date dataDeNascimento;

	@Column(nullable = false)
	private Date dataDeCadastro;

	@Column(nullable = false, unique = true)
	private String email;

	@Column
	private String celular;

	@Column(nullable = false)
	private String senha;

	public Usuario(String cpf, String nome, Date dataDeNascimento, String email, String celular, String senha)
			throws EmailInvalidoException {
		this.cpf = cpf;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.dataDeCadastro = new Date();
		this.email = email;
		this.celular = celular;
		this.senha = senha;
	}

	public Usuario(String cpf, String nome, Date dataDeNascimento, String email, String senha)
			throws EmailInvalidoException {
		this.cpf = cpf;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.dataDeCadastro = new Date();
		this.email = email;
		this.senha = senha;
	}

	public Usuario() {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuário: {" + cpf + " / " + nome + " / " + email + " / " + celular + " / " + senha + "}";
	}
}
