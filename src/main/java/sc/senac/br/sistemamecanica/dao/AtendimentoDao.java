package sc.senac.br.sistemamecanica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sc.senac.br.sistemamecanica.dao.util.JPAUtil;
import sc.senac.br.sistemamecanica.dto.ServicoPorAtendimentoDTO;
import sc.senac.br.sistemamecanica.model.Atendimento;

public class AtendimentoDao extends BaseDao<Atendimento> {

	public List<ServicoPorAtendimentoDTO> buscaServicosPorAtendimento() {
		EntityManager manager = JPAUtil.getEntityManager();

		StringBuilder jpqlBuilder = new StringBuilder();
		jpqlBuilder.append("select new sc.senac.br.sistemamecanica.dto.ServicoPorAtendimentoDTO( ");
		jpqlBuilder.append("  count(ate), ");
		jpqlBuilder.append("  ser.descricao ");
		jpqlBuilder.append(") ");
		jpqlBuilder.append("from Atendimento ate ");
		jpqlBuilder.append("right join ate.servicos ser ");
		jpqlBuilder.append("group by ser.descricao ");

		TypedQuery<ServicoPorAtendimentoDTO> query = manager.createQuery(jpqlBuilder.toString(),
				ServicoPorAtendimentoDTO.class);

		List<ServicoPorAtendimentoDTO> resultado = query.getResultList();

		manager.close();

		return resultado;
	}
}
