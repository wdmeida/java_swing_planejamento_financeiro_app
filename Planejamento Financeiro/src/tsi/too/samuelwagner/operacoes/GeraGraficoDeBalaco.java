package tsi.too.samuelwagner.operacoes;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import tsi.too.samuelwagner.controle.ControleRendaMensal;
import tsi.too.samuelwagner.enumeracoes.TipoPagamento;
import tsi.too.samuelwagner.enumeracoes.TituloJanela;
import tsi.too.samuelwagner.gui.IgGraficoBalacoMensal;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.RendaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**Classe responsavel pela criação dos gráficos <code>Balanço Mensal</code> e <code>Balanço de Pagamento</code>.
 * @author Samuel
 * @author Wagner.
 *
 */
public class GeraGraficoDeBalaco {
	private  double valorGastos,
				   valorInvestimentos,
				   valorReceita,
				   valorCartao,
				   valorVista,
				   valorCrediario,
				   valorCheque,
				   valorFinaciameto,
				   valorParcelado;
	
	/**Método responsavel de gerar o gráfico.
	 * @param mesAno <code>String</code>.
	 * @param igGrafico <code>IgGraficoBalacoMensal</code>.
	 * @param tituloJanela <code>TituloJanela</code>.
	 * @return um <code>ChartPanel</code> com o gráfico gerado.
	 */
	public  ChartPanel geraGraficoPanel(String mesAno,IgGraficoBalacoMensal igGrafico,TituloJanela tituloJanela ) {
		 if(tituloJanela.getTitulo().equalsIgnoreCase(TituloJanela.IG_GRAFICO.getTitulo()))
			 return geraGraficoBalancoMesal(mesAno,igGrafico,tituloJanela);
		 else
			 if(tituloJanela.getTitulo().equalsIgnoreCase(TituloJanela.IG_GRAFICO_DETALHE.getTitulo()))
				 return geraGraficoBalancoPagamento(mesAno,igGrafico,tituloJanela);
		 return null;
	 }
	 
	/**Método responsavel por gera o grafico Balanco Pagamento.
	 * @param mesAno <code>String</code>.
	 * @param igGrafico <code>IgGraficoBalacoMensal</code>.
	 * @param tituloJanela <code>TituloJanela</code>.
	 * @return um <code>ChartPanel</code>.
	 */
	private ChartPanel geraGraficoBalancoPagamento(String mesAno,IgGraficoBalacoMensal igGrafico, TituloJanela tituloJanela) {
		//Cria a base da dados do Grafico.
		 DefaultPieDataset dados = new DefaultPieDataset();
		 
		 geraValorGastosPagamento(mesAno);
		 
		 //Define os Dados do Grafico.
		 dados.setValue(TipoPagamento.CARTAO.getTipoPagamento(), valorCartao);
		 dados.setValue(TipoPagamento.A_VISTA.getTipoPagamento(), valorVista);
		 dados.setValue(TipoPagamento.CREDIARIO.getTipoPagamento(), valorCrediario);
		 dados.setValue(TipoPagamento.CHEQUE.getTipoPagamento(), valorCheque);
		 dados.setValue(TipoPagamento.FINANCIAMENTO.getTipoPagamento(), valorFinaciameto);
		 dados.setValue(TipoPagamento.PARCELADO.getTipoPagamento(), valorParcelado);
		 
		////Cria o Grafico com os Dados definidos;
		JFreeChart chart = ChartFactory.createPieChart3D(tituloJanela.getTitulo(), dados,true,true,true);
		
		//Obtem a referencia do Grafico para definir a transparencia. 
		PiePlot3D plot = (PiePlot3D) chart.getPlot ();
		plot.setForegroundAlpha(0.50F);
		
		//Define os valores do Campos.
		igGrafico.getCartaoField().setText(String.format("R$ %1.2f", valorCartao));
		igGrafico.getVistaField().setText(String.format("R$ %1.2f", valorVista));
		igGrafico.getCrediarioField().setText(String.format("R$ %1.2f", valorCrediario));
		igGrafico.getChequeField().setText(String.format("R$ %1.2f", valorCheque));
		igGrafico.getFinaciamentoField().setText(String.format("R$ %1.2f", valorFinaciameto));
		igGrafico.getParceladoField().setText(String.format("R$ %1.2f", valorParcelado));
		
		// adiciona o chart a um ChartPanel que é um painel
		ChartPanel chartPanel = new ChartPanel (chart);
		
		finaliza();
		dados = null;
		chart = null;
		plot = null;
		
		chartPanel.setSize(igGrafico.getGraficoBalacoMesalPanel().getWidth(), igGrafico.getGraficoBalacoMesalPanel().getHeight());
		
		return chartPanel;
	}
	
