package boundary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import control.DatabaseController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteEmployee extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField IDtoDelete;
    private ManageEmployees ef; // reference to manage employee page


    public DeleteEmployee(ManageEmployees main) {
    	
    	ef = main; // set reference

        setTitle("Delete Wine");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    	getContentPane().setBackground(SystemColor.activeCaption);

        JLabel lblNewLabel = new JLabel("Insert Employee ID to delete:");
        lblNewLabel.setBackground(Color.RED);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(80, 21, 277, 67);
        contentPane.add(lblNewLabel);

        IDtoDelete = new JTextField();
        IDtoDelete.setFont(new Font("Dialog", Font.PLAIN, 16));
        IDtoDelete.setBounds(171, 89, 96, 34);
        contentPane.add(IDtoDelete);
        IDtoDelete.setColumns(10);

        JButton delete_btn = new CustomButton("Delete",150,50,40);
        delete_btn.setFont(new Font("Arial", Font.BOLD, 18));
                
        // ask before deleting the employee
        delete_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to delete this employee?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            new DeleteEmployee(ef);
                        }
                        
                    int EmployeeID = Integer.parseInt(IDtoDelete.getText().trim()); // Read the emp ID
                    
                    // stop processing
                    if(confirm == JOptionPane.NO_OPTION)
                    	return;
                    	
                    boolean isDeleted = DatabaseController.deleteEmployee(EmployeeID);
                    
                    if (isDeleted) {
                    	ef.loadDataFromDatabase();
                        JOptionPane.showMessageDialog(DeleteEmployee.this, "Employee deleted successfully!");
                        dispose(); // Close the window after successful deletion
                    } else {
                        JOptionPane.showMessageDialog(DeleteEmployee.this, "Failed to delete Employee. Please check the ID and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DeleteEmployee.this, "Please enter a valid numeric Employee ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DeleteEmployee.this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dispose();
        delete_btn.setBounds(171, 134, 96, 57);
        contentPane.add(delete_btn);
    }
}