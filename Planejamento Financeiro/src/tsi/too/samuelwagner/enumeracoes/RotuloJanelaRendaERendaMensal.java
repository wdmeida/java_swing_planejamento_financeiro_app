package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os rótulos utilizados na interface de configuração da janela <code>IgCadastraRendaERendaMensal</code>.
 * @author Samuel
 * @author Wagner
 *
 */
public enum RotuloJanelaRendaERendaMensal {
	TITULO("Cadastrar "),
	VAZIO_RENDA("Campo de descrição não pode ser vazio."),
	VAZIO_RENDA_MENSAL("Campo de valor não pode ser vazio ou ter valor negativo."),
	REPETIDO(" digitada já cadastrado no sistema."),
	SALVO(" cadastrada com sucesso."),
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