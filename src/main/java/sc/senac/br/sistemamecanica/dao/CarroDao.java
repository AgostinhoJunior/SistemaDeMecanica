package sc.senac.br.sistemamecanica.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import sc.senac.br.sistemamecanica.dao.util.JPAUtil;
import sc.senac.br.sistemamecanica.dto.CarroPorAtendimentoDTO;
import sc.senac.br.sistemamecanica.model.Carro;

public class CarroDao extends BaseDao<Carro> {

	public List<CarroPorAtendimentoDTO> buscaCarrosPorAtendimento() {
		EntityManager manager = JPAUtil.getEntityManager();

		StringBuilder jpqlBuilder = new StringBuilder();
		jpqlBuilder.append("select new sc.senac.br.sistemamecanica.dto.CarroPorAtendimentoDTO( ");
		jpqlBuilder.append("  count(car), ");
		jpqlBuilder.append("  car.marca ");
		jpqlBuilder.append(") ");
		jpqlBuilder.append("from Atendimento ate ");
		jpqlBuilder.append("right join ate.cliente cli ");
		jpqlBuilder.append("right join cli.carro car ");
		jpqlBuilder.append("group by car.marca");

		TypedQuery<CarroPorAtendimentoDTO> query = manager.createQuery(jpqlBuilder.toString(),
				CarroPorAtendimentoDTO.class);

		List<CarroPorAtendimentoDTO> resultado = query.getResultList();

		manager.close();

		return resultado;
	}
}
