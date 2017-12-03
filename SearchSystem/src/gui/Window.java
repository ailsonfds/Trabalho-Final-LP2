/**
 * 
 */
package gui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * The GUI elements generator
 * @author4 Valmir Correa
 *
 */
public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	/**
	 * @wbp.nonvisual location=199,119
	 */
	private final ImageIcon imageIcon = new ImageIcon();
	
	public static void main (String args[]) {
		Window teste = new Window ();
		teste.setVisible(true);
	}

	/**
	 * 
	 */
	public Window() {
		
		setBounds(500, 300, 450, 300);		// posi��o de inicio
		
		imageIcon.setImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\valmir.correa\\Downloads\\toogle.jpg"));
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(84, 115, 256, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBuscaAnd = new JButton("Busca AND");
		btnBuscaAnd.setBounds(106, 146, 105, 23);
		getContentPane().add(btnBuscaAnd);
		
		JButton btnBuscaOr = new JButton("Busca OR");
		btnBuscaOr.setBounds(221, 146, 105, 23);
		getContentPane().add(btnBuscaOr);
		// TODO Auto-generated constructor stub
	}
}