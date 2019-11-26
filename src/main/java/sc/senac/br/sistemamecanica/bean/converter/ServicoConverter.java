package sc.senac.br.sistemamecanica.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sc.senac.br.sistemamecanica.dao.ServicoDao;
import sc.senac.br.sistemamecanica.model.Servico;

@FacesConverter(forClass = Servico.class, value = "ServicoConverter")
public class ServicoConverter implements Converter {

	private ServicoDao servicoDao;

	public ServicoConverter() {
		servicoDao = new ServicoDao();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (servicoDao.buscarPorID(Long.valueOf(value)) == null) {
			return null;
		} else {
			Servico servico = (Servico) servicoDao.buscarPorID(Long.valueOf(value));
			return servico;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Servico servico = (Servico) value;
		servico = servicoDao.buscarPorID(servico.getCodigo());

		if (servico == null) {
			return null;
		} else {
			return servico.getCodigo().toString();
		}
	}

}
