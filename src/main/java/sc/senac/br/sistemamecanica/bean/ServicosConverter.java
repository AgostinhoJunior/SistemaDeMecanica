package sc.senac.br.sistemamecanica.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sc.senac.br.sistemamecanica.dao.ServicoDao;
import sc.senac.br.sistemamecanica.model.Servico;

@FacesConverter(forClass = Servico.class)
public class ServicosConverter implements Converter {

	private ServicoDao dao;

	public ServicosConverter() {
		dao = new ServicoDao();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		return dao.buscarPorID(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		
		return ((Servico) value).getCodigo().toString();
	}

}
