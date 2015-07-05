package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de configuração da janela <code>IgCategoria</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public enum RotuloJanelaCategoria {
	/**
	 * Título da janela de cadastro de categoria.
	 */
	TITULO("Cadastrar Categoria"),
	
	/**
	 * Mensagem de campo de cadstro de categoria vazio.
	 */
	VAZIO("Campo de cadastro não pode ser vazio"),
	
	/**
	 * Mensagem  de categoria já cadastrada no sistema.
	 */
	REPETIDO("Valor digitado já cadastrado no sistema"),
	
	/**
	 * Mensagem de categoria salva com sucesso.
	 */
	SALVO("Categoria cadastrada com sucesso"),
	
	/**
	 * Mensagem de categoria já cadastrada.
	 */
	CADASTRADAS("Categoria já cadastrada"),
	
	/**
	 * Mensagem de inserir nova categoria.
	 */
	INSERIR("Nova"),
	
	/**
	 * Mensagem de adicionar categoria.
	 */
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