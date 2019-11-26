package sc.senac.br.sistemamecanica.bean.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import sc.senac.br.sistemamecanica.dao.FuncionarioDao;
import sc.senac.br.sistemamecanica.model.Funcionario;

@FacesConverter(forClass = Funcionario.class)
public class FuncionarioConverter implements Converter {

	private FuncionarioDao funcionarioDao;

	public FuncionarioConverter() {
		funcionarioDao = new FuncionarioDao();
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}

		return funcionarioDao.buscarPorID(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		return ((Funcionario) value).getCodigo().toString();
	}

}
