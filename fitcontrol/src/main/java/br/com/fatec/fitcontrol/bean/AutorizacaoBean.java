package br.com.fatec.fitcontrol.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.fatec.fitcontrol.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutorizacaoBean {
	private Usuario usuarioLogado;
	
}
