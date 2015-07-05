package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de configuração da janela <code>IgCategoria</code>.
 * @author Samuel
 * @author Wagner
 *
 */
public enum RotuloJanelaCategoria {
	TITULO("Cadastrar Categoria"),
	VAZIO("Campo de cadastro não pode ser vazio"),
	REPETIDO("Valor digitado já cadastrado no sistema"),
	SALVO("Categoria cadastrada com sucesso"),
	CADASTRADAS("Categoria já cadastrada"),
	INSERIR("Nova"),
	ADICIONAR("Adicionar");

	private String descricao;
	
	/**
	 * Contrutor defaul da enumeração.
	 * @param rotulo <code>String</code> com o valor da enumeração.
	 */
	private RotuloJanelaCategoria(String rotulo){
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