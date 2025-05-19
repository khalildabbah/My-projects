package boundary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import control.DatabaseController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteOrder extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField orderNumToDelete;
    private ManageOrders ef; // reference to manage employee page


    public DeleteOrder(ManageOrders main) {
    	
    	ef = main; // set reference

        setTitle("Delete Order");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Insert Order Number to delete:");
        lblNewLabel.setBackground(Color.RED);
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(80, 21, 277, 67);
        contentPane.add(lblNewLabel);

        orderNumToDelete = new JTextField();
        orderNumToDelete.setFont(new Font("Dialog", Font.PLAIN, 16));
        orderNumToDelete.setBounds(171, 89, 96, 34);
        PlainDocument doc = (PlainDocument)orderNumToDelete.getDocument();
        doc.setDocumentFilter(new PositiveNumbersFilter());
        contentPane.add(orderNumToDelete);
        orderNumToDelete.setColumns(10);

        JButton delete_btn = new CustomButton("Delete",150,50,40);
        delete_btn.setFont(new Font("Arial", Font.BOLD, 18));
                
        // ask before deleting the employee
        delete_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to delete this order?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            new DeleteOrder(ef);
                        }
                        
                    int orderNum = Integer.parseInt(orderNumToDelete.getText().trim()); // Read the order ID
                    
                    // stop processing
                    if(confirm == JOptionPane.NO_OPTION)
                    	return;
                    	
                    boolean isDeleted = DatabaseController.deleteOrder(orderNum); // make this METHOD
                    
                    if (isDeleted) {
                    	ef.loadDataFromDatabase();
                        JOptionPane.showMessageDialog(DeleteOrder.this, "Order deleted successfully!");
                        dispose(); // Close the window after successful deletion
                    } else {
                        JOptionPane.showMessageDialog(DeleteOrder.this, "Failed to delete Order. Please check the Order Number and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DeleteOrder.this, "Please enter a valid numeric Order Number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DeleteOrder.this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dispose();
        delete_btn.setBounds(171, 134, 96, 57);
        contentPane.add(delete_btn);
    }
}