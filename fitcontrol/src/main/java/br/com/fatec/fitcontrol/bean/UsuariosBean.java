package br.com.fatec.fitcontrol.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.exception.DAOException;
import br.com.fatec.fitcontrol.service.UsuarioService;

@ManagedBean
@ViewScoped
public class UsuariosBean {
	private Usuario usuarioLogado;
	private Usuario usuario;
	private List<Usuario> usuarios;

	private UsuarioService usuarioService;

	@PostConstruct
	void init() {
		AutorizacaoBean autorizacaoBean = Faces.getSessionAttribute("autorizacaoBean");
		usuarioLogado = autorizacaoBean.getUsuarioLogado();
		usuario = new Usuario();

		usuarios = new ArrayList<>();

		usuarioService = new UsuarioService();
		listarUsuarios();
	}

	private void listarUsuarios() {
		try {
			usuarios = usuarioService.listar();
		} catch (DAOException e) {
			Messages.addGlobalError("Erro ao listar usuários!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			usuarioService.salvar(usuario);
			listarUsuarios();
			Messages.addGlobalInfo("Usuário salvo com sucesso!");
		} catch (DAOException e) {
			Messages.addGlobalError("Erro ao salvar usuário!");
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
