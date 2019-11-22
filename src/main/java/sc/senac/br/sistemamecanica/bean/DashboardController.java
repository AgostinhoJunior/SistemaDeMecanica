package sc.senac.br.sistemamecanica.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import sc.senac.br.sistemamecanica.dao.AtendimentoDao;
import sc.senac.br.sistemamecanica.dao.CarroDao;
import sc.senac.br.sistemamecanica.dto.CarroPorAtendimentoDTO;
import sc.senac.br.sistemamecanica.dto.ServicoPorAtendimentoDTO;

@ManagedBean
public class DashboardController implements Serializable {

	private static final long serialVersionUID = 1L;

	private AtendimentoDao atendimentoDao;
	private CarroDao carroDao;
	private List<ServicoPorAtendimentoDTO> servicoPorAtendimentoDTOs;
	private List<CarroPorAtendimentoDTO> carrosPorAtendimentoDTOs;

	private PieChartModel servicosPorAtendimentoModel;

	private PieChartModel carrosPorAtendimentoModel;

	private final List<String> cores = Arrays.asList("#ff8a73", "#e8cd74", "#74d5e8", "#a2ff8c", "#c8a3ff");

	public DashboardController() {
		atendimentoDao = new AtendimentoDao();
		carroDao = new CarroDao();
		servicoPorAtendimentoDTOs = atendimentoDao.buscaServicosPorAtendimento();
		carrosPorAtendimentoDTOs = carroDao.buscaCarrosPorAtendimento();
		inicializarGraficoServicosPorAtendimento();
		inicializarGraficoCarrosPorAtendimento();
	}

	private void inicializarGraficoServicosPorAtendimento() {
		servicosPorAtendimentoModel = new PieChartModel();

		List<Number> valores = new ArrayList<>();
		List<String> rotulos = new ArrayList<>();

		for (ServicoPorAtendimentoDTO dto : servicoPorAtendimentoDTOs) {
			rotulos.add(dto.getServico());
			valores.add((Number) dto.getQuantidadeDeAtendimentoPorServico());
		}

		PieChartDataSet dataset = new PieChartDataSet();
		dataset.setData(valores);
		dataset.setBackgroundColor(cores);

		ChartData dados = new ChartData();
		dados.addChartDataSet(dataset);
		dados.setLabels(rotulos);

		servicosPorAtendimentoModel.setData(dados);

	}

	private void inicializarGraficoCarrosPorAtendimento() {
		carrosPorAtendimentoModel = new PieChartModel();

		List<Number> valores = new ArrayList<>();
		List<String> rotulos = new ArrayList<>();

		for (CarroPorAtendimentoDTO dto : carrosPorAtendimentoDTOs) {
			rotulos.add(dto.getCarro());
			valores.add((Number) dto.getQuantidadeDeCarroPorAtendimento());
		}

		PieChartDataSet dataset = new PieChartDataSet();
		dataset.setData(valores);
		dataset.setBackgroundColor(cores);

		ChartData dados = new ChartData();
		dados.addChartDataSet(dataset);
		dados.setLabels(rotulos);

		servicosPorAtendimentoModel.setData(dados);

	}

	public PieChartModel getServicosPorAtendimentoModel() {
		return servicosPorAtendimentoModel;
	}

	public PieChartModel getCarrosPorAtendimentoModel() {
		return carrosPorAtendimentoModel;
	}

	public void setCarrosPorAtendimentoModel(PieChartModel carrosPorAtendimentoModel) {
		this.carrosPorAtendimentoModel = carrosPorAtendimentoModel;
	}

}
