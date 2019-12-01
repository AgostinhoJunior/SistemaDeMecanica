package sc.senac.br.sistemamecanica.service;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import sc.senac.br.sistemamecanica.dao.UsuarioDao;
import sc.senac.br.sistemamecanica.exception.UsuarioInvalidoException;
import sc.senac.br.sistemamecanica.exception.UsuarioNaoEstaAtivo;
import sc.senac.br.sistemamecanica.model.Usuario;
import sc.senac.br.sistemamecanica.util.CriptografiaUtil;

public class LoginService {

	private UsuarioDao dao;

	public LoginService() {
		dao = new UsuarioDao();
	}

	public Usuario verificaLogin(String email, String senha) throws UsuarioInvalidoException, UsuarioNaoEstaAtivo {

		String senhaCriptografada = CriptografiaUtil.criptografar(senha);
		Usuario usuarioLogado = dao.verificaLogin(email, senhaCriptografada);

		if (usuarioLogado == null) {
			throw new UsuarioInvalidoException("Usuario invalido");
		}

		if (!verificaSeUsuarioEstaAtivo(usuarioLogado)) {
			throw new UsuarioNaoEstaAtivo("Usuario existe no banco mas esta inativo");
		}

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.setAttribute("usuarioLogado", usuarioLogado);

		return usuarioLogado;
	}

	public boolean verificaSeJaExisteNoBanco(Usuario usuario) {
		boolean existeNoBanco = false;

		List<Usuario> usuariosExistentes = dao.buscarTodos();
		for (int i = 0; i < usuariosExistentes.size(); i++) {
			Usuario usuarioDoBanco = usuariosExistentes.get(i);
			if (usuarioDoBanco.getEmail().equalsIgnoreCase(usuario.getEmail())) {
				existeNoBanco = true;
			}
		}
		return existeNoBanco;
	}

	private boolean verificaSeUsuarioEstaAtivo(Usuario usuario) {
		Usuario usuarioDoBanco = dao.buscarPorID(usuario.getCodigo());
		if (usuarioDoBanco != null) {
			return usuarioDoBanco.getAtivo();
		}
		return true;
	}
}
