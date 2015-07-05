package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de configura��o da janela <code>IgCadastrarDespesa</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public enum RotuloJanelaDespesa {
	
	/**
	 * T�tulo da janela de cadastro de despesa.
	 */
	TITULO("Cadastrar Despesa"),
	
	/**
	 * R�tulo de inser��o de nova categoria.
	 */
	NOVA_CATEGORIA("Nova categoria..."),
	
	/**
	 * R�tulo de inser��o de nova forma de pagamento.
	 */
	NOVA_FORMA_PAGAMENTO("Nova forma de Pagamento..."),
	
	/**
	 * Mensagem de dados inv�lidos.
	 */
	MSG_DADOS_INVALIDOS("Erro no cadastro!!! Posicione o cursor sobre os campos marcados em vermelho para saber o motivo."),
	
	/**
	 * Mensagem de despesa cadastrada com sucesso.
	 */
	MSG_DADOS_CORRETOS("Despesa cadastrada com sucesso."),
	
	/**
	 * Mensagem de informa��o sobre a despesa j� cadastrada com sucesso.
	 */
	MSG_DADOS_DUPLICADOS("Despesa j� cadastrada no sistema."),
	
	/**
	 * Mensagem de informa��o sobre a categoria da despesa n�o selecionada.
	 */
	MSG_CATEGORIA("Deve conter a categoria em que a despesa se enquadra."),
	
	/**
	 * Mensagem de informa��o sobre a data de pagamento da despesa.
	 */
	MGS_PAGAMENTO_DATA("Data de pagamento da despesa - Utilizar formato dd/mm/aaaa."),
	
	/**
	 * Mensagem de informa��o sobre a data da despesa.
	 */
	MGS_DESPESA__DATA("Data em que foi realizada a despesa - Utilizar formato dd/mm/aaaa."),
	
	/**
	 * Mensagem de informa��o sobre a descri��o da despesa.
	 */
	MSG_DESCRICAO("Deve conter a descri��o da despesa."),
	
	/**
	 * Mensagem de informa��o sobre o valor da despesa.
	 */
	MSG_VALOR("Valor da despesa deve ser num�rico."),
	
	/**
	 * Mensagem de informa��o sobre a escolha da forma de pagamento.
	 */
	MSG_FORMA_PAGAMENTO("Forma de pagamento utilizada."),
	
	/**
	 * Mensagem de informa��o sobre o pagamento com cheque.
	 */
	MSG_CHEQUE("Quando a op��o for cheque, est� op��o dever� ser preenchida obrigat�riamente."),
	
	/**
	 * Mensagem de informa��o sobre pagamentos que n�o s�o a vista.
	 */
	MSG_AVISTA("Quando o pagamento n�o for � vista, est� op��o dever� ser preenchida com a quantidade de parcelas da despesa.");

	private String descricao;
	
	/**
	 * Contrutor defaul da enumera��o.
	 * @param rotulo <code>String</code> com o valor da enumera��o.
	 */
	private RotuloJanelaDespesa(String rotulo){
		this.descricao = rotulo;
	}
	
	/**
	 * Retorna um <code>String</code> com o valor da enumera��o.
	 * @return um <code>String</code>.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Atribui um valor a enumera��o.
	 * @param descricao <code>String</code>
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}//Enum RotuloJanelaCategoria