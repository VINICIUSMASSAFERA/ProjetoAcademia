package br.com.fatec.fitcontrol.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.fatec.fitcontrol.bean.AutorizacaoBean;
import br.com.fatec.fitcontrol.domain.Usuario;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();

		boolean ehPaginaDeAutenticacao = paginaAtual.contains("login.xhtml") || paginaAtual.contains("cadastrar.xhtml");

		if (!ehPaginaDeAutenticacao) {
			AutorizacaoBean autorizacaoBean = Faces.getSessionAttribute("autorizacaoBean");

			if (autorizacaoBean == null) {
				Faces.navigate("/pages/login.xhtml");
			}

			Usuario usuario = autorizacaoBean.getUsuarioLogado();
			if (usuario == null) {
				Faces.navigate("/pages/login.xhtml");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}