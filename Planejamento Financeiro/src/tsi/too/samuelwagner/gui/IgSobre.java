package tsi.too.samuelwagner.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
/**
 * A classe <code>IgSobre</code> define a janela a ser utilizada para exibir as informações do programa.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class IgSobre extends JDialog {
	private static Color corPainel = new Color(248, 248, 248);
	/**
	 * Construtor sobrecarregado da classe <code>IgSobre</code>. Monta a janela de exibição, com as informações do
	 * programa.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> com a referência da janela principal.
	 */
	public IgSobre(IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarDespesas.class.getResource("/tsi/too/samuelwagner/imagens/about.png")));

		setTitle("Sobre");
		setSize(460, 375);
		setBackground(corPainel);
		setLocationRelativeTo(igPlanejamentoFinanceiro);
		getContentPane().setBackground(new Color(87, 163, 123));
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgSobre.this.dispose();
			}
		});
		
		setResizable(true);
		getContentPane().setLayout(null);
		
		JPanel sobrePanel = new JPanel();
		sobrePanel.setBackground(corPainel);
		sobrePanel.setBounds(53, 0, 391, 337);
		getContentPane().add(sobrePanel);
		sobrePanel.setLayout(null);
		
		JLabel lblPlanejamentoFinanceiro = new JLabel("Planejamento Financeiro");
		lblPlanejamentoFinanceiro.setFont(new Font("Serif", Font.BOLD, 22));
		lblPlanejamentoFinanceiro.setBounds(10, 11, 241, 24);
		lblPlanejamentoFinanceiro.setForeground(new Color(87, 163, 123));
		sobrePanel.add(lblPlanejamentoFinanceiro);
		
		JPanel panel = new JPanel();
		panel.setBackground(corPainel);
		panel.setBorder(new TitledBorder(null, "Sobre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 46, 371, 257);
		sobrePanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane sobreScrollPane = new JScrollPane();
		panel.add(sobreScrollPane, BorderLayout.CENTER);
		
		JTextArea sobreTextArea = new JTextArea();
		sobreTextArea.setEditable(false);
		sobreTextArea.setText("Programa desenvolvido como trabalho final da disciplina Tecnologia em Orienta\u00E7\u00E3o \u00E0 Objetos, ministrada no Instituto Federal do Sudeste de Minas Gerais, campus Barbacena, para o curso de Tecnologia em Sistemas para Internet.\r\n\r\nDesenvolvido por:\r\n\tSamuel Gon\u00E7alves\r\n\tWagner Almeida\r\n\r\nProfessor: Márlon");
		sobreTextArea.setWrapStyleWord(true);
		sobreTextArea.setLineWrap(true);
		sobreTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		sobreScrollPane.setViewportView(sobreTextArea);
		
		JLabel lblTsi = new JLabel("TSI - 2015");
		lblTsi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTsi.setBounds(163, 312, 65, 14);
		sobrePanel.add(lblTsi);
		setModal(true);
		setVisible(true);
	}//IgSobre
}//class IgSobre
