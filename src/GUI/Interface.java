package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.RepositorioMidia;
import DAO.RepositorioProduto;
import Models.Produto;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Canvas;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField codField;
	private JTextField valorField;
	private JTextField descField;
	private String caminho;
	private JTextField CodConsField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Consulta = new JPanel();
		tabbedPane.addTab("Consulta", null, Consulta, null);
		Consulta.setLayout(null);
		
		JPanel Busca = new JPanel();
		Busca.setBounds(0, 0, 419, 33);
		Consulta.add(Busca);
		
		JLabel lblCdigo_1 = new JLabel("C\u00F3digo:");
		Busca.add(lblCdigo_1);
		
		CodConsField = new JTextField();
		Busca.add(CodConsField);
		CodConsField.setColumns(10);
		
		
		JLabel lblValor_1 = new JLabel("Valor:");
		lblValor_1.setBounds(10, 38, 46, 20);
		Consulta.add(lblValor_1);
		
		JTextPane valorPane = new JTextPane();
		valorPane.setBounds(51, 38, 139, 20);
		Consulta.add(valorPane);
		
		JTextPane descricaoPane = new JTextPane();
		descricaoPane.setBounds(10, 71, 180, 142);
		Consulta.add(descricaoPane);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		 
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(200, 39, 209, 174);
		Consulta.add(lblNewLabel);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int codigoConsulta = Integer.parseInt(CodConsField.getText());
				Produto p = null;
				try {
					p = (Produto) RepositorioProduto.consultarCod(codigoConsulta);
					valorPane.setText("" + p.getvalor());
					descricaoPane.setText(p.getdescricao());
				} catch (SQLException e) {
					e.printStackTrace();
					descricaoPane.setText("Código não cadastrado");
				}
				try {
					String icon =  (String) RepositorioMidia.consultarCod(codigoConsulta);
					lblNewLabel.setIcon(new ImageIcon(icon));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		Busca.add(btnConsultar);
		
		
		JPanel Cadastro = new JPanel();
		tabbedPane.addTab("Cadastro", null, Cadastro, null);
		Cadastro.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigo.setBounds(10, 11, 73, 19);
		Cadastro.add(lblCdigo);
		
		codField = new JTextField();
		codField.setBounds(100, 12, 309, 20);
		Cadastro.add(codField);
		codField.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 42, 73, 19);
		Cadastro.add(lblValor);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrio.setBounds(10, 72, 73, 19);
		Cadastro.add(lblDescrio);
		
		valorField = new JTextField();
		valorField.setBounds(100, 43, 309, 20);
		Cadastro.add(valorField);
		valorField.setColumns(10);
		
		descField = new JTextField();
		descField.setBounds(100, 73, 309, 44);
		Cadastro.add(descField);
		descField.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setEnabled(false);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try{
				int codigo = Integer.parseInt(codField.getText());
				double valor = Double.parseDouble(valorField.getText());
				String descricao = descField.getText();
				Produto produto = new Produto(codigo,descricao,valor);
				RepositorioProduto.armazenar(produto);
				RepositorioMidia.armazenar(produto, caminho);
				
				}catch(NumberFormatException er){
					JOptionPane.showMessageDialog(null, "Apenas números");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
			}
		});
		btnCadastrar.setBounds(274, 184, 135, 29);
		Cadastro.add(btnCadastrar);
		
		JButton btnSelecionarImagem = new JButton("Selecionar imagem");
		btnSelecionarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser abrir = new JFileChooser();
				int retorno = abrir.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					caminho = abrir.getSelectedFile().getAbsolutePath();
					btnCadastrar.setEnabled(true);
				}
			}
		});
		btnSelecionarImagem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSelecionarImagem.setBounds(100, 184, 136, 29);
		Cadastro.add(btnSelecionarImagem);

		
		
	}
}
