/**
 * Package to generate a user interface
 */
package gui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.FileAlreadyExistsException;
import exceptions.FileTypeException;
import index.Indexer;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

/**
 * The GUI elements generator
 * @author4 Valmir Correa
 *
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	
	/**
	 * @wbp.nonvisual location=199,149
	 */
	private final ImageIcon imageIcon = new ImageIcon();
	
	public static void main (String args[]) {
		Window teste = new Window ();
		teste.setVisible(true);
	}

	/**
	 * Default builder for initializing interface components
	 */
	public Window() {
		
		setBounds(500, 300, 450, 300);		// posi��o de inicio
		
		imageIcon.setImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\valmir.correa\\Downloads\\toogle.jpg"));
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(103, 145, 256, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscaAnd = new JButton("Busca AND"); 	// PEGAR A PALAVRA E BUSCAR AQUI
		btnBuscaAnd.setBounds(103, 177, 119, 23);
		btnBuscaAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscaAnd ();
			}
		});
		getContentPane().add(btnBuscaAnd);
		
		JButton btnBuscaOr = new JButton("Busca OR");		// PEGAR A PALAVRA E BUSCAR AQUI
		btnBuscaOr.setBounds(240, 177, 119, 23);
		btnBuscaOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscaOr ();
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
	private void buscaAnd () {

	}
	
	/**
	* Perform "OR" Search
	*/
	private void buscaOr () {
		
	}
	
	/**
	* Add file to database
	*/
	private void adicionarArquivo () {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter ("*.txt","txt");
		chooser.setFileFilter(filter);
		
		int returnVal = chooser.showOpenDialog(new JDialog());
		
		File file = new File ("");
				
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			 
			file = chooser.getSelectedFile();
			JOptionPane.showMessageDialog(null, "Arquivo incluido com sucesso!");
			
		}
		else if (returnVal == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Cancelado!");
		}
		addFile(file.getName());
	}
	
	/**
	* Add the file
	*/
	public void addFile(String file) {
		Indexer index = new Indexer();
		try {
			index.addDocument(file);
		} catch (FileTypeException | FileAlreadyExistsException e2) {
			e2.printStackTrace();
		}
	}
}