package sc.senac.br.sistemamecanica.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import sc.senac.br.sistemamecanica.dao.util.JPAUtil;
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
}
