package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

public class ErauzleaGUI extends JFrame {

	private JPanel contentPane;
	private String irteera;
	private JProgressBar progressBar = new JProgressBar();
	private JLabel lblAbisua;
	
	private static int hasiera_denboraldia = 35;
	private static int hasiera_partidua = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErauzleaGUI frame = new ErauzleaGUI();
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
	public ErauzleaGUI() {
		setTitle("HTML Erauzlea");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 246);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lb_aukera = new JLabel("Aukeratu irteera direktorioa:");
		
		JButton btnArakatu = new JButton("Arakatu...");
		btnArakatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser  fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("."));
				fc.setDialogTitle("Aukeratu irteera direktorioa");
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int result = fc.showOpenDialog(null);			
				if(result == JFileChooser.APPROVE_OPTION){
				 irteera = fc.getSelectedFile().getAbsolutePath();
				 lblAbisua.setText(irteera);
				 System.out.println(irteera);
				}
			}
		});
		
		JButton btnJaitsi = new JButton("Jaitsi");
		btnJaitsi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if(irteera != null){
					progressBar.setMinimum(0);
					progressBar.setMaximum(6732);
					progressBar.setValue(0);
					progressBar.setStringPainted(true);
					
					Thread t = new Thread(){
				        public void run(){
				        	
				        	String html;
				        	for(int i=hasiera_denboraldia;i<57;i++){
				        		for(int j=hasiera_partidua;j<307;j++){
				        			
				        			try {
										html = html_erauzlea.Erauzlea.erauziHTML(i,j);
										html_erauzlea.Erauzlea.gordeHTML(html, irteera, i, j);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
				        			
				        							        			
					                final int percent = j+(i-35)*306;
					                SwingUtilities.invokeLater(new Runnable() {
					                    public void run() {
					                    	progressBar.setValue(percent);
					                    }
					                  });
	
					                try {
					                    Thread.sleep(100);
					                } catch (InterruptedException e) {}
				        		}
				            }
				        }
				    };
				    t.start();
				    
				}
				else{
					lblAbisua.setText("Irteera direktorioa aukeratu behar duzu");
				}
			}
		});
		
		JTextPane txtpnAzalpena = new JTextPane();
		txtpnAzalpena.setEnabled(false);
		txtpnAzalpena.setText("Datuen erauzketa prozesuarekin hasteko, aukeratu webgunea nora deskargatuko den eta ondoren sakatu \"Hasi\" botoia.");
		txtpnAzalpena.setEditable(false);
		txtpnAzalpena.setDisabledTextColor(Color.BLACK);
		
		JButton btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		
		lblAbisua = new JLabel("Ez dago irteerarik aukeratuta");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnAzalpena, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnJaitsi)
							.addGap(38)
							.addComponent(btnEzeztatu))
						.addComponent(lb_aukera)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnArakatu)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblAbisua)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnAzalpena, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lb_aukera)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnArakatu)
						.addComponent(lblAbisua))
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnJaitsi)
						.addComponent(btnEzeztatu))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
