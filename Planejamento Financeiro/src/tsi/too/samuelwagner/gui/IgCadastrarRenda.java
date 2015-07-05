package tsi.too.samuelwagner.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import tsi.too.samuelwagner.enumeracoes.RotuloJanelaRendaERendaMensal;
import tsi.too.samuelwagner.enumeracoes.TituloJanela;
import tsi.too.samuelwagner.trataeventos.TratadorEventoRenda;

/**A classe <code>IgCadastrarRenda</code> é responsável por construir a janela para adição de novas
 * rendas.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class IgCadastrarRenda extends JDialog {
	
	private JButton adicionarButton;
	private JButton	cancelaButton;
	private JTextField areaDescricao;
	private static Color corPainel = new Color(248, 248, 248);

	/** Constrói a janela de cadastro de renda.
	 * @param tituloJanela <code>TituloJanela</code> referencia do titulo da janela.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> referencia da Janela Principal.
	 */
	public IgCadastrarRenda(TituloJanela tituloJanela, IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarDespesas.class.getResource("/tsi/too/samuelwagner/imagens/money_bag.png")));
		
		//Define o titulo da Janela, o tamanho da Janela e o Layout utilizado na Janela.
		setTitle(RotuloJanelaRendaERendaMensal.TITULO.getDescricao() + tituloJanela.getTitulo());
		setBounds(100, 100, 307, 108);
		setBackground(corPainel);
		//Cria um JLabel e define seu tamanho.
		JLabel descricaoLabel = new JLabel("Descrição: ");
		descricaoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		descricaoLabel.setBounds(10,16, 67,14);
	
		//Cria um JTextField e difene suas propriedades.
		areaDescricao = new JTextField();
		areaDescricao.setToolTipText("Informa a Descri\u00E7\u00E3o da Renda.");
		areaDescricao.setColumns(20);
		areaDescricao.setBounds(87, 13, 198,20);
		getContentPane().setLayout(null);
		
		//Adiciona o JLabel e o JTextField a Janela.
		getContentPane().add(descricaoLabel);
		getContentPane().add(areaDescricao);
		getContentPane().setBackground(corPainel);
		
		//Cria os JButton e adiciona eles a Janela.
		adicionarButton = new JButton("Add");
		adicionarButton.setIcon(new ImageIcon(IgCadastrarRenda.class.getResource("/tsi/too/samuelwagner/imagens/checkmark.png")));
		adicionarButton.setBounds(97, 44, 90, 25);
		adicionarButton.setToolTipText("Cadastra a Renda.");
		cancelaButton = new JButton("Sair");
		cancelaButton.setIcon(new ImageIcon(IgCadastrarRenda.class.getResource("/tsi/too/samuelwagner/imagens/delete.png")));
		cancelaButton.setBounds(195, 44, 90, 25);
		cancelaButton.setToolTipText("Cancela o Cadastro.");
		getContentPane().add(adicionarButton);
		getContentPane().add(cancelaButton);
		
		//Cria o tratador de Evento e Adiciona ele aos Buttons
		TratadorEventoRenda tratadorEventoRenda = new TratadorEventoRenda(this, tituloJanela);
		adicionarButton.addActionListener(tratadorEventoRenda);
		cancelaButton.addActionListener(tratadorEventoRenda);
		
		//Define as teclas Mnemonic dos Buttons.
		adicionarButton.setMnemonic(KeyEvent.VK_A);
		cancelaButton.setMnemonic(KeyEvent.VK_S);
		
		//Define o tratador de evento quando o usuario clicar para fechar a janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		//Define se a Janela e redimensionável e visivel. 
		setModal(true);
		setLocationRelativeTo(igPlanejamentoFinanceiro);
		setResizable(false);
		setVisible(true);
	}

	/**Retorna a referencia do adicionarButton.
	 * @return um <code>JButton</code>.
	 */
	public JButton getAdicionarButton() {
		return adicionarButton;
	}

	/**Retorna a referencia do cancelaButton.
	 * @return um <code>JButton</code>.
	 */
	public JButton getCancelaButton() {
		return cancelaButton;
	}

	/**Retorna a referencia da areaDescricao.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getAreaDescricao() {
		return areaDescricao;
	}
}