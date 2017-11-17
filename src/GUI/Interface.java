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

public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String caminho;
	private JTextField textField_3;

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
		
		JLabel lblCdigo_1 = new JLabel("C\u00F3digo:");
		lblCdigo_1.setBounds(10, 199, 46, 14);
		Consulta.add(lblCdigo_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(66, 196, 143, 20);
		Consulta.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(320, 195, 89, 23);
		Consulta.add(btnConsultar);
		tabbedPane.setBackgroundAt(0, Color.GRAY);
		
		JPanel Cadastro = new JPanel();
		tabbedPane.addTab("Cadastro", null, Cadastro, null);
		Cadastro.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCdigo.setBounds(10, 11, 73, 19);
		Cadastro.add(lblCdigo);
		
		textField = new JTextField();
		textField.setBounds(100, 12, 309, 20);
		Cadastro.add(textField);
		textField.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 42, 73, 19);
		Cadastro.add(lblValor);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescrio.setBounds(10, 72, 73, 19);
		Cadastro.add(lblDescrio);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 43, 309, 20);
		Cadastro.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 73, 309, 44);
		Cadastro.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSelecionarImagem = new JButton("Selecionar imagem");
		btnSelecionarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser abrir = new JFileChooser();
				int retorno = abrir.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION) {
					caminho = abrir.getSelectedFile().getAbsolutePath();
				}
			}
		});
		btnSelecionarImagem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSelecionarImagem.setBounds(100, 184, 136, 29);
		Cadastro.add(btnSelecionarImagem);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigo = Integer.parseInt(textField.getText());
				double valor = Double.parseDouble(textField_1.getText());
				String descricao = textField_2.getText();
				Produto produto = new Produto(codigo,descricao,valor);
				try {
					RepositorioProduto.armazenar(produto);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					RepositorioMidia.armazenar(produto, caminho);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(274, 184, 135, 29);
		Cadastro.add(btnCadastrar);
	}
}
