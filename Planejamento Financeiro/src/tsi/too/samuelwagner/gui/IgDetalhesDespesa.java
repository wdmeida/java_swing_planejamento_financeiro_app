package tsi.too.samuelwagner.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tsi.too.samuelwagner.operacoes.GerenciamentoDeFinanca;
import tsi.too.samuelwagner.tipo.Despesa;
import tsi.too.samuelwagner.validacoes.FuncaoAuxiliar;

/**
 * A classe <code>IgDetalhesDespesa</code> exibe a janela com todas as informações das despesas.
 * @author Wagner Almeida
 * @authos Samuel Gonçalves
 *
 */
public class IgDetalhesDespesa extends JDialog {
	private IgPlanejamentoFinanceiro planejamentoFinanceiro;
	private GerenciamentoDeFinanca gerenciamentoDeFinanca;
	private JScrollPane tabelaDespesaScrollPane;
	private JTable despesaTable;
	private JComboBox<String> periodoComboBox;
	private static Color corPainel = new Color(248, 248, 248);
	
	/**
	 * Contrutor sobrecarregado da classe <code>IgDetalhesDespesa</code> responsável por construir a interface gráfica.
	 * @param planejamentoFinanceiro <code>IgPlanejamentoFinanceiro</code> com a referência da janela principal.
	 * @param gerenciamentoDeFinanca <code>GerenciamentoDeFinanca</code> com a referência da classe que controla as operações.
	 */
	public IgDetalhesDespesa(IgPlanejamentoFinanceiro planejamentoFinanceiro, GerenciamentoDeFinanca gerenciamentoDeFinanca) {
		setTitle("Despesas");
		//Define as propriedades da janela.
		setSize(968, 500);
		setModal(true);
		setLocationRelativeTo(planejamentoFinanceiro);
		setResizable(false);
		
		
		//Atribui a referência da janela principal e da classe que controla as operações as variáveis de instância.
		this.planejamentoFinanceiro = planejamentoFinanceiro;
		this.gerenciamentoDeFinanca = gerenciamentoDeFinanca;
		getContentPane().setLayout(null);
		getContentPane().setBackground(corPainel);
		JPanel opcaoDespesaPanel = new JPanel();
		opcaoDespesaPanel.setBackground(corPainel);
		opcaoDespesaPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Despesas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		opcaoDespesaPanel.setBounds(809, 11, 143, 124);
		getContentPane().add(opcaoDespesaPanel);
		opcaoDespesaPanel.setLayout(null);
		
		setLocationRelativeTo(this.planejamentoFinanceiro);
		
		//Cria o painel de despesas
		JPanel despesaPanel = new JPanel();
		despesaPanel.setBackground(corPainel);
		despesaPanel.setBounds(10, 11, 789, 450);
		getContentPane().add(despesaPanel);
		despesaPanel.setLayout(new BorderLayout(0, 0));
		
		//Cria o periodo comboBox.
		periodoComboBox = new JComboBox<String>(FuncaoAuxiliar.obterMesesComboBox());
		periodoComboBox.setToolTipText("Selecione o m\u00EAs das despesas que queira pesquisar.");
		periodoComboBox.setBounds(10, 48, 123, 20);
		opcaoDespesaPanel.add(periodoComboBox);
		
		//Cria um painel rolável.
		tabelaDespesaScrollPane = new JScrollPane();
		despesaPanel.add(tabelaDespesaScrollPane, BorderLayout.CENTER);
		
		//Cria uma tabela para exibir as despesas.
		String titulos[] = {"Descrição","Categoria","Data de Pagamento","Data da Despesa", "Forma de Pagamento",
							"Número do Cheque","Parcelas","Valor"};
		despesaTable = new JTable();
		despesaTable.setFont(new Font("Arial", Font.PLAIN, 13));
		// por padrão, vem sem bordas, então colocamos:
		despesaTable.setBorder(new LineBorder(Color.black));
		despesaTable.setGridColor(Color.black);
		despesaTable.setShowGrid(true);
		despesaTable.setEnabled(false);
		tabelaDespesaScrollPane.setViewportView(despesaTable);
		
		JLabel periodoLabel = new JLabel("M\u00EAs:");
		periodoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		periodoLabel.setBounds(10, 23, 113, 14);
		opcaoDespesaPanel.add(periodoLabel);
		
		JButton fecharButton = new JButton("Fechar");
		fecharButton.setIcon(new ImageIcon(IgDetalhesDespesa.class.getResource("/tsi/too/samuelwagner/imagens/delete.png")));
		fecharButton.setBounds(10, 79, 123, 28);
		opcaoDespesaPanel.add(fecharButton);
		
		//Registra o tratador de eventos do botão fechar.
		fecharButton.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				IgDetalhesDespesa.this.dispose();
			}
		});
		
		//Registra o tratador de eventos da janela.
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				IgDetalhesDespesa.this.dispose();
			}
		});
		
		//Registra o tratador de eventos do comboBox
		periodoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				despesaTable.setModel(new DefaultTableModel(carregarDespesas(periodoComboBox.getItemAt(periodoComboBox.getSelectedIndex())),titulos));
			}
		});
		
		periodoComboBox.setSelectedIndex(0);
		setVisible(true);
	}//IgDetalhesDespesa()
	
	
	/**
	 * Obtém as informações das despesas selecionadas pelo usuário e retorna um array multidimensional 
	 * contendo as informações.
	 * @param mesAno <code>String</code> com o mês e ano da despesa.
	 * @return um <code>String[][]</code> com as informações das despesas.
	 */
	private String[][] carregarDespesas(String mesAno) {		
		//Obtém o mesAno já em calendar
		if(!mesAno.equals("")){
			Calendar data = FuncaoAuxiliar.converteDataParaCalendar(mesAno);
		
			//Obtém as despesas do mês pesquisado.
			Despesa despesas[] = gerenciamentoDeFinanca.getControleDespesa().pesquisarDespesas(data);
		
			String dados[][] = new String[despesas.length][8];
			
			for(int linha = 0; linha < despesas.length; linha++){
					dados[linha][0] = despesas[linha].getDescricao(); //Descrição.
					dados[linha][1] = gerenciamentoDeFinanca.getControleCategoria().pesquisaCategoria(despesas[linha].getCodigoCategoria()); //Categoria
					dados[linha][2] = FuncaoAuxiliar.coverteDataParaString(despesas[linha].getDataPagamento(),true); //Data Pagamento.
					dados[linha][3] = FuncaoAuxiliar.coverteDataParaString(despesas[linha].getDataDespesa(),true); //Data Despesa.
					dados[linha][4] = gerenciamentoDeFinanca.getControleFormaPagamento().pesquisaFormaPagamento(despesas[linha].getCodigoPagamento()); //Forma de Pagamento
					dados[linha][5] = despesas[linha].getNumeroCheque(); //Número Cheque
					dados[linha][6] = Integer.toString(despesas[linha].getNumeroParcelas()); //Número Parcelas
					dados[linha][7] = String.format("R$ %1.2f",despesas[linha].getValorDespesa()); //Valor Despesa
			}
			return dados;
		}
		return null;
	}//carregarDespesas()
}//class IgDetalhesDespesa
