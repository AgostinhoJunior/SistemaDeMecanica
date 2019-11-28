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
import sc.senac.br.sistemamecanica.dao.UsuarioDao;
import sc.senac.br.sistemamecanica.model.Usuario;

@ViewScoped
@ManagedBean
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosFiltro;
	private IBaseDao<Usuario> usuarioDao;

	@PostConstruct
	public void init() {
		usuarioDao = new UsuarioDao();
		limpar();
		buscar();
	}

	public void salvar() {
		if (usuario.getCodigo() == null) {
			usuarioDao.salvar(usuario);

			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			mensagem.setSummary("Usuario salvo com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, mensagem);

		} else {
			usuarioDao.alterar(usuario);

			FacesMessage mensagem = new FacesMessage();
			mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
			mensagem.setSummary("Usuario alterado com sucesso!");

			FacesContext.getCurrentInstance().addMessage(null, mensagem);
		}

		limpar();
		buscar();
	}

	public void excluir() {
		usuarioDao.excluir(usuario);
		limpar();
		buscar();

		FacesMessage mensagem = new FacesMessage();
		mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
		mensagem.setSummary("Usuario excluido com sucesso!");

		FacesContext.getCurrentInstance().addMessage(null, mensagem);
	}

	public void limpar() {
		usuario = new Usuario();
		usuarios = new ArrayList<>();
	}

	public void buscar() {
		usuarios = usuarioDao.buscarTodos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosFiltro() {
		return usuariosFiltro;
	}

	public void setUsuariosFiltro(List<Usuario> usuariosFiltro) {
		this.usuariosFiltro = usuariosFiltro;
	}

}
