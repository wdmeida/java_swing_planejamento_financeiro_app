package tsi.too.samuelwagner.trataeventos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;

/** Classe responsavel por tratar o evento referente ao ComboBox.
 * @author Samuel
 * @author Wagner
 */
public class TratadorEventoMesRendaComboBox implements ActionListener {
	
	private IgPlanejamentoFinanceiro igPlanejamentoFinanceiro;
	
	/**Contrutor sobrecarregado.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code>
	 */
	public TratadorEventoMesRendaComboBox(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		this.igPlanejamentoFinanceiro = igPlanejamentoFinanceiro;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		igPlanejamentoFinanceiro.getRendaTable().setModel(new DefaultTableModel(
				OperacoesDoIgPlanejamentoFinanceiro.preencheTabelaRenda((String)igPlanejamentoFinanceiro.getMesRendaComboBox().getSelectedItem())
				, new String[] {"Descri��o","Data","Valor"}));
	}
}
