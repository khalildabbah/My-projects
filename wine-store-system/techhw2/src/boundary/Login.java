package boundary;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import control.DatabaseController;



public class Login extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton;




	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 311);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	getContentPane().setBackground(SystemColor.activeCaption);
    	setBackground(SystemColor.activeCaption);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(SystemColor.activeCaption);

		JLabel lblNewLabel = new JLabel("Cheers - Control System");
		lblNewLabel.setFont(new Font("Bookman Old Style", Font.BOLD, 30));
		lblNewLabel.setBounds(53, 23, 475, 54);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Please enter username and password to proceed");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(25, 90, 450, 46);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(25, 179, 110, 20);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(25, 205, 122, 20);
		contentPane.add(lblNewLabel_2_1);


		textField = new JTextField();
		textField.setBounds(134, 181, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(134, 205, 96, 20);
		contentPane.add(passwordField);

		btnNewButton = new CustomButton("Login",150,50,40);
		btnNewButton.setToolTipText("Press to login");
		btnNewButton.addActionListener(e -> checkLogin());
        getRootPane().setDefaultButton(btnNewButton);
		btnNewButton.setBounds(250, 179, 96, 46);
		contentPane.add(btnNewButton);





	}

	private void checkLogin() {



		String username = textField.getText();
		String password ="";
		for(char c : passwordField.getPassword()) {
			password = password + c;
		}
		if(username.equals("Admin") == false && username.equals("Sales") == false && username.equals("Marketing") == false) {
            JOptionPane.showMessageDialog(this, "Incorrect username, Please try again.", "Login Alert", JOptionPane.ERROR_MESSAGE);
            return;
			
		}

		if(username.equals("Admin") && password.equals("Admin")) {
            openAdminFrame();
            dispose();
            return;
		}
		
		
		
		if(username.equals("Sales") && password.equals("Sales")) {
            openSalesFrame();
            dispose();
            return;
		}
		if(username.equals("Marketing") && password.equals("Marketing")) {
            openMarketingFrame();
            dispose();
            return;
		}
		
	    JOptionPane.showMessageDialog(this, "Incorrect password, Please try again.", "Login Alert", JOptionPane.ERROR_MESSAGE);
			
	}


	private void openAdminFrame() {
		JFrame f = new Admin();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		dispose();


	}
	
	private void openSalesFrame() {
		JFrame f = new SalesFrame();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		dispose();


	}
	private void openMarketingFrame() {
		JFrame f = new MarketingFrame();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		dispose();
	}



}

