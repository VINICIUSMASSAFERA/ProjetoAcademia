package br.com.fatec.fitcontrol.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.fatec.fitcontrol.domain.DicasSemana;
import br.com.fatec.fitcontrol.domain.Historico;
import br.com.fatec.fitcontrol.domain.Usuario;
import br.com.fatec.fitcontrol.hibernate.HibernateUtil;
import br.com.fatec.fitcontrol.repository.DAOGenerico;

public class HistoricoService extends DAOGenerico<Historico> {
	public List<Historico> listarHistoricoDoUsuario(Usuario usuario) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Historico.class);
			consulta.add(Restrictions.eq("usuario.cpf", usuario.getCpf()));
			consulta.addOrder(Order.desc("id"));
			List<Historico> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		} finally {
			sessao.close();
		}
		return Collections.emptyList();
	}
}
