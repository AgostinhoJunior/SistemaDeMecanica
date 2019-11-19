package sc.senac.br.sistemamecanica.dto;

public class ServicosPorAtendimentoDTO {

	private String servico;

	private Long quantidadeServicos;

	public ServicosPorAtendimentoDTO(String servico, Long quantidadeServicos) {
		this.servico = servico;
		this.quantidadeServicos = quantidadeServicos;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Long getQuantidadeServicos() {
		return quantidadeServicos;
	}

	public void setQuantidadeServicos(Long quantidadeServicos) {
		this.quantidadeServicos = quantidadeServicos;
	}

}
