package br.com.fatec.fitcontrol.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.fatec.fitcontrol.domain.Treino;
import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.service.TreinoService;
import br.com.fatec.fitcontrol.service.UsuarioService;

@ManagedBean
@ViewScoped
public class TreinoBean {
	private Usuario usuarioLogado;
	private Treino treino;
	private List<Treino> treinos;

	private UsuarioService usuarioService;
	private TreinoService treinoService;

	@PostConstruct
	void init() {
		AutorizacaoBean autorizacaoBean = Faces.getSessionAttribute("autorizacaoBean");
		usuarioLogado = autorizacaoBean.getUsuarioLogado();
		treino = new Treino();
		treinos = new ArrayList<>();

		usuarioService = new UsuarioService();
		treinoService = new TreinoService();

		listarTreinos();
	}

	private void listarTreinos() {
		try {
			if (Boolean.TRUE.equals(usuarioLogado.getAdmin())) {
				treinos = treinoService.listar();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			treino.setDataCriacao(new Date());
			treinoService.salvar(treino);
			listarTreinos();
			Messages.addGlobalInfo("Treino salvo com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalInfo("Erro ao salvar o treino!");
			e.printStackTrace();
		}
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}

	public List<Treino> getTreinos() {
		return treinos;
	}

	public void setTreinos(List<Treino> treinos) {
		this.treinos = treinos;
	}

}
