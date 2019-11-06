package sc.senac.br.sistemamecanica.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sc.senac.br.sistemamecanica.dao.util.JPAUtil;
import sc.senac.br.sistemamecanica.model.IBaseModel;

@SuppressWarnings("unchecked")
public abstract class BaseDao<T extends IBaseModel> implements IBaseDao<T> {

	private Class<T> persistenceClass;

	public BaseDao() {
		persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(T model) {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.persist(model);
		manager.getTransaction().commit();
		manager.close();

	}

	public void alterar(T model) {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.merge(model);
		manager.getTransaction().commit();
		manager.close();

	}

	public void excluir(T model) {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		manager.remove(manager.find(model.getClass(), model.getCodigo()));
		manager.getTransaction().commit();
		manager.close();

	}

	public T buscarPorID(Long id) {
		EntityManager manager = JPAUtil.getEntityManager();
		T resultado = manager.find(persistenceClass, id);
		manager.close();
		return resultado;
	}

	public List<T> buscarTodos() {
		EntityManager manager = JPAUtil.getEntityManager();

		TypedQuery<T> query = manager.createQuery("from " + persistenceClass.getName(), persistenceClass);

		List<T> resultado = query.getResultList();
		manager.close();

		return resultado;
	}

}
