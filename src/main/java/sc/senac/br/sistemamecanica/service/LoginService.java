package sc.senac.br.sistemamecanica.service;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import sc.senac.br.sistemamecanica.dao.UsuarioDao;
import sc.senac.br.sistemamecanica.exception.UsuarioInvalidoException;
import sc.senac.br.sistemamecanica.model.Usuario;
import sc.senac.br.sistemamecanica.util.CriptografiaUtil;

public class LoginService {

	private UsuarioDao dao;

	public LoginService() {
		dao = new UsuarioDao();
	}

	public Usuario verificaLogin(String email, String senha) throws UsuarioInvalidoException {

		String senhaCriptografada = CriptografiaUtil.criptografar(senha);
		Usuario usuarioLogado = dao.verificaLogin(email, senhaCriptografada);

		if (usuarioLogado == null) {
			throw new UsuarioInvalidoException();
		}

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("usuarioLogado", usuarioLogado);

		return usuarioLogado;
	}
}
