package tsi.too.samuelwagner.tipo;

import java.util.Calendar;
/**
 * Define os atributos e m�todos da classe PlanejamentoMensal. A classe possui os seguintes atributos:
 * <code>int</code> codigoDespesa refer�nte ao c�digo da despesa e um <code>Calendar</code> mesAnoPlanejamento
 * refer�nte ao m�s/ano do planejamento.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class PlanejamentoMensal {
	private int codigoDespesa;
	private Calendar mesAnoPlanejamento;
	
	/**
	 * Construtor default da classe PlanejamentoMensal.
	 */
	public PlanejamentoMensal() {}
	
	/**
	 * Construtor sobrecarregado da classe PlanejamentoMensal. Recebe como par�metros.
	 * @param codigoDespesa <code>int</code> refer�nte ao c�digo da despesa.
	 * @param mesAnoPlanejamento <code>Calendar</code> refer�nte ao m�s/ano do planejamento.
	 */
	public PlanejamentoMensal(int codigoDespesa, Calendar mesAnoPlanejamento) {
		this.codigoDespesa = codigoDespesa;
		this.mesAnoPlanejamento = mesAnoPlanejamento;
	}//Planejamento()
	
	/**
	 * Retorna o c�digo da despesa.
	 * @return um <code>int</code> com o c�digo da despesa.
	 */
	public int getCodigoDespesa() {
		return codigoDespesa;
	}//getCodigoDespesa()
	
	/**
	 * Atribui o c�digo da despesa.
	 * @param codigoDespesa <code>int</code> com o c�digo da despesa.
	 */
	public void setCodigoDespesa(int codigoDespesa) {
		this.codigoDespesa = codigoDespesa;
	}//setCodigoDespesa()
	
	/**
	 * Retorna o m�s/ano do planejamento.
	 * @return um <code>Calendar</code> com o m�s/ano do planejamento.
	 */
	public Calendar getMesAnoPlanejamento() {
		return mesAnoPlanejamento;
	}//getMesAnoPlanejamento()
	
	/**
	 * Atribui o m�s/ano do planejamento.
	 * @param mesAnoPlanejamento <code>Calendar</code> com o m�s/ano do planejamento.
	 */
	public void setMesAnoPlanejamento(Calendar mesAnoPlanejamento) {
		this.mesAnoPlanejamento = mesAnoPlanejamento;
	}//setMesAnoPlanejamento()
	
	/**
	 * Retornar uma refer�ncia de uma String contendo as informa��es do planejamento mensal.
	 * @return um <code>String</code> com as informa��es de planejamento.
	 */
	@Override
	public String toString() {
		return "C�digo Despesa: " + codigoDespesa + ", M�s/Ano Planejamento:" + mesAnoPlanejamento;
	}//toString()
}//class PlanejamentoMensal
