package tsi.too.samuelwagner.operacoes;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.RendaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**Classe respons�vel por gerar o Gr�fico da Categoria.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class GeraGraficoCategoria {
	
	/**
	 * Construtor Default da classe <code>GeraGraficoCategoria</code>
	 */
	public GeraGraficoCategoria() {}

	/** Gera o gr�fico da categoria segundo o m�s informado.
	 * @param mesAno <code>String</code> m�s e ano.
	 * @param porcentagem <code>boolean</code> verifica se o gr�fico e exibido por porcentagem ou valor real.
	 * @return um <code>ChartPanel</code> retorna o gr�fico .
	 */
	public ChartPanel geraGraficoBarra(String mesAno, boolean porcentagem){
		String tipoGrafico;
		if(porcentagem)
			tipoGrafico = "Porcentagem";
		else
			tipoGrafico = "Valor Gasto por Categoria";
		JFreeChart chart = ChartFactory.createBarChart3D("Gr�fico Categoria", null, tipoGrafico,geraValoresDoGrafico(mesAno,porcentagem), PlotOrientation.VERTICAL,
				true, true,true);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(10, 33, 905, 367);
		return chartPanel;
	}
	
	/** Gera os dados de cada categoria.
	 * @param mesAno <code>String</code> m�s e ano.
	 * @param porcentagem <code>boolean</code> verifica se o gr�fico e exibido por porcentagem ou valor real.
	 * @return um <code>CategoryDataset</code> contendo os dados do Gr�fico.
	 */
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

	/**Obt�m o valor da Receita de um m�s determinado.
	 *  @param mesAno <code>String</code> m�s e ano.
	 * @return um <code>double</code> o valor total das receitas.
	 */
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
	
	/** Gera o valor gasto com as despesas de cada categoria e retorna em Porcentagem ou valor Real.
	 * @param despesas <code>Despesas</code> array de despesas.
	 * @param porcentagem <code>boolean</code> verifica se o valor ser� gerado por porcentagem ou valor real.
	 * @param valorReceita <code>double</code> valor da receita usado para a gera��o da porcentagem.
	 * @return um <code>double</code> com a porcentagem ou valor da despesa.
	 */
	private double geraValorDespesa(Despesa[] despesas, boolean porcentagem, double valorReceita){
		double valor = 0;
		for(Despesa despesa : despesas)
			valor += despesa.getValorDespesa();
		if(porcentagem){
				return valor/valorReceita*100;
		}
		else return valor;
	}
}
