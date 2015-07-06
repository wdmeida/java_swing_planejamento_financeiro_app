package tsi.too.samuelwagner.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.enumeracoes.RotuloAjuda;
/**
 * A classe <code>IgAjuda</code> é responsável por contruir a tela de informações ao usuário.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgAjuda extends JDialog {
	private Color corBase = new Color(248,248,248);
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton despesasRadioButton;
	private JRadioButton visualizarDespesaRadioButton;
	private JRadioButton metasRadioButton;
	private JRadioButton visualizarMetasRadionButton;
	private JRadioButton rendaRadioButton;
	private JRadioButton graficosRadioButton;
	private JRadioButton salvarRadioButton;
	private JTextArea respostaTextArea;
	/**
	 * Construtor default da classe <code>IgAjuda</code> responsável por contruir a tela de ajuda.
	 * 
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> com a referência da classe principal.
	 */
	public IgAjuda(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		setTitle("Ajuda");
		
		getContentPane().setBackground(corBase);
		getContentPane().setLayout(null);
		
		JPanel opcaoPanel = new JPanel();
		opcaoPanel.setBackground(corBase);
		opcaoPanel.setBorder(new TitledBorder(null, "Como posso ajudar?", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		opcaoPanel.setBounds(10, 11, 251, 263);
		getContentPane().add(opcaoPanel);
		opcaoPanel.setLayout(null);
		
		despesasRadioButton = new JRadioButton("Como cadastro minhas despesas?");
		despesasRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				exibirAjuda();
			}
		});
		
		buttonGroup.add(despesasRadioButton);
		despesasRadioButton.setBounds(6, 50, 228, 23);
		opcaoPanel.add(despesasRadioButton);
		despesasRadioButton.setBackground(corBase);
		
		metasRadioButton = new JRadioButton("Como defino uma meta?");
		buttonGroup.add(metasRadioButton);
		metasRadioButton.setBackground(corBase);
		metasRadioButton.setBounds(6, 102, 211, 23);
		opcaoPanel.add(metasRadioButton);
		metasRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				exibirAjuda();
			}
		});
		
		visualizarMetasRadionButton = new JRadioButton("Posso visualizar minhas metas?");
		buttonGroup.add(visualizarMetasRadionButton);
		visualizarMetasRadionButton.setBackground(corBase);
		visualizarMetasRadionButton.setBounds(6, 128, 184, 23);
		opcaoPanel.add(visualizarMetasRadionButton);
		visualizarMetasRadionButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				exibirAjuda();
			}
		});
		
		visualizarDespesaRadioButton = new JRadioButton("Como visualizar minhas despesas?");
		buttonGroup.add(visualizarDespesaRadioButton);
		visualizarDespesaRadioButton.setBounds(6, 76, 211, 23);
		opcaoPanel.add(visualizarDespesaRadioButton);
		visualizarDespesaRadioButton.setBackground(corBase);
		visualizarDespesaRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				exibirAjuda();
			}
		});
		
		rendaRadioButton = new JRadioButton("E se eu quiser cadastrar uma renda?");
		buttonGroup.add(rendaRadioButton);
		rendaRadioButton.setBounds(6, 154, 211, 23);
		rendaRadioButton.setBackground(corBase);
		opcaoPanel.add(rendaRadioButton);
		rendaRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				exibirAjuda();
			}
		});
		
		graficosRadioButton = new JRadioButton("Posso visualizar os valores em gr\u00E1ficos?");
		buttonGroup.add(graficosRadioButton);
		graficosRadioButton.setBounds(6, 180, 228, 23);
		opcaoPanel.add(graficosRadioButton);
		graficosRadioButton.setBackground(corBase);
		graficosRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				exibirAjuda();
			}
		});
		
		salvarRadioButton = new JRadioButton("E se quiser salvar um gr\u00E1fico?");
		buttonGroup.add(salvarRadioButton);
		salvarRadioButton.setBackground(corBase);
		salvarRadioButton.setBounds(6, 206, 211, 23);
		opcaoPanel.add(salvarRadioButton);
		salvarRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				exibirAjuda();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(corBase);
		panel.setBorder(new TitledBorder(null, "Basta apenas...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(290, 11, 430, 261);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		respostaTextArea = new JTextArea();
		respostaTextArea.setEditable(false);
		respostaTextArea.setWrapStyleWord(true);
		respostaTextArea.setLineWrap(true);
		scrollPane.setViewportView(respostaTextArea);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarCategoria.class.getResource("/tsi/too/samuelwagner/imagens/online_support.png")));
		setSize(746,321);
		
		setLocationRelativeTo(igPlanejamentoFinanceiro);
		
		//Define a janela como não modal.
		setModal(true);
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgAjuda.this.dispose();
			}
		});
		
		setVisible(true);
	}//IgAjuda()
	
	/**
	 * Exibe a ajuda relativa a dúvida do usuário.
	 */
	private void exibirAjuda(){
		if(despesasRadioButton.isSelected()) respostaTextArea.setText(RotuloAjuda.CADASTRO_DESPESA.getDescricao());
		if(visualizarDespesaRadioButton.isSelected()) respostaTextArea.setText(RotuloAjuda.VISUALIZAR_DESPESA.getDescricao());
		if(metasRadioButton.isSelected()) respostaTextArea.setText(RotuloAjuda.CADASTRAR_META.getDescricao());
		if(visualizarMetasRadionButton.isSelected()) respostaTextArea.setText(RotuloAjuda.VISUALIZAR_META.getDescricao());
		if(rendaRadioButton.isSelected()) respostaTextArea.setText(RotuloAjuda.CADASTRAR_RENDA.getDescricao());
		if(graficosRadioButton.isSelected()) respostaTextArea.setText(RotuloAjuda.VISUALIZAR_GRAFICO.getDescricao());
		if(salvarRadioButton.isSelected()) respostaTextArea.setText(RotuloAjuda.SALVAR_GRAFICO.getDescricao());			
	}
}//class IgAjuda
