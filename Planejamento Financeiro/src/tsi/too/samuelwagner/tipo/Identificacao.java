package tsi.too.samuelwagner.tipo;
/**
 * A classe Identificacao, possui os atributos que s�o comuns entre as classes do trabalho. 
 * Os atributos s�o um <code>int</code> codigo e <code>String</code> descricao.
 * 
 * @author Samuel e Wagner
 */
public abstract class Identificacao {
	protected int codigo;
	protected String descricao;
	
	/**
	 * Construtor default da classe Identificacao.
	 */
	public Identificacao() {}
	
	/**
	 * Contrutor sobrecarregado da classe Identificacao. Recebe o seguinte par�metro:
	 * @param codigo <code>int</code> de identifica��o.
	 */
	public Identificacao(int codigo){
		this.codigo = codigo;
	}//Identificacao
	
	/**
	 * Contrutor sobrecarregado da classe Identificacao. Recebe os seguintes par�metros:
	 * @param codigo <code>int</code> de identifica��o.
	 * @param descricao <code>String</code> com o nome identificando o item.
	 */
	public Identificacao(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}//Identificacao()
	
	/**
	 * Retorna o c�digo cadastrado.
	 * @return um <code>int</code> com o c�digo
	 */
	public int getCodigo() {
		return codigo;
	}//getCodigo()

	/**
	 * Atribui o valor recebido por par�metro como c�digo do item.
	 * @param codigo <code>int</code> com o c�digo do item.
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}//setCodigo()
	
	/**
	 * Retorna a descri��o cadastrada.
	 * @return um <code>String</code> com a descri��o do item.
	 */
	public String getDescricao() {
		return descricao;
	}//getDescricao()
	
	/**
	 * Atribui o valor recebido por par�metro como descri��o do item.
	 * @param descricao <code>String</code> do item.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}//setDescricao()
	
	/**
	 * Retorna uma refer�ncia em String do c�digo e da descri��o.
	 * @return um <code>String</code> refer�nte aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "C�digo: " + codigo + ", Descri��o: " + descricao;
	}//toString()
}//class Identificacao
