package sc.senac.br.sistemamecanica.dao;

import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import sc.senac.br.sistemamecanica.dao.util.JPAUtil;
import sc.senac.br.sistemamecanica.filter.UsuarioFilter;
import sc.senac.br.sistemamecanica.model.Usuario;

public class UsuarioDao extends BaseDao<Usuario> {

	public Usuario verificaLogin(String email, String senha) {
		EntityManager manager = JPAUtil.getEntityManager();

		String jpql = "select u from Usuario u where email = :email and senha = :senha";

		TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);

		query.setParameter("email", email);
		query.setParameter("senha", senha);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			manager.close();
		}
	}

	public List<Usuario> buscar(UsuarioFilter filtro) {
		EntityManager manager = JPAUtil.getEntityManager();

		try {

			StringBuilder jpqlBuilder = new StringBuilder();
			jpqlBuilder.append("select u from Usuario u ");
			jpqlBuilder.append("where 1=1 ");

			for (Entry<String, Object> entry : filtro.getFiltros().entrySet()) {
				jpqlBuilder.append(" and u.").append(entry.getKey()).append(" like :").append(entry.getKey());
			}

			if (filtro.getPropriedadeOrdenacao() != null) {
				jpqlBuilder.append("order by ").append(filtro.getPropriedadeOrdenacao());

				if (!filtro.isAscendente()) {
					jpqlBuilder.append(" desc");
				}
			}

			TypedQuery<Usuario> query = manager.createQuery(jpqlBuilder.toString(), Usuario.class);

			for (Entry<String, Object> entry : filtro.getFiltros().entrySet()) {
				query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
			}

			query.setFirstResult(filtro.getPrimeiroRegistro());
			query.setMaxResults(filtro.getQuantidadeRegistros());

			return query.getResultList();

		} finally {
			manager.close();
		}
	}

	public int total() {
		EntityManager manager = JPAUtil.getEntityManager();

		try {
			String jpql = "select count(u) from Usuario u";
			TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
			return query.getSingleResult().intValue();
		} finally {
			manager.close();
		}
	}
}
