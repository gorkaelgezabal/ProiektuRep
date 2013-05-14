package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ParseatzaileaGUI extends JFrame {

	private JPanel contentPane;
	private String irteera;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParseatzaileaGUI frame = new ParseatzaileaGUI();
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
	public ParseatzaileaGUI() {
		setTitle("Parseatzailea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[][][]"));
		
		JButton btnAukeratuDirektorioa = new JButton("Aukeratu direktorioa");
		btnAukeratuDirektorioa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser  fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("."));
				fc.setDialogTitle("Aukeratu irteera direktorioa");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fc.showOpenDialog(null);			
				if(result == JFileChooser.APPROVE_OPTION){
				 irteera = fc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		contentPane.add(btnAukeratuDirektorioa, "cell 0 1");
		
		JButton btnDatubaseratu = new JButton("Datubaseratu");
		contentPane.add(btnDatubaseratu, "cell 0 2");
	}

}
