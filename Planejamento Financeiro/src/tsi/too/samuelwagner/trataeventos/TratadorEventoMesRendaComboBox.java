package tsi.too.samuelwagner.trataeventos;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;

import tsi.too.samuelwagner.gui.IgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;

/** Classe responsável por tratar o evento referente ao ComboBox da janela de renda mensal.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class TratadorEventoMesRendaComboBox implements ActionListener {
	
	private IgPlanejamentoFinanceiro igPlanejamentoFinanceiro;
	
	/**Contrutor sobrecarregado do <code>TratadorEventoMesRendaComboBox</code>.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code>
	 */
	public TratadorEventoMesRendaComboBox(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		this.igPlanejamentoFinanceiro = igPlanejamentoFinanceiro;
	}
	
	/**
	 * Registra os métodos que trataram os eventos do comboBox MesRenda.
	 * @param event <code>ActionEvent</code>.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		igPlanejamentoFinanceiro.getRendaTable().setModel(new DefaultTableModel(
				OperacoesDoIgPlanejamentoFinanceiro.preencheTabelaRenda((String)igPlanejamentoFinanceiro.getMesRendaComboBox().getSelectedItem())
				, new String[] {"Descrição","Data","Valor"}));
	}
}