	/**Método responsavel por gera o grafico Balanco Mensal.
	 * @param mesAno <code>String</code>.
	 * @param igGrafico <code>IgGraficoBalacoMensal</code>.
	 * @param tituloJanela <code>TituloJanela</code>.
	 * @return um <code>ChartPanel</code>.
	 */
	private ChartPanel geraGraficoBalancoMesal(String mesAno,IgGraficoBalacoMensal igGrafico,TituloJanela tituloJanela){
		 //Cria a base da dados do Grafico.
		 DefaultPieDataset dados = new DefaultPieDataset();
		 
		 //Gera os valores a serem usados.
		 geraValorReceita(mesAno);
		 geraValorGastosEInvestimentos(mesAno);
		 
		 //Define os Dados do Grafico.
		 dados.setValue("Receita", valorReceita);
		 dados.setValue("Investimentos", valorInvestimentos);
		 dados.setValue("Gastos", valorGastos);
		 dados.setValue("Saldo Atual", geraSaldoAtual());
		 
		////Cria o Grafico com os Dados definidos;
		JFreeChart chart = ChartFactory.createPieChart3D("Balanço Gráfico", dados,true,true,true);
		
		//Obtem a referencia do Grafico para definir a transparencia. 
		PiePlot3D plot = (PiePlot3D) chart.getPlot ();
		plot.setForegroundAlpha(0.50F);
		
		//Define os valores do Campos.
		igGrafico.getReceitaField().setText(String.format("R$ %1.2f", valorReceita));
		igGrafico.getInvestimentoField().setText(String.format("R$ %1.2f", valorInvestimentos));
		igGrafico.getGastosField().setText(String.format("R$ %1.2f", valorGastos));
		igGrafico.getSaldoAtualField().setText(String.format("R$ %1.2f", geraSaldoAtual()));
		
		
		// adiciona o chart a um ChartPanel que é um painel
		ChartPanel chartPanel = new ChartPanel (chart);
		
		//Reseta as variaveis;
		finaliza();
		dados = null;
		chart = null;
		plot = null;
		
		chartPanel.setSize(igGrafico.getGraficoBalacoMesalPanel().getWidth(), igGrafico.getGraficoBalacoMesalPanel().getHeight());
		
		return chartPanel;
	 }
	
	/** Método responsavel por gerar os valores gastos pelo Investimentos e damais despesas.
	 * @param mesAno <code>String</code>.
	 */
	private void geraValorGastosEInvestimentos(String mesAno){
		 if(mesAno == null || mesAno.equals("")) return;
		 Despesa despesas[] = GerenciamentoDeFinanca.getGerenciamentoFincanca().
				 getControleDespesa().pesquisarDespesas(FuncaoAuxiliar.converteDataParaCalendar(mesAno));
		
		if(despesas == null) return;
		
		final String investimento = "Investimentos";
		
		 for(Despesa despesa : despesas){
			 //Obtem a referencia do objeto despesa do arquivo.
			//Verifica se a despesa e da data procurada.
			if(FuncaoAuxiliar.comparaString(mesAno, FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(despesa.getDataDespesa(), true)))){
				//obtem a descrição da da categoria da despesa;
				String descricao = obtemDescricaoCategoria(despesa.getCodigoCategoria());
				if(descricao == null) return;
				//Compara a descrisao da categoria.
				if(FuncaoAuxiliar.comparaString(investimento, descricao))
					valorInvestimentos += despesa.getValorDespesa();
				else
					valorGastos += despesa.getValorDespesa();
			}
				
		 }
		 
