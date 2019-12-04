package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import sc.senac.br.sistemamecanica.bean.lazymodel.UsuarioLazyModel;
import sc.senac.br.sistemamecanica.dao.IBaseDao;
import sc.senac.br.sistemamecanica.dao.UsuarioDao;
import sc.senac.br.sistemamecanica.model.Usuario;
import sc.senac.br.sistemamecanica.service.LoginService;
import sc.senac.br.sistemamecanica.util.CriptografiaUtil;
import sc.senac.br.sistemamecanica.util.MensagemUtil;

@ViewScoped
@ManagedBean
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Usuario> usuariosFiltro;
	private IBaseDao<Usuario> usuarioDao;
	private UsuarioDao usuarioDaoLazy;
	private LoginService loginService;

	private LazyDataModel<Usuario> model;

	@PostConstruct
	public void init() {
		usuarioDaoLazy = new UsuarioDao();
		usuarioDao = new UsuarioDao();
		usuario = new Usuario();
		loginService = new LoginService();
		limpar();
		buscar();
	}

	public void salvar() {
		if (usuario.getCodigo() == null) {

			if (loginService.verificaSeJaExisteNoBanco(usuario)) {

				MensagemUtil.addMensagemError("mensagem.usuariojaexistenobanco");
				return;
			}

			String senhaCriptografada = CriptografiaUtil.criptografar(usuario.getSenha());
			usuario.setSenha(senhaCriptografada);

			usuarioDao.salvar(usuario);

			MensagemUtil.addMensagemInfo("mensagem.cadastrosucesso");

		} else {
			usuarioDao.alterar(usuario);

			MensagemUtil.addMensagemInfo("mensagem.alteradosucesso");
		}

		limpar();
		buscar();
	}

	public void excluir() {
		usuarioDao.excluir(usuario);
		limpar();
		buscar();

		MensagemUtil.addMensagemInfo("mensagem.deletadosucesso");
	}

	public void limpar() {
		usuario = new Usuario();
		usuarios = new ArrayList<>();
	}

	public void buscar() {
		model = new UsuarioLazyModel(usuarioDaoLazy);
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

	public LazyDataModel<Usuario> getModel() {
		return model;
	}

	public void setModel(LazyDataModel<Usuario> model) {
		this.model = model;
	}

}
