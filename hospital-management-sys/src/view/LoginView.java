package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import control.Hospital;
import model.StaffMember;
import view.utils.MainFrame;
import view.utils.MedicalMenuBar;
import view.utils.ViewType;

public class LoginView extends JPanel implements ActionListener {

	private JTextField usernameF;
	private JPasswordField passwordF;
	private JButton loginBTN;
	
	/**
	 * Create the frame.
	 */
	public LoginView() {
		
		setBackground(new Color(144, 238, 144));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(307, 54, 293, 119);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(10, 11, 63, 14);
		panel.add(lblNewLabel);
		
		usernameF = new JTextField();
		usernameF.setBounds(83, 8, 200, 20);
		panel.add(usernameF);
		usernameF.setColumns(10);
		
		passwordF = new JPasswordField();
		passwordF.setBounds(83, 39, 200, 20);
		panel.add(passwordF);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 42, 63, 14);
		panel.add(lblPassword);
		
		loginBTN = new JButton("Login");
		loginBTN.setForeground(new Color(255, 255, 255));
		loginBTN.setBackground(new Color(0, 128, 0));
		loginBTN.setBounds(10, 70, 111, 38);
		loginBTN.addActionListener(this);
		panel.add(loginBTN);
		
		JLabel mainLabel = new JLabel("Port Hospital");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		mainLabel.setBounds(10, 11, 864, 32);
		add(mainLabel);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginBTN) {
			String username = usernameF.getText();
			String password = String.valueOf(passwordF.getPassword());
			if (username.equals("ADMIN") && password.equals("ADMIN")) {
				MainFrame.setProfileImage("data/admin.jpg");
				MainFrame.setAdmin(true);
				MainFrame.getInstance().setJMenuBar(new MedicalMenuBar(true));
				MainFrame.getInstance().setView(ViewType.DASHBOARD);
			} else {
				boolean found = false;
				for (StaffMember each: Hospital.getInstance().getStaffMembers().values()) {
					if (each.getEmail().equals(username) && each.getPassword().equals(password)) {
						found = true;
						MainFrame.setProfileImage(each.getProfilePicture());
						MainFrame.setAdmin(false);
						MainFrame.setStaff(each);
						MainFrame.getInstance().setJMenuBar(new MedicalMenuBar(false));
						MainFrame.getInstance().setView(ViewType.DASHBOARD);
						break;
					}
				}
				if (!found) {
					JOptionPane.showMessageDialog(this, "Username or password is wrong!");
				}
			}
			
		}
	}
}
