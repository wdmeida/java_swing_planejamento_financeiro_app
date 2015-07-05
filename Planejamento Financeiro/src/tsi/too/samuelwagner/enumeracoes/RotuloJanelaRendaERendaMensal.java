package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de configuração da janela <code>IgCadastraRendaERendaMensal</code>.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public enum RotuloJanelaRendaERendaMensal {
	/**
	 * Título da janela.
	 */
	TITULO("Cadastrar "),
	
	/**
	 * Mensagem de campo não pode ser vazio.
	 */
	VAZIO_RENDA("Campo de descrição não pode ser vazio."),
	
	/**
	 * Mensagem de renda mensal não pode ser vazia ou valor negativo.
	 */
	VAZIO_RENDA_MENSAL("Campo de valor não pode ser vazio ou ter valor negativo."),
	
	/**
	 * Mensagem de renda já cadastrada.
	 */
	REPETIDO(" digitada já cadastrado no sistema."),
	
	/**
	 * Mensagem de valor já salvo no sistema.
	 */
	SALVO(" cadastrada com sucesso."),
	
	/**
	 * Mensagem de nova renda.
	 */
	NOVA_RENDA("Nova Renda...");
	

	private String descricao;
	
	/**
	 * Contrutor defaul da enumeração.
	 * @param rotulo <code>String</code> com o valor da enumeração.
	 */
	private RotuloJanelaRendaERendaMensal(String rotulo){
		this.descricao = rotulo;
	}
	
	/**
	 * Retorna um <code>String</code> com o valor da enumeração.
	 * @return <code>String</code>.
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
}//Enum RotuloJanelaRendaERendaMensal