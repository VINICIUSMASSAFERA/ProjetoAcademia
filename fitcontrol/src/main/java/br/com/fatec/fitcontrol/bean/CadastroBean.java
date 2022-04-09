package br.com.fatec.fitcontrol.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.fatec.fitcontrol.domain.DadosObjetivoUsuario;
import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.exception.DAOException;
import br.com.fatec.fitcontrol.service.DadosObjetivoUsuarioService;
import br.com.fatec.fitcontrol.service.UsuarioService;
import br.com.fatec.fitcontrol.util.Helper;

@ManagedBean
@ViewScoped
public class CadastroBean {
	private Usuario usuario;
	private DadosObjetivoUsuario dados;

	private UsuarioService usuarioService;
	private DadosObjetivoUsuarioService dadosService;

	@PostConstruct
	void init() {
		usuario = new Usuario();
		dados = new DadosObjetivoUsuario();

		usuarioService = new UsuarioService();
		dadosService = new DadosObjetivoUsuarioService();
	}

	public void cadastrarUsuario() {
		try {
			Helper.emailValido(usuario.getEmail());
			usuario.setCpf(Helper.removerMascara(usuario.getCpf()));
			usuario.setDataDeCadastro(new Date());
			usuario.setAdmin(false);
			salvarObjetivosDoUsuario();
			usuarioService.salvar(usuario);
			limparCampos();
			Messages.addGlobalInfo("Usuário salvo com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError(e.getMessage());
			e.printStackTrace();
		}
	}

	private void limparCampos() {
		usuario = new Usuario();
		dados = new DadosObjetivoUsuario();
	}

	private void salvarObjetivosDoUsuario() throws DAOException {
		dados.setUsuario(usuario.getCpf());
		dadosService.salvar(dados);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DadosObjetivoUsuario getDados() {
		return dados;
	}

	public void setDados(DadosObjetivoUsuario dados) {
		this.dados = dados;
	}

}
