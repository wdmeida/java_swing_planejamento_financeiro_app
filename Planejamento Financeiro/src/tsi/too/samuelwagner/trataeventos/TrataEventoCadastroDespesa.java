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
import tsi.too.samuelwagner.gui.IgCadastrarFormaPagamento;
import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.tipo.MetaMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;
import tsi.too.samuelwagner.validacoes.Validador;

import com.toedter.calendar.JDateChooser;

/**
 * A classe <code>TrataEventoCadastroDespesa</code> é responsável por tratar os eventos da classe <code>IgCadastraDespesa</code>
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class TrataEventoCadastroDespesa implements ActionListener {
	private GerenciamentoDeFinanca gerenciamentoDeFinanca;
	private IgCadastrarDespesas cadastrarDespesas;
	private IgPlanejamentoFinanceiro igPlanejamentoFinanceiro;
	
	/**
	 * Construtor sobrecarregado da classe <code>TrataEventoCadastroDespesa</code>.
	 * @param cadastrarDespesas <code>CadastrarDespesas</code> com a referência da janela gráfica que a classe tratará os eventos.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> com a referência da janela principal do programa.
	 */
	public TrataEventoCadastroDespesa(IgCadastrarDespesas cadastrarDespesas,IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		this.gerenciamentoDeFinanca = GerenciamentoDeFinanca.getGerenciamentoFincanca();
		this.cadastrarDespesas = cadastrarDespesas;
		this.igPlanejamentoFinanceiro = igPlanejamentoFinanceiro;
	}
	
	/**
	 * Metódo ouvinte, responsável por tratar os eventos do botão cadastrar da janela e dos dois comboBox.
	 * @param event <code>ActionEvent</code> responsável por capturar os eventos.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		//Verifica se o evento foi botão cadastrar, caso seja, cadastra o novo objeto.
		if(event.getSource() == cadastrarDespesas.getCadastrarDespesaButton())
			cadastrarDespesa();
		else
			//Verifica se o evento foi no comboBox das categorias.
			if(event.getSource() == cadastrarDespesas.getCategoriaComboBox())
				eventosCategoria();
			else
				//Verifica se o evento foi no comboBox do pagamento.
				if(event.getSource() == cadastrarDespesas.getFormaPagamentoComboBox())
					eventosFormaPagamento();
	}//actionPerformed()
	
	/**
	 * Valida os dados dos campos da janela, e caso válidos cadastra os dados no arquivo através do método de cadastro 
	 * da classe <code>ControleDespesa</code>
	 */
	private void cadastrarDespesa(){
		//Valida as entradas do usuário
		boolean invalido = false;
		Despesa despesa = new Despesa();
		
		//Valida a descrição
		if(validarDescricao()) despesa.setDescricao(cadastrarDespesas.getDescricaoTextField().getText());
		else invalido = true;
		
		//Valida as datas.
		if(validarDatas(cadastrarDespesas.getDataDespesaDateChooser())) despesa.setDataDespesa(cadastrarDespesas.getDataDespesaDateChooser().getCalendar());
		else invalido = true;
		if(validarDatas(cadastrarDespesas.getDataPagamentoDateChooser())) despesa.setDataPagamento(cadastrarDespesas.getDataPagamentoDateChooser().getCalendar());
		else invalido = true;
		
		//Valida o valor.
		if(validarValor()) despesa.setValorDespesa(Double.parseDouble(FuncaoAuxiliar.converteVirgulaEmPonto(cadastrarDespesas.getValorTextField().getText())));
		else invalido = true;
		
		//Obtém o nome da categoria e a forma de pagamento.
		String nomeCategoria = cadastrarDespesas.getCategoriaComboBox().getItemAt(cadastrarDespesas.getCategoriaComboBox().getSelectedIndex());
		String formaPagamento = cadastrarDespesas.getFormaPagamentoComboBox().getItemAt(cadastrarDespesas.getFormaPagamentoComboBox().getSelectedIndex());
		
		//Verifica se o tipo de pagamento é cheque.
		if(formaPagamento.equalsIgnoreCase(TipoPagamento.CHEQUE.getTipoPagamento()))
		{
			if(validarCheque()) despesa.setNumeroCheque(cadastrarDespesas.getNumeroChequeTextField().getText());
			else invalido = true;	
		}
		else
			despesa.setNumeroCheque("");
		
		//Verifica se o tipo de pagamento é à vista.
		if(!formaPagamento.equalsIgnoreCase(TipoPagamento.A_VISTA.getTipoPagamento()))
		{
			if(validarParcelas()) despesa.setNumeroParcelas(Integer.parseInt(cadastrarDespesas.getQuantidadeParcelasTextField().getText()));
			else invalido = true;	
		}
		else
			despesa.setNumeroParcelas(1);
		
		if(invalido){
			ativarMensagemPreenchimento(true);
			FuncaoAuxiliar.exibirMensagemErro(cadastrarDespesas, RotuloJanelaDespesa.MSG_DADOS_INVALIDOS.getDescricao(), RotuloJanelaDespesa.TITULO.getDescricao());
		}else{
			ativarMensagemPreenchimento(false);
			//Calcula o valor da parcela.
			if(despesa.getNumeroParcelas() > 1) despesa.setValorDespesa(despesa.getValorDespesa() / despesa.getNumeroParcelas());
			FuncaoAuxiliar.exibirMensagem(cadastrarDespesas, RotuloJanelaDespesa.MSG_DADOS_CORRETOS.getDescricao(), RotuloJanelaDespesa.TITULO.getDescricao());
			
			//Cadastra a despesa, atualiza a tabela e verifica se o orçamento foi estourado.
			GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleDespesa().cadastrarDespesa(despesa, formaPagamento, nomeCategoria);
			OperacoesDoIgPlanejamentoFinanceiro.atualizaTabelaDespesa(igPlanejamentoFinanceiro);
			verificaSituacaoCategoria(nomeCategoria, cadastrarDespesas.getDataDespesaDateChooser().getCalendar());
			this.cadastrarDespesas.dispose();
		}
	}//cadastrarDespesa()
	
	/**
	 * Valida o valor do campo parcela.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarParcelas() {
		//Verifica se não é um número falso.
		if(!Validador.validaNumeroInteiro(cadastrarDespesas.getQuantidadeParcelasTextField().getText())){
			cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.RED));
			return false;
		}else{
			if(validaQuantidadeParcelas(Integer.parseInt(cadastrarDespesas.getQuantidadeParcelasTextField().getText())))
			{
				cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.GRAY));
				return true;
			}
			else{
				cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.RED));
				return false;
			}
		}
	}//validarParcelas()
	
	/**
	 * Valida a quantidade de parcelas referente ao pagamento do item.
	 * @param parcelas <code>int</code> com a quantidade de parcelas.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validaQuantidadeParcelas(int parcelas){
		//Obtém a forma de pagamento.
		String pagamento = cadastrarDespesas.getFormaPagamentoComboBox().getItemAt(cadastrarDespesas.getFormaPagamentoComboBox().getSelectedIndex());
		
		//Verifica se o pagamento é diferente de cheque ou cartão.
		if(!pagamento.equalsIgnoreCase(TipoPagamento.CHEQUE.getTipoPagamento()) && !pagamento.equalsIgnoreCase(TipoPagamento.CARTAO.getTipoPagamento())){
			if(parcelas > 1) return true;
			else { 				
				cadastrarDespesas.getQuantidadeParcelasTextField().setBorder(new LineBorder(Color.RED));
				return false;}
		}
		else return true;
	}//validaQuantidadeParcelas()
	
	/**
	 * Método responsável por ativar e desativar os campos da janela dependendo da forma de pagamento utilizada.
	 */
	private void eventosFormaPagamento(){

	}//cadastrarFormaPagamento
	
	/**
	 * Ativa s campo para aceitar pagamento via cheque.
	 * @param ativar <code>boolean</code> com os valores <code>true</code> para ativar ou <code>false</code> para desativar.
	 */
	private void ativarCampoPagamentoCheque(boolean ativar){
		if(ativar) cadastrarDespesas.getNumeroChequeTextField().setEnabled(true);
		else cadastrarDespesas.getNumeroChequeTextField().setEditable(false);
	}//ativarCamposPagementoCheque
	
	/**
	 * Ativa o campo para pagamento parcelado.
	 * @param ativar ativar <code>boolean</code> com os valores <code>true</code> para ativar ou <code>false</code> para desativar.
	 */
	private void ativarCampoNumeroParcelas(boolean ativar){
		if(ativar) cadastrarDespesas.getQuantidadeParcelasTextField().setEnabled(true);
		else cadastrarDespesas.getQuantidadeParcelasTextField().setEnabled(false);	
	}
	
	/**
	 * Verifica se o usuário quer cadastrar uma nova categoria. Caso queira, abre a caixa de cadastro.
	 */
	private void eventosCategoria(){
		//Verifica se a opção selecionada foi cadastrar uma nova categoria.
		if(cadastrarDespesas.getCategoriaComboBox().getItemAt(cadastrarDespesas.getCategoriaComboBox().getSelectedIndex()).equalsIgnoreCase(RotuloJanelaDespesa.NOVA_CATEGORIA.getDescricao())){
			new IgCadastrarCategoria(cadastrarDespesas.getPlanejamentoFinanceiro(), cadastrarDespesas, gerenciamentoDeFinanca.getControleCategoria());
			cadastrarDespesas.carregarCategorias();
		}
	}//cadastrarCategoria()
	
	/**
	 * Valida o valor dos campos descrição.
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
	 * Valida o valor do cheque.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarCheque(){
		if(Validador.validaCampoVazio(cadastrarDespesas.getNumeroChequeTextField().getText())){
			cadastrarDespesas.getNumeroChequeTextField().setBorder(new LineBorder(Color.RED));
			return false;
		}
		else
		{
			cadastrarDespesas.getNumeroChequeTextField().setBorder(new LineBorder(Color.GRAY));
			return true;
		}
	}//validarCheque()
	
	/**
	 * Valida o valor dos valor da despesa. Muda a borda para vermelha se for um valor inválido e deixa cinza e volta pra cinza caso seja.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarValor() {
		if(!Validador.validaNumeroReal(FuncaoAuxiliar.converteVirgulaEmPonto(cadastrarDespesas.getValorTextField().getText()))){
			cadastrarDespesas.getValorTextField().setBorder(new LineBorder(Color.RED));
			return false;
		}			
		else{
			cadastrarDespesas.getValorTextField().setBorder(new LineBorder(Color.GRAY));
			return true;
		}
	}//validarValor
	
	/**
	 * Valida as datas. Muda a borda para vermelha se for um valor inválido e deixa cinza e volta pra cinza caso seja.
	 * @param data <code>JDateChooser</code> componente com a data escolhida pelo usuario.
	 * @return <code>boolean</code> informando <code>true</code> se verdadeiro ou <code>false</code> se falso.
	 */
	private boolean validarDatas(JDateChooser data) {
		if(data.getCalendar() == null){
			data.setBorder(new LineBorder(Color.RED));
			return false;
		}else{
			data.setBorder(new LineBorder(Color.GRAY));
			return true;
		}
	}//validarDatas
	
	/**
	 * Ativa a mensagem de campos preenchidos de forma inválida.
	 * @param ativar <code>boolean</code> com <code>true</code> para ativar a mensagem ou <code>false</code> para desativar.
	 */
	private void ativarMensagemPreenchimento(Boolean ativar) {
		if(ativar)
			cadastrarDespesas.getCamposObrigatoriosLabel().setVisible(true);
		else
			cadastrarDespesas.getCamposObrigatoriosLabel().setVisible(false);
	}//ativarMensagemPreenchimento()
	
	/**
	 * Verifica a situação da categoria em que a despesa foi cadastrada. Caso tenha extrapolado a meta, emite uma mensagem ao usuário
	 * informando que a meta foi ultrapassada.
	 * @param nomeCategoria <code>String</code> com o nome da categoria.
	 * @param data <code>Calendar</code> com a data da categoria.
	 */
	public void verificaSituacaoCategoria(String nomeCategoria, Calendar data) {
		//Pesquisa pela categoria.
		int pesquisar = gerenciamentoDeFinanca.getControleCategoria().pesquisaCategoria(nomeCategoria);
		
		//Caso tenha encontrado obtém o código.
		if(pesquisar != -1){
			pesquisar = gerenciamentoDeFinanca.getControleCategoria().obtemCategoria(pesquisar).getCodigo();
			
			//obtém as despesas dessa categoria.
			Despesa despesas[] = gerenciamentoDeFinanca.getControleDespesa().pesquisarDespesas(pesquisar, data);
			
			//Verifica se foram encontradas despesas.
			if(despesas != null){
				MetaMensal meta = gerenciamentoDeFinanca.getControleMetaMensal().pesquisarMetaMensal(pesquisar, data);
				if(meta != null)
				{
					double valorMeta = FuncaoAuxiliar.valorSinalEmDinheiro(meta.getValor(), meta.getPorcentagem());
					
					double valorDespesas = TratadorEventoPainelMetas.somaDespesas(despesas);
					//Verifica se o valor das despesa já ultrapassou o valor da meta definido pelo usuário.
					if(valorDespesas > valorMeta){
						//Verifica se o valor da despesa é maior que o valor total da meta.
						if(valorDespesas < meta.getValor())
							FuncaoAuxiliar.exibirMensagemErro(cadastrarDespesas, "Você extrapolou a porcentagem definida para sua meta.", "Cadastrar Despesa");
						else
							FuncaoAuxiliar.exibirMensagemErro(cadastrarDespesas, "Você extrapolou sua meta de orçamento.", "Cadastrar Despesa");
					}
				}//meta
			}//despesa
		}//pesquisar
	}//verificaSituacaoCategoria
}//class TrataEventpCadastroDespesa
