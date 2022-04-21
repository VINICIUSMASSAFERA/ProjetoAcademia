package br.com.fatec.fitcontrol.service;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.fatec.fitcontrol.domain.DadosObjetivoUsuario;
import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.exception.DAOException;
import br.com.fatec.fitcontrol.hibernate.HibernateUtil;
import br.com.fatec.fitcontrol.repository.DAOGenerico;

public class DadosObjetivoUsuarioService extends DAOGenerico<DadosObjetivoUsuario> {

	public DadosObjetivoUsuario buscarPorUsuario(Usuario usuario) throws DAOException {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(DadosObjetivoUsuario.class);
			consulta.add(Restrictions.ilike("usuario", usuario.getCpf()));
			return (DadosObjetivoUsuario) consulta.uniqueResult();
		} catch (RuntimeException erro) {
			throw new DAOException("Erro ao listar!");
		} finally {
			sessao.close();
		}
	}

}
