package tsi.too.samuelwagner.tipo;
/**
 * A classe categoria, armazena informações sobre a categoria dos gastos.
 * Possui os parâmetros <code>int</code>codigo e <code>String</code>descricao herdados
 * da superclasse 
 * @author Samuel Gonçalves
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
	 * Construtor sobrecarregado da classe Categoria. Recebe o seguinte parâmetro:
	 * @param codigo <code>int</code> da categoria.
	 */
	public Categoria(int codigo){
		super(codigo);
	}//Categoria()
	
	/**
	 * Construtor sobrecarregado da classe Categoria. Recebe os seguintes parâmetros:
	 * @param codigo <code>int</code> da categoria.
	 * @param descricao <code>String</code> da categoria.
	 */
	public Categoria(int codigo, String descricao) {
		super(codigo, descricao);
	}//Categoria()
	
}//class Categoria
