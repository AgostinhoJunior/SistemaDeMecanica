package sc.senac.br.sistemamecanica.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "atendimento")
public class Atendimento implements IBaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@OneToOne(cascade = CascadeType.REMOVE)
	private Cliente cliente;

	@OneToMany(mappedBy = "atendimento", fetch = FetchType.EAGER)
	private List<AtendimentoServico> atendimentoServicos;

	private Double valorTotal;

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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<AtendimentoServico> getAtendimentoServicos() {
		return atendimentoServicos;
	}

	public void setAtendimentoServicos(List<AtendimentoServico> atendimentoServicos) {
		this.atendimentoServicos = atendimentoServicos;
	}

}
