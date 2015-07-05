package tsi.too.samuelwagner.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.controle.ControleRenda;
import tsi.too.samuelwagner.enumeracoes.RotuloJanelaRendaERendaMensal;
import tsi.too.samuelwagner.enumeracoes.TituloJanela;
import tsi.too.samuelwagner.trataeventos.TratadorEventoRendaMensal;

import com.toedter.calendar.JCalendar;

/**A classe <code>IgCadastraRendaMensal</code> é responsável por construir a janela para adição de novas
 * rendas mensais.
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 *
 */
public class IgCadastraRendaMensal extends JDialog {
	
	private JButton adicionarButton;
	private JButton	cancelaButton;
	private JCalendar jCalendar;
	private JTextField areaValorField;
	private JComboBox<String> tipoRendaBox;
	private static Color corPainel = new Color(248, 248, 248);
	
	/**Construtor da Janela rendaMensal.
	 @param tituloJanela <code>TituloJanela</code> referencia do titulo da janela.
	 * @param igPlanejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> referencia da Janela Principal.
	 */
	public IgCadastraRendaMensal(TituloJanela tituloJanela, IgPlanejamentoFinanceiro igPlanejamentoFinanceiro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarDespesas.class.getResource("/tsi/too/samuelwagner/imagens/money393.png")));
		
		setTitle(RotuloJanelaRendaERendaMensal.TITULO.getDescricao() + tituloJanela.getTitulo());
		setBounds(100, 100, 321, 413);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		getContentPane().setBackground(corPainel);
		getContentPane().setLayout(null);
		//Cria um Painel para adicionar os Buttons, ja definindo o Layout.
		JPanel panelButton = new JPanel();
		panelButton.setBounds(10, 325, 296, 52);
		panelButton.setBackground(corPainel);
		//Cria os buttons adicionar e cancelar.
		adicionarButton = new JButton("Adicionar");
		adicionarButton.setBounds(47, 11, 116, 35);
		adicionarButton.setIcon(new ImageIcon(IgCadastraRendaMensal.class.getResource("/tsi/too/samuelwagner/imagens/add_list.png")));
		cancelaButton = new JButton("Cancelar");
		cancelaButton.setBounds(170, 11, 116, 35);
		cancelaButton.setIcon(new ImageIcon(IgCadastraRendaMensal.class.getResource("/tsi/too/samuelwagner/imagens/delete.png")));
		
		//Adiciona aos Buttons texto de Ajuda.
		adicionarButton.setToolTipText("Adiciona a "+tituloJanela.getTitulo()+".");
		cancelaButton.setToolTipText("Cancela o cadastro da " + tituloJanela.getTitulo()+".");
		panelButton.setLayout(null);
		
		///Adiciona os Buttons a Janela.
		panelButton.add(adicionarButton);
		panelButton.add(cancelaButton);
		
		//Adiciona o panelButton a Janela do Programa.
		getContentPane().add(panelButton);
		
		//Cria um painel para adicionar ao centro da Janela.
		JPanel panelCentral = new JPanel(null);
		panelCentral.setBorder(new TitledBorder(null, "Nova Renda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentral.setBounds(10, 11, 296, 303);
		panelCentral.setBackground(corPainel);
		getContentPane().add(panelCentral);
		
		//Cria 3 JLabel.
		JLabel valorLabel = new JLabel("Valor: "),
			   tipoRendaLabel = new JLabel("Tipo de Renda:");
		valorLabel.setBounds(20, 66, 54, 20);
		tipoRendaLabel.setBounds(20, 20, 94, 20);
		tipoRendaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		valorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		//Cria um JTextField.
		areaValorField = new JTextField();
		areaValorField.setBounds(130, 66, 151, 20);
		areaValorField.setToolTipText("Valor da Renda.");
		//Chama a função para precher o JComboBox.
		//Cria o JComboBox e preenche com seus respectivos falores.
		tipoRendaBox = new JComboBox<String>(prencheComboBox());
		//tipoRendaBox.setToolTipText("Tipos de Renda.");
		//Desativa a edição do comboBox.
		tipoRendaBox.setEditable(false);
		panelCentral.setLayout(null);
		
		//Adiciona os componentes ao panel Central.
		panelCentral.add(tipoRendaLabel);
		panelCentral.add(tipoRendaBox);
		panelCentral.add(valorLabel);
		panelCentral.add(areaValorField);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setBackground(corPainel);
		dataPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Data de Recebimento", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		dataPanel.setBounds(20, 97, 262, 195);
		panelCentral.add(dataPanel);
		//Cria um JCalendar.
		jCalendar = new JCalendar(Calendar.getInstance());
		dataPanel.add(jCalendar);
		jCalendar.setToolTipText("Data da Renda.");
		tipoRendaBox.setBounds(130, 20, 151, 20);
		
		//Define as teclas Mnemonic dos Buttons.
		adicionarButton.setMnemonic(KeyEvent.VK_A);
		cancelaButton.setMnemonic(KeyEvent.VK_C);
		
		//Cria o tratador de Evento e Adiciona ele aos Buttons
		TratadorEventoRendaMensal eventoRendaERendaMensal = new TratadorEventoRendaMensal(tituloJanela,
																	  IgCadastraRendaMensal.this,igPlanejamentoFinanceiro);
		adicionarButton.addActionListener(eventoRendaERendaMensal);
		cancelaButton.addActionListener(eventoRendaERendaMensal);
		
		tipoRendaBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(RotuloJanelaRendaERendaMensal.NOVA_RENDA.getDescricao().equalsIgnoreCase(
						(tipoRendaBox.getItemAt(tipoRendaBox.getSelectedIndex())))){
					new IgCadastrarRenda(TituloJanela.RENDA, igPlanejamentoFinanceiro);
					tipoRendaBox.setModel(new DefaultComboBoxModel<String>(prencheComboBox()));
				}
			}
		});
		
		//Define se a Janela e redimensionável e visivel. 
		setModal(true);
		setLocationRelativeTo(igPlanejamentoFinanceiro);
		setResizable(false);
		setVisible(true);
	}
	
	/**Esse método obtém e preenche o comboBox.
	 */
	private String[] prencheComboBox(){
		ControleRenda controle = new ControleRenda();
		String rendas[] = new String[controle.retornaNumeroDeRendas()+1];
		int indice;
		for(indice = 0;indice < controle.retornaNumeroDeRendas();indice++){
			rendas[indice] = controle.obtemRenda(indice).getDescricao();
		}
		rendas[indice] = RotuloJanelaRendaERendaMensal.NOVA_RENDA.getDescricao();
		return rendas;
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

	/**Retornar a referencia do jCalendar.
	 * @return um <code>JCalendar</code>.
	 */
	public JCalendar getjCalendar() {
		return jCalendar;
	}
	
	/**Retorna a referencia da areaValorField.
	 * @return um <code>JTextField</code>.
	 */
	public JTextField getAreaValorField() {
		return areaValorField;
	}

	/**Retorna a Referencia do tipoRendaBox.
	 * @return um <code>JComboBox</code>.
	 */
	public JComboBox<String> getTipoRendaBox() {
		return tipoRendaBox;
	}
}