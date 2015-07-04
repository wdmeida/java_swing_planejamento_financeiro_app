package tsi.too.samuelwagner.operacoes;

import javax.swing.JProgressBar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.RendaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

public class GeraGraficoCategoria {
	
	public GeraGraficoCategoria() {}

	public ChartPanel geraGraficoBarra(String mesAno, boolean porcentagem){
		String tipoGrafico;
		if(porcentagem)
			tipoGrafico = "Porcentagem";
		else
			tipoGrafico = "Valor Gasto por Categoria";
		JFreeChart chart = ChartFactory.createBarChart3D("Gráfico Categoria", null, tipoGrafico,geraValoresDoGrafico(mesAno,porcentagem), PlotOrientation.VERTICAL,
				true, true,true);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(10, 33, 905, 367);
		return chartPanel;
	}
	
	private CategoryDataset geraValoresDoGrafico(String mesAno,boolean porcentagem) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			
		if(mesAno != null){
		
			String nomesCategorias[] = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().obterNomesCategorias();
			
			double valorReceita = obtemValorReceita(mesAno);
			
			for(String categoria : nomesCategorias){
				Despesa[] despesas = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().
						pesquisarDespesas(GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().pesquisaCategoria(categoria)+1, 
								FuncaoAuxiliar.converteDataParaCalendar(mesAno));
	
				if(despesas != null)
					dataset.addValue(geraValorDespesa(despesas,porcentagem,valorReceita), categoria, "Categorias");
				else
					dataset.addValue(0.0, categoria, "Categorias");
			}
		}
		return dataset;
	}

	public static double obtemValorReceita(String mesAno){
		double receita = 0;
		int numeroReceita = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRendaMensal().numerosDeRendas();
		for(int indice = 0; indice < numeroReceita;indice++){
			RendaMensal rendaMensal = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRendaMensal().obtemRendaMensal(indice);
			if(FuncaoAuxiliar.comparaString(mesAno, FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(rendaMensal.getData(), true))))
				receita += rendaMensal.getValor();
		}
		return receita;
	}
	
	private double geraValorDespesa(Despesa[] despesas, boolean porcentagem, double valorReceita){
		double valor = 0;
		for(Despesa despesa : despesas)
			valor += despesa.getValorDespesa();
		if(porcentagem){
			System.out.println(valor/valorReceita*100);
			return valor/valorReceita*100;
		}
			
		else return valor;
		
	}
}
