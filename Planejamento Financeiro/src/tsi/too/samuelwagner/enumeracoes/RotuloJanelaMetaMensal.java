package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de configura��o da janela <code>IgCadastrarMetaMensal</code>.
 * @author Samuel
 * @author Wagner
 *
 */
public enum RotuloJanelaMetaMensal {
	TITULO("Cadastrar Meta Mensal"),
	TITULO_META("Visualizar Meta"),
	META_VAZIA("N�o h� meta cadastrada para esta categoria."),
	DESPESA_VAZIA("Voc� n�o realizou nenhum gasto nesta categoria este m�s."),
	VAZIO_VALOR("Campo valor n�o pode ser vazio."),
	VAZIO_INVALIDO("Campo valor deve ser n�merico."),
	PORCENTAGEM("Porcentagem deve ser maior que 0 e menor ou igual a 100."),
	REPETIDO("Meta para este per�odo j� cadastrada."),
	SALVO("Meta cadastrada com sucesso."),
	INSERIR("Cadastrar"),
	FECHAR("Fechar");

	private String descricao;
	
	/**
	 * Contrutor defaul da enumera��o.
	 * @param rotulo <code>String</code> com o valor da enumera��o.
	 */
	private RotuloJanelaMetaMensal(String rotulo){
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
}