		 despesas = null;
		 
	 }
	
	/** Método responsavel por gerar os valores gastos por todos os tipos de pagamento.
	 * @param mesAno <code>String</code>.
	 */
	private void geraValorGastosPagamento(String mesAno){
		 if(mesAno == null || mesAno.equals("")) return;
		 Despesa despesas[] = GerenciamentoDeFinanca.getGerenciamentoFincanca().
				 getControleDespesa().pesquisarDespesas(FuncaoAuxiliar.converteDataParaCalendar(mesAno));
		 
		 if(despesas == null) return;
		 
		 for(Despesa despesa : despesas){

			//Verifica se a despesa e da data procurada.
			if(FuncaoAuxiliar.comparaString(mesAno, FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(despesa.getDataDespesa(), true)))){

				if(FuncaoAuxiliar.comparaString(TipoPagamento.A_VISTA.getTipoPagamento(),obtemDescricaoTipoPagamento(despesa.getCodigoPagamento())))
					valorVista += despesa.getValorDespesa();
				else if(FuncaoAuxiliar.comparaString(TipoPagamento.CARTAO.getTipoPagamento(),obtemDescricaoTipoPagamento(despesa.getCodigoPagamento())))
						valorCartao += despesa.getValorDespesa();
				else if(FuncaoAuxiliar.comparaString(TipoPagamento.CHEQUE.getTipoPagamento(),obtemDescricaoTipoPagamento(despesa.getCodigoPagamento())))
							valorCheque += despesa.getValorDespesa();
				else if(FuncaoAuxiliar.comparaString(TipoPagamento.PARCELADO.getTipoPagamento(),obtemDescricaoTipoPagamento(despesa.getCodigoPagamento())))
								valorParcelado += despesa.getValorDespesa();
				else if(FuncaoAuxiliar.comparaString(TipoPagamento.FINANCIAMENTO.getTipoPagamento(),obtemDescricaoTipoPagamento(despesa.getCodigoPagamento())))
									valorFinaciameto += despesa.getValorDespesa();
				else if(FuncaoAuxiliar.comparaString(TipoPagamento.CREDIARIO.getTipoPagamento(),obtemDescricaoTipoPagamento(despesa.getCodigoPagamento())))
					valorCrediario += despesa.getValorDespesa();
			}
				
		 }
		 
	 }
	 
	/** Obtém a descrição da categoria referente ao código passado.
	 * @param codigo <code>int</code>.
	 * @return uma <code>String</code>.
	 */
	private String obtemDescricaoCategoria(int codigo) {
		//Obtem a descricao da categoria atravez do codigo.
		return GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().pesquisaCategoria(codigo);
	}
	
	/** Obtém a descrição do tipo de pagamento referente ao código passado.
	 * @param codigo <code>int</code>.
	 * @return uma <code>String</code>.
	 */
	private String obtemDescricaoTipoPagamento(int codigo) {
		//Obtem a descricao da categoria atravez do codigo.
		return GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleFormaPagamento().pesquisaFormaPagamento(codigo);
	}
	
	/**Gera o valor de receita ao mês referido.
	 * @param mesAno <code>String</code>.
	 */
	private void geraValorReceita(String mesAno) {
		//Obtem a referencia do ControleRendaMensal
		ControleRendaMensal controleRendaMensal = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleRendaMensal();
		//Obtem o numero de rendas mensais no arquivo.
		int numeroRendaMensal = controleRendaMensal.numerosDeRendas();
		
		for(int indice = 0; indice < numeroRendaMensal; indice++){
			//Obtem a referencia do objeto rendaMensal do arquivo.
			RendaMensal rendaMensal = controleRendaMensal.obtemRendaMensal(indice);
			//Verifica se a renda mensal e da data procurada.
			if(FuncaoAuxiliar.comparaString(mesAno, FuncaoAuxiliar.obtemMesAno(FuncaoAuxiliar.coverteDataParaString(rendaMensal.getData(), true))))
				valorReceita += rendaMensal.getValor();
		}
	}
	
	/** Gerá o saldo atual.
	 * @return um <code>double</code>.
	 */
	private double geraSaldoAtual(){
		//Calcula o saldo atual.
		return valorReceita - (valorGastos + valorInvestimentos);
	}
	
	private void finaliza(){
		//Reseta as variaveis;
		valorCartao = 0;
		valorVista = 0;
		valorCrediario = 0;
		valorCheque = 0;
		valorFinaciameto = 0;
		valorParcelado = 0;
	}
}
