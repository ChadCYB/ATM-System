import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SaveFrame extends JFrame {
	
	private JPanel contentPane=new JPanel();
	private JPanel north = new JPanel();
	private JLabel now$ = new JLabel("�ثe���B:");
	private JLabel money = new JLabel("����");
	private JPanel center = new JPanel();
	private JLabel lab$ = new JLabel("�п�J���B:");
	private JTextPane textPane = new JTextPane();
	private JPanel bottom = new JPanel();
	private JButton Confirm = new JButton("�T�{");
	private JButton Break = new JButton("�h�^");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveFrame frame = new SaveFrame();
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
	public SaveFrame() {
		SaveFrame1();
	}
	public void SaveFrame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 5, 5));
		
		north.setLayout(new GridLayout(1, 0, 5, 5));
		
		now$.setFont(new Font("�s�ө���", Font.BOLD, 22));
		now$.setHorizontalAlignment(SwingConstants.CENTER);
		
		center.setLayout(new GridLayout(1, 0, 0, 0));
		
		lab$.setFont(new Font("�s�ө���", Font.PLAIN, 16));
		
		bottom.setLayout(new GridLayout(1, 2, 5, 5));
		
		Confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		Break.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		contentPane.add(north);
		north.add(now$);
		north.add(money);
		contentPane.add(center);
		center.add(lab$);
		center.add(textPane);
		contentPane.add(bottom);
		bottom.add(Confirm);
		bottom.add(Break);
		
	}

}
