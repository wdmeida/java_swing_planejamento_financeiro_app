package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de configuração da janela <code>IgCadastrarDespesa</code>.
 * @author Samuel
 * @author Wagner
 *
 */
public enum RotuloJanelaDespesa {
	TITULO("Cadastrar Despesa"),
	NOVA_CATEGORIA("Nova categoria..."),
	NOVA_FORMA_PAGAMENTO("Nova forma de Pagamento..."),
	MSG_DADOS_INVALIDOS("Campos de preenchimento obrigatório devem obedecer ao seguinte critério:\n\n"
			+ "Descrição - Deve conter o nome da despesa.\n"
			+ "Data de Pagamento - Data no formato dd/mm/aaaa.\n"
			+ "Data da Despesa - Data no formato dd/mm/aaaa.\n"
			+ "Categoria - Deve conter a categoria em que a despesa se enquadra.\n"
			+ "Forma de pagamento - Forma de pagamento utilizada.\n"
			+ "Número do cheque - Quando a opção for cheque, está opção deverá ser utilizada.\n"
			+ "Quantidade de parcelas - Quando o pagamento não for à vista, está opção deverá ser utilizada.\n"),
	MSG_DADOS_CORRETOS("Dados cadastrados com sucesso."),
	MSG_DADOS_DUPLICADOS("Dados já cadastrados no sistema.");

	private String descricao;
	
	/**
	 * Contrutor defaul da enumeração.
	 * @param rotulo <code>String</code> com o valor da enumeração.
	 */
	private RotuloJanelaDespesa(String rotulo){
		this.descricao = rotulo;
	}
	
	/**
	 * Retorna um <code>String</code> com o valor da enumeração.
	 * @return um <code>String</code>.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Atribui um valor a enumeração.
	 * @param descricao <code>String</code>
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}//Enum RotuloJanelaCategoria