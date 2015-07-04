package tsi.too.samuelwagner.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import tsi.too.samuelwagner.enumeracoes.TituloJanela;
import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.operacoes.OperacoesDoIgPlanejamentoFinanceiro;
import tsi.too.samuelwagner.trataeventos.TratadorEventoMesRendaComboBox;
import tsi.too.samuelwagner.trataeventos.TratadorEventoPainelMetas;
import tsi.too.samuelwagner.trataeventos.TratadorEventosBalancoMensal;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;
/**
 * A classe IgPlanejamentoFinanceiro implementa o Interface Gráfica do Usuário (GUI) da janela principal do aplicativo 
 * Planejamento Financeiro.
 * 
 * @author Samuel Gonçalves 
 * @author Wagner Almeida
 */
public class IgPlanejamentoFinanceiro extends JFrame {
	private JTable despesasRecentesTable;
	private JComboBox <String> selecionarCategoriaComboBox;
	private JTextField metaCategoriaTextField;
	private JTextField metaTextField;
	private JTextField gastoTextField;
	private JTable rendaTable;
	private JComboBox <String> mesRendaComboBox;
	private GerenciamentoDeFinanca gerenciamentoFinanceiro;
	private JTextField alertaTextField;
	private JButton visaoGraficaButton;
	private JTextField receitasTextField;
	private JTextField despesaTextField;
	private static Color corPainel = new Color(248, 248, 248);
	private JComboBox<String> mesBalancoComboBox;
	private JTextField aVistaTextField;
	private JTextField cartaoCreditoTextField;
	private JTextField chequeTextField;
	private JTextField parceladoTextField;
	private JTextField financiamentoTextField;
	private JTextField crediarioTextField;
	private JTextField saldoTextField;
	
