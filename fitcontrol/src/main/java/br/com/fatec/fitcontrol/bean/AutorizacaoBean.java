package br.com.fatec.fitcontrol.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.service.UsuarioService;

@ManagedBean
@SessionScoped
public class AutorizacaoBean {
	private Usuario usuarioLogado;

	private String login;
	private String senha;

	private UsuarioService usuarioService;

	@PostConstruct
	void init() {
		usuarioLogado = new Usuario();
		usuarioService = new UsuarioService();
	}

	public void logar() {
		try {
			usuarioLogado = usuarioService.logar(login, senha);
			if (usuarioLogado != null) {
				Faces.redirect("paginas/inicial.xhtml", "");
			}
		} catch (IOException e) {
			Messages.addGlobalError("Erro ao realizar o login!");
			e.printStackTrace();
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
}
