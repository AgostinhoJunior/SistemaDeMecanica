package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sc.senac.br.sistemamecanica.dao.FornecedorDao;
import sc.senac.br.sistemamecanica.dao.IBaseDao;
import sc.senac.br.sistemamecanica.dao.PessoaDao;
import sc.senac.br.sistemamecanica.model.Fornecedor;
import sc.senac.br.sistemamecanica.model.Pessoa;
import sc.senac.br.sistemamecanica.util.MensagemUtil;

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

			MensagemUtil.addMensagemInfo("mensagem.cadastrosucesso");

		} else {
			pessoaDao.alterar(fornecedor.getPessoa());
			fornecedorDao.alterar(fornecedor);

			MensagemUtil.addMensagemInfo("mensagem.alteradosucesso");
		}

		limpar();
		buscar();
	}

	public void excluir() {
		fornecedorDao.excluir(fornecedor);
		limpar();
		buscar();

		MensagemUtil.addMensagemInfo("mensagem.deletadosucesso");
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
