package br.com.fatec.fitcontrol.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.fatec.fitcontrol.domain.DadosObjetivoUsuario;
import br.com.fatec.fitcontrol.domain.Historico;
import br.com.fatec.fitcontrol.domain.Treino;
import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.exception.DAOException;
import br.com.fatec.fitcontrol.service.DadosObjetivoUsuarioService;
import br.com.fatec.fitcontrol.service.HistoricoService;
import br.com.fatec.fitcontrol.service.TreinoService;

@ManagedBean
@ViewScoped
public class TreinoBean {
	private Usuario usuarioLogado;
	private Treino treino;
	private DadosObjetivoUsuario dados;
	private List<Treino> treinos;
	private List<Historico> treinosHistorico;
	private List<Historico> treinosHoje;

	private boolean treinoGeradoHoje = false;

	private TreinoService treinoService;
	private HistoricoService historicoService;
	private DadosObjetivoUsuarioService dadosService;

	@PostConstruct
	void init() {
		AutorizacaoBean autorizacaoBean = Faces.getSessionAttribute("autorizacaoBean");
		usuarioLogado = autorizacaoBean.getUsuarioLogado();
		treino = new Treino();
		treinos = new ArrayList<>();
		treinosHistorico = new ArrayList<>();
		treinosHoje = new ArrayList<>();

		treinoService = new TreinoService();
		historicoService = new HistoricoService();
		dadosService = new DadosObjetivoUsuarioService();
		
		try {
			dados = dadosService.buscarPorUsuario(usuarioLogado);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		listarTreinos();
		buscarTreinoUsuario();
	}

	@SuppressWarnings("deprecation")
	private void buscarTreinoUsuario() {
		treinosHistorico = historicoService.listarHistoricoDoUsuario(usuarioLogado);
		treinosHoje = new ArrayList<>();
		
		Calendar cal = Calendar.getInstance();
		
		for (Historico h : treinosHistorico) {
			
			if (h.getDataTreino().getDate() == cal.getTime().getDate()
					&& h.getDataTreino().getMonth() == cal.getTime().getMonth()) {
				treinosHoje.add(h);
				treinoGeradoHoje = true;
			}
		}
	}

	public void gerarTreino() {
		try {
			dados = dadosService.buscarPorUsuario(usuarioLogado);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -2);

		List<Historico> treinosHistoricoAux = new ArrayList<>(treinosHistorico);

		for (Historico h : treinosHistorico) {
			if (h.getDataTreino().after(cal.getTime())) {
				treinosHistoricoAux.remove(h);
			}
		}

		List<Treino> restantes = new ArrayList<>(treinos);
		for (Treino t : treinos) {
			for (Historico h : treinosHistoricoAux) {
				if (h.getTreino().equals(t)) {
					restantes.remove(t);
				}
			}
		}

		int tempo = Integer.parseInt(dados.getTempoDisponivel());

		if (restantes.size() < 2) {
			restantes = treinos;
		}

		for (Treino t : restantes) {
			Historico his = new Historico();
			his.setDataTreino(new Date());
			his.setTreino(t);
			his.setUsuario(usuarioLogado);

			if (tempo > 0) {
				try {
					historicoService.salvar(his);
				} catch (DAOException e) {
					e.printStackTrace();
				}
				tempo = tempo - 20;
			}
		}
		buscarTreinoUsuario();
		Messages.addGlobalInfo("Treino gerado com sucesso!");
	}

	private void listarTreinos() {
		if(dados == null) {
			try {
				treinos = treinoService.listar();
			} catch (DAOException e) {
				e.printStackTrace();
			}
			return;
		}
		try {
			treinos = treinoService.listarPorObjetivo(dados.getObjetivo());
			ArrayList<Treino> aux = (ArrayList<Treino>) treinoService.listar();
			for (Treino treino : aux) {
				if(!treinos.contains(treino)) {
					treinos.add(treino);
				}
			}
			
			for (Treino treino : treinos) {
				System.out.println(treino.getDescricao() + " " + treino.getObjetivo());
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

	public void excluir(Treino treinoSelecionado) {
		try {
			treinoService.excluir(treinoSelecionado);
			listarTreinos();
			Messages.addGlobalInfo("Treino excluído com sucesso!");
		} catch (DAOException e) {
			Messages.addGlobalInfo("Erro ao excluir o treino!");
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

	public boolean isTreinoGeradoHoje() {
		return treinoGeradoHoje;
	}

	public List<Historico> getTreinosHoje() {
		return treinosHoje;
	}

	public void setTreinosHoje(List<Historico> treinosHoje) {
		this.treinosHoje = treinosHoje;
	}

	public void setTreinoGeradoHoje(boolean treinoGeradoHoje) {
		this.treinoGeradoHoje = treinoGeradoHoje;
	}

}
