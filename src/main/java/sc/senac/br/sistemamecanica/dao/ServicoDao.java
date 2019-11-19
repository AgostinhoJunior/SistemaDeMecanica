package sc.senac.br.sistemamecanica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sc.senac.br.sistemamecanica.dao.util.JPAUtil;
import sc.senac.br.sistemamecanica.dto.ServicosPorAtendimentoDTO;
import sc.senac.br.sistemamecanica.model.Servico;

public class ServicoDao extends BaseDao<Servico> {

	public List<ServicosPorAtendimentoDTO> buscaPessoasPorServico() {
		EntityManager manager = JPAUtil.getEntityManager();

		StringBuilder jpqlBuilder = new StringBuilder();
		jpqlBuilder.append("select new sc.senac.br.cadastropessoa.dto.PessoasPorProfissaoDTO( ");
		jpqlBuilder.append("  prof.descricao, ");
		jpqlBuilder.append("  count(pe) ");
		jpqlBuilder.append(") ");
		jpqlBuilder.append("from Pessoa pe ");
		jpqlBuilder.append("right join pe.profissao prof  ");
		jpqlBuilder.append("group by prof.descricao ");

		TypedQuery<ServicosPorAtendimentoDTO> query = manager.createQuery(jpqlBuilder.toString(),
				ServicosPorAtendimentoDTO.class);

		List<ServicosPorAtendimentoDTO> resultado = query.getResultList();

		manager.close();

		return resultado;
	}

}
