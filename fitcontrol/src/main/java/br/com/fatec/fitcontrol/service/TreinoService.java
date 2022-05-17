package br.com.fatec.fitcontrol.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.fatec.fitcontrol.domain.Treino;
import br.com.fatec.fitcontrol.hibernate.HibernateUtil;
import br.com.fatec.fitcontrol.repository.DAOGenerico;

public class TreinoService extends DAOGenerico<Treino> {

	public List<Treino> listarPorObjetivo(String objetivo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Treino.class);
			consulta.add(Restrictions.ilike("objetivo", objetivo));
			consulta.addOrder(Order.desc("id"));
			List<Treino> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		} finally {
			sessao.close();
		}
		return Collections.emptyList();
	}

}
