package tsi.too.samuelwagner.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.enumeracoes.TituloJanela;
import tsi.too.samuelwagner.operacoes.GeraGraficoMetaMensal;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;

public class IgGraficoMetaMensal extends JDialog {

	private GeraGraficoMetaMensal geraGraficoMetaMensal;
	private JPanel panelGraficoMetaMesal;
	private JComboBox<String> metaMesalComboBox;
	private JComboBox<String> mesAnoComboBox;
	private JLabel lblCategoria;
	private JLabel lblMeta;
	private JLabel lblValorGasto;
	private JTextField categoriaField;
	private JTextField metaField;
	private JTextField valorGastoLabel;
	private JTextField valorRestanteField;
	
	public IgGraficoMetaMensal(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro,TituloJanela tituloJanela) {
		setBounds(100, 100, 951, 511);
		
		setResizable(false);
		getContentPane().setLayout(null);
		
		panelGraficoMetaMesal = new JPanel();
		panelGraficoMetaMesal.setBorder(new TitledBorder(null, "Grafico Meta Mensal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGraficoMetaMesal.setBounds(10, 11, 686, 464);
		getContentPane().add(panelGraficoMetaMesal);
		
		JLabel escolhaMetaLabel = new JLabel("Escolha a Meta:");
		escolhaMetaLabel.setBounds(706, 21, 76, 20);
		getContentPane().add(escolhaMetaLabel);
		
		metaMesalComboBox = new JComboBox<String>(OperacoesDoIgPlanejamentoFinanceiro.carregarValoresComboBoxMeta());
		metaMesalComboBox.setBounds(706, 52, 229, 22);
		metaMesalComboBox.setSelectedIndex(-1);
		
		getContentPane().add(metaMesalComboBox);
		
		JLabel escolhaMesAnoLabel = new JLabel("Escolha o M\u00EAs:");
		escolhaMesAnoLabel.setBounds(706, 85, 76, 20);
		getContentPane().add(escolhaMesAnoLabel);
		
		mesAnoComboBox = new JComboBox<String>(OperacoesDoIgPlanejamentoFinanceiro.preencheMesAnteriorMeta());
		mesAnoComboBox.setBounds(706, 116, 229, 22);
		getContentPane().add(mesAnoComboBox);
		mesAnoComboBox.setSelectedIndex(-1);
		
		metaMesalComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				atualizaGrafico();
			}
		});
		
		mesAnoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizaGrafico();
			}
		});
		
		JPanel inforPanel = new JPanel();
		inforPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		inforPanel.setBounds(706, 212, 229, 263);
		getContentPane().add(inforPanel);
		inforPanel.setLayout(null);
		
		lblCategoria = new JLabel("Categoria: ");
		lblCategoria.setBounds(10, 21, 62, 21);
		inforPanel.add(lblCategoria);
		
		lblMeta = new JLabel("Meta:");
		lblMeta.setBounds(10, 78, 46, 21);
		inforPanel.add(lblMeta);
		
		lblValorGasto = new JLabel("Valor Gasto:");
		lblValorGasto.setBounds(10, 134, 62, 21);
		inforPanel.add(lblValorGasto);
		
		categoriaField = new JTextField();
		categoriaField.setBackground(Color.WHITE);
		categoriaField.setBounds(10, 47, 209, 21);
		inforPanel.add(categoriaField);
		categoriaField.setColumns(10);
		
		metaField = new JTextField();
		metaField.setBackground(Color.WHITE);
		metaField.setBounds(10, 102, 209, 21);
		inforPanel.add(metaField);
		metaField.setColumns(10);
		
		valorGastoLabel = new JTextField();
		valorGastoLabel.setBackground(Color.WHITE);
		valorGastoLabel.setBounds(10, 166, 209, 21);
		inforPanel.add(valorGastoLabel);
		valorGastoLabel.setColumns(10);
		
		JLabel valorRestanteLabel = new JLabel("Valor Restante:");
		valorRestanteLabel.setBounds(10, 197, 103, 21);
		inforPanel.add(valorRestanteLabel);
		
		valorRestanteField = new JTextField();
		valorRestanteField.setBackground(Color.WHITE);
		valorRestanteField.setBounds(10, 229, 209, 21);
		inforPanel.add(valorRestanteField);
		valorRestanteField.setColumns(10);
		
		categoriaField.setEditable(false);
		categoriaField.setEnabled(false);
		metaField.setEditable(false);
		metaField.setEnabled(false);
		valorGastoLabel.setEditable(false);
		valorGastoLabel.setEnabled(false);
		valorRestanteField.setEditable(false);
		valorRestanteField.setEnabled(false);
		
		geraGraficoMetaMensal = new GeraGraficoMetaMensal(IgGraficoMetaMensal.this);
		
		panelGraficoMetaMesal.add(geraGraficoMetaMensal.geraGraficoMetaMensal(metaMesalComboBox.getItemAt(metaMesalComboBox.getSelectedIndex()),
				mesAnoComboBox.getItemAt(mesAnoComboBox.getSelectedIndex())));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgGraficoMetaMensal.this.dispose();
			}
		});
		
		setModal(true);
		setLocationRelativeTo(igPlanejamentoFinanceiro);
		setVisible(true);
	}
	
	private void atualizaGrafico(){
			//Remove todos os Componentes do Painel.
			panelGraficoMetaMesal.removeAll();
			
			//Adiciona um novo componente
			panelGraficoMetaMesal.add(geraGraficoMetaMensal.geraGraficoMetaMensal(metaMesalComboBox.getItemAt(metaMesalComboBox.getSelectedIndex()),
				mesAnoComboBox.getItemAt(mesAnoComboBox.getSelectedIndex())));
			
			//Revalida o painel.
			panelGraficoMetaMesal.revalidate();
			
			//Repinta o painel.
			panelGraficoMetaMesal.repaint();
	}

	public JTextField getCategoriaField() {
		return categoriaField;
	}

	public JTextField getMetaField() {
		return metaField;
	}

	public JTextField getValorGastoLabel() {
		return valorGastoLabel;
	}

	public JTextField getValorRestanteField() {
		return valorRestanteField;
	}
}
