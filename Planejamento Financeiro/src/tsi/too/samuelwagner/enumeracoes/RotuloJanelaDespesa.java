package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de configura��o da janela <code>IgCadastrarDespesa</code>.
 * @author Samuel
 * @author Wagner
 *
 */
public enum RotuloJanelaDespesa {
	TITULO("Cadastrar Despesa"),
	NOVA_CATEGORIA("Nova categoria..."),
	NOVA_FORMA_PAGAMENTO("Nova forma de Pagamento..."),
	MSG_DADOS_INVALIDOS("Dados da despesa ausentes ou inv�lidos."),
	MSG_DADOS_CORRETOS("Despesa cadastrada com sucesso."),
	MSG_DADOS_DUPLICADOS("Despesa j� cadastrada no sistema."),
	MSG_CATEGORIA("Deve conter a categoria em que a despesa se enquadra."),
	MGS_PAGAMENTO_DATA("Data de pagamento da despesa - Utilizar formato dd/mm/aaaa."),
	MGS_DESPESA__DATA("Data em que foi realizada a despesa - Utilizar formato dd/mm/aaaa."),
	MSG_DESCRICAO("Deve conter a descri��o da despesa."),
	MSG_VALOR("Valor da despesa deve ser num�rico."),
	MSG_FORMA_PAGAMENTO("Forma de pagamento - Forma de pagamento utilizada."),
	MSG_CHEQUE("Quando a op��o for cheque, est� op��o dever� ser utilizada."),
	MSG_AVISTA("Quando o pagamento n�o for � vista, est� op��o dever� ser utilizada.");

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