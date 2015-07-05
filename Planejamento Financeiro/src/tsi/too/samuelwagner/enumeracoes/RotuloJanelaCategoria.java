package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de configura��o da janela <code>IgCategoria</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public enum RotuloJanelaCategoria {
	/**
	 * T�tulo da janela de cadastro de categoria.
	 */
	TITULO("Cadastrar Categoria"),
	
	/**
	 * Mensagem de campo de cadstro de categoria vazio.
	 */
	VAZIO("Campo de cadastro n�o pode ser vazio"),
	
	/**
	 * Mensagem  de categoria j� cadastrada no sistema.
	 */
	REPETIDO("Valor digitado j� cadastrado no sistema"),
	
	/**
	 * Mensagem de categoria salva com sucesso.
	 */
	SALVO("Categoria cadastrada com sucesso"),
	
	/**
	 * Mensagem de categoria j� cadastrada.
	 */
	CADASTRADAS("Categoria j� cadastrada"),
	
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