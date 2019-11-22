package sc.senac.br.sistemamecanica.dto;

public class CarroPorAtendimentoDTO {

	private String carro;

	private Long quantidadeDeCarroPorAtendimento;

	public CarroPorAtendimentoDTO(Long quantidadeDeCarroPorAtendimento, String carro) {
		this.quantidadeDeCarroPorAtendimento = quantidadeDeCarroPorAtendimento;
		this.carro = carro;
	}

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public Long getQuantidadeDeCarroPorAtendimento() {
		return quantidadeDeCarroPorAtendimento;
	}

	public void setQuantidadeDeCarroPorAtendimento(Long quantidadeDeCarroPorAtendimento) {
		this.quantidadeDeCarroPorAtendimento = quantidadeDeCarroPorAtendimento;
	}

}
