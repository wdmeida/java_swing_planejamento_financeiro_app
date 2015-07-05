package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de configura��o da janela <code>IgCadastrarMetaMensal</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public enum RotuloJanelaMetaMensal {
	/**
	 * T�tulo da janela de cadastro de meta mensal.
	 */
	TITULO("Cadastrar Meta Mensal"),
	
	/**
	 * T�tulo do bot�o de visualiza��o de meta.
	 */
	TITULO_META("Visualizar Meta"),
	
	/**
	 * Mensagem de informa��o de meta n�o cadastrada.
	 */
	META_VAZIA("N�o h� meta cadastrada para esta categoria."),
	
	/**
	 * Mensagem de informa��o sobre despesa vazia.
	 */
	DESPESA_VAZIA("Voc� n�o realizou nenhum gasto nesta categoria este m�s."),
	
	/**
	 * Mensagem de informa��o sobre valor vazio.
	 */
	VAZIO_VALOR("Campo valor n�o pode ser vazio."),
	
	/**
	 * Mensagem de informa��o sobre valor digitado inv�lido.
	 */
	VAZIO_INVALIDO("Campo valor deve ser n�merico."),
	
	/**
	 * Mensagem de informa��o sobre porcentagem informada inv�lida.
	 */
	PORCENTAGEM("Porcentagem deve ser maior que 0 e menor ou igual a 100."),
	
	/**
	 * Mensagem de informa��o de meta j� cadastrada.
	 */
	REPETIDO("Meta para este per�odo j� cadastrada."),
	
	/**
	 * Mensagem de informa��o de meta salva com sucesso.
	 */
	SALVO("Meta cadastrada com sucesso."),
	
	/**
	 * Mensagem do bot�o inserir nova menta.
	 */
	INSERIR("Cadastrar"),
	
	/**
	 * Mensagem do bot�o fechar janela.
	 */
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
