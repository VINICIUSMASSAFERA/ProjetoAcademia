package br.com.fatec.fitcontrol.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.fatec.fitcontrol.domain.DicasSemana;
import br.com.fatec.fitcontrol.hibernate.HibernateUtil;
import br.com.fatec.fitcontrol.repository.DAOGenerico;

public class DicasSemanaService extends DAOGenerico<DicasSemana> {

	public List<DicasSemana> listarDicasPorValidade() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, +7);
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(DicasSemana.class);
			consulta.add(Restrictions.between("dataValidade", new Date(), cal.getTime()));
			List<DicasSemana> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			erro.printStackTrace();
		} finally {
			sessao.close();
		}
		return Collections.emptyList();
	}
}
