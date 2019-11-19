package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import sc.senac.br.sistemamecanica.dao.AtendimentoDao;
import sc.senac.br.sistemamecanica.dao.CarroDao;
import sc.senac.br.sistemamecanica.dao.ClienteDao;
import sc.senac.br.sistemamecanica.dao.IBaseDao;
import sc.senac.br.sistemamecanica.dao.PessoaDao;
import sc.senac.br.sistemamecanica.model.Atendimento;
import sc.senac.br.sistemamecanica.model.Carro;
import sc.senac.br.sistemamecanica.model.Cliente;
import sc.senac.br.sistemamecanica.model.Pessoa;

@ManagedBean
@ViewScoped
public class CadastroAtendimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private IBaseDao<Atendimento> atendimentoDao;
	private IBaseDao<Cliente> clienteDao;
	private IBaseDao<Pessoa> pessoaDao;
	private IBaseDao<Carro> carroDao;

	private Atendimento atendimento;
	private Cliente cliente;

	private List<Atendimento> atendimentos;
	private List<Atendimento> atendimentosFiltro;

	@PostConstruct
	public void init() {
		atendimentoDao = new AtendimentoDao();
		clienteDao = new ClienteDao();
		pessoaDao = new PessoaDao();
		carroDao = new CarroDao();
		limpar();
		buscar();
	}

	public void salvar() {
		if (atendimento.getCodigo() == null) {

			carroDao.salvar(atendimento.getCliente().getCarro());
			pessoaDao.salvar(atendimento.getCliente().getPessoa());
			clienteDao.salvar(atendimento.getCliente());

			atendimentoDao.salvar(atendimento);

			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			mensagem.setSummary("Atendimento salvo com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, mensagem);

		} else {
			carroDao.salvar(atendimento.getCliente().getCarro());
			pessoaDao.salvar(atendimento.getCliente().getPessoa());
			clienteDao.salvar(atendimento.getCliente());
			
			atendimentoDao.alterar(atendimento);

			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			mensagem.setSummary("Atendimento alterado com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		}

		limpar();
		buscar();
	}

	public void excluir() {
		atendimentoDao.excluir(atendimento);
		limpar();
		buscar();

		FacesMessage mensagem = new FacesMessage();
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		mensagem.setSummary("Atendimento excluído com sucesso!");

		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public void limpar() {
		atendimento = new Atendimento();
		atendimento.setCliente(new Cliente());
		atendimento.getCliente().setPessoa(new Pessoa());
		atendimento.getCliente().setCarro(new Carro());
		atendimentos = new ArrayList<>();
	}

	public void buscar() {
		atendimentos = atendimentoDao.buscarTodos();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public IBaseDao<Atendimento> getAtendimentoDao() {
		return atendimentoDao;
	}

	public void setAtendimentoDao(IBaseDao<Atendimento> atendimentoDao) {
		this.atendimentoDao = atendimentoDao;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public List<Atendimento> getAtendimentosFiltro() {
		return atendimentosFiltro;
	}

	public void setAtendimentosFiltro(List<Atendimento> atendimentosFiltro) {
		this.atendimentosFiltro = atendimentosFiltro;
	}

}
