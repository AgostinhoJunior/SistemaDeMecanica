package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sc.senac.br.sistemamecanica.dao.IBaseDao;
import sc.senac.br.sistemamecanica.model.Atendimento;
import sc.senac.br.sistemamecanica.model.Cliente;
import sc.senac.br.sistemamecanica.model.Servico;


@ViewScoped
@ManagedBean
public class CadastroAtendimentoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private IBaseDao<Servico> servicosDao;

	private Atendimento atendimento;
	
	private Cliente cliente;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public IBaseDao<Servico> getServicosDao() {
		return servicosDao;
	}

	public void setServicosDao(IBaseDao<Servico> servicosDao) {
		this.servicosDao = servicosDao;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
}
