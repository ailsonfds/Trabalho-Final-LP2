/**
 * Package to generate a user interface
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.BlackListException;
import exceptions.EmptySearchException;
import exceptions.EmptyWordException;
import exceptions.FileAlreadyExistsException;
import exceptions.FileTypeException;
import index.Indexer;
import utils.TrieNode;

/**
 * The GUI elements generator
 * 
 * @author Valmir Correa
 * @author Ailson Forte dos Santos
 *
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private Indexer indexer;

	/**
	 * @wbp.nonvisual location=199,149
	 */
	private final ImageIcon imageIcon = new ImageIcon();

	public static void main(String args[]) {
		Window teste = new Window();
		teste.setVisible(true);
	}

	/**
	 * Default builder for initializing interface components
	 */
	public Window() {

		indexer = new Indexer();

		setBounds(500, 300, 450, 300); // posi��o de inicio

		imageIcon.setImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\valmir.correa\\Downloads\\toogle.jpg"));
		getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(103, 145, 256, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnBuscaAnd = new JButton("Busca AND"); // PEGAR A PALAVRA E BUSCAR AQUI
		btnBuscaAnd.setBounds(103, 177, 119, 23);
		btnBuscaAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField.getText();
				buscaOr(str);
			}
		});
		getContentPane().add(btnBuscaAnd);

		JButton btnBuscaOr = new JButton("Busca OR"); // PEGAR A PALAVRA E BUSCAR AQUI
		btnBuscaOr.setBounds(240, 177, 119, 23);
		btnBuscaOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText();
				buscaOr(str);
			}
		});
		getContentPane().add(btnBuscaOr);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Arquivo                 ");
		menuBar.add(mnArquivo);

		JMenuItem mntmAddArquivo = new JMenuItem("Adicionar");
		mntmAddArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarArquivo();
			}
		});
		mnArquivo.add(mntmAddArquivo);

		JMenuItem mntmRemover = new JMenuItem("Remover ");
		mnArquivo.add(mntmRemover);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mnArquivo.add(mntmListar);

		JMenuItem mntmAtualizar = new JMenuItem("Atualizar");
		mnArquivo.add(mntmAtualizar);

		JMenuItem mntmAddListaNegra = new JMenuItem("Add Lista Negra");
		mnArquivo.add(mntmAddListaNegra);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Perform "AND" Search
	 */
	private void buscaAnd(String arg) {
		try {
			HashMap<String, TrieNode> result = indexer.searchAND(arg);
			Container actualPanel = getContentPane();
			JPanel resultPanel = new JPanel();
			JList<String> list = new JList<>();
			JScrollPane scroll = new JScrollPane(list);
			JButton returnBtn = new JButton("Retornar a página inicial");
			
			returnBtn.setBounds(240, 177, 119, 23);
			
			resultPanel.add(scroll);
			resultPanel.add(returnBtn, BorderLayout.SOUTH);
			
			returnBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					remove(resultPanel);
					add(actualPanel);
				}
			});
			
			remove(actualPanel);
			add(resultPanel);
		} catch (EmptyWordException | EmptySearchException | BlackListException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Perform "OR" Search
	 */
	private void buscaOr(String arg) {

	}

	/**
	 * Add file to database
	 */
	private void adicionarArquivo() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(new JDialog());

		File file = new File("");

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			file = chooser.getSelectedFile();
			JOptionPane.showMessageDialog(null, "Arquivo incluido com sucesso!");

		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Cancelado!");
		}
		addFile(file.getName());
	}

	/**
	 * Add the file
	 */
	public void addFile(String file) {
		try {
			indexer.addDocument(file);
		} catch (FileTypeException | FileAlreadyExistsException e2) {
			e2.printStackTrace();
		}
	}
}