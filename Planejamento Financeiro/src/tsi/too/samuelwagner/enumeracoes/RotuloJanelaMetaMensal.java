package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de configuração da janela <code>IgCadastrarMetaMensal</code>.
 * @author Samuel
 * @author Wagner
 *
 */
public enum RotuloJanelaMetaMensal {
	TITULO("Cadastrar Meta Mensal"),
	TITULO_META("Visualizar Meta"),
	META_VAZIA("Não há meta cadastrada para esta categoria."),
	DESPESA_VAZIA("Você não realizou nenhum gasto nesta categoria este mês."),
	VAZIO_VALOR("Campo valor não pode ser vazio."),
	VAZIO_INVALIDO("Campo valor deve ser númerico."),
	PORCENTAGEM("Porcentagem deve ser maior que 0 e menor ou igual a 100."),
	REPETIDO("Meta para este período já cadastrada."),
	SALVO("Meta cadastrada com sucesso."),
	INSERIR("Cadastrar"),
	FECHAR("Fechar");

	private String descricao;
	
	/**
	 * Contrutor defaul da enumeração.
	 * @param rotulo <code>String</code> com o valor da enumeração.
	 */
	private RotuloJanelaMetaMensal(String rotulo){
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
}
