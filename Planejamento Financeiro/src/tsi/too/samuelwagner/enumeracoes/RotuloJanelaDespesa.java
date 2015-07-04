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
	MSG_DADOS_INVALIDOS("Campos de preenchimento obrigat�rio devem obedecer ao seguinte crit�rio:\n\n"
			+ "Descri��o - Deve conter o nome da despesa.\n"
			+ "Data de Pagamento - Data no formato dd/mm/aaaa.\n"
			+ "Data da Despesa - Data no formato dd/mm/aaaa.\n"
			+ "Categoria - Deve conter a categoria em que a despesa se enquadra.\n"
			+ "Forma de pagamento - Forma de pagamento utilizada.\n"
			+ "N�mero do cheque - Quando a op��o for cheque, est� op��o dever� ser utilizada.\n"
			+ "Quantidade de parcelas - Quando o pagamento n�o for � vista, est� op��o dever� ser utilizada.\n"),
	MSG_DADOS_CORRETOS("Dados cadastrados com sucesso."),
	MSG_DADOS_DUPLICADOS("Dados j� cadastrados no sistema.");

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