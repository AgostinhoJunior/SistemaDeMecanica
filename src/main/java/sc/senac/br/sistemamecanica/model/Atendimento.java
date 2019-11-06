package sc.senac.br.sistemamecanica.model;

import java.util.List;

public class Atendimento {

	private Long codigo;
	
	private Cliente cliente;
	
	private List<Servico> servico;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Servico> getServico() {
		return servico;
	}

	public void setServico(List<Servico> servico) {
		this.servico = servico;
	}
	
	
}
