package sc.senac.br.sistemamecanica.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sc.senac.br.sistemamecanica.dao.ServicoDao;
import sc.senac.br.sistemamecanica.model.Servico;

@FacesConverter(forClass = Servico.class)
public class ServicoConverter implements Converter {

	private ServicoDao servicoDao;

	public ServicoConverter() {
		servicoDao = new ServicoDao();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		return servicoDao.buscarPorID(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		return ((Servico) value).getCodigo().toString();
	}

}
