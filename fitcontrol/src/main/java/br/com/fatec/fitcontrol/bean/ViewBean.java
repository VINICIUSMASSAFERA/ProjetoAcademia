package br.com.fatec.fitcontrol.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.omnifaces.util.Faces;

@ManagedBean
@ViewScoped
public class ViewBean {
	public void redirecionaUsuarios() {
		try {
			Faces.redirect("paginas/usuarios.xhtml", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
