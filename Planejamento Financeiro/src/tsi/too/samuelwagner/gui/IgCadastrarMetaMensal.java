package tsi.too.samuelwagner.gui;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.controle.ControleMetaMensal;
import tsi.too.samuelwagner.enumeracoes.RotuloJanelaMetaMensal;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.validacoes.Validador;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
/**
 * A classe <code>IgCadastrarMetaMensal</code> é responsável por criar a interface gráfica responsável por 
 * cadastrar uma nova meta pro usuário.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgCadastrarMetaMensal extends JDialog {
	private ControleMetaMensal controleMetaMensal;
	private JComboBox<String> categoriaComboBox;
	private JMonthChooser mesMonthChooser;
	private JYearChooser anoYearChooser;
	private JTextField valorTextField;
	private JTextField porcentagemTextField;
	private static Color corPainel = new Color(248, 248, 248);
	
	/**
	 * Construtor da classe <code>IgCadastrarMetaMensal</code>.
	 * @param planejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code>
	 * @param controleMetaMensal <code>ControleMetaMensal</code>
	 */
	public IgCadastrarMetaMensal(IgPlanejamentoFinanceiro planejamentoFinanceiro, ControleMetaMensal controleMetaMensal) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarDespesas.class.getResource("/tsi/too/samuelwagner/imagens/edit.png")));

		setTitle("Cadastrar Meta Mensal");
		
		//Obtem a recerência do objeto responsável por persistir os dados.
		this.controleMetaMensal = controleMetaMensal;
		setBackground(corPainel);
		//Define o tamanho da janela.
		setSize(367, 274);
		
		//Define a janela como modal.
		setModal(true);
		
		//Define o posicionamento da janela.
		setLocationRelativeTo(planejamentoFinanceiro);
		
		//Cria o cadastrarMetaPanel, define suas configurações e adiciona a janela.
		JPanel cadastrarMetaPanel = new JPanel();
		cadastrarMetaPanel.setBackground(corPainel);
		cadastrarMetaPanel.setBorder(new TitledBorder(null, "Cadastrar Meta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(cadastrarMetaPanel, BorderLayout.CENTER);
		cadastrarMetaPanel.setLayout(null);
		
		//Cria um label e adiciona ao painel.
		JLabel categoriaLabel = new JLabel("Categoria:");
		categoriaLabel.setBounds(12, 30, 122, 16);
		cadastrarMetaPanel.add(categoriaLabel);
		
		//Cria o comboBox e adiciona a janela.
		categoriaComboBox = new JComboBox <String>();
		categoriaComboBox.setToolTipText("Selecionar uma Categoria");
		categoriaComboBox.setBounds(103, 26, 236, 25);
		cadastrarMetaPanel.add(categoriaComboBox);
	
		//Carrega os valores do comboBox.
		carregarValoresComboBox();
		
		
		//Cria o mesLabel e adiciona a janela.
		JLabel mesLabel = new JLabel("M\u00EAs:");
		mesLabel.setBounds(12, 75, 75, 16);
		cadastrarMetaPanel.add(mesLabel);
		
		//Cria o JMonthChooser e adiciona ao painel.
		mesMonthChooser = new JMonthChooser();
		mesMonthChooser.setBounds(103, 70, 110, 26);
		cadastrarMetaPanel.add(mesMonthChooser);
		
		//Cria o label ano e adiciona a janela.
		JLabel anoLabel = new JLabel("Ano:");
		anoLabel.setBounds(223, 75, 33, 16);
		cadastrarMetaPanel.add(anoLabel);
		
		//Cria o YearChooser e adiciona a janela.
		anoYearChooser = new JYearChooser();
		anoYearChooser.getSpinner().setLocation(0, 71);
		anoYearChooser.setBounds(253, 71, 86, 25);
		cadastrarMetaPanel.add(anoYearChooser);
		
		//Cria o botão cadastrar e adiciona a janela.
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setIcon(new ImageIcon(IgCadastrarMetaMensal.class.getResource("/tsi/too/samuelwagner/imagens/add_list.png")));
		cadastrarButton.setToolTipText("Cadastrar uma Meta");
		cadastrarButton.setMnemonic(KeyEvent.VK_C);
		cadastrarButton.setBounds(103, 190, 116, 35);
		cadastrarMetaPanel.add(cadastrarButton);
		
		//Registra o tratador de eventos do botão cadastrarMetaPanel
		cadastrarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarMetaMensal();
			}
		});
		
		//Cria o botão cancelar e adiciona a janela.
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setIcon(new ImageIcon(IgCadastrarMetaMensal.class.getResource("/tsi/too/samuelwagner/imagens/delete.png")));
		cancelarButton.setToolTipText("Cancelar Opera\u00E7\u00E3o");
		cancelarButton.setMnemonic(KeyEvent.VK_A);
		cancelarButton.setBounds(223, 190, 116, 35);
		cadastrarMetaPanel.add(cancelarButton);
		
		//Define o label do valor e adiciona ao painel.
		JLabel valorLabel = new JLabel("Valor:");
		valorLabel.setBounds(12, 109, 55, 16);
		cadastrarMetaPanel.add(valorLabel);
		
		//Define o valorTextField e adiciona ao painel.
		valorTextField = new JTextField();
		valorTextField.setToolTipText("Valor da Meta");
		valorTextField.setBounds(103, 107, 95, 20);
		cadastrarMetaPanel.add(valorTextField);
		valorTextField.setColumns(10);
		
		porcentagemTextField = new JTextField();
		porcentagemTextField.setText("70");
		porcentagemTextField.setToolTipText("Porcentagem a ser atingida para receber um aviso do programa.");
		porcentagemTextField.setBounds(102, 138, 43, 20);
		cadastrarMetaPanel.add(porcentagemTextField);
		porcentagemTextField.setColumns(10);
		
		JLabel lblPorcentagem = new JLabel("Porcentagem:");
		lblPorcentagem.setBounds(12, 141, 86, 14);
		cadastrarMetaPanel.add(lblPorcentagem);
		
		JLabel label = new JLabel("%");
		label.setBounds(155, 141, 46, 14);
		cadastrarMetaPanel.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 180, 327, 2);
		cadastrarMetaPanel.add(separator);
		
		//Registra o tratador de eventos do botão fechar.
		cancelarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IgCadastrarMetaMensal.this.dispose();
			}
		});
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgCadastrarMetaMensal.this.dispose();
			}
		});
		
		//Define a janela como visivel.
		setVisible(true);
	}//IgCadastrarMetaMensal()
	
	/**
	 * Carrega os nomes das categorias salvas em disco para o comboBox.
	 */
	private void carregarValoresComboBox(){
		String[] nomesCategorias = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().obterNomesCategorias();
		if(nomesCategorias == null) return;
		DefaultComboBoxModel<String> nomes = new DefaultComboBoxModel<String>();
		for(String nome : nomesCategorias)
			nomes.addElement(nome);
		
		categoriaComboBox.setModel(nomes);
	}//carregarValoresComboBox()
	
	/**
	 * Cadastra uma nova meta da categoria caso esta ja esteja cadastrada previamente.
	 */
	private void cadastrarMetaMensal(){
		Calendar mesAno = Calendar.getInstance();
		
		mesAno.set(anoYearChooser.getYear(), mesMonthChooser.getMonth(), mesAno.get(Calendar.DAY_OF_MONTH));
		if(categoriaComboBox.getSelectedIndex() != -1){
			//Verifica se o campo valor é vazio.
			if(Validador.validaCampoVazio(valorTextField.getText())){
				showMessageDialog(this, RotuloJanelaMetaMensal.VAZIO_VALOR.getDescricao(),RotuloJanelaMetaMensal.TITULO.getDescricao(), ERROR_MESSAGE);
				return;
			}
			//Verifica se o valor da porcentagem é válido.
			if(Validador.validaCampoVazio(porcentagemTextField.getText()) || !Validador.validaPorcentagem(porcentagemTextField.getText())){
				showMessageDialog(this, RotuloJanelaMetaMensal.PORCENTAGEM.getDescricao(),RotuloJanelaMetaMensal.TITULO.getDescricao(), ERROR_MESSAGE);
				return;
			}
			controleMetaMensal.cadastrarMetaMensal(categoriaComboBox.getItemAt(categoriaComboBox.getSelectedIndex()), mesAno, valorTextField.getText(),Integer.parseInt(porcentagemTextField.getText()));
			valorTextField.setText("");
		}
		else
			showMessageDialog(this,"Não há categoria da meta selecionada","Cadastrar Meta",ERROR_MESSAGE);
	}//cadastrarCategoria()
}//class IgCadastrarMetamensal
