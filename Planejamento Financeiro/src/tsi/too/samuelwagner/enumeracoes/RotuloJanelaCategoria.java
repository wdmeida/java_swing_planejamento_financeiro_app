package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de configura��o da janela <code>IgCategoria</code>.
 * @author Samuel
 * @author Wagner
 *
 */
public enum RotuloJanelaCategoria {
	TITULO("Cadastrar Categoria"),
	VAZIO("Campo de cadastro n�o pode ser vazio"),
	REPETIDO("Valor digitado j� cadastrado no sistema"),
	SALVO("Categoria cadastrada com sucesso"),
	CADASTRADAS("Categoria j� cadastrada"),
	INSERIR("Nova"),
	ADICIONAR("Adicionar");

	private String descricao;
	
	/**
	 * Contrutor defaul da enumera��o.
	 * @param rotulo <code>String</code> com o valor da enumera��o.
	 */
	private RotuloJanelaCategoria(String rotulo){
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