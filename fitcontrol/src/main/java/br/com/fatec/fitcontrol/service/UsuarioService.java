package br.com.fatec.fitcontrol.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.omnifaces.util.Messages;

import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.exception.DAOException;
import br.com.fatec.fitcontrol.hibernate.HibernateUtil;
import br.com.fatec.fitcontrol.repository.DAOGenerico;
import br.com.fatec.fitcontrol.util.Helper;

public class UsuarioService extends DAOGenerico<Usuario> {

	private Usuario buscarUsuario(String login) throws DAOException {
		Usuario usuario = null;
		login = Helper.removerMascara(login);
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("cpf", login));
			usuario = (Usuario) consulta.uniqueResult();
			return usuario;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		} finally {
			sessao.close();
		}
		return usuario;
	}

	public Usuario logar(String login, String senha) {
		try {
			Usuario usuario = buscarUsuario(login);
			if (usuario == null) {
				Messages.addGlobalError("Usuário não encontrado!");
				return null;
			} else if (usuario.getSenha().equals(senha)) {
				return usuario;
			} else {
				Messages.addGlobalError("Senha incorreta!");
				return null;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
