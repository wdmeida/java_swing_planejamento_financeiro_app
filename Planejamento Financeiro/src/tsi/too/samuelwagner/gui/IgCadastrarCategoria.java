package tsi.too.samuelwagner.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.controle.ControleCategoria;
import tsi.too.samuelwagner.enumeracoes.RotuloJanelaCategoria;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

/**
 * A classe <code>IgCadastrarCategoria</code> é responsável por construir a janela para adição de novas
 * categorias.
 * 
 * @author Samuel Gonçalves
 * @author Wagner Almeida
 */
public class IgCadastrarCategoria extends JDialog {
	private JList<String> categoriaList;
	private JTextField categoriaTextField;
	private JButton inserirCategoriaButton;
	private JLabel novaCategoriaLabel;
	private ControleCategoria controleCategoria;
	private static Color corPainel = new Color(248, 248, 248);
	private IgPlanejamentoFinanceiro planejamentoFinanceiro;
	
	/**
	 * Constrói a janela de cadastro de categoria.
	 * 
	 *  @param planejamento <code>IgPlanejamentoFinanceiro</code> com a referência da janela principal.
	 *  @param componente <code>Component</code> ao qual a janela se posicionará.
	 *  @param controleCategoria <code>ControleCategoria</code> com a referência do objeto responsável por salvar os dados.
	 */
	public IgCadastrarCategoria(IgPlanejamentoFinanceiro planejamento, Component componente, ControleCategoria controleCategoria) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IgCadastrarCategoria.class.getResource("/tsi/too/samuelwagner/imagens/bill.png")));
		this.planejamentoFinanceiro = planejamento;
		this.controleCategoria = controleCategoria;
		
		setBackground(corPainel);
		
		setTitle("Cadastrar Categoria");
		//Define as dimensões da janela.
		setSize(380, 258);
		
		//Define a janela como não redimensionável.
		setResizable(false);
		
		//Define a ação a ser tomada caso a janela seja fechada.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgCadastrarCategoria.this.dispose();
			}
		});
		
		//Define a janela em relação a janela principal.
		setLocationRelativeTo(componente);
		
		//Define a janela como modal.
		setModal(true);
		
		//Define o layout da tela principal.
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Cria o cadastrarCategoriaPanel, adiciona seu conteúdo a janela principal e define seu layout como absoluto.
		JPanel cadastrarCategoriaPanel = new JPanel();
		cadastrarCategoriaPanel.setBackground(corPainel);
		getContentPane().add(cadastrarCategoriaPanel, BorderLayout.CENTER);
		cadastrarCategoriaPanel.setLayout(null);
		
		//Cria o inserirCategoriaBurron, define seu rótudo, e o adiciona ao painel cadastrarCategoriaPanel.
		inserirCategoriaButton = new JButton("Nova");
		inserirCategoriaButton.setIcon(new ImageIcon(IgCadastrarCategoria.class.getResource("/tsi/too/samuelwagner/imagens/add_list.png")));
		inserirCategoriaButton.setForeground(Color.BLACK);
		inserirCategoriaButton.setMnemonic(KeyEvent.VK_N);
		inserirCategoriaButton.setBounds(210, 138, 141, 33);
		cadastrarCategoriaPanel.add(inserirCategoriaButton);
		
		//Definindo o botão padrão da janela.
		getRootPane().setDefaultButton(inserirCategoriaButton);
		
		//Cria o fecharBurron, define seu rótudo, e o adiciona ao painel cadastrarCategoriaPanel.
		JButton fecharButton = new JButton("Fechar");
		fecharButton.setIcon(new ImageIcon(IgCadastrarCategoria.class.getResource("/tsi/too/samuelwagner/imagens/close_window.png")));
		fecharButton.setForeground(Color.BLACK);
		fecharButton.setMnemonic(KeyEvent.VK_F);
		fecharButton.setBounds(210, 184, 141, 33);
		cadastrarCategoriaPanel.add(fecharButton);
		
		//Cria o categoriasPanel, adiciona e define o seu layout.
		JPanel categoriasPanel = new JPanel();
		categoriasPanel.setBackground(corPainel);
		categoriasPanel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Categorias Cadastradas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		categoriasPanel.setBounds(12, 12, 188, 205);
		cadastrarCategoriaPanel.add(categoriasPanel);
		categoriasPanel.setLayout(new BorderLayout(0, 0));
		
		//Cria um painel de rolagem e adiciona ao painel.
		JScrollPane categoriaScrollPane = new JScrollPane();
		categoriasPanel.add(categoriaScrollPane, BorderLayout.CENTER);
		
		//Instância a lista, define os valores e adiciona ao painel rolável.
		categoriaList = new JList<String>();
		categoriaList.setModel(new AbstractListModel<String>() {
			String[] values = {""};
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		//Adiciona o jList ao painel rolável.
		categoriaScrollPane.setViewportView(categoriaList);
		
		//Cria a caixa de texto, define suas propriedades e adiciona ao painel.
		categoriaTextField = new JTextField();
		categoriaTextField.setBounds(210, 101, 141, 26);
		cadastrarCategoriaPanel.add(categoriaTextField);
		categoriaTextField.setColumns(10);	
		
		//Cria o rótulo sobre a caixa de texto e adiciona ao painel.
		novaCategoriaLabel = new JLabel("Nova Categoria");
		novaCategoriaLabel.setBounds(210, 80, 96, 16);
		cadastrarCategoriaPanel.add(novaCategoriaLabel);
		trocaBotoes(false);
		
		//Carrega os dados das categorias para o jList.
		carregarCategoriasJList();
		
		//Registra o tratador de eventos do botão fecharButton
		fecharButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IgCadastrarCategoria.this.dispose();
			}
		});
		
		//Registra o tratador de eventos do botão inserirCategoriaButton
		inserirCategoriaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				IgCadastrarCategoria.this.cadastrarCategoria();
			}
		});
		
		//Registra o tratador de eventos do teclado.
		IgCadastrarCategoria.this.addKeyListener(new KeyAdapter() {
			@Override
			//Verifica se a tela pressionada foi o enter, caso seja, cadastra uma nova categoria.
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) IgCadastrarCategoria.this.cadastrarCategoria(); 
			}
		});
		
		//Define a janela como visível.
		setVisible(true);
	}//IgCadastrarCategoria()
	
	/**
	 * Retorna um objeto <code>JList</code> com o conteúdo da lista da janela.
	 * @return categoriaList 
	 */
	public JList<String> getCategoriaList() {
		return categoriaList;
	}//getCategoriaList()
	
	/**
	 * Ativa os componentes para o cadastro de categoria e realiza a chamada ao método responsável pelo cadastro.
	 */
	public void cadastrarCategoria() {
		//Ativa os botões de cadastro e modifica o nome do botão.
		if(inserirCategoriaButton.getActionCommand().equalsIgnoreCase(RotuloJanelaCategoria.INSERIR.getDescricao())){
			trocaBotoes(true);
			inserirCategoriaButton.setText(RotuloJanelaCategoria.ADICIONAR.getDescricao());
			inserirCategoriaButton.setMnemonic(KeyEvent.VK_A);
		}
		else{
				if(controleCategoria.cadastrarCategoria(categoriaTextField.getText())){
					categoriaTextField.setText("");
					trocaBotoes(false);
					inserirCategoriaButton.setText(RotuloJanelaCategoria.INSERIR.getDescricao());
					carregarCategoriasJList();
					planejamentoFinanceiro.atualizaComboBox();
					inserirCategoriaButton.setMnemonic(KeyEvent.VK_I);
				}
		}
	}//cadastrarCategoria()
	
	/**
	 * Carrega as categorias cadastradas para o JList.
	 */
	private void carregarCategoriasJList(){
		String[] nomesCategorias = GerenciamentoDeFinanca.getGerenciamentoFincanca().getControleCategoria().obterNomesCategorias();
		
		if(nomesCategorias == null) return;
		
		DefaultListModel<String> nomes = new DefaultListModel<String>();
		for(String nome : nomesCategorias)
			nomes.addElement(nome);
		
		categoriaList.setModel(nomes);
	}//carregarCategoriasComboBox()
	
	/**
	 * Ativa e desativa o label de inserção de categoria e a caixa de texto.
	 * @param troca <code>boolean</code> com <code>true</code> para ativar e <code>false</code> para desativar.
	 */
	public void trocaBotoes(boolean troca) {
		if(troca){
			categoriaTextField.setVisible(true);
			novaCategoriaLabel.setVisible(true);
		}
		else{
			categoriaTextField.setVisible(false);
			novaCategoriaLabel.setVisible(false);
		}
	}//trocaBotoes
}//class IgCadastrarCategoria
