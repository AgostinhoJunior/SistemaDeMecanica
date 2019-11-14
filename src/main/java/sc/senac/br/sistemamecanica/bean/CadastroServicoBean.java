package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;

import sc.senac.br.sistemamecanica.model.Servico;

public class CadastroServicoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Servico servico;

	
	
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}
