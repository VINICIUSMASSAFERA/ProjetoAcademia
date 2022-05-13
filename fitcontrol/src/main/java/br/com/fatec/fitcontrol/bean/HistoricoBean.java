package br.com.fatec.fitcontrol.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Faces;

import br.com.fatec.fitcontrol.domain.Historico;
import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.service.HistoricoService;

@ManagedBean
@ViewScoped
public class HistoricoBean {
	private Usuario usuarioLogado;
	private List<Historico> treinosHistorico;

	private HistoricoService historicoService;

	@PostConstruct
	void init() {
		AutorizacaoBean autorizacaoBean = Faces.getSessionAttribute("autorizacaoBean");
		usuarioLogado = autorizacaoBean.getUsuarioLogado();
		treinosHistorico = new ArrayList<>();

		historicoService = new HistoricoService();

		buscarTreinoUsuario();
	}

	private void buscarTreinoUsuario() {
		treinosHistorico = historicoService.listarHistoricoDoUsuario(usuarioLogado);
	}

	public List<Historico> getTreinosHistorico() {
		return treinosHistorico;
	}

	public void setTreinosHistorico(List<Historico> treinosHistorico) {
		this.treinosHistorico = treinosHistorico;
	}

}
