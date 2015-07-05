package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de configuração da janela <code>IgCadastrarMetaMensal</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public enum RotuloJanelaMetaMensal {
	/**
	 * Título da janela de cadastro de meta mensal.
	 */
	TITULO("Cadastrar Meta Mensal"),
	
	/**
	 * Título do botão de visualização de meta.
	 */
	TITULO_META("Visualizar Meta"),
	
	/**
	 * Mensagem de informação de meta não cadastrada.
	 */
	META_VAZIA("Não há meta cadastrada para esta categoria."),
	
	/**
	 * Mensagem de informação sobre despesa vazia.
	 */
	DESPESA_VAZIA("Você não realizou nenhum gasto nesta categoria este mês."),
	
	/**
	 * Mensagem de informação sobre valor vazio.
	 */
	VAZIO_VALOR("Campo valor não pode ser vazio."),
	
	/**
	 * Mensagem de informação sobre valor digitado inválido.
	 */
	VAZIO_INVALIDO("Campo valor deve ser númerico."),
	
	/**
	 * Mensagem de informação sobre porcentagem informada inválida.
	 */
	PORCENTAGEM("Porcentagem deve ser maior que 0 e menor ou igual a 100."),
	
	/**
	 * Mensagem de informação de meta já cadastrada.
	 */
	REPETIDO("Meta para este período já cadastrada."),
	
	/**
	 * Mensagem de informação de meta salva com sucesso.
	 */
	SALVO("Meta cadastrada com sucesso."),
	
	/**
	 * Mensagem do botão inserir nova menta.
	 */
	INSERIR("Cadastrar"),
	
	/**
	 * Mensagem do botão fechar janela.
	 */
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
