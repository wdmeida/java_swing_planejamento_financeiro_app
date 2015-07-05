package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de configuração da janela <code>IgCadastrarDespesa</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public enum RotuloJanelaDespesa {
	
	/**
	 * Título da janela de cadastro de despesa.
	 */
	TITULO("Cadastrar Despesa"),
	
	/**
	 * Rótulo de inserção de nova categoria.
	 */
	NOVA_CATEGORIA("Nova categoria..."),
	
	/**
	 * Rótulo de inserção de nova forma de pagamento.
	 */
	NOVA_FORMA_PAGAMENTO("Nova forma de Pagamento..."),
	
	/**
	 * Mensagem de dados inválidos.
	 */
	MSG_DADOS_INVALIDOS("Erro no cadastro!!! Posicione o cursor sobre os campos marcados em vermelho para saber o motivo."),
	
	/**
	 * Mensagem de despesa cadastrada com sucesso.
	 */
	MSG_DADOS_CORRETOS("Despesa cadastrada com sucesso."),
	
	/**
	 * Mensagem de informação sobre a despesa já cadastrada com sucesso.
	 */
	MSG_DADOS_DUPLICADOS("Despesa já cadastrada no sistema."),
	
	/**
	 * Mensagem de informação sobre a categoria da despesa não selecionada.
	 */
	MSG_CATEGORIA("Deve conter a categoria em que a despesa se enquadra."),
	
	/**
	 * Mensagem de informação sobre a data de pagamento da despesa.
	 */
	MGS_PAGAMENTO_DATA("Data de pagamento da despesa - Utilizar formato dd/mm/aaaa."),
	
	/**
	 * Mensagem de informação sobre a data da despesa.
	 */
	MGS_DESPESA__DATA("Data em que foi realizada a despesa - Utilizar formato dd/mm/aaaa."),
	
	/**
	 * Mensagem de informação sobre a descrição da despesa.
	 */
	MSG_DESCRICAO("Deve conter a descrição da despesa."),
	
	/**
	 * Mensagem de informação sobre o valor da despesa.
	 */
	MSG_VALOR("Valor da despesa deve ser numérico."),
	
	/**
	 * Mensagem de informação sobre a escolha da forma de pagamento.
	 */
	MSG_FORMA_PAGAMENTO("Forma de pagamento utilizada."),
	
	/**
	 * Mensagem de informação sobre o pagamento com cheque.
	 */
	MSG_CHEQUE("Quando a opção for cheque, está opção deverá ser preenchida obrigatóriamente."),
	
	/**
	 * Mensagem de informação sobre pagamentos que não são a vista.
	 */
	MSG_AVISTA("Quando o pagamento não for à vista, está opção deverá ser preenchida com a quantidade de parcelas da despesa.");

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