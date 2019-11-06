package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sc.senac.br.sistemamecanica.dao.FornecedorDao;
import sc.senac.br.sistemamecanica.dao.IBaseDao;
import sc.senac.br.sistemamecanica.dao.PessoaDao;
import sc.senac.br.sistemamecanica.model.Fornecedor;
import sc.senac.br.sistemamecanica.model.Pessoa;

@ViewScoped
@ManagedBean
public class CadastroFornecedorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;
	private List<Fornecedor> fornecedores;
	private List<Fornecedor> fornecedorFiltro;
	private IBaseDao<Fornecedor> fornecedorDao;
	private IBaseDao<Pessoa> pessoaDao;

	@PostConstruct
	public void init() {
		fornecedorDao = new FornecedorDao();
		pessoaDao = new PessoaDao();
		limpar();
		buscar();
	}

	public void salvar() {
		if (fornecedor.getCodigo() == null) {
			pessoaDao.salvar(fornecedor.getPessoa());
			fornecedorDao.salvar(fornecedor);

			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			mensagem.setSummary("Fornecedor salvo com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, mensagem);

		} else {
			pessoaDao.alterar(fornecedor.getPessoa());
			fornecedorDao.alterar(fornecedor);

			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			mensagem.setSummary("Pessoa alterada com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		}

		limpar();
		buscar();
	}

	public void excluir() {
		fornecedorDao.excluir(fornecedor);
		limpar();
		buscar();

		FacesMessage mensagem = new FacesMessage();
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		mensagem.setSummary("Pessoa excluída com sucesso!");

		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public void limpar() {
		fornecedor = new Fornecedor();
		fornecedor.setPessoa(new Pessoa());
		fornecedores = new ArrayList<>();
	}

	public void buscar() {
		fornecedores = fornecedorDao.buscarTodos();
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public IBaseDao<Fornecedor> getFornecedorDao() {
		return fornecedorDao;
	}

	public void setFornecedorDao(IBaseDao<Fornecedor> fornecedorDao) {
		this.fornecedorDao = fornecedorDao;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public List<Fornecedor> getFornecedorFiltro() {
		return fornecedorFiltro;
	}

	public void setFornecedorFiltro(List<Fornecedor> fornecedorFiltro) {
		this.fornecedorFiltro = fornecedorFiltro;
	}

}
