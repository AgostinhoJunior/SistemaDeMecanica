package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import sc.senac.br.sistemamecanica.dao.UsuarioDao;
import sc.senac.br.sistemamecanica.model.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;

	private UsuarioDao usuarioDao;

	public LoginBean() {
		usuarioDao = new UsuarioDao();
	}

	public String login() {
		Usuario usuarioLogado = usuarioDao.verificaLogin(email, senha);

		if (usuarioLogado == null) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail ou senha inválido!", null);

			FacesContext.getCurrentInstance().addMessage(null, mensagem);

			return "/login.xhtml";
		}

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		session.setAttribute("usuarioLogado", usuarioLogado);

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
