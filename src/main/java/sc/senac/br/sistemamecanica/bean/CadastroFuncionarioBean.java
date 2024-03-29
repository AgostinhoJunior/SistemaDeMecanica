package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sc.senac.br.sistemamecanica.dao.FuncionarioDao;
import sc.senac.br.sistemamecanica.dao.IBaseDao;
import sc.senac.br.sistemamecanica.dao.PessoaDao;
import sc.senac.br.sistemamecanica.model.Funcionario;
import sc.senac.br.sistemamecanica.model.Pessoa;
import sc.senac.br.sistemamecanica.util.MensagemUtil;

@ViewScoped
@ManagedBean
public class CadastroFuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Funcionario> funcionariosFiltro;
	private IBaseDao<Funcionario> funcionarioDao;
	private IBaseDao<Pessoa> pessoaDao;

	@PostConstruct
	public void init() {
		funcionarioDao = new FuncionarioDao();
		pessoaDao = new PessoaDao();
		limpar();
		buscar();
	}

	public void salvar() {
		if (funcionario.getCodigo() == null) {
			pessoaDao.salvar(funcionario.getPessoa());
			funcionarioDao.salvar(funcionario);

			MensagemUtil.addMensagemInfo("mensagem.cadastrosucesso");

		} else {
			pessoaDao.alterar(funcionario.getPessoa());
			funcionarioDao.alterar(funcionario);

			MensagemUtil.addMensagemInfo("mensagem.alteradosucesso");
		}

		limpar();
		buscar();
	}

	public void excluir() {
		funcionarioDao.excluir(funcionario);
		limpar();
		buscar();

		MensagemUtil.addMensagemInfo("mensagem.deletadosucesso");
	}

	public void limpar() {
		funcionario = new Funcionario();
		funcionario.setPessoa(new Pessoa());
		funcionarios = new ArrayList<>();
	}

	public void buscar() {
		funcionarios = funcionarioDao.buscarTodos();
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Funcionario> getFuncionariosFiltro() {
		return funcionariosFiltro;
	}

	public void setFuncionariosFiltro(List<Funcionario> funcionariosFiltro) {
		this.funcionariosFiltro = funcionariosFiltro;
	}

	public IBaseDao<Funcionario> getFuncionarioDao() {
		return funcionarioDao;
	}

	public void setFuncionarioDao(IBaseDao<Funcionario> funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}

}
