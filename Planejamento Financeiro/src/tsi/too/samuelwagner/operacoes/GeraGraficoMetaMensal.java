package tsi.too.samuelwagner.operacoes;

import java.awt.Color;
import java.util.Calendar;

import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import tsi.too.samuelwagner.gui.IgGraficoMetaMensal;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.MetaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/** Classe reponsavel pela crianção do Grafico de Metas.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class GeraGraficoMetaMensal {
	private MetaMensal meta;
	private Despesa[] despesas;
	private IgGraficoMetaMensal igGraficoMetaMensal;
	
	/**Contrutor default da classe <code>GeraGraficoMetaMensal</code>
	 * @param igGraficoMetaMensal <code>IgGraficoMetaMensal</code>
	 */
	public GeraGraficoMetaMensal(IgGraficoMetaMensal igGraficoMetaMensal) {
		meta = null;
		despesas = null;
		this.igGraficoMetaMensal = igGraficoMetaMensal;
	}

	/**Método responsavel pela criação do Gráfico.
	 * @param categoria <code>String</code> o nome da categoria procurada.
	 * @param mesAno <code>String</code> a data da criação da meta.
	 * @return um <code>ChartPanel</code> contendo o gráfico gerado.
	 */
	public ChartPanel geraGraficoMetaMensal(String categoria,String mesAno){
		if(mesAno != null && categoria != null)
				obtemMetaMesalEDespesas(categoria,mesAno);
		
		XYSeries series = new XYSeries("");
		
		if(meta != null)
			obtemValoresDoGrafico(categoria,series);
			
			XYSeriesCollection dados = new XYSeriesCollection(series);
			
			JFreeChart chart = ChartFactory.createXYLineChart("Metas Categorias", "Valor Gasto", "Porcentagem", dados, PlotOrientation.VERTICAL, true, true, false);  
			  		
		
		return new ChartPanel(chart);
	}//geraGrafico()
	
	/**Esse Método obtém os valores que serão utilizados no gráfico. 
	 * @param nomeCategoria <code>String</code>.
	 * @param series <code>XYSeries</code>.
	 */
	private void obtemValoresDoGrafico(String nomeCategoria, XYSeries series){
		series.setDescription("Valor Gasto");
		double valorTotalGasto = 0,
			   porcentagem;
		series.add(0, 0);
		if(despesas == null)return;
		
		for(Despesa despesa : despesas)
			valorTotalGasto += despesa.getValorDespesa();
		
		porcentagem = valorTotalGasto/meta.getValor()*100;
		
		if(porcentagem >= meta.getPorcentagem())
			igGraficoMetaMensal.getValorGastoLabel().setBorder(new LineBorder(Color.YELLOW));
		else
			if(porcentagem >= 100)
				igGraficoMetaMensal.getValorGastoLabel().setBorder(new LineBorder(Color.YELLOW));
		
		igGraficoMetaMensal.getValorGastoLabel().setText(String.format("R$ %1.2f", valorTotalGasto));
		igGraficoMetaMensal.getCategoriaField().setText(nomeCategoria);
		igGraficoMetaMensal.getMetaField().setText(String.format("R$ %1.2f",meta.getValor()));
		igGraficoMetaMensal.getValorRestanteField().setText(String.format("R$ %1.2f",meta.getValor() - valorTotalGasto));
		
		series.add(valorTotalGasto, porcentagem);
		
	}
	
	/**Esse metodo obtém a meta e as despesas associada a ela.
	 * @param nomeCategoria <code>String</code>.
	 * @param mesAno <code>String</code>.
	 */
	private void obtemMetaMesalEDespesas(String nomeCategoria,String mesAno) {
		
		//Obtém o código da categoria.
		int codigoCategoria = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().pesquisaCategoria(nomeCategoria);
		
		//Verifica se achou.
		if(codigoCategoria != -1){
			//Obtém o código.
			codigoCategoria = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().obtemCategoria(codigoCategoria).getCodigo();
			
			//Obtém todas as despesas com o código do corrente mês.
			despesas = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().pesquisarDespesas(codigoCategoria, FuncaoAuxiliar.converteDataParaCalendar(mesAno));
			
			//Verifica se encontro as despesas.
			if(despesas != null){
				//Caso tenha encontrado, obtém a meta deste mês.
				meta = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleMetaMensal().pesquisarMetaMensal(codigoCategoria, Calendar.getInstance());
				
			}
		}
	}//carregarPainelGrafico()	
	
}
