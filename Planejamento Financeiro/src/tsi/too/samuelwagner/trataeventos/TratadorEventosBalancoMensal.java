package tsi.too.samuelwagner.trataeventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tsi.too.samuelwagner.enumeracoes.TipoPagamento;
import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.operacoes.GeraGraficoCategoria;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**
 * Esta classe registra os métodos responsáveis por tratar os eventos do painel balanço mensal.
 * 
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class TratadorEventosBalancoMensal implements ActionListener {
	private IgPlanejamentoFinanceiro igPlanejamentoFinanceiro;
	
	/**
	 * Construtor sobrecarregado da classe <code>TratadorEventosBalancoMensal</code>.
	 * @param igPlanejamentoFinanceiro <code>TratadorEventosBalancoMensal</code> com a referência da janela principal
	 * para poder acessar os componentes e realizar as operações.
	 */
	public TratadorEventosBalancoMensal(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		this.igPlanejamentoFinanceiro = igPlanejamentoFinanceiro;
	}//TratadorEventosBalancoMensal
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == igPlanejamentoFinanceiro.getMesBalancoComboBox()) obterInformacoes();
	}
	
	/**
	 * Método responsável por responder aos eventos do painel balanço mensal. Obtém as informações para o mês selecionado
	 * pelo usuário e exibe no painel.
	 */
	private void obterInformacoes() {
		//Obtém o mêsAno selecionado pelo usuário.
		String mesAno = igPlanejamentoFinanceiro.getMesBalancoComboBox().getItemAt(igPlanejamentoFinanceiro.getMesBalancoComboBox().getSelectedIndex());
		
		//Obtém os códigos das despesas relativas ao mês escolhido pelo usuário.
		Integer codigosDespesas[] = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControlePlanejamentoMensal().obterCodigosPlanejamentoMensal(FuncaoAuxiliar.converteDataParaCalendar(mesAno));
		
		//Verifica se existem despesas nesse mês.
		if(codigosDespesas != null){
			
			//Obtém a relação de despesas do mês e já soma o total de gastos.
			double somaGastos = 0;
			List<Despesa> despesas = new ArrayList<Despesa>();
			for(Integer codigo : codigosDespesas){
				Despesa despesa = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().pesquisarDespesas(codigo);
				despesas.add(despesa);
				somaGastos += despesa.getValorDespesa();
			}
			
			//Atribui a soma ao painel de balanço mensal.
			igPlanejamentoFinanceiro.getDespesaTextField().setText(String.format("R$ %1.2f", somaGastos));
			
			//Chama o método para calcular cada categoria separadamente.
			calcularGastosPorTipoPagamento(despesas);
			
			//Obtém a receita
			double receita = GeraGraficoCategoria.obtemValorReceita(mesAno);
			
			//Preenche os valores no painel.
			igPlanejamentoFinanceiro.getReceitasTextField().setText(String.format("R$ %1.2f", receita));
			igPlanejamentoFinanceiro.getSaldoTextField().setText(String.format("R$ %1.2f", receita - somaGastos));
		}
		else{
			//Retira o foco do comboBox, zera os valores e exibe uma mensagem ao usuário.
			zerarCampos();
			//Libera o JComboBox transferindo o foco para o próximo componente que será exibido.
			igPlanejamentoFinanceiro.getMesBalancoComboBox().transferFocus();
			FuncaoAuxiliar.exibirMensagemErro(igPlanejamentoFinanceiro,"Não existem despesas para o Mês solicitado.", "Balanço Mensal");
			igPlanejamentoFinanceiro.getMesBalancoComboBox().transferFocusBackward();
		}
	}//carregarInformacoes()
	
	/**
	 * Calcula os valores gastos no mês por cada tipo de pagamento.
	 * @param lista <code>List<><code> com as despesas do mês.
	 */
	private void calcularGastosPorTipoPagamento(List<Despesa> lista){		
		Iterator<Despesa> it = lista.iterator();
		
		//Cria as variáveis que receberão os valores.
		double pagoAVista = 0, pagoCartao = 0, pagoCrediario = 0, pagoFinanciado = 0, pagoCheque = 0, pagoParcelado = 0;
		
		//Começa a ler os valores dos registros.
		while(it.hasNext()){
			Despesa despesa = it.next();
			//Obtém o nome da forma de pagamento referente ao código da mesma.
			String nome = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleFormaPagamento().pesquisaFormaPagamento(despesa.getCodigoPagamento());
			
			//Verifica o nome da forma de pagamento baseada na enumeração, e adiciona o valor a forma de pagamento realizada.
			if(nome.equalsIgnoreCase(TipoPagamento.A_VISTA.getTipoPagamento()))
				pagoAVista += despesa.getValorDespesa();
			else if(nome.equalsIgnoreCase(TipoPagamento.CARTAO.getTipoPagamento()))
				pagoCartao += despesa.getValorDespesa();
			else if(nome.equalsIgnoreCase(TipoPagamento.CHEQUE.getTipoPagamento()))
				pagoCheque += despesa.getValorDespesa();
			else if(nome.equalsIgnoreCase(TipoPagamento.CREDIARIO.getTipoPagamento()))
				pagoCrediario += despesa.getValorDespesa();
			else if(nome.equalsIgnoreCase(TipoPagamento.FINANCIAMENTO.getTipoPagamento()))
				pagoFinanciado += despesa.getValorDespesa();
			else if(nome.equalsIgnoreCase(TipoPagamento.PARCELADO.getTipoPagamento()))
				pagoParcelado += despesa.getValorDespesa();		
		}//while()
		
		//Inicializa os campos de pagamentos com seus respectivos valores.
		igPlanejamentoFinanceiro.getAVistaTextField().setText(String.format("R$ %1.2f", pagoAVista));
		igPlanejamentoFinanceiro.getCartaoCreditoTextField().setText(String.format("R$ %1.2f", pagoCartao));
		igPlanejamentoFinanceiro.getChequeTextField().setText(String.format("R$ %1.2f", pagoCheque));
		igPlanejamentoFinanceiro.getCrediarioTextField().setText(String.format("R$ %1.2f", pagoCrediario));
		igPlanejamentoFinanceiro.getFinanciamentoTextField().setText(String.format("R$ %1.2f", pagoFinanciado));
		igPlanejamentoFinanceiro.getParceladoTextField().setText(String.format("R$ %1.2f", pagoParcelado));	
		
	}//calcularGastosPorTipoPagamento
	
	/**
	 * Apaga os valores dos campos que estavam preenchidos caso o usuário clique em uma opção que não possua
	 * despesas.
	 */
	private void zerarCampos(){
		igPlanejamentoFinanceiro.getDespesaTextField().setText("");
		igPlanejamentoFinanceiro.getReceitasTextField().setText("");
		igPlanejamentoFinanceiro.getSaldoTextField().setText("");
		igPlanejamentoFinanceiro.getAVistaTextField().setText("");
		igPlanejamentoFinanceiro.getCartaoCreditoTextField().setText("");
		igPlanejamentoFinanceiro.getChequeTextField().setText("");
		igPlanejamentoFinanceiro.getCrediarioTextField().setText("");
		igPlanejamentoFinanceiro.getFinanciamentoTextField().setText("");
		igPlanejamentoFinanceiro.getParceladoTextField().setText("");	
	}//zerarCampos()
}//class TratadorEventosBalancoMensal 
