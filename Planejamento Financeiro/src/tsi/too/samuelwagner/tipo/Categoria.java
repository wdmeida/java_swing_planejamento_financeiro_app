package tsi.too.samuelwagner.tipo;
/**
 * A classe categoria, armazena informa��es sobre a categoria dos gastos.
 * Possui os par�metros <code>int</code>codigo e <code>String</code>descricao herdados
 * da superclasse 
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public class Categoria extends Identificacao {
	/**
	 * Construtor default.
	 */
	public Categoria() {
		super();
	}//Categoria()
	
	/**
	 * Construtor sobrecarregado da classe Categoria. Recebe o seguinte par�metro:
	 * @param codigo <code>int</code> da categoria.
	 */
	public Categoria(int codigo){
		super(codigo);
	}//Categoria()
	
	/**
	 * Construtor sobrecarregado da classe Categoria. Recebe os seguintes par�metros:
	 * @param codigo <code>int</code> da categoria.
	 * @param descricao <code>String</code> da categoria.
	 */
	public Categoria(int codigo, String descricao) {
		super(codigo, descricao);
	}//Categoria()
	
}//class Categoria
