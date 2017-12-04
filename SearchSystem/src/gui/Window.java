/**
 * Package to generate a user interface
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.BlackListException;
import exceptions.EmptySearchException;
import exceptions.EmptyWordException;
import exceptions.FileAlreadyExistsException;
import exceptions.FileNotFoundException;
import exceptions.FileTypeException;
import exceptions.TrieInsertionException;
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
		setBounds(500, 300, 450, 300); // posição de inicio

		imageIcon.setImage(Toolkit.getDefaultToolkit().getImage("1f50e.png"));
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
				buscaAnd(str);
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

		JMenuItem mntmAddArquivo = new JMenuItem("Adicionar arquivo"); // ok
		mntmAddArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adicionarArquivo();
			}
		});
		mnArquivo.add(mntmAddArquivo);

		JMenuItem mntmRemover = new JMenuItem("Remover arquivo"); // ok
																	
		mntmRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removerArquivo();
			}
		});
		mnArquivo.add(mntmRemover);

		JMenuItem mntmListar = new JMenuItem("Listar arquivos"); // ok
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				listFiles();
			}
		});
		mnArquivo.add(mntmListar);

		JMenuItem mntmAtualizar = new JMenuItem("Atualizar arquivo"); // ok
		mntmAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizarArquivo();
			}
		});
		mnArquivo.add(mntmAtualizar);

		JMenuItem mntmAddListaNegra = new JMenuItem("Add Blacklist"); // ok
		mntmAddListaNegra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg1) {
				addBlacklist();
			}
		});
		mnArquivo.add(mntmAddListaNegra);
	}

	/**
	 * Perform "AND" Search
	 * 
	 * @throws TrieInsertionException
	 */
	private void buscaAnd(String arg) {
		try {
			DefaultListModel<String> model = new DefaultListModel<>();
			HashMap<String, TrieNode> result = indexer.searchAND(arg);
			for (String keySearch : result.keySet()) {
				TrieNode node = result.get(keySearch);
				if (node.getValue() == null)
					throw new TrieInsertionException("Ferrou");
				for (String file : node.getValue().keySet()) {
					HashMap<Integer, Integer> occr = node.getValue().get(file);
					for (Integer line : occr.keySet()) {
						String printout = keySearch + ": " + file + "-> " + line + " : " + occr.get(line) / 2;
						model.addElement(printout);
					}
				}
			}
			JPanel actualPanel = new JPanel();
			JList<String> list = new JList<>(model);
			JScrollPane scroll = new JScrollPane(list);
			scroll.setVisible(true);

			actualPanel.add(scroll, BorderLayout.EAST);
			actualPanel.setVisible(true);
		} catch (EmptyWordException | EmptySearchException | BlackListException | TrieInsertionException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Perform "OR" Search
	 */
	private void buscaOr(String word) {
		try {
			ArrayList<String> model = new ArrayList<>();
			HashMap<String, TrieNode> result = indexer.searchOR(word);
			for (String keySearch : result.keySet()) {
				TrieNode node = result.get(keySearch);
				if (node.getValue() == null)
					throw new TrieInsertionException("Something went wrong!");
				for (String file : node.getValue().keySet()) {
					HashMap<Integer, Integer> occr = node.getValue().get(file);
					for (Integer line : occr.keySet()) {
						String printout = keySearch + ": " + " FILE " + file + "-> " + " LINE " + line + " : " + " OCORRÊNCIAS " + occr.get(line) / 2 ;
						model.add(printout);
					}
				}
			}
			JFrame frame = new JFrame();
			frame.setBounds(1000, 600, 700, 300);
			frame.setVisible(true);
			 JTextArea textArea = new JTextArea();
			 for(String m : model){
				   textArea.append(m + "\n");
				}
			 textArea.setBounds(500, 300, 450, 300);
			 frame.getContentPane().add(textArea);
		} catch (EmptyWordException | EmptySearchException | BlackListException | TrieInsertionException e) {
			JOptionPane.showMessageDialog(null, e);
		}
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
			if (addFile(file.getName())) {
				JOptionPane.showMessageDialog(null, "Arquivo incluido com sucesso!");
			}
		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Cancelado!");
		}
	}

	/**
	 * Update file to database
	 */
	private void atualizarArquivo() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(new JDialog());

		File file = new File("");

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			if (updateFile(file.getName())) {
				JOptionPane.showMessageDialog(null, "Arquivo atualizado com sucesso!");
			}
		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Cancelado!");
		}
	}

	/**
	 * Update the file
	 */
	public boolean updateFile(String file) {
		try {
			indexer.updateDocuments(file);
			return true;
		} catch (FileTypeException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}

	/**
	 * Remove a file to database
	 */
	private void removerArquivo() {
		JFrame remove = new JFrame();
		JPanel contentPane = new JPanel();
		JTextField textFieldRemove = new JTextField();
		remove.setTitle("Remover arquivo");
		remove.getContentPane().setLayout(null);
		remove.setBounds(500, 300, 450, 150);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		remove.setContentPane(contentPane);

		JLabel lblcampo = new JLabel("Nome do arquivo:");
		lblcampo.setBounds(25, 59, 46, 14);
		textFieldRemove.setBounds(134, 56, 86, 20);
		textFieldRemove.setColumns(10);
		remove.getContentPane().add(lblcampo);
		remove.getContentPane().add(textFieldRemove);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(23, 151, 89, 23);
		remove.getContentPane().add(btnOk);

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textFieldRemove.getText();
				if (removeFile(str)) {
					JOptionPane.showMessageDialog(null, "Arquivo removido com sucesso!");
				}
			}
		});
		remove.getContentPane().add(btnOk);

		remove.setVisible(true);
	}

	/**
	 * Remove the file
	 */
	public boolean removeFile(String file) {
		try {
			indexer.removeDocument(file);
			return true;
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}

	/**
	 * List files
	 */
	public void listFiles() {
		ArrayList<String> list = indexer.listDocuments();
		String aux = "";
		for (String l : list) {
			aux += l + "\n";
		}
		if (aux == "") {
			JOptionPane.showMessageDialog(null, "No files in the system");
		} else {
			JOptionPane.showMessageDialog(null, aux);
		}
	}

	/**
	 * Add the file
	 */
	public boolean addFile(String file) {
		try {
			indexer.addDocument(file);
			return true;
		} catch (FileTypeException | FileAlreadyExistsException e2) {
			JOptionPane.showMessageDialog(null, e2);
		}
		return false;
	}

	/**
	 * Add the blacklist
	 */
	public boolean addBlacklistFile(String file) {
		try {
			indexer.readBlacklist(file);
			return true;
		} catch (TrieInsertionException | FileTypeException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return false;
	}

	/**
	 * Add the blackList
	 */
	public void addBlacklist() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
		chooser.setFileFilter(filter);

		int returnVal = chooser.showOpenDialog(new JDialog());

		File file = new File("");

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			if (addBlacklistFile(file.getName())) {
				JOptionPane.showMessageDialog(null, "Arquivo incluido com sucesso!");
			}
		} else if (returnVal == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Cancelado!");
		}
	}
}