	/**
	 * Construtor default da classe <code>IgPlanejamentoFinanceiro</code>.
	 * Monta a interface gráfica e ao final exibe o conteúdo.
	 */
	public IgPlanejamentoFinanceiro(){
		//Define o título da janela.
		setTitle("Planejamento Financeiro");
		
		//Define o tamanho da janela.
		setSize(768, 442);
		
		//Muda o icone da barra de títulos da janela. Cria um objeto url e passa o endereço da imagem.
		URL url = this.getClass().getResource("/tsi/too/samuelwagner/imagens/calculator.png");
	
		//Cria um objeto imagem, Obtém a referência da imagem da barra e seta a nova imagem.
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
		
		//Define a nova imagem para a janela.
		this.setIconImage(imagemTitulo);
		
		//Define o posicionamento relativo a um componente (null para se posicionar no centro).
		setLocationRelativeTo(null);
		
		//Define a operação a ser realizada ao clicar no botão fechar da janela.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Define a janela como não redimensionável.
		setResizable(false);
		
		//Obtêm a instância do objeto que contém todas as operações financeiras.
		gerenciamentoFinanceiro = GerenciamentoDeFinanca.getGerenciamentoFincanca();
		
		//Cria a barra de menu e adiciona ao painel.
		JMenuBar barraMenu = new JMenuBar();
		setJMenuBar(barraMenu);
		
		//Cria o menu Arquivo, define sua tecla mnêmonica e adiciona a barra de menus.
		JMenu arquivoMenu = new JMenu("Arquivo");
		arquivoMenu.setMnemonic(KeyEvent.VK_A);
		barraMenu.add(arquivoMenu);
		
		//Cria um item de menu e adiciona ao menu arquivo.
		JMenuItem sairMenuItem = new JMenuItem("Sair");
		sairMenuItem.setMnemonic(KeyEvent.VK_S);
		sairMenuItem.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/close_window.png")));
		sairMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		arquivoMenu.add(sairMenuItem);
		
		//Registra o tratador de eventos do sairMenuItem
		sairMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//Define os itens de menus e menus e seus respectivos tratadores de eventos.
		JMenu menuNavegar = new JMenu("Navegar");
		menuNavegar.setMnemonic(KeyEvent.VK_N);
		barraMenu.add(menuNavegar);
		
		JMenu menuCadastrar = new JMenu("Cadastrar");
		menuCadastrar.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/data_recovery.png")));
		menuCadastrar.setMnemonic(KeyEvent.VK_C);
		menuNavegar.add(menuCadastrar);
		
		//Cadastrar Despesas
		JMenuItem despesasMenuItem = new JMenuItem("Despesas");
		despesasMenuItem.setMnemonic(KeyEvent.VK_D);
		despesasMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastrarDespesas(IgPlanejamentoFinanceiro.this);
			}
		});
		despesasMenuItem.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/add_list.png")));
		despesasMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		menuCadastrar.add(despesasMenuItem);
		
		//Cadastrar Metas.
		JMenuItem metasMenuItem = new JMenuItem("Metas");
		metasMenuItem.setMnemonic(KeyEvent.VK_M);
		metasMenuItem.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/edit.png")));
		metasMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		menuCadastrar.add(metasMenuItem);
		metasMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgCadastrarMetaMensal(IgPlanejamentoFinanceiro.this, gerenciamentoFinanceiro.getControleMetaMensal());
			}
		});
		
		//Cadastrar Rendas.
		JMenuItem rendasMenuItem = new JMenuItem("Rendas");
		rendasMenuItem.setMnemonic(KeyEvent.VK_R);
		rendasMenuItem.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/money393.png")));
		rendasMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		menuCadastrar.add(rendasMenuItem);
		rendasMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgCadastraRendaMensal(TituloJanela.RENDA_MENSAL, IgPlanejamentoFinanceiro.this);
				
			}
		});
		
		//Cria o menu visualizar
		JMenu menuVisualizar = new JMenu("Visualizar");
		menuVisualizar.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/glasses.png")));
		menuVisualizar.setMnemonic(KeyEvent.VK_V);
		menuNavegar.add(menuVisualizar);
		
		//Cria o menu balanço mensal.
		JMenuItem balancoMensalMenuItem = new JMenuItem("Balanço Mensal");
		balancoMensalMenuItem.setMnemonic(KeyEvent.VK_B);
		balancoMensalMenuItem.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/stats58.png")));
		balancoMensalMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		menuVisualizar.add(balancoMensalMenuItem);
		balancoMensalMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgGraficoBalacoMensal(IgPlanejamentoFinanceiro.this,TituloJanela.IG_GRAFICO);
			}
		});
		
		//Cria o menu balanço pagamento
		JMenuItem balancoPagamentoMenuItem = new JMenuItem("Balanço Pagamentos");
		balancoPagamentoMenuItem.setMnemonic(KeyEvent.VK_P);
		balancoPagamentoMenuItem.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/dollars5.png")));
		balancoPagamentoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		menuVisualizar.add(balancoPagamentoMenuItem);
		balancoPagamentoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgGraficoBalacoMensal(IgPlanejamentoFinanceiro.this,TituloJanela.IG_GRAFICO_DETALHE);
			}
		});
		
		JMenuItem balancoCategoriasMenu = new JMenuItem("Balanço Categorias");
		balancoCategoriasMenu.setMnemonic(KeyEvent.VK_C);
		balancoCategoriasMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgGraficoBarra(IgPlanejamentoFinanceiro.this, TituloJanela.IG_GRAFICO_CATEGORIA);
			}
		});
		balancoCategoriasMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		balancoCategoriasMenu.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/bars graphic4.png")));
		menuVisualizar.add(balancoCategoriasMenu);
		
		//Cria o menu de despesas cadastradas.
		JMenuItem despesasCadastradasMenuItem = new JMenuItem("Despesas Cadastradas");
		despesasCadastradasMenuItem.setMnemonic(KeyEvent.VK_D);
		despesasCadastradasMenuItem.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/detective.png")));
		despesasCadastradasMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		menuVisualizar.add(despesasCadastradasMenuItem);
		despesasCadastradasMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgDetalhesDespesa(IgPlanejamentoFinanceiro.this, gerenciamentoFinanceiro);
			}
		});
		
		//Cria um menu ajuda e adiciona a barra de menus.
		JMenu ajudaMenuItem = new JMenu("Ajuda");
		ajudaMenuItem.setMnemonic(KeyEvent.VK_J);
		barraMenu.add(ajudaMenuItem);
		
		//Cria um item de menu e adiciona ao menu ajuda.
		JMenuItem sobreMenuItem = new JMenuItem("Sobre");
		sobreMenuItem.setMnemonic(KeyEvent.VK_S);
		sobreMenuItem.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/about.png")));
		sobreMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		ajudaMenuItem.add(sobreMenuItem);
		
		//Registra o tratador de eventos do menu sobre.
		sobreMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgSobre(IgPlanejamentoFinanceiro.this);
			}
		});
		
		//Cria um painél de abas e adiciona ao painel principal.
		JTabbedPane abaTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		abaTabbedPane.setBackground(new Color(192, 192, 192));
		getContentPane().add(abaTabbedPane, BorderLayout.CENTER);
		
		/*
		 * Cria o inicioPanel e adiciona ao painel principal.
		 */
		JPanel inicioPanel = new JPanel();
		inicioPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		abaTabbedPane.addTab("Início", new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/home.png")), inicioPanel, null);
		inicioPanel.setLayout(null);
		
		//Cria o painel de acesso rápido.
		JPanel acessoRapidoPanel = new JPanel();
		acessoRapidoPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		acessoRapidoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cadastrar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		acessoRapidoPanel.setBounds(579, 11, 168, 151);
		inicioPanel.add(acessoRapidoPanel);
		acessoRapidoPanel.setLayout(null);
		
		//Cria o botão de metas.
		JButton metasButton = new JButton("Metas");
		metasButton.setMnemonic(KeyEvent.VK_M);
		metasButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/edit.png")));
		metasButton.setFont(new Font("Dialog", Font.BOLD, 12));
		metasButton.setToolTipText("Cadastrar Metas");
		metasButton.setBounds(10, 65, 148, 33);
		acessoRapidoPanel.add(metasButton);
		metasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgCadastrarMetaMensal(IgPlanejamentoFinanceiro.this, gerenciamentoFinanceiro.getControleMetaMensal());
			}
		});
		
		//Cria o botão de despesas.
		JButton despesasButton = new JButton("Despesa");
		despesasButton.setBorder(UIManager.getBorder("Button.border"));
		despesasButton.setMnemonic(KeyEvent.VK_D);
		despesasButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/add_list.png")));
		despesasButton.setFont(new Font("Dialog", Font.BOLD, 12));
		despesasButton.setForeground(Color.BLACK);
		despesasButton.setToolTipText("Cadastrar Despesas");
		despesasButton.setBounds(10, 23, 148, 33);
		acessoRapidoPanel.add(despesasButton);
		
		//Cria o botão de renda.
		JButton rendaButton = new JButton("Rendas");
		rendaButton.setMnemonic(KeyEvent.VK_R);
		rendaButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/money393.png")));
		rendaButton.setFont(new Font("Dialog", Font.BOLD, 12));
		rendaButton.setBounds(10, 107, 148, 33);
		acessoRapidoPanel.add(rendaButton);
		rendaButton.setToolTipText("Cadastrar Renda");
		
		//Registra o tratador de eventos do botão de renda.
		rendaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastraRendaMensal(TituloJanela.RENDA_MENSAL, IgPlanejamentoFinanceiro.this);
			}
		});
		
		//Registra o tratador de eventos do botão de despesas.
		despesasButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastrarDespesas(IgPlanejamentoFinanceiro.this);
			}
		});
		
		//Cria a área de informações do painel principal.
		JPanel panel = new JPanel();
		panel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), obtemData(), TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(60, 11, 509, 331);
		inicioPanel.add(panel);
		panel.setLayout(null);
		
		//Cria o rótulo de planejamento.
		JLabel lblPlanejamentoFinanceiro = new JLabel("Planejamento Financeiro");
		lblPlanejamentoFinanceiro.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblPlanejamentoFinanceiro.setBounds(139, 29, 260, 24);
		lblPlanejamentoFinanceiro.setForeground(new Color(87, 163, 123));
		panel.add(lblPlanejamentoFinanceiro);
		
		//Cria o rótulo de sobre.
		JLabel sobreLabel = new JLabel("Planejamento Financeiro auxilia você no controle de finanças. Com ele você: ");
		sobreLabel.setForeground(Color.BLACK);
		sobreLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sobreLabel.setBounds(10, 77, 455, 24);
		panel.add(sobreLabel);
		
		//Cria o rótulo de cadastro.
		JLabel cadastroLabel = new JLabel("Cadastra e visualiza despesas;");
		cadastroLabel.setForeground(Color.BLACK);
		cadastroLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cadastroLabel.setBounds(20, 112, 182, 14);
		panel.add(cadastroLabel);
		
		//Cria o rótulo de cadastro e vizualização.
		JLabel lblCadastroEVisualizao = new JLabel("Cadastra e visualiza metas;");
		lblCadastroEVisualizao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCadastroEVisualizao.setBounds(20, 137, 159, 14);
		panel.add(lblCadastroEVisualizao);
		
		//Cria o rótulo de cadastro de rendas.
		JLabel lblCadastroDeRendas = new JLabel("Cadastra rendas;");
		lblCadastroDeRendas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCadastroDeRendas.setBounds(20, 162, 106, 14);
		panel.add(lblCadastroDeRendas);
		
		//Cria o rótulo de visualização de balancos
		JLabel visualizarBalancoLabel = new JLabel("Visualiza balanços financeiros");
		visualizarBalancoLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		visualizarBalancoLabel.setBounds(20, 187, 182, 14);
		panel.add(visualizarBalancoLabel);
		
		//Cria o painel de visualização.
		JPanel visualizarPanel = new JPanel();
		visualizarPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		visualizarPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Visualizar Balan\u00E7os", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		visualizarPanel.setBounds(579, 191, 168, 151);
		inicioPanel.add(visualizarPanel);
		visualizarPanel.setLayout(null);
		
		//Cria o botão de visualização de despesas.
		JButton visualizarDespesaButton = new JButton("Despesas");
		visualizarDespesaButton.setMnemonic(KeyEvent.VK_E);
		visualizarDespesaButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/detective.png")));
		visualizarDespesaButton.setFont(new Font("Dialog", Font.BOLD, 12));
		
		//Registra o tratador de eventos do botão visualizar despesas do painel de acesso rápido.
		visualizarDespesaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgDetalhesDespesa(IgPlanejamentoFinanceiro.this, gerenciamentoFinanceiro);
			}
		});
		visualizarDespesaButton.setBounds(10, 108, 148, 33);
		visualizarPanel.add(visualizarDespesaButton);
		
		//Cria um botão com o balanço mensal, define seu rótulo e propriedades e adiciona na janela.
		JButton balancoButton = new JButton("Mensais");
		balancoButton.setMnemonic(KeyEvent.VK_S);
		balancoButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/stats58.png")));
		balancoButton.setFont(new Font("Dialog", Font.BOLD, 12));
		balancoButton.setBounds(10, 24, 148, 33);
		visualizarPanel.add(balancoButton);
		balancoButton.setToolTipText("Visualizar Balan\u00E7o Mensal");
		
		//Cria um botão com o balanço pagamento, define seu rótulo e propriedades e adiciona na janela.
		JButton balancoPagamentosButton = new JButton("Pagamentos");
		balancoPagamentosButton.setMnemonic(KeyEvent.VK_P);
		balancoPagamentosButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/dollars5.png")));
		balancoPagamentosButton.setFont(new Font("Dialog", Font.BOLD, 12));
		balancoPagamentosButton.setMargin(new Insets(0, 0, 0, 0));
		balancoPagamentosButton.setBounds(10, 66, 148, 33);
		visualizarPanel.add(balancoPagamentosButton);
		
		//Registra o tratador de eventos do botão balanço dos pagamentos.
		balancoPagamentosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IgGraficoBalacoMensal(IgPlanejamentoFinanceiro.this,TituloJanela.IG_GRAFICO_DETALHE);
			}
		});
		
		//Cria o painel com a cor lateral.
		JPanel corPrincipalPanel = new JPanel();
		corPrincipalPanel.setBounds(0, 0, 50, 353);
		corPrincipalPanel.setBackground(new Color(87, 163, 123));
		inicioPanel.add(corPrincipalPanel);
		
		//Registra o tratador de eventos do botão visualizar balanço mensal do painel de acesso rápido.
		balancoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgGraficoBalacoMensal(IgPlanejamentoFinanceiro.this,TituloJanela.IG_GRAFICO);
			}
		});
		
		//Cria o rendaPanel e adiciona ao painel principal.
		abaTabbedPane.addTab("Rendas", new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/money_bag.png")), criarPainelRenda(), null);
		
		//Cria o metaPanel e adiciona ao painel principal.
		abaTabbedPane.addTab("Metas", new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/bill.png")), criarPainelMeta(), null);
		
		//Cria o despesasPanel e adiciona ao painel principal.
		abaTabbedPane.addTab("Despesas", new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/excel.png")), criarPainelDespesas(), null);

		//Cria o areaGraficoPanel e adiciona ao painel principal.
		abaTabbedPane.addTab("Balanço Mensal", new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/clock245.png")), criarPainelBalancoMensal(), null);

		//Define a janela como visível.
		setVisible(true);
	}//IgPlanejamentoFinanceiro()
	
	/**
	 * Cria o painel de renda para ser adicionado a uma das abas da janela principal.
	 * @return rendaPanel <code>JPanel</code>
	 */
	private JPanel criarPainelRenda() {
		JPanel rendaPanel = new JPanel();
		rendaPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		rendaPanel.setLayout(null);
		
		//Cria o painel interno, define suas propriedades e adiciona ao rendaPanel.
		JPanel rendasMensaisPanel = new JPanel();
		rendasMensaisPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		rendasMensaisPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rendas Mensais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		rendasMensaisPanel.setBounds(60, 11, 494, 331);
		rendaPanel.add(rendasMensaisPanel);
		rendasMensaisPanel.setLayout(new BorderLayout(0, 0));
		
		//Cria o painel rolável em que será adicionada a tabela.
		JScrollPane rendaScrollPane = new JScrollPane();
		rendasMensaisPanel.add(rendaScrollPane, BorderLayout.CENTER);
		
		String[] titulo = {"Descrição","Data","Valor"};
		
		//Cria o mesRendaComboBox e adiciona ao rendaPanel.
		mesRendaComboBox = new JComboBox<String>(OperacoesDoIgPlanejamentoFinanceiro.preencheMesAnteriorRenda());
		mesRendaComboBox.setBounds(586, 30, 159, 25);
		rendaPanel.add(mesRendaComboBox);
				
		mesRendaComboBox.addActionListener(new TratadorEventoMesRendaComboBox(IgPlanejamentoFinanceiro.this));
		
		//Cria a tabela de renda, seta as propriedades e adiciona ao painél rolável.
		rendaTable = new JTable(OperacoesDoIgPlanejamentoFinanceiro.preencheTabelaRenda(mesRendaComboBox.getItemAt(0)),titulo);
		rendaTable.setFont(new Font("Arial", Font.PLAIN, 13));
		
		rendaScrollPane.setViewportView(rendaTable);
		
		//Cria o rendaButtonPanel, define as propriedades e adiciona ao rendaPanel.
		JPanel rendaButtonPanel = new JPanel();
		rendaButtonPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		rendaButtonPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rendas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		rendaButtonPanel.setBounds(571, 268, 172, 74);
		rendaPanel.add(rendaButtonPanel);
		rendaButtonPanel.setLayout(null);
		
		//Cria o botão de cadastro, alterar e excluir de renda e adiciona ao rendaButtonPanel.
		JButton cadastrarRendaButton = new JButton("Cadastrar");
		cadastrarRendaButton.setFont(new Font("Dialog", Font.BOLD, 12));
		cadastrarRendaButton.setMnemonic(KeyEvent.VK_C);
		cadastrarRendaButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/money393.png")));
		cadastrarRendaButton.setBounds(10, 25, 148, 33);
		rendaButtonPanel.add(cadastrarRendaButton);
		
		cadastrarRendaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastraRendaMensal(TituloJanela.RENDA_MENSAL, IgPlanejamentoFinanceiro.this);
			}
		});
		
		//Cria ao label mesAnterior e adiciona ao rendaPanel.
		JLabel mesAnteriorLabel = new JLabel("Escolha o Mês:");
		mesAnteriorLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		mesAnteriorLabel.setBounds(586, 11, 136, 16);
		rendaPanel.add(mesAnteriorLabel);
		
		rendaTable.setEnabled(false);
		rendaTable.setAutoCreateRowSorter(false);
		
		JPanel corRendaPanel = new JPanel();
		corRendaPanel.setBounds(0, 0, 50, 353);
		corRendaPanel.setBackground(new Color(76, 178, 225));
		rendaPanel.add(corRendaPanel);
		return rendaPanel;
	}//criarRendaPanel()
	
	/**
	 * Cria o painel de meta para ser adicionado a aba Rendas da janela principal.
	 * @return metaPanel <code>JPanel</code>
	 */
	private JPanel criarPainelMeta() {
		JPanel metaPanel = new JPanel();
		metaPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		metaPanel.setLayout(null);
		
		//Cria o metasMensaisPanel, define suas propriedades e adiciona ao metaPanel.
		JPanel metasMensaisPanel = new JPanel();
		metasMensaisPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		metasMensaisPanel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Metas Mensais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		metasMensaisPanel.setBounds(60, 12, 500, 330);
		metaPanel.add(metasMensaisPanel);
		metasMensaisPanel.setLayout(null);
		
		//Define selecionarCategoriaLabel e adiciona ao metasMensaisPanel.
		JLabel selecionarCategoriaLabel = new JLabel("Selecionar Categoria:");
		selecionarCategoriaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		selecionarCategoriaLabel.setBounds(12, 30, 122, 16);
		metasMensaisPanel.add(selecionarCategoriaLabel);
		
		//Cria o selecionarCategoriaComboBox e adiciona ao metasMensaisPanel.
		selecionarCategoriaComboBox = new JComboBox<String>(OperacoesDoIgPlanejamentoFinanceiro.carregarValoresComboBoxMeta());
		selecionarCategoriaComboBox.setBounds(152, 26, 214, 25);
		metasMensaisPanel.add(selecionarCategoriaComboBox);
		
		//Registra o tratador de eventos do comboBox.
		TratadorEventoPainelMetas tratadorMetas = new TratadorEventoPainelMetas(this);
		selecionarCategoriaComboBox.addActionListener(tratadorMetas);
		
		
		//Cria o exibirMetaPanel, define as propriedades e adiciona ao exibirMetaPanel.
		JPanel exibirMetaPanel = new JPanel();
		exibirMetaPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		exibirMetaPanel.setBounds(12, 94, 477, 229);
		exibirMetaPanel.setBorder(new TitledBorder(null, "Dados da Meta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		metasMensaisPanel.add(exibirMetaPanel);
		
		exibirMetaPanel.setLayout(null);
		
		//Cria o metaCategoriaLabel e adiciona ao exibirMetaPanel.
		JLabel metaCategoriaLabel = new JLabel("Categoria:");
		metaCategoriaLabel.setBounds(13, 26, 66, 15);
		metaCategoriaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		exibirMetaPanel.add(metaCategoriaLabel);
		
		//Cria o metaCategoriaTextFiels e adiciona ao exibirMetaPanel.
		metaCategoriaTextField = new JTextField();
		metaCategoriaTextField.setBackground(Color.WHITE);
		metaCategoriaTextField.setBounds(103, 23, 86, 20);
		metaCategoriaTextField.setEditable(false);
		exibirMetaPanel.add(metaCategoriaTextField);
		metaCategoriaTextField.setColumns(10);
		
		//Cria o metaLabel e adiciona ao exibirMetaPanel.
		JLabel metaLabel = new JLabel("Meta:");
		metaLabel.setBounds(13, 50, 47, 15);
		metaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		exibirMetaPanel.add(metaLabel);
		
		//Cria o metaTextFiels e adiciona ao exibirMetaPanel.
		metaTextField = new JTextField();
		metaTextField.setBackground(Color.WHITE);
		metaTextField.setBounds(103, 47, 118, 20);
		metaTextField.setEditable(false);
		exibirMetaPanel.add(metaTextField);
		metaTextField.setColumns(10);
		
		//Cria o valorGastoLabel e adiciona ao exibirMetaPanel.
		JLabel valorGastoLabel = new JLabel("Valor Gasto:");
		valorGastoLabel.setBounds(13, 74, 80, 15);
		valorGastoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		exibirMetaPanel.add(valorGastoLabel);
		
		//Cria o gastoTextFiels e adiciona ao exibirMetaPanel.
		gastoTextField = new JTextField();
		gastoTextField.setBackground(Color.WHITE);
		gastoTextField.setBounds(103, 71, 118, 20);
		gastoTextField.setEditable(false);
		exibirMetaPanel.add(gastoTextField);
		gastoTextField.setColumns(10);
		
		JPanel opcaoPanel = new JPanel();
		opcaoPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		opcaoPanel.setBorder(new TitledBorder(null, "Op\u00E7\u00F5es Adicionais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		opcaoPanel.setBounds(13, 135, 454, 80);
		exibirMetaPanel.add(opcaoPanel);
		opcaoPanel.setLayout(null);
		
		JLabel labelAlerta = new JLabel("Ligar Alerta ao atingir:");
		labelAlerta.setBounds(10, 41, 137, 15);
		opcaoPanel.add(labelAlerta);
		labelAlerta.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		alertaTextField = new JTextField();
		alertaTextField.setBackground(Color.WHITE);
		alertaTextField.setEditable(false);
		alertaTextField.setBounds(144, 38, 118, 20);
		opcaoPanel.add(alertaTextField);
		alertaTextField.setColumns(10);
		
		visaoGraficaButton = new JButton("Vis\u00E3o Gr\u00E1fica");
		visaoGraficaButton.setFont(new Font("Dialog", Font.BOLD, 12));
		visaoGraficaButton.setMnemonic(KeyEvent.VK_V);
		visaoGraficaButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/stats59.png")));
		visaoGraficaButton.setBounds(296, 33, 148, 33);
		opcaoPanel.add(visaoGraficaButton);
		
		//Registra o tratador de eventos do botão visaoGraficaButton.
		visaoGraficaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgGraficoMetaMensal(IgPlanejamentoFinanceiro.this, TituloJanela.IG_GRAFICO_META);
			}
		});
		
		//Cria o metaButtonsPane e adiciona ao metaPanel.
		JPanel metaButtonsPane = new JPanel();
		metaButtonsPane.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		metaButtonsPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Definir", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		metaButtonsPane.setBounds(570, 275, 177, 67);
		metaPanel.add(metaButtonsPane);
		metaButtonsPane.setLayout(null);
		
		//Cria o cadastraMetaButton e adiciona ao metaButtonsPane.
		JButton cadastrarMetaButton = new JButton("Metas");
		cadastrarMetaButton.setFont(new Font("Dialog", Font.BOLD, 12));
		cadastrarMetaButton.setMnemonic(KeyEvent.VK_M);
		cadastrarMetaButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/edit.png")));
		cadastrarMetaButton.setBounds(13, 23, 148, 33);
		metaButtonsPane.add(cadastrarMetaButton);
		
		JPanel corMetaPanel = new JPanel();
		corMetaPanel.setBackground(new Color(221, 231, 90));
		corMetaPanel.setBounds(0, 0, 50, 353);
		metaPanel.add(corMetaPanel);
		
		//Registra o tratador de eventos do botão cadastrar meta.
		cadastrarMetaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IgCadastrarMetaMensal(IgPlanejamentoFinanceiro.this, gerenciamentoFinanceiro.getControleMetaMensal());
			}
		});
		
		return metaPanel;
	}//criarMetaPanel()
	
	/**
	 * Cria o painel de despesas para ser adicionado a aba Despesas do janela principal.
	 * @return despesasPanel <code>JPanel</code>
	 */
	private JPanel criarPainelDespesas() {
		JPanel despesasPanel = new JPanel();
		despesasPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		despesasPanel.setLayout(null);
		
		//Cria o despesasRecentesPanel, define suas propriedades e adiciona ao despesasPanel.
		JPanel despesasRecentesPanel = new JPanel();
		despesasRecentesPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		despesasRecentesPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Resumo das Despesas Recentes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		despesasRecentesPanel.setBounds(63, 12, 509, 330);
		despesasPanel.add(despesasRecentesPanel);
		despesasRecentesPanel.setLayout(new BorderLayout(0, 0));
		
		//Cria o painel rolável que recepera a tabela.
		JScrollPane despesaScrollPane = new JScrollPane();
		despesasRecentesPanel.add(despesaScrollPane, BorderLayout.CENTER);
		
		//Cria o despesasRecentesTable, define as propriedades e adiciona ao painel rolável.
		String tituloTabela[] = {"Descrição","Data do Pagamento","Valor"};
		despesasRecentesTable = new JTable(OperacoesDoIgPlanejamentoFinanceiro.resumoDespesas(),tituloTabela);
		despesasRecentesTable.setFont(new Font("Arial", Font.PLAIN, 13));
		despesaScrollPane.setViewportView(despesasRecentesTable);
		despesasRecentesTable.setEnabled(false);
		
		//Cria o botoesDespesaPanel e adiciona ao despesasPanel.
		JPanel botoesDespesaPanel = new JPanel();
		botoesDespesaPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		botoesDespesaPanel.setBorder(new TitledBorder(null, "Despesas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		botoesDespesaPanel.setBounds(576, 227, 171, 115);
		despesasPanel.add(botoesDespesaPanel);
		botoesDespesaPanel.setLayout(null);
		
		//Cria o cadastrarDespesaButton e adiciona ao botoesDespesaPanel.
		JButton cadastrarDespesaButton = new JButton("Cadastrar");
		cadastrarDespesaButton.setFont(new Font("Dialog", Font.BOLD, 12));
		cadastrarDespesaButton.setMnemonic(KeyEvent.VK_C);
		cadastrarDespesaButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/add_list.png")));
		cadastrarDespesaButton.setMargin(new Insets(2, 8, 2, 8));
		cadastrarDespesaButton.setBounds(10, 26, 148, 33);
		botoesDespesaPanel.add(cadastrarDespesaButton);
		
		//Cria o detalheDespesaButton e adiciona ao botoesDespesaPanel.
		JButton detalheDespesaButton = new JButton("Detalhes");
		detalheDespesaButton.setFont(new Font("Dialog", Font.BOLD, 12));
		detalheDespesaButton.setMnemonic(KeyEvent.VK_D);
		detalheDespesaButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/detective.png")));
		detalheDespesaButton.setBounds(10, 70, 148, 33);
		botoesDespesaPanel.add(detalheDespesaButton);
		
		//Registra o tratador de eventos do botão despesa.
		detalheDespesaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new IgDetalhesDespesa(IgPlanejamentoFinanceiro.this, gerenciamentoFinanceiro);
			}
		});
		
		//Registra o tratador de eventos do botão de cadastro de nova despesa.
		cadastrarDespesaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new IgCadastrarDespesas(IgPlanejamentoFinanceiro.this);
			}
		});
		
		JPanel corDespesaPanel = new JPanel();
		corDespesaPanel.setBackground(new Color(234, 39, 39));
		corDespesaPanel.setBounds(0, 0, 50, 353);
		despesasPanel.add(corDespesaPanel);
		
		return despesasPanel;
	}//criarPainelDespesas()
	
	/**
	 * Cria o painel de balanço mensal para ser adicionado a aba Balanço Mensal janela principal.
	 * @return balancoMensalPanel <code>JPanel</code>
	 */
	private JPanel criarPainelBalancoMensal() {
		JPanel balancoMensalPanel = new JPanel();
		balancoMensalPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		balancoMensalPanel.setLayout(null);
		
		//Cria o areaGraficoPanel, define as configurações e adiciona ao graficosPanel.
		JPanel areaGraficoPanel = new JPanel();
		areaGraficoPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		areaGraficoPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Balan\u00E7o Mensal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		areaGraficoPanel.setBounds(60, 12, 504, 330);
		balancoMensalPanel.add(areaGraficoPanel);
		areaGraficoPanel.setLayout(null);
		
		JPanel balancoGeralPanel = new JPanel();
		balancoGeralPanel.setBorder(new TitledBorder(null, "Geral", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		balancoGeralPanel.setBounds(10, 26, 281, 129);
		balancoGeralPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		areaGraficoPanel.add(balancoGeralPanel);
		balancoGeralPanel.setLayout(null);
		
		JLabel lblTotalDeReceitas = new JLabel("Total de Receitas:");
		lblTotalDeReceitas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalDeReceitas.setBounds(10, 29, 115, 14);
		balancoGeralPanel.add(lblTotalDeReceitas);
		
		receitasTextField = new JTextField();
		receitasTextField.setBackground(Color.WHITE);
		receitasTextField.setEditable(false);
		receitasTextField.setBounds(147, 26, 115, 20);
		balancoGeralPanel.add(receitasTextField);
		receitasTextField.setToolTipText("Total de receitas recebidas no m\u00EAs.");
		receitasTextField.setColumns(10);
		
		JLabel lblTotalDespesas = new JLabel("Total Despesas:");
		lblTotalDespesas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalDespesas.setBounds(10, 61, 104, 14);
		balancoGeralPanel.add(lblTotalDespesas);
		
		despesaTextField = new JTextField();
		despesaTextField.setBackground(Color.WHITE);
		despesaTextField.setEditable(false);
		despesaTextField.setBounds(147, 58, 115, 20);
		balancoGeralPanel.add(despesaTextField);
		despesaTextField.setToolTipText("Total de despesas do m\u00EAs.");
		despesaTextField.setColumns(10);
		
		JLabel saldoLabel = new JLabel("Saldo:");
		saldoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		saldoLabel.setBounds(10, 91, 63, 14);
		balancoGeralPanel.add(saldoLabel);
		
		saldoTextField = new JTextField();
		saldoTextField.setBackground(Color.WHITE);
		saldoTextField.setEditable(false);
		saldoTextField.setToolTipText("Saldo no mês.");
		saldoTextField.setBounds(147, 89, 115, 20);
		balancoGeralPanel.add(saldoTextField);
		saldoTextField.setColumns(10);
		
		JPanel gastosFormaPanel = new JPanel();
		gastosFormaPanel.setBorder(new TitledBorder(null, "Gastos por Forma de Pagamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		gastosFormaPanel.setBounds(10, 161, 484, 160);
		gastosFormaPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		areaGraficoPanel.add(gastosFormaPanel);
		gastosFormaPanel.setLayout(null);
		
		JLabel vistaLabel = new JLabel("À Vista:");
		vistaLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		vistaLabel.setBounds(10, 33, 46, 14);
		gastosFormaPanel.add(vistaLabel);
		
		JLabel cartaoLabel = new JLabel("Cartão de crédito:");
		cartaoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		cartaoLabel.setBounds(10, 70, 111, 14);
		gastosFormaPanel.add(cartaoLabel);
		
		JLabel chequeLabel = new JLabel("Cheque:");
		chequeLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		chequeLabel.setBounds(10, 108, 46, 14);
		gastosFormaPanel.add(chequeLabel);
		
		aVistaTextField = new JTextField();
		aVistaTextField.setBackground(Color.WHITE);
		aVistaTextField.setEditable(false);
		aVistaTextField.setBounds(120, 30, 100, 20);
		gastosFormaPanel.add(aVistaTextField);
		aVistaTextField.setColumns(10);
		
		cartaoCreditoTextField = new JTextField();
		cartaoCreditoTextField.setBackground(Color.WHITE);
		cartaoCreditoTextField.setEditable(false);
		cartaoCreditoTextField.setBounds(120, 67, 100, 20);
		gastosFormaPanel.add(cartaoCreditoTextField);
		cartaoCreditoTextField.setColumns(10);
		
		chequeTextField = new JTextField();
		chequeTextField.setBackground(Color.WHITE);
		chequeTextField.setEditable(false);
		chequeTextField.setBounds(120, 105, 100, 20);
		gastosFormaPanel.add(chequeTextField);
		chequeTextField.setColumns(10);
		
		JLabel parceladoLabel = new JLabel("Parcelado:");
		parceladoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		parceladoLabel.setBounds(265, 33, 65, 14);
		gastosFormaPanel.add(parceladoLabel);
		
		JLabel financiamentoLabel = new JLabel("Financiamento:");
		financiamentoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		financiamentoLabel.setBounds(265, 70, 91, 14);
		gastosFormaPanel.add(financiamentoLabel);
		
		JLabel crediarioLabel = new JLabel("Crediário:");
		crediarioLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		crediarioLabel.setBounds(265, 108, 71, 14);
		gastosFormaPanel.add(crediarioLabel);
		
		parceladoTextField = new JTextField();
		parceladoTextField.setBackground(Color.WHITE);
		parceladoTextField.setEditable(false);
		parceladoTextField.setBounds(366, 30, 103, 20);
		gastosFormaPanel.add(parceladoTextField);
		parceladoTextField.setColumns(10);
		
		financiamentoTextField = new JTextField();
		financiamentoTextField.setBackground(Color.WHITE);
		financiamentoTextField.setEditable(false);
		financiamentoTextField.setBounds(366, 67, 103, 20);
		gastosFormaPanel.add(financiamentoTextField);
		financiamentoTextField.setColumns(10);
		
		crediarioTextField = new JTextField();
		crediarioTextField.setBackground(Color.WHITE);
		crediarioTextField.setEditable(false);
		crediarioTextField.setBounds(366, 105, 103, 20);
		gastosFormaPanel.add(crediarioTextField);
		crediarioTextField.setColumns(10);
		
		//Cria o graficoBotoesPanel e adiciona ao graficosPanel.
		JPanel graficoBotoesPanel = new JPanel();
		graficoBotoesPanel.setBackground(corPainel/*UIManager.getColor("Panel.background")*/);
		graficoBotoesPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Visão Gráfica Balanços", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		graficoBotoesPanel.setBounds(574, 188, 171, 154);
		balancoMensalPanel.add(graficoBotoesPanel);
		
		//Cria o receitasButton e adiciona ao graficoBotoesPanel.
		JButton balancoPagamentoButton = new JButton("Pagamentos");
		balancoPagamentoButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/dollars5.png")));
		balancoPagamentoButton.setFont(new Font("Dialog", Font.BOLD, 12));
		balancoPagamentoButton.setBounds(13, 66, 148, 33);
		balancoPagamentoButton.setToolTipText("Exibe o gr\u00E1fico com o balan\u00E7o mensal.");
		
		balancoPagamentoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgGraficoBalacoMensal(IgPlanejamentoFinanceiro.this,TituloJanela.IG_GRAFICO_DETALHE);
			}
		});
		
		//Cria o despesasButton e adiciona ao graficoBotoesPanel.
		JButton balancoMensalButton = new JButton("Mensais");
		balancoMensalButton.setFont(new Font("Dialog", Font.BOLD, 12));
		balancoMensalButton.setBounds(13, 23, 148, 33);
		balancoMensalButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/stats58.png")));
		
		balancoMensalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgGraficoBalacoMensal(IgPlanejamentoFinanceiro.this,TituloJanela.IG_GRAFICO);
			}
		});
		graficoBotoesPanel.setLayout(null);
		
		balancoMensalButton.setMnemonic(KeyEvent.VK_M);
		graficoBotoesPanel.add(balancoMensalButton);
		
		balancoPagamentoButton.setMnemonic(KeyEvent.VK_P);
		graficoBotoesPanel.add(balancoPagamentoButton);
		
		//Cria o categoriasButton e adiciona ao graficoBotoesPanel.
		JButton categoriasButton = new JButton("Categorias");
		categoriasButton.setFont(new Font("Dialog", Font.BOLD, 12));
		categoriasButton.setIcon(new ImageIcon(IgPlanejamentoFinanceiro.class.getResource("/tsi/too/samuelwagner/imagens/bars graphic4.png")));
		categoriasButton.setBounds(13, 110, 148, 33);
		categoriasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new IgGraficoBarra(IgPlanejamentoFinanceiro.this, TituloJanela.IG_GRAFICO_CATEGORIA);
			}
		});
		graficoBotoesPanel.add(categoriasButton);
		categoriasButton.setMnemonic(KeyEvent.VK_C);
		
		JPanel corBalancoPanel = new JPanel();
		corBalancoPanel.setBackground(new Color(59, 59, 59));
		corBalancoPanel.setBounds(0, 0, 50, 353);
		balancoMensalPanel.add(corBalancoPanel);
		
		JLabel mesBalancoLabel = new JLabel("Escolha o mês:");
		mesBalancoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		mesBalancoLabel.setBounds(593, 12, 88, 14);
		balancoMensalPanel.add(mesBalancoLabel);
		
		//Cria o comboBox do painel de balanço e adiciona os meses que possuem despesas cadastradas.
		mesBalancoComboBox = new JComboBox<String>(FuncaoAuxiliar.obterMesesComboBox());
		mesBalancoComboBox.setBounds(593, 37, 152, 20);
		balancoMensalPanel.add(mesBalancoComboBox);
		TratadorEventosBalancoMensal tratadorBalancoMensal = new TratadorEventosBalancoMensal(IgPlanejamentoFinanceiro.this);
		//Registra o tratador de eventos do comboBox
		mesBalancoComboBox.addActionListener(tratadorBalancoMensal);
		
		return balancoMensalPanel;
	}//criarPainelGrafico()
	
	/**
	 * Retorna a referência de uma <code>JTable</code> com as despesas recentes.
	 * @return <code>JTable</code>
	 */
	public JTable getDespesasRecentesTable() {
		return despesasRecentesTable;
	}//getDespesasRecentesTable()

	/**
	 * Retorna a referência de um <code>JComboBox</code> com as categorias para selecionar.
	 * @return <code>JComboBox</code>
	 */
	public JComboBox<String> getSelecionarCategoriaComboBox() {
		return selecionarCategoriaComboBox;
	}//getSelecionarCategoriaComboBox()
	
	/**
	 * Retorna a referência do <code>JTextField</code> com a meta da categoria.
	 * @return <code>JTextField</code>
	 */
	public JTextField getMetaCategoriaTextField() {
		return metaCategoriaTextField;
	}//getMetaCategoriaTextField()
	
	/**
	 * Retorna a referência do <code>JTextField</code> o valor da meta.
	 * @return <code>JTextField</code>
	 */
	public JTextField getMetaTextField() {
		return metaTextField;
	}//getMetaTextField()
	
	/**
	 * Retorna a referência do <code>JTextField</code> com o gasto da categoria.
	 * @return <code>JTextField</code>
	 */
	public JTextField getGastoTextField() {
		return gastoTextField;
	}//getGastoTextField() 
	
	/**
	 * Retorna a referência de uma <code>JTable</code> com a renda.
	 * @return <code>JTable</code>
	 */
	public JTable getRendaTable() {
		return rendaTable;
	}//getRendaTable()
	
	/**
	 * Retorna a referência de um <code>JComboBox</code> com o Mês da renda.
	 * @return <code>JComboBox</code>
	 */
	public JComboBox<String> getMesRendaComboBox() {
		return mesRendaComboBox;
	}//getMesRendaComboBox()
	
	/**
	 * Retorna a referência do <code>JTextField</code> responsável por emitir o alerta de gastos ao usuário.
	 * @return <code>JTextField</code>
	 */
	public JTextField getAlertaTextField() {
		return alertaTextField;
	}//getAlertaTextField
	
	/**
	 * Retorna a referência do <code>JButton</code> responsável por gerar a visão gráfica da meta. 
	 * @return <code>JButton</code>
	 */
	public JButton getVisaoGraficaButton() {
		return visaoGraficaButton;
	}//getVisaoGraficaButton
	
	/**
	 * Obtém um objeto string formatado com a data atual.
	 * @return <code>String</code> com a data formatada.
	 */
	private String obtemData() {
		Date data = new Date();
					
		data.setTime(Calendar.getInstance().getTimeInMillis());
		return String.format("%1$tA, %1$te de %1$tB de %1$tY.",data);	
	}//obtemData()
	
	/**
	 * Atualiza as informações do comboBox categoria com os nomes das categorias salvas.
	 */
	public void atualizaComboBox(){
		selecionarCategoriaComboBox.setModel(new DefaultComboBoxModel<String>(
				OperacoesDoIgPlanejamentoFinanceiro.carregarValoresComboBoxMeta()));
	}//atualizaComboBox

	/**
	 * Retorna a referência de um <code>JTextField</code> da receita do balanço.
	 * @return <code>JTextField</code>
	 */
	public JTextField getReceitasTextField() {
		return receitasTextField;
	}//getReceitasTextField()
	
	/**
	 * Retorna a referência de um <code>JComboBox</code> com os meses do balanço mensal.
	 * @return <code>JComboBox</code>
	 */
	public JComboBox<String> getMesBalancoComboBox() {
		return mesBalancoComboBox;
	}//getMesBalancoComboBox()

	/**
	 * Retorna a referência de um <code>JTextField</code> para o valor de pagamentos à vista do balanço mensal.
	 * @return <code>JTextField</code>
	 */
	public JTextField getAVistaTextField() {
		return aVistaTextField;
	}//getaVistaTextField()

	/**
	 * Retorna a referência de um <code>JTextField</code> para o valor de pagamentos no cartão do balanço mensal.
	 * @return <code>JTextField</code>
	 */
	public JTextField getCartaoCreditoTextField() {
		return cartaoCreditoTextField;
	}//getCartaoCreditoTextField()
	
	/**
	 * Retorna a referência de um <code>JTextField</code> para o valor de pagamentos com cheque do balanço mensal.
	 * @return <code>JTextField</code>
	 */
	public JTextField getChequeTextField() {
		return chequeTextField;
	}//getChequeTextField()

	/**
	 * Retorna a referência de um <code>JTextField</code> para o valor de pagamentos parcelados do balanço mensal.
	 * @return <code>JTextField</code>
	 */
	public JTextField getParceladoTextField() {
		return parceladoTextField;
	}//getParceladoTextField()

	/**
	 * Retorna a referência de um <code>JTextField</code> para o valor de pagamentos financiados do balanço mensal.
	 * @return <code>JTextField</code>
	 */
	public JTextField getFinanciamentoTextField() {
		return financiamentoTextField;
	}//getFinanciamentosTextField()
	
	/**
	 * Retorna a referência de um <code>JTextField</code> para o valor de pagamentos no crediário do balanço mensal.
	 * @return <code>JTextField</code>
	 */
	public JTextField getCrediarioTextField() {
		return crediarioTextField;
	}//getCrediarioTextField()
	
	/**
	 * Retorna a referência de um <code>JTextField</code> para o valor das despesas do balanço mensal.
	 * @return <code>JTextField</code>
	 */
	public JTextField getDespesaTextField() {
		return despesaTextField;
	}//getDespesaTextField()
	
	/**
	 * Retorna a referência de um <code>JTextField</code> para o saldo do balanço mensal.
	 * @return <code>JTextField</code>
	 */
	public JTextField getSaldoTextField() {
		return saldoTextField;
	}//getSaldoTextField()
}//class IgPlanejamentoFinanceiro
