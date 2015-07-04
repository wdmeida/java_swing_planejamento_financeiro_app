package tsi.too.samuelwagner.tipo;

import java.util.Calendar;

/**
 * A classe RendaMensal herda da classe Renda os atributos e possui o 
 * seguinte atributo Valor do tipo <code>Double</code>
 * 
 * @author Samuel e Wagner
 */
public class RendaMensal extends Renda {
	private double valor;
	private Calendar data;
	
	/**
	 * Construtor default
	 */
	public RendaMensal() {
		super();
	}

	/**
	 * Construtor sobrecarregado, recebe os seguintes parametros:
	 * @param descricao <code>String</code> da renda.
	 * @param codigo <code>int</code> de identifica��o da rendaMensal.
	 * @param data <code>Calendar</code> com a data da receita.
	 * @param valor <code>double</code> da rendaMensal.
	 */
	public RendaMensal(String descricao,int codigo,Calendar data, double valor) {
		super(codigo, descricao);
		setValor(valor);
		setData(data);
	}

	/**
	 * Retorna o valor cadastrado.
	 * @return <code>double</code> valor.
	 */
	public double getValor() {
		return valor;
	}
	
	/**
	 * Atribui o valor recebido por parâmetro como valor do item.
	 * @param valor <code>double</code>
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	/**
	 * Retorna a data da renda.
	 * @return <code>Calendar</code> data.
	 */
	public Calendar getData() {
		return data;
	}

	/**
	 * Atribui a data da renda.
	 * @param data <code>Calendar</code>
	 */
	public void setData(Calendar data) {
		this.data = data;
	}

	/**
	 * Retorna uma referÃªncia em String do código, descrição e do valor.
	 * @return <code>String</code> referênte aos dados cadastrados.
	 */
	@Override
	public String toString() {
		return "C�digo: " + getCodigo() + String.format("Data da Renda: %02d/%02d/%04d", data.get(Calendar.DAY_OF_MONTH),
																						 data.get(Calendar.MONTH) + 1,
																						 data.get(Calendar.YEAR) + 
																						 String.format(" , Valor: %.2f", valor));
	}
}
