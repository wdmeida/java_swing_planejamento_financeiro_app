package tsi.too.samuelwagner.gui;

import java.awt.BorderLayout;
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
import tsi.too.samuelwagner.operacoes.GeraGraficoDeBalaco;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;
/**
 * A classe <code>IgGraficoMeta</code> constrói a tela de visualização dos gráficos do programa.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class IgGraficoBalacoMensal extends JDialog {

	private JTextField receitaField;
	private JTextField investimentoField;
	private JTextField gastosField;
	private JTextField saldoAtualField;
	private JTextField vistaField;
	private JTextField cartaoField;
	private JTextField crediarioField;
	private JTextField parceladoField;
	private JTextField finaciamentoField;
	private JTextField chequeField;
	private JPanel graficoPanel;
	private JComboBox<String> mesComboBox;
	private GeraGraficoDeBalaco geraGraficoBalacoMensal;
	/**
	 * Contrutor sobrecarregado da classe <code>IgGrafico</code>.
	 * @param planejamento <code>IgPlanejamento</code> com a referência da janela principal.
	 * @param tituloJanela <code>TituloJanela</code> com o título da janela.
	 */
	public IgGraficoBalacoMensal(IgPlanejamentoFinanceiro planejamento, TituloJanela tituloJanela) {
		if(tituloJanela.getTitulo().equalsIgnoreCase(TituloJanela.IG_GRAFICO.getTitulo()))
			geraPanelBalancoMensal(tituloJanela);
		else
			if(tituloJanela.getTitulo().equalsIgnoreCase(TituloJanela.IG_GRAFICO_DETALHE.getTitulo()))
				geraPanelBalancoMensalDetalahdo(tituloJanela);
		
		getContentPane().add(graficoPanel);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgGraficoBalacoMensal.this.dispose();
			}
		});
		setResizable(false);
		setModal(true);
		setLocationRelativeTo(planejamento);
		setVisible(true);
	}//IgGraficoMeta
	
	/** Gera o painel que tera o gráfico.
	 * @param tituloJanela <code>TituloJanela</code>.
	 */
	private void geraPanelBalancoMensal(TituloJanela tituloJanela) {
		
		//Define as propriedades da janela de exibição do gráfico
		setTitle(tituloJanela.getTitulo());
		setSize(967, 501);
		//Tamanho pequeno.
		//setSize(820, 394);
		getContentPane().setLayout(null);
		
		graficoPanel = new JPanel();
		graficoPanel.setBorder(new TitledBorder(null, "Balanço Mensal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		graficoPanel.setBounds(10, 11, 702, 454);
		graficoPanel.setLayout(new BorderLayout());
		
		JLabel escolhaMesLabel = new JLabel("Escolha o M\u00EAs:");
		escolhaMesLabel.setBounds(726, 21, 126, 22);
		getContentPane().add(escolhaMesLabel);
		
		mesComboBox = new JComboBox<String>(OperacoesDoIgPlanejamentoFinanceiro.preencheMesAnteriorRenda());
		mesComboBox.setBounds(726, 60, 223, 22);
		getContentPane().add(mesComboBox);
		
		mesComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				actionBalanco(tituloJanela);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informa\u00E7\u00E3o do Balan\u00E7o Mensal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(722, 199, 230, 266);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel receitaLabel = new JLabel("Receita: ");
		receitaLabel.setBounds(10, 22, 60, 21);
		panel.add(receitaLabel);
		
		receitaField = new JTextField();
		receitaField.setBounds(37, 51, 183, 21);
		receitaField.setBackground(Color.WHITE);
		panel.add(receitaField);
		receitaField.setColumns(10);
		
		investimentoField = new JTextField();
		investimentoField.setBounds(37, 113, 183, 21);
		investimentoField.setBackground(Color.WHITE);
		panel.add(investimentoField);
		investimentoField.setColumns(10);
		
		JLabel investimentosLabel = new JLabel("Investimentos:");
		investimentosLabel.setBounds(10, 82, 83, 21);
		panel.add(investimentosLabel);
		
		JLabel gastosLabel = new JLabel("Gastos:");
		gastosLabel.setBounds(10, 145, 60, 21);
		panel.add(gastosLabel);
		
		gastosField = new JTextField();
		gastosField.setBounds(37, 168, 183, 21);
		gastosField.setBackground(Color.WHITE);
		panel.add(gastosField);
		gastosField.setColumns(10);
		
		JLabel saldoAtualLabel = new JLabel("Saldo Atual:");
		saldoAtualLabel.setBounds(10, 200, 83, 21);
		panel.add(saldoAtualLabel);
		
		saldoAtualField = new JTextField();
		saldoAtualField.setBackground(Color.WHITE);
		saldoAtualField.setBounds(37, 231, 183, 21);
		panel.add(saldoAtualField);
		saldoAtualField.setColumns(10);
		
		receitaField.setEditable(false);
		investimentoField.setEditable(false);
		gastosField.setEditable(false);
		saldoAtualField.setEditable(false);
		geraGraficoBalacoMensal = new GeraGraficoDeBalaco();
		graficoPanel.add(geraGraficoBalacoMensal.geraGraficoPanel(mesComboBox.getItemAt(
				mesComboBox.getSelectedIndex()),IgGraficoBalacoMensal.this,tituloJanela));
		
		}
	
	/** Gera o painel que contera o gráfico Balanco Pagamento.
	 * @param tituloJanela <code>TituloJanela</code>.
	 */
	private void geraPanelBalancoMensalDetalahdo(TituloJanela tituloJanela) {
		
		//Define as propriedades da janela de exibição do gráfico
		setTitle("Balaço Mensal");
		setSize(967, 501);
		//Tamanho pequeno.
		//setSize(820, 394);
		getContentPane().setLayout(null);
		
		graficoPanel = new JPanel();
		graficoPanel.setBorder(new TitledBorder(null, "Balanço Mensal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		graficoPanel.setBounds(10, 11, 702, 454);
		graficoPanel.setLayout(new BorderLayout());
		
		JLabel escolhaMesLabel = new JLabel("Escolha o M\u00EAs:");
		escolhaMesLabel.setBounds(726, 21, 126, 22);
		getContentPane().add(escolhaMesLabel);
		
		mesComboBox = new JComboBox<String>(OperacoesDoIgPlanejamentoFinanceiro.preencheMesAnteriorDespesa());
		mesComboBox.setBounds(726, 60, 223, 22);
		getContentPane().add(mesComboBox);
		
		mesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actionBalanco(tituloJanela);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informa\u00E7\u00E3o do Balan\u00E7o Mensal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(722, 96, 230, 369);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel vistaLabel = new JLabel("À Vista: ");
		vistaLabel.setBounds(10, 22, 60, 21);
		panel.add(vistaLabel);
		
		vistaField = new JTextField();
		vistaField.setBackground(Color.WHITE);
		vistaField.setBounds(37, 54, 183, 21);
		panel.add(vistaField);
		vistaField.setColumns(10);
		
		cartaoField = new JTextField();
		cartaoField.setBackground(Color.WHITE);
		cartaoField.setBounds(37, 113, 183, 21);
		panel.add(cartaoField);
		cartaoField.setColumns(10);
		
		JLabel cartaoLabel = new JLabel("Cartão:");
		cartaoLabel.setBounds(10, 81, 83, 21);
		panel.add(cartaoLabel);
		
		JLabel parceladoLabel = new JLabel("Parcelado:");
		parceladoLabel.setBounds(10, 145, 60, 21);
		panel.add(parceladoLabel);
		
		parceladoField = new JTextField();
		parceladoField.setBackground(Color.WHITE);
		parceladoField.setBounds(37, 177, 183, 21);
		panel.add(parceladoField);
		parceladoField.setColumns(10);
		
		JLabel chequeLabel = new JLabel("Cheque:");
		chequeLabel.setBounds(10, 199, 83, 21);
		panel.add(chequeLabel);
		
		chequeField = new JTextField();
		chequeField.setBackground(Color.WHITE);
		chequeField.setBounds(37, 231, 183, 21);
		panel.add(chequeField);
		chequeField.setColumns(10);
		
		JLabel lblCrediario = new JLabel("Credi\u00E1rio:");
		lblCrediario.setBounds(10, 263, 83, 21);
		panel.add(lblCrediario);
		
		crediarioField = new JTextField();
		crediarioField.setBackground(Color.WHITE);
		crediarioField.setBounds(37, 284, 183, 20);
		panel.add(crediarioField);
		crediarioField.setColumns(10);
		
		JLabel lblFinaciamento = new JLabel("Finaciamento:");
		lblFinaciamento.setBounds(10, 310, 83, 21);
		panel.add(lblFinaciamento);
		
		finaciamentoField = new JTextField();
		finaciamentoField.setBackground(Color.WHITE);
		finaciamentoField.setBounds(37, 338, 183, 20);
		panel.add(finaciamentoField);
		finaciamentoField.setColumns(10);
		
		vistaField.setEditable(false);
		vistaField.setEditable(false);
		cartaoField.setEditable(false);
		cartaoField.setEditable(false);
		parceladoField.setEditable(false);
		parceladoField.setEditable(false);
		chequeField.setEditable(false);
		chequeField.setEditable(false);
		crediarioField.setEditable(false);
		crediarioField.setEditable(false);
		finaciamentoField.setEditable(false);
		finaciamentoField.setEditable(false);
		
		geraGraficoBalacoMensal = new GeraGraficoDeBalaco();
		graficoPanel.add(geraGraficoBalacoMensal.geraGraficoPanel(mesComboBox.getItemAt(
				mesComboBox.getSelectedIndex()),IgGraficoBalacoMensal.this,tituloJanela));

	}
	
	/** Retorna a referencia do panel que contem o gráfico.
	 * @return um <code>JPanel</code>.
	 */
	public JPanel getGraficoBalacoMesalPanel() {
		return graficoPanel;
	}

	/** Retorna a referencia do component mesComboBox.
	 * @return um <code>ComboBox</code>.
	 */
	public JComboBox<String> getMesComboBox() {
		return mesComboBox;
	}

	/** Retorna a referencia do componente receitaField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getReceitaField() {
		return receitaField;
	}

	/** Retorna a referencia do component investimentoField
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getInvestimentoField() {
		return investimentoField;
	}

	/** Retorna a referencia do component gastosField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getGastosField() {
		return gastosField;
	}

	/**Retorna a referencia do component saldoAtualField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getSaldoAtualField() {
		return saldoAtualField;
	}

	/** Retorna a referencia do geraGraficoBalacoMensal.
	 * @return um <code>JPanel</code>.
	 */
	public GeraGraficoDeBalaco getGeraGraficoBalacoMensal() {
		return geraGraficoBalacoMensal;
	}
	
	/** Retorna a referencia do component vistaField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getVistaField() {
		return vistaField;
	}

	/** Retorna a referencia do component cartaoField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getCartaoField() {
		return cartaoField;
	}

	/** Retorna a referencia do component crediarioField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getCrediarioField() {
		return crediarioField;
	}

	/** Retorna a referencia do component parceladoField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getParceladoField() {
		return parceladoField;
	}

	/** Retorna a referencia do component finaciamentoField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getFinaciamentoField() {
		return finaciamentoField;
	}

	/** Retorna a referencia do component chequeField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getChequeField() {
		return chequeField;
	}

	/**Retorna a referencia do panel graficoPanel.
	 * @return um <code>JPanel</code>.
	 */
	public JPanel getGraficoPanel() {
		return graficoPanel;
	}

	/**
	 * @param tituloJanela
	 */
	private void actionBalanco(TituloJanela tituloJanela){
		//Remove todos os Componentes do Painel.
		getGraficoBalacoMesalPanel().removeAll();
		
		//Adiciona um novo componente
		getGraficoBalacoMesalPanel().add(getGeraGraficoBalacoMensal().geraGraficoPanel(getMesComboBox().getItemAt(
				getMesComboBox().getSelectedIndex()), IgGraficoBalacoMensal.this,tituloJanela));
		
		//Revalida o painel.
		getGraficoBalacoMesalPanel().revalidate();
		
		//Repinta o painel.
		getGraficoBalacoMesalPanel().repaint();
	}
	
}//class IgGrafico()
