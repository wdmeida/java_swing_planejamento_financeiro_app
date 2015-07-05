package tsi.too.samuelwagner.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.enumeracoes.TituloJanela;
import tsi.too.samuelwagner.operacoes.GeraGraficoCategoria;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;

import javax.swing.ButtonGroup;
import java.awt.event.KeyEvent;

public class IgGraficoBarra extends JDialog {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelGraficoMetaMesal;
	private GeraGraficoCategoria geraGraficoCategoria;
	private JComboBox<String> mesAnoComboBox;
	private JRadioButton graficoValorRadio;
	private JRadioButton graficoPorcentagemRadio;
	
	public IgGraficoBarra(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro,TituloJanela tituloJanela) {
		getContentPane().setLayout(null);
		
		panelGraficoMetaMesal = new JPanel();
		panelGraficoMetaMesal.setBorder(new TitledBorder(null, "Grafico Categorias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGraficoMetaMesal.setBounds(10, 64, 925, 411);
		getContentPane().add(panelGraficoMetaMesal);
		panelGraficoMetaMesal.setLayout(null);
		geraGraficoCategoria = new GeraGraficoCategoria();
		
		
		JPanel radioPanel = new JPanel();
		radioPanel.setBorder(new TitledBorder(null, "Escolha o Tipo de Gr\u00E1fico:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		radioPanel.setBounds(217, 11, 342, 53);
		getContentPane().add(radioPanel);
		radioPanel.setLayout(null);
		
		graficoValorRadio = new JRadioButton("Gr\u00E1fico Valor");
		graficoValorRadio.setMnemonic(KeyEvent.VK_V);
		buttonGroup.add(graficoValorRadio);
		graficoValorRadio.setBounds(202, 19, 134, 23);
		radioPanel.add(graficoValorRadio);
		
		graficoPorcentagemRadio = new JRadioButton("Gr\u00E1fico Porcentagem");
		graficoPorcentagemRadio.setMnemonic(KeyEvent.VK_P);
		buttonGroup.add(graficoPorcentagemRadio);
		graficoPorcentagemRadio.setBounds(18, 19, 182, 23);
		radioPanel.add(graficoPorcentagemRadio);
		
		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setBorder(new TitledBorder(null, "Escolha o M\u00EAs:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		comboBoxPanel.setBounds(10, 11, 197, 53);
		getContentPane().add(comboBoxPanel);
		comboBoxPanel.setLayout(null);
		
		mesAnoComboBox = new JComboBox<String>(OperacoesDoIgPlanejamentoFinanceiro.preencheMesAnteriorDespesa());
		mesAnoComboBox.setBounds(10, 20, 177, 22);
		
		graficoPorcentagemRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaGrafico();
			}
		});
		
		graficoValorRadio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaGrafico();
			}
		});
		
		mesAnoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaGrafico();
			}
		});
		
		graficoValorRadio.setSelected(true);
		panelGraficoMetaMesal.add(geraGraficoCategoria.geraGraficoBarra(mesAnoComboBox.getItemAt(mesAnoComboBox.getSelectedIndex()), false));
		
		comboBoxPanel.add(mesAnoComboBox);
		setModal(true);
		setTitle(tituloJanela.getTitulo());
		setBounds(100, 100, 951, 511);
		setResizable(false);
		setLocationRelativeTo(igPlanejamentoFinanceiro);
		setVisible(true);
	}
	
	private void atualizaGrafico(){
		boolean porcentagem;
		if(graficoValorRadio.isSelected())
			porcentagem = false;
		else
			porcentagem = true;
		//Remove todos os Componentes do Painel.
		panelGraficoMetaMesal.removeAll();
		
		//Adiciona um novo componente
		panelGraficoMetaMesal.add(geraGraficoCategoria.geraGraficoBarra(
				mesAnoComboBox.getItemAt(mesAnoComboBox.getSelectedIndex()), porcentagem));
		
		//Revalida o painel.
		panelGraficoMetaMesal.revalidate();
		
		//Repinta o painel.
		panelGraficoMetaMesal.repaint();
	}
	
}
