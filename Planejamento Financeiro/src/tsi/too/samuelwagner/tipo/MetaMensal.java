package tsi.too.samuelwagner.tipo;

import java.util.Calendar;
/**
 * A classe MetaMensal armazenar as informa��es sobre as metas e gastos referentes a um Mes/Ano determinado.
 * Os atributos da classe MetaMensal s�o um <code>Calendar</code> mesAnoMeta e um <code>double</code> valor.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 *
 */
public class MetaMensal extends Categoria {
	private Calendar mesAnoMeta;
	private double valor;
	private int porcentagem;
	
	/**
	 * Construtor default da classe MetaMensal.
	 */
	public MetaMensal() {
		super();
	}//MetaMensal()
	
	/**
	 * Construtor sobrecarregado da classe MetaMensal. Recebe os seguintes par�metros:
	 * @param codigo <code>int</code> da meta mensal.
	 * @param mesAnoMeta <code>Calendar</code> com o m�s e o ano da meta mensal.
	 * @param valor <code>double</code> da meta para os gastos.
	 * @param porcentagem <code>int</code>.
	 */
	public MetaMensal(int codigo, Calendar mesAnoMeta, double valor, int porcentagem) {
		super(codigo);
		this.mesAnoMeta = mesAnoMeta;
		this.valor = valor;
		this.porcentagem = porcentagem;
	}//MetaMensal()

	/**
	 * Retorna o m�s/ano da meta.
	 * @return um <code>Calendar</code> 
	 */
	public Calendar getMesAnoMeta() {
		return mesAnoMeta;
	}//getMesAnoMeta()
	
	/**
	 * Atribui o m�s/ano da meta mensal.
	 * @param mesAnoMeta <code>Calendar</code>
	 */
	public void setMesAnoMeta(Calendar mesAnoMeta) {
		this.mesAnoMeta = mesAnoMeta;
	}//setMesAnoMeta
	
	/**
	 * Retornar o valor estipulado para a meta mensal.
	 * @return valor <code>double</code>
	 */
	public double getValor() {
		return valor;
	}//getValor()

	/**
	 * Atribui o valor da meta mensal.
	 * @param valor <code>double</code>
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}//setValor()
	
	/**
	 * Retornar uma refer�ncia de uma String contendo as informa��es da meta.
	 * @return <code>String</code>
	 */
	@Override
	public String toString() {
		return "C�digo: " + getCodigo() + ", Mes/Ano da Meta:" + mesAnoMeta + String.format(", Valor R$ %1.2f", valor);
	}//toString()

	/**
	 * Retornar o valor da porcentagem.
	 * @return porcentagem <code>int</code>
	 */
	public int getPorcentagem() {
		return porcentagem;
	}

	/**
	 * Atribui o valor da porcentagem.
	 * @param porcentagem <code>int</code>
	 */
	public void setPorcentagem(int porcentagem) {
		this.porcentagem = porcentagem;
	}
}//class MetaMensal
