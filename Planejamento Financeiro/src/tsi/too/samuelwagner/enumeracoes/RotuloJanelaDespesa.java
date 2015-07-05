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
	MSG_DADOS_INVALIDOS("Dados da despesa ausentes ou inválidos."),
	MSG_DADOS_CORRETOS("Despesa cadastrada com sucesso."),
	MSG_DADOS_DUPLICADOS("Despesa já cadastrada no sistema."),
	MSG_CATEGORIA("Deve conter a categoria em que a despesa se enquadra."),
	MGS_PAGAMENTO_DATA("Data de pagamento da despesa - Utilizar formato dd/mm/aaaa."),
	MGS_DESPESA__DATA("Data em que foi realizada a despesa - Utilizar formato dd/mm/aaaa."),
	MSG_DESCRICAO("Deve conter a descrição da despesa."),
	MSG_VALOR("Valor da despesa deve ser numérico."),
	MSG_FORMA_PAGAMENTO("Forma de pagamento - Forma de pagamento utilizada."),
	MSG_CHEQUE("Quando a opção for cheque, está opção deverá ser utilizada."),
	MSG_AVISTA("Quando o pagamento não for à vista, está opção deverá ser utilizada.");

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