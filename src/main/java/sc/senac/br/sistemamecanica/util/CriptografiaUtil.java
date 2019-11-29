package sc.senac.br.sistemamecanica.util;

import org.apache.commons.codec.digest.Crypt;

public class CriptografiaUtil {

	private static final String SALT = "f2e35273c33ac8f63700fbad00e0c901";

	public static String criptografar(String texto) {
		return Crypt.crypt(texto, SALT);
	}

	public static void main(String[] args) {
		System.out.println(criptografar("admin" + SALT));
	}
}
