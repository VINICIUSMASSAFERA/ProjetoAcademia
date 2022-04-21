package br.com.fatec.fitcontrol.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.fatec.fitcontrol.domain.DadosObjetivoUsuario;
import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.exception.DAOException;
import br.com.fatec.fitcontrol.service.DadosObjetivoUsuarioService;
import br.com.fatec.fitcontrol.service.UsuarioService;

@ManagedBean
@ViewScoped
public class MeusObjetivosBean {
	private Usuario usuarioLogado;
	private DadosObjetivoUsuario dados;

	private UsuarioService usuarioService;
	private DadosObjetivoUsuarioService dadosService;

	@PostConstruct
	void init() {
		AutorizacaoBean autorizacaoBean = Faces.getSessionAttribute("autorizacaoBean");
		usuarioLogado = autorizacaoBean.getUsuarioLogado();
		dados = new DadosObjetivoUsuario();

		usuarioService = new UsuarioService();
		dadosService = new DadosObjetivoUsuarioService();

		try {
			dados = dadosService.buscarPorUsuario(usuarioLogado);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void editar() {
		try {
			dadosService.alterar(dados);
			Messages.addGlobalInfo("Editado com sucesso!");
		} catch (DAOException e) {
			Messages.addGlobalError("Erro ao editar o objetivo!");
			e.printStackTrace();
		}
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public DadosObjetivoUsuario getDados() {
		return dados;
	}

	public void setDados(DadosObjetivoUsuario dados) {
		this.dados = dados;
	}

}
