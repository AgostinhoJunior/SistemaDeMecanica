package sc.senac.br.sistemamecanica.bean.lazymodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import sc.senac.br.sistemamecanica.dao.UsuarioDao;
import sc.senac.br.sistemamecanica.filter.UsuarioFilter;
import sc.senac.br.sistemamecanica.model.Usuario;

public class UsuarioLazyModel extends LazyDataModel<Usuario> {

	private static final long serialVersionUID = 1L;

	private UsuarioFilter filtro;

	private UsuarioDao usuarioDao;

	public UsuarioLazyModel(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
		filtro = new UsuarioFilter();
	}

	@Override
	public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		filtro.setPrimeiroRegistro(first);
		filtro.setQuantidadeRegistros(pageSize);
		filtro.setPropriedadeOrdenacao(sortField);
		filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
		filtro.setFiltros(filters);

		setRowCount(usuarioDao.total());

		return usuarioDao.buscar(filtro);
	}
	
	
}
