package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import sc.senac.br.sistemamecanica.exception.UsuarioInvalidoException;
import sc.senac.br.sistemamecanica.model.Usuario;
import sc.senac.br.sistemamecanica.service.LoginService;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;

	private LoginService service;

	public LoginBean() {
		service = new LoginService();
	}

	public String login() {

		try {
			Usuario usuarioLogado = service.verificaLogin(email, senha);
			
		} catch (UsuarioInvalidoException ex) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha inválido!", null);

			FacesContext.getCurrentInstance().addMessage(null, mensagem);

			return "/login.xhtml";
		}
		
		return "/secured/dashboard.xhtml?faces-redirect=true";
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

}
