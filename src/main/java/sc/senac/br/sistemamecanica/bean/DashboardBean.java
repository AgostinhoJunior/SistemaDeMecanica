package sc.senac.br.sistemamecanica.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import sc.senac.br.sistemamecanica.dao.AtendimentoDao;
import sc.senac.br.sistemamecanica.dao.IBaseDao;
import sc.senac.br.sistemamecanica.dao.ServicoDao;
import sc.senac.br.sistemamecanica.model.Atendimento;
import sc.senac.br.sistemamecanica.model.Servico;

@ManagedBean
public class DashboardBean {

	private List<Atendimento> atendimentos;
	private List<Servico> servicos;
	private IBaseDao<Atendimento> atendimentoDao;
	private IBaseDao<Servico> servicoDao;
	private PieChartModel pieModel;

	@PostConstruct
	public void init() {
		atendimentos = new ArrayList<>();
		atendimentoDao = new AtendimentoDao();
		servicoDao = new ServicoDao();
		atendimentos = atendimentoDao.buscarTodos();
		servicos = servicoDao.buscarTodos();
		criandoPieModel();
	}

	private void criandoPieModel() {
		pieModel = new PieChartModel();
		ChartData data = new ChartData();

		PieChartDataSet dataSet = new PieChartDataSet();
		List<Number> values = new ArrayList<>();
		for (int i = 0; i < atendimentos.size(); i++) {
			Atendimento atendimento = atendimentos.get(i);
			values.add(atendimento.getServicos().size());
		}
		dataSet.setData(values);

		List<String> cores = Arrays.asList("#ff8a73", "#e8cd74", "#74d5e8", "#a2ff8c", "#c8a3ff");

		dataSet.setBackgroundColor(cores);

		data.addChartDataSet(dataSet);
		List<String> labels = new ArrayList<>();
		for (int i = 0; i < servicos.size(); i++) {
			Servico servico = servicos.get(i);
			labels.add(servico.getDescricao());
		}
		data.setLabels(labels);
		pieModel.setData(data);
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
}
