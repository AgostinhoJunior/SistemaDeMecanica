package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sc.senac.br.sistemamecanica.dao.IBaseDao;
import sc.senac.br.sistemamecanica.dao.ServicoDao;
import sc.senac.br.sistemamecanica.model.Servico;

@ManagedBean
@ViewScoped
public class CadastroServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Servico servico;
	private List<Servico> servicos;
	private List<Servico> servicosFiltro;
	private IBaseDao<Servico> servicoDao;

	@PostConstruct
	public void init() {
		servicoDao = new ServicoDao();
		limpar();
		buscar();
	}

	public void salvar() {
		if (servico.getCodigo() == null) {
			servicoDao.salvar(servico);

			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			mensagem.setSummary("Servico salvo com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, mensagem);

		} else {
			servicoDao.alterar(servico);

			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			mensagem.setSummary("Servico alterado com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		}

		limpar();
		buscar();
	}

	public void excluir() {
		servicoDao.excluir(servico);
		limpar();
		buscar();

		FacesMessage mensagem = new FacesMessage();
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		mensagem.setSummary("Servico excluído com sucesso!");

		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public void limpar() {
		servico = new Servico();
		servicos = new ArrayList<>();
	}

	public void buscar() {
		servicos = servicoDao.buscarTodos();
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Servico> getServicosFiltro() {
		return servicosFiltro;
	}

	public void setServicosFiltro(List<Servico> servicosFiltro) {
		this.servicosFiltro = servicosFiltro;
	}

	public IBaseDao<Servico> getServicoDao() {
		return servicoDao;
	}

	public void setServicoDao(IBaseDao<Servico> servicoDao) {
		this.servicoDao = servicoDao;
	}

	@Override
	public String toString() {
		return "CadastroServicoBean [servico=" + servico + ", servicos=" + servicos + ", servicosFiltro="
				+ servicosFiltro + ", servicoDao=" + servicoDao + "]";
	}

}
