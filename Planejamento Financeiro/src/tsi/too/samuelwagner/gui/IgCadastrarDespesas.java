package tsi.too.samuelwagner.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.enumeracoes.RotuloJanelaDespesa;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.trataeventos.TratadorEventoCadastroDespesa;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**A classe <code>IgCadastrarDespesa</code> � respons�vel por construir a janela para adi��o de novas
 * despesas do usu�rio.
 * @author Samuel Gon�alves
 * @author Wagner Almeida
 */
public class IgCadastrarDespesas extends JDialog {

	private IgPlanejamentoFinanceiro planejamentoFinanceiro;
	private GerenciamentoDeFinanca gerenciamentoDeFinanca;
	private JTextField descricaoTextField;
	private JDateChooser dataDespesaDateChooser;
	private JComboBox<String> formaPagamentoComboBox;
	private JTextField numeroChequeTextField;
	private JTextField quantidadeParcelasTextField;
	private JTextField valorTextField;
	private JDateChooser dataPagamentoDateChooser;
	private JComboBox<String> categoriaComboBox;
	private JButton cadastrarDespesaButton;
	private JLabel camposObrigatoriosLabel;
	private static Color corPainel = new Color(248, 248, 248);
	
	/**
	 * Construtor da classe <code>IgCadastrarDespesas</code>, respons�vel por construir a janela gr�fica de cadastro de
	 * despesa.
	 * @param planejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> com a refer�ncia da classe principal do aplicativo.
	 */
	public IgCadastrarDespesas(IgPlanejamentoFinanceiro planejamentoFinanceiro) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarDespesas.class.getResource("/tsi/too/samuelwagner/imagens/excel.png")));
		//Obt�m a refer�ncia da janela principal
		this.planejamentoFinanceiro = planejamentoFinanceiro;
		
		//Obt�m a refer�ncia da classe que ger�ncia todas as outras.
		this.gerenciamentoDeFinanca = GerenciamentoDeFinanca.getGerenciamentoFincanca();
		
		//Define o t�tulo da janela.
		setTitle(RotuloJanelaDespesa.TITULO.getDescricao());
		
		//Define a janela como modal.
		setModal(true);
		
		//Define o tamanho da janela e a posi��o relativa a janela principal.
		setSize(580, 400);
		setLocationRelativeTo(planejamentoFinanceiro);
		
		//Define o ouvinte que responder� pelos eventos da janela.
		TratadorEventoCadastroDespesa trataEvento = new TratadorEventoCadastroDespesa(this,planejamentoFinanceiro);
				
		//Cria o painel principal da janela e adiciona ao painel.
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cadastrar Nova Despesa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(corPainel);
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//Cria o painel de informa��o das despesas e adiciona ao painel principal.
		JPanel infoDespesaPanel = new JPanel();
		infoDespesaPanel.setBackground(corPainel);
		infoDespesaPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Gerais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoDespesaPanel.setBounds(12, 34, 539, 200);
		panel.add(infoDespesaPanel);
		infoDespesaPanel.setLayout(null);
		
		//Cria os r�tulos e adiciona ao painel.
		JLabel descricaoLabel = new JLabel("Descri\u00E7\u00E3o:");
		descricaoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		descricaoLabel.setBounds(13, 37, 66, 16);
		infoDespesaPanel.add(descricaoLabel);
		
		JLabel dataDespesaLabel = new JLabel("Data da Despesa:");
		dataDespesaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dataDespesaLabel.setBounds(13, 67, 249, 16);
		infoDespesaPanel.add(dataDespesaLabel);
		
		JLabel dataPagamentoLabel = new JLabel("Data do Pagamento:");
		dataPagamentoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		dataPagamentoLabel.setBounds(280, 67, 122, 16);
		infoDespesaPanel.add(dataPagamentoLabel);
		
		//Cria o descricaoTextField e adiciona ao painel.
		descricaoTextField = new JTextField();
		descricaoTextField.setToolTipText(RotuloJanelaDespesa.MSG_DESCRICAO.getDescricao());
		descricaoTextField.setForeground(Color.BLACK);
		descricaoTextField.setBounds(140, 35, 387, 20);
		infoDespesaPanel.add(descricaoTextField);
		descricaoTextField.setColumns(10);
		
		JLabel valorDespesaLabel = new JLabel("Valor da Despesa:");
		valorDespesaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		valorDespesaLabel.setBounds(13, 97, 109, 16);
		infoDespesaPanel.add(valorDespesaLabel);
		
		//Cria dataDespesaDateChooser e o dataPagamentoDateChooser e adiciona ao painel.
		dataDespesaDateChooser = new JDateChooser();
		dataDespesaDateChooser.setToolTipText(RotuloJanelaDespesa.MGS_DESPESA__DATA.getDescricao());
		dataDespesaDateChooser.setBounds(140, 65, 122, 20);
		infoDespesaPanel.add(dataDespesaDateChooser);
		
		dataPagamentoDateChooser = new JDateChooser();
		dataPagamentoDateChooser.setBounds(407, 65, 121, 20);
		dataPagamentoDateChooser.setToolTipText(RotuloJanelaDespesa.MGS_PAGAMENTO_DATA.getDescricao());
		infoDespesaPanel.add(dataPagamentoDateChooser);
		
		JLabel formaDePagamentoLabel = new JLabel("Forma de Pagamento:");
		formaDePagamentoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		formaDePagamentoLabel.setBounds(13, 130, 130, 16);
		infoDespesaPanel.add(formaDePagamentoLabel);
		
		//Cria o formaPagamentoComboBox e adiciona ao painel.
		formaPagamentoComboBox = new JComboBox<String>();
		formaPagamentoComboBox.setBounds(140, 126, 220, 25);
		infoDespesaPanel.add(formaPagamentoComboBox);
		
		//Carrega as formas de pagamento para a janela.
		carregarFormasPagamento();
		
		//Define para que nenhuma forma de pagamento esteja pr� selecionada.
		formaPagamentoComboBox.setSelectedIndex(0);
		
		formaPagamentoComboBox.addActionListener(trataEvento);	
		
		//Cria o JComboBox das categorias e adiciona ao painel.
		categoriaComboBox = new JComboBox<String>();
		categoriaComboBox.setBounds(140, 159, 220, 25);
		infoDespesaPanel.add(categoriaComboBox);
		
		//Carrega as categorias para a janela.
		carregarCategorias();
		
		//Define para que nenhuma categoria esteja pr� selecionada.
		categoriaComboBox.setSelectedIndex(0);
		
		//Registra o tratador de ventos do categoriaComboBox.
		categoriaComboBox.addActionListener(trataEvento);
		
		JLabel categoriaLabel = new JLabel("Categoria:");
		categoriaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		categoriaLabel.setBounds(13, 163, 78, 16);
		infoDespesaPanel.add(categoriaLabel);
		
		//Cria o valorTextField e adiciona ao painel.
		valorTextField = new JTextField();
		valorTextField.setToolTipText(RotuloJanelaDespesa.MSG_VALOR.getDescricao());
		valorTextField.setBounds(140, 95, 100, 20);
		infoDespesaPanel.add(valorTextField);
		valorTextField.setColumns(10);
				
		//Cria o cadastrarDespesaButton e adiciona ao painel.
		cadastrarDespesaButton = new JButton("Cadastrar");
		cadastrarDespesaButton.setIcon(new ImageIcon(IgCadastrarDespesas.class.getResource("/tsi/too/samuelwagner/imagens/add_list.png")));
		cadastrarDespesaButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		cadastrarDespesaButton.setMnemonic(KeyEvent.VK_C);
		cadastrarDespesaButton.setBounds(284, 315, 130, 33);
		panel.add(cadastrarDespesaButton);
		
		//Registra o tratador de eventos do bot�o cadastrarDespesaButton.
		cadastrarDespesaButton.addActionListener(trataEvento);
		
		//Cria o fecharButton e adiciona ao painel.
		JButton fecharButton = new JButton("Cancelar");
		fecharButton.setIcon(new ImageIcon(IgCadastrarDespesas.class.getResource("/tsi/too/samuelwagner/imagens/delete.png")));
		fecharButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		fecharButton.setMnemonic(KeyEvent.VK_A);
		fecharButton.setBounds(424, 315, 130, 33);
		panel.add(fecharButton);
		
		//Registra o tratador de eventos do bot�o fechar.
		fecharButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IgCadastrarDespesas.this.dispose();
			}
		});
		
		//Cria o painel de informa��es adicionais e adicionais.
		JPanel infoAdicionalPanel = new JPanel();
		infoAdicionalPanel.setBackground(corPainel);
		infoAdicionalPanel.setBorder(new TitledBorder(null, "Informa\u00E7\u00F5es Adicionais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoAdicionalPanel.setBounds(12, 246, 539, 65);
		panel.add(infoAdicionalPanel);
		infoAdicionalPanel.setLayout(null);
		
		//Cria os r�tulos do painel adicional e insere no painel.
		JLabel lblNmeroDeParcelas = new JLabel("N\u00FAmero de Parcelas:");
		lblNmeroDeParcelas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNmeroDeParcelas.setBounds(12, 33, 130, 16);
		infoAdicionalPanel.add(lblNmeroDeParcelas);
		JLabel numeroChequeLabel = new JLabel("N\u00FAmero do Cheque:");
		numeroChequeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		numeroChequeLabel.setBounds(262, 33, 118, 16);
		infoAdicionalPanel.add(numeroChequeLabel);
		
		//Cria os jTextField do painel adicional e insere no neste.
		quantidadeParcelasTextField = new JTextField();
		quantidadeParcelasTextField.setToolTipText(RotuloJanelaDespesa.MSG_AVISTA.getDescricao());
		quantidadeParcelasTextField.setBounds(140, 31, 82, 20);
		infoAdicionalPanel.add(quantidadeParcelasTextField);
		quantidadeParcelasTextField.setColumns(10);
		
		//Cria o numeroChequeTextField e adiciona ao painel.
		numeroChequeTextField = new JTextField();
		numeroChequeTextField.setToolTipText(RotuloJanelaDespesa.MSG_CHEQUE.getDescricao());
		numeroChequeTextField.setBounds(379, 31, 148, 20);
		infoAdicionalPanel.add(numeroChequeTextField);
		numeroChequeTextField.setEditable(false);
		numeroChequeTextField.setColumns(10);
		
		camposObrigatoriosLabel = new JLabel("* Posicione o mouse sobre os campos em vermelho.");
		camposObrigatoriosLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		camposObrigatoriosLabel.setVisible(false);
		camposObrigatoriosLabel.setForeground(Color.RED);
		camposObrigatoriosLabel.setBounds(12, 323, 262, 25);
		panel.add(camposObrigatoriosLabel);
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgCadastrarDespesas.this.dispose();
			}
		});
		
		//Define a janela como v�sivel.
		setVisible(true);
	}//IgCadastrarDespesas()
	
	/**
	 * Retorna a refer�ncia da janela principal do aplicativo.
	 * @return planejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code>
	 */
	public IgPlanejamentoFinanceiro getPlanejamentoFinanceiro() {
		return planejamentoFinanceiro;
	}

	/**
	 * Retorna a refer�ncia da classe <code>GerenciamentoDeFinanca</code> que possui a refer�ncia das classes de leitura de dados.
	 * @return gerenciamentoDeFinanca <code>GerenciamentoDeFinanca</code>
	 */
	public GerenciamentoDeFinanca getGerenciamentoDeFinanca() {
		return gerenciamentoDeFinanca;
	}

	/**
	 * Retorna uma refer�ncia do <code>JTextField</code> contendo a descri��o da despesa.
	 * @return <code>JTextField</code>
	 */
	public JTextField getDescricaoTextField() {
		return descricaoTextField;
	}

	/**
	 * Retorna uma refer�ncia do <code>JDateChooser</code> contendo a data em que a despesa foi realizada.
	 * @return dataPagamentoDateChooser <code>JDateChooser</code>
	 */
	public JDateChooser getDataDespesaDateChooser() {
		return dataDespesaDateChooser;
	}

	/**
	 * Retorna uma refer�ncia do <code>JComboBox</code> contendo a listagem das formas de pagamento da despesa.
	 * @return formaPagamentoComboBox <code>JComboBox</code>
	 */
	public JComboBox<String> getFormaPagamentoComboBox() {
		return formaPagamentoComboBox;
	}

	/**
	 * Retorna uma refer�ncia do <code>JTextField</code> contendo o n�mero do cheque caso a forma de pagamento seja cheque.
	 * @return <code>JTextField</code>
	 */
	public JTextField getNumeroChequeTextField() {
		return numeroChequeTextField;
	}

	/**
	 * Retorna uma refer�ncia do <code>JTextField</code> contendo a quantidade de parcelas caso o pagamento n�o seja �
	 *  vista.
	 * @return <code>JTextField</code>
	 */
	public JTextField getQuantidadeParcelasTextField() {
		return quantidadeParcelasTextField;
	}

	/**
	 * Retorna uma refer�ncia do <code>JTextField</code> contendo o valor da despesa.
	 * @return <code>JTextField</code>
	 */
	public JTextField getValorTextField() {
		return valorTextField;
	}

	/**
	 * Retorna uma refer�ncia do <code>JDateChooser</code> contendo a data de pagamento da despesa.
	 * @return dataPagamentoDateChooser <code>JDateChooser</code>
	 */
	public JDateChooser getDataPagamentoDateChooser() {
		return dataPagamentoDateChooser;
	}

	/**
	 * Retorna uma refer�ncia do <code>JComboBox</code> contendo a listagem das categorias em que se enquadram as despesa.
	 * @return categoriaComboBox <code>JComboBox</code>
	 */
	public JComboBox<String> getCategoriaComboBox() {
		return categoriaComboBox;
	}
	
	/**
	 * Retorna uma refer�ncia do <code>JLabel</code> contendo a mensagem a ser exibida caso os dados n�o sejam v�lidos.
	 * @return camposObrigatoriosLabel <code>JLabel</code>
	 */
	public JLabel getCamposObrigatoriosLabel() {
		return camposObrigatoriosLabel;
	}
	
	/**
	 * Retorna uma refer�ncia do bot�o cadastrar.
	 * @return cadastrarDespesaButton <code>JButton</code>
	 */
	public JButton getCadastrarDespesaButton() {
		return cadastrarDespesaButton;
	}
	
	/**
	 * Carrega as categorias para o <code>JComboBox</code> respons�vel por exibi-l�s ao usu�rio.
	 */
	public void carregarCategorias(){
		//Obtem as categorias salvas em disco j� ordenadas.
		String nomesCategorias[] = gerenciamentoDeFinanca.getControleCategoria().obterNomesCategorias();
		
		DefaultComboBoxModel<String> categorias = new DefaultComboBoxModel<String>();
		
		if(nomesCategorias != null)
		{
			for(int indice = 0; indice < nomesCategorias.length; indice++)
				categorias.addElement(nomesCategorias[indice]);
		}
		//Adiciona a op��o de criar uma nova categoria ao elemento.
		categorias.addElement(RotuloJanelaDespesa.NOVA_CATEGORIA.getDescricao());
		categoriaComboBox.setModel(categorias);
	}//carregarCategorias
	
	/**
	 * Carrega as formas de pagamento para o <code>JComboBox</code> respons�vel por exibi-l�s para o usu�rio.
	 */
	public void carregarFormasPagamento() {
		//Obt�m as formas de pagamento salvas em disco j� ordenadas.
		String formasDePagamento[] = gerenciamentoDeFinanca.getControleFormaPagamento().obterFormasPagamento();
		
		DefaultComboBoxModel<String> pagamento = new DefaultComboBoxModel<String>();
		
		if(formasDePagamento != null) 
		{
			for(int indice = 0; indice < formasDePagamento.length; indice++)
				pagamento.addElement(formasDePagamento[indice]);
		}
		//pagamento.addElement(RotuloJanelaDespesa.NOVA_FORMA_PAGAMENTO.getDescricao());
		formaPagamentoComboBox.setModel(pagamento);
	}//carregarFormasPagamento()
}//class IgCadastrarDespesas
