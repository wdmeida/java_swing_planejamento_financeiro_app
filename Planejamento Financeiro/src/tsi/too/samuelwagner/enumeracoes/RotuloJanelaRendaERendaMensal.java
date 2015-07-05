package tsi.too.samuelwagner.enumeracoes;

/**
 * Esta <code>Enum</code> possui os r�tulos utilizados na interface de configura��o da janela <code>IgCadastraRendaERendaMensal</code>.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public enum RotuloJanelaRendaERendaMensal {
	/**
	 * T�tulo da janela.
	 */
	TITULO("Cadastrar "),
	
	/**
	 * Mensagem de campo n�o pode ser vazio.
	 */
	VAZIO_RENDA("Campo de descri��o n�o pode ser vazio."),
	
	/**
	 * Mensagem de renda mensal n�o pode ser vazia ou valor negativo.
	 */
	VAZIO_RENDA_MENSAL("Campo de valor n�o pode ser vazio ou ter valor negativo."),
	
	/**
	 * Mensagem de renda j� cadastrada.
	 */
	REPETIDO(" digitada j� cadastrado no sistema."),
	
	/**
	 * Mensagem de valor j� salvo no sistema.
	 */
	SALVO(" cadastrada com sucesso."),
	
	/**
	 * Mensagem de nova renda.
	 */
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