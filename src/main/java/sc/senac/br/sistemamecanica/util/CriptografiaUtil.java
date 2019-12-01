package sc.senac.br.sistemamecanica.util;

import org.apache.commons.codec.digest.Crypt;

import sc.senac.br.sistemamecanica.dao.UsuarioDao;
import sc.senac.br.sistemamecanica.model.Usuario;

public class CriptografiaUtil {

	private static final String SALT = "61bc845e72ce84e3e1a84b4b6d73086d";

	public static String criptografar(String texto) {
		return Crypt.crypt(texto, SALT);
	}

	public static void main(String[] args) {
		Usuario usuario = new Usuario();
		usuario.setAtivo(true);
		usuario.setEmail("admin");
		usuario.setNome("admin");
		usuario.setSenha("615Wal92G.9iU");
		UsuarioDao usuarioDao = new UsuarioDao();
		usuarioDao.salvar(usuario);
	}
}
