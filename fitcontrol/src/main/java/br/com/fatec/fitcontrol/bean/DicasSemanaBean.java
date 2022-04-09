package br.com.fatec.fitcontrol.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.fatec.fitcontrol.domain.DicasSemana;
import br.com.fatec.fitcontrol.service.DicasSemanaService;

@ManagedBean
@ViewScoped
public class DicasSemanaBean {
	private List<DicasSemana> dicasDaSemana;
	private DicasSemana dicaAdicionar;
	private DicasSemanaService dicasSemanaService;

	@PostConstruct
	void init() {
		dicasSemanaService = new DicasSemanaService();
		dicaAdicionar = new DicasSemana();
		listarDicas();
	}

	public void salvar() {
		try {
			dicaAdicionar.setDataCriacao(new Date());
			dicasSemanaService.salvar(dicaAdicionar);
			init();
			Messages.addGlobalInfo("Dica salva com sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao salvar a dica!");
			e.printStackTrace();
		}
	}

	private void listarDicas() {
		dicasDaSemana = dicasSemanaService.listarDicasPorValidade();
	}

	public List<DicasSemana> getDicasDaSemana() {
		return dicasDaSemana;
	}

	public void setDicasDaSemana(List<DicasSemana> dicasDaSemana) {
		this.dicasDaSemana = dicasDaSemana;
	}

	public DicasSemana getDicaAdicionar() {
		return dicaAdicionar;
	}

	public void setDicaAdicionar(DicasSemana dicaAdicionar) {
		this.dicaAdicionar = dicaAdicionar;
	}
}
