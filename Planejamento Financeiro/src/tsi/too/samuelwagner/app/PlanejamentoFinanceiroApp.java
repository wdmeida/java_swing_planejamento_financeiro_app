package tsi.too.samuelwagner.app;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
/**
 * A classe <code>PlanejamentoFinanceiroApp</code> possui o método main, responsável por inicializar o aplicativo
 * de Planejamento Financeiro.
 * 
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class PlanejamentoFinanceiroApp {
	
	/**
	 * Método main, responsável por inicializar as funções do aplicativo de Planejamento Financeiro.
	 * @param args 
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
					e.printStackTrace();
		}
		new IgPlanejamentoFinanceiro();
	}//main
}//class PlanejamentoFinanceiroApp
