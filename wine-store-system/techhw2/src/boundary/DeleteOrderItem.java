package boundary;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

import boundary.CustomButton;
import boundary.DeleteOrder;
import boundary.ManageOrders;
import boundary.PositiveNumbersFilter;
import control.DatabaseController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteOrderItem extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ManageOrders ef; // reference to manage orders page
    private JTextField textField_1;


    public DeleteOrderItem(ManageOrders main) {
    	
    	ef = main; // set reference

        setTitle("Delete Order Item");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Dialog", Font.PLAIN, 16));
        textField_1.setColumns(10);
        textField_1.setBounds(171, 86, 96, 33);
        contentPane.add(textField_1);
        
        JButton delete_btn = new CustomButton("Delete",150,50,40);
        delete_btn.setFont(new Font("Arial", Font.BOLD, 18));
        delete_btn.setBounds(171, 134, 96, 57);
        contentPane.add(delete_btn);
        
        JLabel lblInsertItemNumber = new JLabel("Insert Item Number to delete:");
        lblInsertItemNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblInsertItemNumber.setFont(new Font("Dialog", Font.BOLD, 16));
        lblInsertItemNumber.setBackground(Color.RED);
        lblInsertItemNumber.setBounds(81, 32, 277, 57);
        contentPane.add(lblInsertItemNumber);
        // ask before deleting the employee
        delete_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to delete this item?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION
                        );

                        if (confirm == JOptionPane.YES_OPTION) {
                            new DeleteOrder(ef);
                        }
                        
                    int itemNum = Integer.parseInt(textField_1.getText().trim()); // Read the item num
            		int rowIndex = ef.getOrdersTable().getSelectedRow();

                    // stop processing
                    if(confirm == JOptionPane.NO_OPTION)
                    	return;
                    	
                    if(DatabaseController.getAllItems().containsKey(itemNum)) {
                        JOptionPane.showMessageDialog(DeleteOrderItem.this, "Failed to delete Item. Number does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    boolean isDeleted = DatabaseController.deleteOrderItem(itemNum); 
                    
                    if (isDeleted) {
                        JOptionPane.showMessageDialog(DeleteOrderItem.this, "Item deleted successfully!");
                        ef.loadDataFromDatabase(); 
                        ef.loadOrderItems(rowIndex);
                        dispose(); // Close the window after successful deletion
                    } else {
                        JOptionPane.showMessageDialog(DeleteOrderItem.this, "Failed to delete Item. Please check the numbers and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DeleteOrderItem.this, "Please enter a valid numeric numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(DeleteOrderItem.this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        dispose();

    }
}