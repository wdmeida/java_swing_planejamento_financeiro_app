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
 * Esta classe registra os m�todos respons�veis por tratar os eventos do painel balan�o mensal.
 * 
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class TratadorEventosBalancoMensal implements ActionListener {
	private IgPlanejamentoFinanceiro igPlanejamentoFinanceiro;
	
	/**
	 * Construtor sobrecarregado da classe <code>TratadorEventosBalancoMensal</code>.
	 * @param igPlanejamentoFinanceiro <code>TratadorEventosBalancoMensal</code> com a refer�ncia da janela principal
	 * para poder acessar os componentes e realizar as opera��es.
	 */
	public TratadorEventosBalancoMensal(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		this.igPlanejamentoFinanceiro = igPlanejamentoFinanceiro;
	}//TratadorEventosBalancoMensal
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == igPlanejamentoFinanceiro.getMesBalancoComboBox()) obterInformacoes();
	}
	
	/**
	 * M�todo respons�vel por responder aos eventos do painel balan�o mensal. Obt�m as informa��es para o m�s selecionado
	 * pelo usu�rio e exibe no painel.
	 */
	private void obterInformacoes() {
		//Obt�m o m�sAno selecionado pelo usu�rio.
		String mesAno = igPlanejamentoFinanceiro.getMesBalancoComboBox().getItemAt(igPlanejamentoFinanceiro.getMesBalancoComboBox().getSelectedIndex());
		
		//Obt�m os c�digos das despesas relativas ao m�s escolhido pelo usu�rio.
		Integer codigosDespesas[] = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControlePlanejamentoMensal().obterCodigosPlanejamentoMensal(FuncaoAuxiliar.converteDataParaCalendar(mesAno));
		
		//Verifica se existem despesas nesse m�s.
		if(codigosDespesas != null){
			
			//Obt�m a rela��o de despesas do m�s e j� soma o total de gastos.
			double somaGastos = 0;
			List<Despesa> despesas = new ArrayList<Despesa>();
			for(Integer codigo : codigosDespesas){
				Despesa despesa = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().pesquisarDespesas(codigo);
				despesas.add(despesa);
				somaGastos += despesa.getValorDespesa();
			}
			
			//Atribui a soma ao painel de balan�o mensal.
			igPlanejamentoFinanceiro.getDespesaTextField().setText(String.format("R$ %1.2f", somaGastos));
			
			//Chama o m�todo para calcular cada categoria separadamente.
			calcularGastosPorTipoPagamento(despesas);
			
			//Obt�m a receita
			double receita = GeraGraficoCategoria.obtemValorReceita(mesAno);
			
			//Preenche os valores no painel.
			igPlanejamentoFinanceiro.getReceitasTextField().setText(String.format("R$ %1.2f", receita));
			igPlanejamentoFinanceiro.getSaldoTextField().setText(String.format("R$ %1.2f", receita - somaGastos));
		}
		else{
			//Retira o foco do comboBox, zera os valores e exibe uma mensagem ao usu�rio.
			zerarCampos();
			//Libera o JComboBox transferindo o foco para o pr�ximo componente que ser� exibido.
			igPlanejamentoFinanceiro.getMesBalancoComboBox().transferFocus();
			FuncaoAuxiliar.exibirMensagemErro(igPlanejamentoFinanceiro,"N�o existem despesas para o M�s solicitado.", "Balan�o Mensal");
			igPlanejamentoFinanceiro.getMesBalancoComboBox().transferFocusBackward();
		}
	}//carregarInformacoes()
	
	/**
	 * Calcula os valores gastos no m�s por cada tipo de pagamento.
	 * @param lista <code>List<><code> com as despesas do m�s.
	 */
	private void calcularGastosPorTipoPagamento(List<Despesa> lista){		
		Iterator<Despesa> it = lista.iterator();
		
		//Cria as vari�veis que receber�o os valores.
		double pagoAVista = 0, pagoCartao = 0, pagoCrediario = 0, pagoFinanciado = 0, pagoCheque = 0, pagoParcelado = 0;
		
		//Come�a a ler os valores dos registros.
		while(it.hasNext()){
			Despesa despesa = it.next();
			//Obt�m o nome da forma de pagamento referente ao c�digo da mesma.
			String nome = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleFormaPagamento().pesquisaFormaPagamento(despesa.getCodigoPagamento());
			
			//Verifica o nome da forma de pagamento baseada na enumera��o, e adiciona o valor a forma de pagamento realizada.
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
	 * Apaga os valores dos campos que estavam preenchidos caso o usu�rio clique em uma op��o que n�o possua
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
