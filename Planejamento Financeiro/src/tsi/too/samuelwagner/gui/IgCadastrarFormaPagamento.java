package tsi.too.samuelwagner.gui;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
/**
 * Esta classe define a interface gráfica responsável por cadastrar uma nova forma de pagamento.
 * @author Wagner Almeida
 * @author Samuel Gonçalves
 *
 */
public class IgCadastrarFormaPagamento extends JDialog {
	private JTextField formaPagamentoTextField;
	private IgCadastrarDespesas cadastrarDespesas;
	private GerenciamentoDeFinanca gerenciamentoDeFinanca;
	
	/**
	 * Construtor da classe <code>IgCadastrarFormaPagamento</code>, responsável por construir a janela 
	 * de cadastro de nova forma de pagamento.
	 * @param cadastrarDespesas <code>IgCadastrarDespesa</code> com a referência da janela responsável por
	 * abrir a janela de cadastro.
	 */
	public IgCadastrarFormaPagamento(IgCadastrarDespesas cadastrarDespesas) {
		setTitle("Cadastrar Forma de Pagamento");
		setModal(true);
		
		this.gerenciamentoDeFinanca = GerenciamentoDeFinanca.getGerenciamentoFincanca();
		this.cadastrarDespesas = cadastrarDespesas;
		//Define o tamanho da janela.
		setSize(351, 140);
		
		JPanel formaPagamentoPanel = new JPanel();
		formaPagamentoPanel.setBorder(new TitledBorder(null, "Forma de Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(formaPagamentoPanel, BorderLayout.CENTER);
		formaPagamentoPanel.setLayout(null);
		
		JLabel novaFormaLabel = new JLabel("Nova Forma:");
		novaFormaLabel.setBounds(12, 34, 76, 16);
		formaPagamentoPanel.add(novaFormaLabel);
		
		setLocationRelativeTo(this.cadastrarDespesas);
		
		formaPagamentoTextField = new JTextField();
		formaPagamentoTextField.setBounds(106, 32, 219, 20);
		formaPagamentoPanel.add(formaPagamentoTextField);
		formaPagamentoTextField.setColumns(10);
		
		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setMnemonic(KeyEvent.VK_C);
		cadastrarButton.setBounds(116, 64, 98, 26);
		formaPagamentoPanel.add(cadastrarButton);
		
		//Registra o tratador de eventos do cadastrarButton
		cadastrarButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrarFormaPagamento();
			}
		});
		
		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton.setMnemonic(KeyEvent.VK_A);
		cancelarButton.setBounds(227, 64, 98, 26);
		formaPagamentoPanel.add(cancelarButton);
		cancelarButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IgCadastrarFormaPagamento.this.dispose();
			}
		});
		
		//Registra o tratador de eventos da janela caso esta seja fechada.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgCadastrarFormaPagamento.this.dispose();
			}
			
		});
		
		setVisible(true);
	}//IgCadastrarFormaPagamento()
	
	/**
	 * Cadastra a nova forma de pagamento preenchida pelo usuário.
	 */
	private void cadastrarFormaPagamento() {
		if(gerenciamentoDeFinanca.getControleFormaPagamento().cadastrarFormaPagamento(cadastrarDespesas, formaPagamentoTextField.getText()))
			showMessageDialog(this, "Forma de pagamento cadastrada com sucesso.","Cadastrar Forma de Pagamento",INFORMATION_MESSAGE);
	}
}//class IgCadastrarFormaPagamento
