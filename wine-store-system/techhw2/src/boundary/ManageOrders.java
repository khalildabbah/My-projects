package boundary;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import control.DatabaseController;
import entity.Employee;
import entity.Order;
import entity.OrderItem;
import utils.AccessDatabaseConnection;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageOrders extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable orderItemsTable, ordersTable;
    private OrderItemsTableModel orderItemsTableModel;
    private OrderTableModel ordersTableModel;
    private ArrayList<OrderItem> orderItems = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    
    public ManageOrders() {
    	
		setBackground(SystemColor.activeCaption);

        // Create models for both tables
        
		for(Order o : DatabaseController.getAllOrders().values()) { 
				orders.add(o);
		}

		orderItemsTableModel = new OrderItemsTableModel(orderItems);
		ordersTableModel = new OrderTableModel(orders);
        // Create tables
		orderItemsTable = new JTable(orderItemsTableModel);
		ordersTable = new JTable(ordersTableModel);

        // Create scroll panes
        JScrollPane salesScrollPane = new JScrollPane(ordersTable);
        salesScrollPane.setBounds(10, 40, 760, 187);
        JScrollPane marketingScrollPane = new JScrollPane(orderItemsTable);
        marketingScrollPane.setBounds(10, 284, 760, 180);
        setLayout(null);

        // Create and position labels
        JLabel salesLabel = new JLabel("Orders", SwingConstants.CENTER);
        salesLabel.setBounds(318, 5, 150, 31);
        salesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(salesLabel);
        add(salesScrollPane);

        JLabel marketingLabel = new JLabel("Order Items", SwingConstants.CENTER);
        marketingLabel.setBounds(318, 240, 150, 39);
        marketingLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(marketingLabel);
        add(marketingScrollPane);
        
        JButton btnAddOrder = new CustomButton("Add Order",150,50,40);
        btnAddOrder.setFont(new Font("Arial", Font.BOLD, 18));
        btnAddOrder.addActionListener(e -> openAddPanel());
        btnAddOrder.setBounds(627, 480, 143, 39);
        add(btnAddOrder);
        
        JButton btnDelete = new CustomButton("Delete Order",150,50,40);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
        btnDelete.addActionListener(e -> openDeletePanel());
        btnDelete.setBounds(467, 480, 150, 39);
        add(btnDelete);  
        
        ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ordersTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = ordersTable.getSelectedRow();
                if (selectedRow != -1) {  // Ensure valid selection
                	loadOrderItems(selectedRow);
                }
            }
        });        
        
        // add double click listener
        ordersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Double click detected
                    int row = ordersTable.getSelectedRow();
                    if (row != -1) {
                        showOrderDetails(row);
                    }
                }
            }
        });

        // add double click listener
        orderItemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Double click detected
                    int row = orderItemsTable.getSelectedRow();
                    if (row != -1) {
                    	showItemDetails(row);
                    }
                }
            }
        });
        
        ordersTable.setToolTipText("Double click - edit order details.");
        orderItemsTable.setToolTipText("Double click - edit item details.");
        
        CustomButton cstmbtnAddItem = new CustomButton("Add Item", 150, 50, 40);
        cstmbtnAddItem.setFont(new Font("Arial", Font.BOLD, 18));
        cstmbtnAddItem.setBounds(307, 480, 150, 39);
        cstmbtnAddItem.addActionListener(e -> openAddItem());
        add(cstmbtnAddItem);
        
        CustomButton cstmbtnDeleteItem = new CustomButton("Delete Item", 150, 50, 40);
        cstmbtnDeleteItem.setFont(new Font("Arial", Font.BOLD, 18));
        cstmbtnDeleteItem.setBounds(147, 480, 150, 39);
        cstmbtnDeleteItem.addActionListener(e -> openDeleteItemPanel());
        add(cstmbtnDeleteItem);

        loadDataFromDatabase();

        setVisible(true);
    }

    public void loadDataFromDatabase() {

    	try {
    	HashMap<Integer,Order> orders = DatabaseController.getAllOrders();
        // Clear the existing table data before adding new rows
    	ordersTableModel.clearTable();
        
        for(Order o : orders.values()) {
        	if(o!=null)
	        	ordersTableModel.Order_tblAdd(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // open delete order window
    public void openDeletePanel() {
        JFrame dl = new DeleteOrder(this);
        dl.setVisible(true);
        
    }

    // open add employee panel
    private void openAddPanel() {
    	new AddOrder(this);
    }
    
    private void openAddItem() {
		int rowIndex = ordersTable.getSelectedRow();
    	if(rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select Order first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
    	}
        JFrame dl = new AddOrderItems(this);
        dl.setVisible(true);
    }
    
    public void openDeleteItemPanel() {
        JFrame dl = new DeleteOrderItem(this);
        dl.setVisible(true);
        
    }

    // Inner class for OrderTableModel
    public class OrderTableModel extends AbstractTableModel {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"Order Number", "Order Date", "Order Type", "Status", "Shipment Date", "Employee", "Customer"};
        public ArrayList<Order> orders;

        public OrderTableModel(ArrayList<Order> orders) {
            this.orders = orders;
        }
        
        public void Order_tblAdd(Order o) {
        	orders.add(o);
        }

        @Override
        public int getRowCount() {
            return orders.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
        
        public void clearTable() {
        	orders.clear();  // Clear the list
            fireTableDataChanged(); // Notify the table that data has changed
        }
        
        

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Order order = orders.get(rowIndex);
            switch (columnIndex) {
                case 0 : return order.getOrderNumber();
                case 1 : return order.getOrderDate();
                case 2 : return order.getOrderType();
                case 3 : return order.getCurrentStatus();
                case 4 : return order.getShipmentDate();
                case 5 : return order.getEmployeeId();
                case 6 : return order.getCustomerId();


      
            };
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    // Inner class for OrderItemTableModel
    public class OrderItemsTableModel extends AbstractTableModel {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"Item Number", "Quantity", "Wine Cat Number" , "Wine Name", "Description", "Production Year","Price Per Bottle","Sweetness-Level","Wine Type"};
	
        private ArrayList<OrderItem> items;

        public OrderItemsTableModel(ArrayList<OrderItem> items) {
            this.items = items;
        }
        
        public void OrderItem_tblAdd(OrderItem i) {
        	items.add(i);
        }

        @Override
        public int getRowCount() {
            return items.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
        
        public void clearTable() {
        	items.clear();  // Clear the list
            fireTableDataChanged(); // Notify the table that data has changed
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            OrderItem item = items.get(rowIndex);
            switch (columnIndex) {
            	case 0 : return item.getOrderItemNumber();
                case 1 : return item.getQuantity();
                case 2 : return item.getWine().getCatalogNumber();
                case 3 : return item.getWine().getName();
                case 4 : return item.getWine().getDescription();
                case 5 : return item.getWine().getProductionYear();
                case 6 : return item.getWine().getPricePerBottle();
                case 7 : return item.getWine().getSweetnessLevel();
                case 8 : return item.getWine().getType();
            };
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }



    private void showOrderDetails(int rowIndex) {
        Order selectedOrder = ordersTableModel.orders.get(rowIndex);
        new EditOrder(this, selectedOrder);
    }
    
    private void showItemDetails(int rowIndex) {
        OrderItem item = orderItemsTableModel.items.get(rowIndex);
        new EditOrderItem(this, item);
    }
    
    public void loadOrderItems(int rowIndex) {
    	orderItems.clear();
        orderItemsTableModel.clearTable();
            
        Order selectedOrder = ordersTableModel.orders.get(rowIndex);

        for(OrderItem i : selectedOrder.getOrderItems())
        	orderItemsTableModel.OrderItem_tblAdd(i);
        }

    public void updOrderItems(Order order) {
    	orderItems.clear();
        orderItemsTableModel.clearTable();

        for(OrderItem i : order.getOrderItems())
        	orderItemsTableModel.OrderItem_tblAdd(i);
        }
    
	public OrderTableModel getOrdersTableModel() {
		return ordersTableModel;
	}

	public void setOrdersTableModel(OrderTableModel ordersTableModel) {
		this.ordersTableModel = ordersTableModel;
	}

	public JTable getOrdersTable() {
		return ordersTable;
	}

	public void setOrdersTable(JTable ordersTable) {
		this.ordersTable = ordersTable;
	}
}
