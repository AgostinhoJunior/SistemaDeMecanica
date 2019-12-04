package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import sc.senac.br.sistemamecanica.exception.UsuarioInvalidoException;
import sc.senac.br.sistemamecanica.exception.UsuarioNaoEstaAtivo;
import sc.senac.br.sistemamecanica.model.Usuario;
import sc.senac.br.sistemamecanica.service.LoginService;
import sc.senac.br.sistemamecanica.util.MensagemUtil;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
	private Usuario usuarioLogado;

	private LoginService service;

	public LoginBean() {
		service = new LoginService();
	}

	public String login() throws UsuarioNaoEstaAtivo {

		try {

			usuarioLogado = service.verificaLogin(email, senha);
			return "/secured/dashboard.xhtml?faces-redirect=true";

		} catch (UsuarioInvalidoException ex) {
			MensagemUtil.addMensagemError("mensagem.emailinvalido");

			return "/login.xhtml";
		} catch (UsuarioNaoEstaAtivo ex) {
			MensagemUtil.addMensagemError("mensagem.usuarioinativo");
			return "/login.xhtml";
		}

	}

	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		session.invalidate();

		return "/login.xhtml?faces-redirect=true";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
