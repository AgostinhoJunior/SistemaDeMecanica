package sc.senac.br.sistemamecanica.exception;

public class UsuarioNaoEstaAtivo extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEstaAtivo(String message) {
		super(message);
	}
}
