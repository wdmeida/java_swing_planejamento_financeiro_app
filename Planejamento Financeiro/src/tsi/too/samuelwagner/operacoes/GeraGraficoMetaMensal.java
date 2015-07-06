package tsi.too.samuelwagner.operacoes;

import java.awt.Color;
import java.util.Calendar;

import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import tsi.too.samuelwagner.gui.IgGraficoMetaMensal;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.MetaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/** Classe reponsável pela crianção do Grafico de Metas.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class GeraGraficoMetaMensal {
	private MetaMensal meta;
	private Despesa[] despesas;
	private IgGraficoMetaMensal igGraficoMetaMensal;
	private double valorTotalGasto;
	
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
		//Verifica se as String contem conteudo.
		if(mesAno != null && categoria != null)
				obtemMetaMesalEDespesas(categoria,mesAno);
		
		//Cria DataBase do grafico.
		DefaultCategoryDataset ds = new DefaultCategoryDataset();  
		
		//Vefirica se a meta está instaciada.
		if(meta != null){
			//Obtém o valor Gasto segundo a categoria.
			obtemValoresDoGrafico(categoria);
			//Difine os valores do grafico;
			ds.addValue( 0, "Valor Gasto", "Valor Inicial" );  
			ds.addValue(meta.getPorcentagem(), "Porcentagem Limite", "Valor Inicial");
			ds.addValue(meta.getPorcentagem(), "Porcentagem Limite", "Valor Final");
			ds.addValue(100, "Porcentagem Final", "Valor Inicial");
			ds.addValue(100, "Porcentagem Final", "Valor Final");
			ds.addValue((valorTotalGasto/meta.getValor()*100), "Valor Gasto", "Valor Final" ); 
		}
			//Cria o Grafico.
			JFreeChart chart = ChartFactory.createLineChart("Metas Categorias",null, "Porcentagem", ds, PlotOrientation.VERTICAL, true, true, true);  
			//Obtem referencia do CategoryItemRenderer para alterar as cores das retas.
			CategoryItemRenderer renderer = chart.getCategoryPlot().getRenderer();  
			renderer.setBaseItemLabelsVisible(false);
	        renderer.setSeriesPaint( 0,Color.BLACK );  
	        renderer.setSeriesPaint( 1, Color.YELLOW );  
	        renderer.setSeriesPaint( 2, Color.RED ); 
		
	        return  new ChartPanel(chart);
	}//geraGrafico()
	
	/**Esse Método obtém os valores que serão utilizados no gráfico. 
	 * @param nomeCategoria <code>String</code>.
	 * @param series <code>XYSeries</code>.
	 */
	private void obtemValoresDoGrafico(String nomeCategoria){
		//Inicia o valor gasto com 0;
		valorTotalGasto = 0;
		double  porcentagem;
		//Verfica se o Array de despesa contem alguma despesa.
		if(despesas == null)return;
		
		//Percorre o array de despesas somando os gastos das despesas.
		for(Despesa despesa : despesas)
			valorTotalGasto += despesa.getValorDespesa();
		
		//obtem a porcentagem 
		porcentagem = valorTotalGasto/meta.getValor()*100;
		
		//Verifica se Passou dos 100%.
		if(porcentagem >= 100)
			igGraficoMetaMensal.getValorGastoLabel().setBorder(new LineBorder(Color.RED));
		else
			//Verifica se passou do limite estabelecido.
			if(porcentagem >= meta.getPorcentagem())
				igGraficoMetaMensal.getValorGastoLabel().setBorder(new LineBorder(Color.YELLOW));
			
		
		//Atribui os valores obtidos ao painel.
		igGraficoMetaMensal.getValorGastoLabel().setText(String.format("R$ %1.2f", valorTotalGasto));
		igGraficoMetaMensal.getCategoriaField().setText(nomeCategoria);
		igGraficoMetaMensal.getMetaField().setText(String.format("R$ %1.2f",meta.getValor()));
		igGraficoMetaMensal.getValorRestanteField().setText(String.format("R$ %1.2f",meta.getValor() - valorTotalGasto));
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
