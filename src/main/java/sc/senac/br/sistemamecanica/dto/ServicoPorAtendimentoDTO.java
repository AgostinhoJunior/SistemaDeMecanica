package sc.senac.br.sistemamecanica.dto;

public class ServicoPorAtendimentoDTO {

	private String servico;

	private Long quantidadeDeAtendimentoPorServico;

	public ServicoPorAtendimentoDTO(Long quantidadeDeAtendimentoPorServico, String servico) {
		this.quantidadeDeAtendimentoPorServico = quantidadeDeAtendimentoPorServico;
		this.servico = servico;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Long getQuantidadeDeAtendimentoPorServico() {
		return quantidadeDeAtendimentoPorServico;
	}

	public void setQuantidadeDeAtendimentoPorServico(Long quantidadeDeAtendimentoPorServico) {
		this.quantidadeDeAtendimentoPorServico = quantidadeDeAtendimentoPorServico;
	}

}
