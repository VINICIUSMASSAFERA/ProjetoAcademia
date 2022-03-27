package br.com.fatec.fitcontrol.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.fatec.fitcontrol.exception.DAOException;
import br.com.fatec.fitcontrol.hibernate.HibernateUtil;

public class DAOGenerico<Entidade> {

	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public DAOGenerico() {
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) throws DAOException {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.saveOrUpdate(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw new DAOException("Erro ao salvar!");
		} finally {
			sessao.close();
		}
	}

	public void alterar(Entidade entidade) throws DAOException {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw new DAOException("Erro ao alterar!");
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() throws DAOException {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw new DAOException("Erro ao listar!");
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar(String campoOrdenacao) throws DAOException {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.addOrder(Order.asc(campoOrdenacao));
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw new DAOException("Erro ao listar!");
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Object buscar(Long codigo) throws DAOException {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Object resultado = (Object) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw new DAOException("Erro ao buscar!");
		} finally {
			sessao.close();
		}
	}

	public void excluir(Object entidade) throws DAOException {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw new DAOException("Erro ao excluir!");
		} finally {
			sessao.close();
		}
	}

}
