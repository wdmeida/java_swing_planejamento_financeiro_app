package tsi.too.samuelwagner.trataeventos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.border.LineBorder;

import tsi.too.samuelwagner.enumeracoes.RotuloJanelaDespesa;
import tsi.too.samuelwagner.enumeracoes.TipoPagamento;
import tsi.too.samuelwagner.gui.IgCadastrarCategoria;
import tsi.too.samuelwagner.gui.IgCadastrarDespesas;
import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.MetaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;
import tsi.too.samuelwagner.validacoes.Validador;

import com.toedter.calendar.JDateChooser;

/**
 * A classe <code>TrataEventoCadastroDespesa</code> � respons�vel por tratar os eventos da classe <code>IgCadastraDespesa</code>
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class TratadorEventoCadastroDespesa implements ActionListener {
	private GerenciamentoDeFinanca gerenciamentoDeFinanca;
	private IgCadastrarDespesas cadastrarDespesas;
	private IgPlanejamentoFinanceiro igPlanejamentoFinanceiro;
	
	/**
	 * Construtor sobrecarregado da classe <code>TrataEventoCadastroDespesa</code>.
	 * @param cadastrarDespesas <code>CadastrarDespesas</code> com a refer�ncia da janela gr�fica que a classe tratar� os eventos.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> com a refer�ncia da janela principal do programa.
	 */
	public TratadorEventoCadastroDespesa(IgCadastrarDespesas cadastrarDespesas,IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		this.gerenciamentoDeFinanca = GerenciamentoDeFinanca.getGerenciamentoFincanca();
		this.cadastrarDespesas = cadastrarDespesas;
		this.igPlanejamentoFinanceiro = igPlanejamentoFinanceiro;
	}
	
	/**
	 * Met�do ouvinte, respons�vel por tratar os eventos do bot�o cadastrar da janela e dos dois comboBox.
	 * @param event <code>ActionEvent</code> respons�vel por capturar os eventos.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		//Verifica se o evento foi bot�o cadastrar, caso seja, cadastra o novo objeto.
		if(event.getSource() == cadastrarDespesas.getCadastrarDespesaButton())
			cadastrarDespesa();
		else
			//Verifica se o evento foi no comboBox das categorias.
			if(event.getSource() == cadastrarDespesas.getCategoriaComboBox())
				eventosCategoria();
			else
				//Verifica se o evento foi no comboBox do pagamento.
				if(event.getSource() == cadastrarDespesas.getFormaPagamentoComboBox())
					validarFormasPagamento(cadastrarDespesas.getFormaPagamentoComboBox().getItemAt(cadastrarDespesas.getFormaPagamentoComboBox().getSelectedIndex()));
	}//actionPerformed()
	
	/**
	 * Valida os dados dos campos da janela, e caso v�lidos cadastra os dados no arquivo atrav�s do m�todo de cadastro 
	 * da classe <code>ControleDespesa</code>
	 */
	private void cadastrarDespesa(){
		//Valida as entradas do usu�rio
		boolean invalido = false;
		Despesa despesa = new Despesa();
		
		//Obt�m a categoria e a forma de pagamento.
		String nomeCategoria = cadastrarDespesas.getCategoriaComboBox().getItemAt(cadastrarDespesas.getCategoriaComboBox().getSelectedIndex());
		String formaPagamento = cadastrarDespesas.getFormaPagamentoComboBox().getItemAt(cadastrarDespesas.getFormaPagamentoComboBox().getSelectedIndex());
		
		//Verifica se a descri��o � vazia.
		if(validarDescricao()) despesa.setDescricao(cadastrarDespesas.getDescricaoTextField().getText());
		else invalido = true;
		
		//Verifica se as datas s�o vazias.
		if(validarDatas(cadastrarDespesas.getDataDespesaDateChooser())) despesa.setDataDespesa(cadastrarDespesas.getDataDespesaDateChooser().getCalendar());
		else invalido = true;
		if(validarDatas(cadastrarDespesas.getDataPagamentoDateChooser())) despesa.setDataPagamento(cadastrarDespesas.getDataPagamentoDateChooser().getCalendar());
		else invalido = true;
		
		//Valida o valor digitado pelo usu�rio.
		if(validarValor()) despesa.setValorDespesa(Double.parseDouble(cadastrarDespesas.getValorTextField().getText()));
		else invalido = true;
		
		//Verifica se o tipo de pagamento � cheque.
		if(formaPagamento.equalsIgnoreCase(TipoPagamento.CHEQUE.getTipoPagamento()))
		{
			if(validarCheque() && validarParcelas()) despesa.setNumeroCheque(cadastrarDespesas.getNumeroChequeTextField().getText());
			else invalido = true;	
		}
		else{
			cadastrarDespesas.getNumeroChequeTextField().setBorder(new LineBorder(Color.GRAY));
			despesa.setNumeroCheque("");
		}
			
		//Verifica se o tipo de pagamento � � vista.
		if(!formaPagamento.equalsIgnoreCase(TipoPagamento.A_VISTA.getTipoPagamento()))
		{
			if(validarParcelas()) despesa.setNumeroParcelas(Integer.parseInt(cadastrarDespesas.getQuantidadeParcelasTextField().getText()));
			else invalido = true;	
		}
		else{
			cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.GRAY));
			despesa.setNumeroParcelas(1);
		}
			
		//Caso algum campo tenha sido informado de forma inv�lida, ativa a mensagem e exibe ao usu�rio.
		if(invalido){
			ativarMensagemPreenchimento(true);
			FuncaoAuxiliar.exibirMensagemErro(cadastrarDespesas, RotuloJanelaDespesa.MSG_DADOS_INVALIDOS.getDescricao(),
					RotuloJanelaDespesa.TITULO.getDescricao());
		}else{
			//Caso n�o tenha sido, cadastra os dados.
			ativarMensagemPreenchimento(false);
			if(despesa.getNumeroParcelas() > 1)	despesa.setValorDespesa(despesa.getValorDespesa() / despesa.getNumeroParcelas());
			FuncaoAuxiliar.exibirMensagem(cadastrarDespesas, RotuloJanelaDespesa.MSG_DADOS_CORRETOS.getDescricao(), 
					RotuloJanelaDespesa.TITULO.getDescricao());
			GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().cadastrarDespesa(despesa, formaPagamento, nomeCategoria);
			OperacoesDoIgPlanejamentoFinanceiro.atualizaTabelaDespesa(igPlanejamentoFinanceiro);
			verificaSituacaoCategoria(nomeCategoria, cadastrarDespesas.getDataDespesaDateChooser().getCalendar());
			this.cadastrarDespesas.dispose();
		}
	}//cadastrarDespesa()
	
	/**
	 * Valida o valor dos campos da parcela. Se a forma de pagamento for Cheque ou Cart�o, � aceito o pagamento em 1 parcela. Caso n�o seja um destes
	 * tipos, o pagamento dever� ser em parcelas maiores que 1. Com exce��o do pagamento � Vista que j� possui um valor padr�o.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarParcelas() {
		//Obt�m a forma de pagamento.
		String formaPagamento = cadastrarDespesas.getFormaPagamentoComboBox().getItemAt(cadastrarDespesas.getFormaPagamentoComboBox().getSelectedIndex());
		
		if(!Validador.validaNumeroInteiro(cadastrarDespesas.getQuantidadeParcelasTextField().getText())){
			cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.RED));
			return false;
		}else
			cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.GRAY));	
		
		//Verifica se a forma de pagamento � cart�o ou cheque.
		if(formaPagamento.equalsIgnoreCase(TipoPagamento.CARTAO.getTipoPagamento()) || formaPagamento.equalsIgnoreCase(TipoPagamento.CHEQUE.getTipoPagamento()))
			if(Integer.parseInt(cadastrarDespesas.getQuantidadeParcelasTextField().getText()) >= 1){
				cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.GRAY));
				return true;
			}else
				return false;
		else{
			//Caso n�o seja.
			if(Integer.parseInt(cadastrarDespesas.getQuantidadeParcelasTextField().getText()) > 1){
				cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.GRAY));
				return true;
			}else
				return false;
		}
	}//validarParcelas()
	
	/**
	 * M�todo respons�vel por ativar e desativar os campos da janela dependendo da forma de pagamento utilizada.
	 */
	private void validarFormasPagamento(String formaPagamento){

		//Verifica se a op��o � diferente de � vista, caso n�o seja ativa a barra de inser��o de dados.
		if(FuncaoAuxiliar.comparaString(formaPagamento,	TipoPagamento.A_VISTA.getTipoPagamento())){
			ativaCampoNumeroParcelas(false);
			ativarCampoPagamentoCheque(false);
		}
		else
			if(!FuncaoAuxiliar.comparaString(formaPagamento, TipoPagamento.CHEQUE.getTipoPagamento())){
				ativarCampoPagamentoCheque(false);
				ativaCampoNumeroParcelas(true);
			}
			else{
				ativarCampoPagamentoCheque(true);
				ativaCampoNumeroParcelas(true);
			}
	}//cadastrarFormaPagamento
	
	/**
	 * Ativa o campo n�mero do cheque.
	 * @param ativar <code>boolean</code> com os valores <code>true</code> para ativar ou <code>false</code> para desativar.
	 */
	private void ativarCampoPagamentoCheque(boolean ativar){
		if(ativar) cadastrarDespesas.getNumeroChequeTextField().setEnabled(true);
		else cadastrarDespesas.getNumeroChequeTextField().setEditable(false);	
	}//ativarCamposPagementoCheque
	
	/**
	 * Ativa o campo para quantidade de parcelas.
	 * @param ativar <code>boolean</code> com os valores <code>true</code> para ativar ou <code>false</code> para desativar.
	 */
	private void ativaCampoNumeroParcelas(boolean ativar){
		if(ativar) cadastrarDespesas.getQuantidadeParcelasTextField().setEnabled(true);
		else cadastrarDespesas.getQuantidadeParcelasTextField().setEditable(false);
	}//ativarCampoNumeroParcelas
	
	
	/**
	 * Verifica se o usu�rio quer cadastrar uma nova categoria. Caso queira, abre a caixa de cadastro.
	 */
	private void eventosCategoria(){
		//Verifica se a op��o selecionada foi cadastrar uma nova categoria.
		if(cadastrarDespesas.getCategoriaComboBox().getItemAt(cadastrarDespesas.getCategoriaComboBox().getSelectedIndex()).equalsIgnoreCase(RotuloJanelaDespesa.NOVA_CATEGORIA.getDescricao())){
			new IgCadastrarCategoria(cadastrarDespesas.getPlanejamentoFinanceiro(), cadastrarDespesas, gerenciamentoDeFinanca.getControleCategoria());
			cadastrarDespesas.carregarCategorias();
		}
	}//cadastrarCategoria()
	
	/**
	 * Valida o valor dos campos descri��o.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarDescricao(){
		if(Validador.validaCampoVazio(cadastrarDespesas.getDescricaoTextField().getText())){
			cadastrarDespesas.getDescricaoTextField().setBorder(new LineBorder(Color.RED));
			return false;
		}			
		else{
			cadastrarDespesas.getDescricaoTextField().setBorder(new LineBorder(Color.GRAY));
			return true;
		}
	}//validarDescricao
	
	/**
	 * Valida o conte�do escrito no campo n�mero do cheque.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarCheque(){
		if(Validador.validaCampoVazio(cadastrarDespesas.getNumeroChequeTextField().getText())){
			cadastrarDespesas.getNumeroChequeTextField().setBorder(new LineBorder(Color.RED));
			return false;
		}else{
			cadastrarDespesas.getNumeroChequeTextField().setBorder(new LineBorder(Color.GRAY));
			return true;
		}
	}//validarCheque()
	
	/**
	 * Valida o valor dos valor da despesa. Muda a borda para vermelha se for um valor inv�lido e deixa cinza e volta pra cinza caso seja.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarValor() {
		if(!Validador.validaNumeroReal(cadastrarDespesas.getValorTextField().getText())){
			cadastrarDespesas.getValorTextField().setBorder(new LineBorder(Color.RED));
			return false;
		}else{
			cadastrarDespesas.getValorTextField().setBorder(new LineBorder(Color.GRAY));
			return true;
		}
	}//validarValor
	
	/**
	 * Valida as datas. Muda a borda para vermelha se for uma data inv�lid e cinza seja v�lida.
	 * @param data <code>JDateChooser</code> componente com a data escolhida pelo usuario.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarDatas(JDateChooser data) {
		if(data.getCalendar() == null){ data.setBorder(new LineBorder(Color.RED)); return false;}
		else{ data.setBorder(new LineBorder(Color.GRAY)); return true;}
	}//validarDatas
	
	/**
	 * Ativa a mensagem de campos preenchidos de forma inv�lida.
	 * @param ativar <code>boolean</code> com <code>true</code> para ativar a mensagem ou <code>false</code> para desativar.
	 */
	private void ativarMensagemPreenchimento(Boolean ativar) { 
		if(ativar) cadastrarDespesas.getCamposObrigatoriosLabel().setVisible(true);
		else cadastrarDespesas.getCamposObrigatoriosLabel().setVisible(false);
	}//ativarMensagemPreenchimento()
	
	/**
	 * Verifica a situa��o da categoria em que a despesa foi cadastrada. Caso tenha extrapolado a meta, emite uma mensagem ao usu�rio
	 * informando que a meta foi ultrapassada.
	 * @param nomeCategoria <code>String</code> com o nome da categoria.
	 * @param data <code>Calendar</code> com a data da categoria.
	 */
	public void verificaSituacaoCategoria(String nomeCategoria, Calendar data) {
		//Pesquisa pela categoria.
		int pesquisar = gerenciamentoDeFinanca.getControleCategoria().pesquisaCategoria(nomeCategoria);
		
		//Caso tenha encontrado obt�m o c�digo.
		if(pesquisar != -1){
			pesquisar = gerenciamentoDeFinanca.getControleCategoria().obtemCategoria(pesquisar).getCodigo();
			
			//obt�m as despesas dessa categoria.
			Despesa despesas[] = gerenciamentoDeFinanca.getControleDespesa().pesquisarDespesas(pesquisar, data);
			
			//Verifica se foram encontradas despesas.
			if(despesas != null){
				MetaMensal meta = gerenciamentoDeFinanca.getControleMetaMensal().pesquisarMetaMensal(pesquisar, data);
				if(meta != null)
				{
					double valorMeta = FuncaoAuxiliar.valorSinalEmDinheiro(meta.getValor(), meta.getPorcentagem());
					
					double valorDespesas = TratadorEventoPainelMetas.somaDespesas(despesas);
					//Verifica se o valor das despesa j� ultrapassou o valor da meta definido pelo usu�rio.
					if(valorDespesas > valorMeta){
						//Verifica se o valor da despesa � maior que o valor total da meta.
						if(valorDespesas < meta.getValor())
							FuncaoAuxiliar.exibirMensagemErro(cadastrarDespesas, "Voc� extrapolou a porcentagem definida para sua meta.", "Cadastrar Despesa");
						else
							FuncaoAuxiliar.exibirMensagemErro(cadastrarDespesas, "Voc� extrapolou sua meta de or�amento.", "Cadastrar Despesa");
					}
				}//meta
			}//despesa
		}//pesquisar
	}//verificaSituacaoCategoria
}//class TrataEventpCadastroDespesa
