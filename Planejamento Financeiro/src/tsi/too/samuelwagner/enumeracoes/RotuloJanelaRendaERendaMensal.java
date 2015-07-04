package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de configura��o da janela <code>IgCadastraRendaERendaMensal</code>.
 * @author Samuel
 * @author Wagner
 *
 */
public enum RotuloJanelaRendaERendaMensal {
	TITULO("Cadastrar "),
	VAZIO_RENDA("Campo de descri��o n�o pode ser vazio."),
	VAZIO_RENDA_MENSAL("Campo de valor n�o pode ser vazio ou ter valor negativo."),
	REPETIDO(" digitada j� cadastrado no sistema."),
	SALVO(" cadastrada com sucesso."),
	NOVA_RENDA("Nova Renda...");
	

	private String descricao;
	
	/**
	 * Contrutor defaul da enumera��o.
	 * @param rotulo <code>String</code> com o valor da enumera��o.
	 */
	private RotuloJanelaRendaERendaMensal(String rotulo){
		this.descricao = rotulo;
	}
	
	/**
	 * Retorna um <code>String</code> com o valor da enumera��o.
	 * @return <code>String</code>.
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
}//Enum RotuloJanelaRendaERendaMensal