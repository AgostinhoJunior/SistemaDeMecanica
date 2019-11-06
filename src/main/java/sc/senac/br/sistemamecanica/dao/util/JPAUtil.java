package sc.senac.br.sistemamecanica.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private final static EntityManagerFactory factory;
	
	static {
		factory = Persistence
			.createEntityManagerFactory("MecanicaPU");
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
}
