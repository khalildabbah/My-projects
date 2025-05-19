package boundary;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import control.DatabaseController;
import entity.Employee;
import utils.AccessDatabaseConnection;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageEmployees extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable salesStaffTable, marketingStaffTable;
    private EmployeeTableModel salesTableModel, marketingTableModel;

    public ManageEmployees() {
    	
		setBackground(SystemColor.activeCaption);

        // Create models for both tables
		ArrayList<Employee> salesEmployees = new ArrayList<>();
        ArrayList<Employee> marketingEmployees = new ArrayList<>();
        
		for(Employee emp : DatabaseController.getAllEmployees().values()) {
			if(emp.getDepartment().equalsIgnoreCase("Sales")) {
				salesEmployees.add(emp);
				continue;
			}else {
				if(emp.getDepartment().equalsIgnoreCase("Marketing"))
				marketingEmployees.add(emp);
			}
		}

        salesTableModel = new EmployeeTableModel(salesEmployees);
        marketingTableModel = new EmployeeTableModel(marketingEmployees);
        // Create tables
        salesStaffTable = new JTable(salesTableModel);
        marketingStaffTable = new JTable(marketingTableModel);

        // Create scroll panes
        JScrollPane salesScrollPane = new JScrollPane(salesStaffTable);
        salesScrollPane.setBounds(10, 40, 760, 187);
        JScrollPane marketingScrollPane = new JScrollPane(marketingStaffTable);
        marketingScrollPane.setBounds(10, 284, 760, 180);
        setLayout(null);

        // Create and position labels
        JLabel salesLabel = new JLabel("Sales Staff", SwingConstants.CENTER);
        salesLabel.setBounds(318, 5, 150, 31);
        salesLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(salesLabel);
        add(salesScrollPane);

        JLabel marketingLabel = new JLabel("Marketing Staff", SwingConstants.CENTER);
        marketingLabel.setBounds(318, 240, 150, 39);
        marketingLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(marketingLabel);
        add(marketingScrollPane);
        
        JButton btnAddEmployee = new CustomButton("Add Employee",150,50,40);
        btnAddEmployee.setFont(new Font("Arial", Font.BOLD, 18));
        btnAddEmployee.addActionListener(e -> openAddPanel());
        btnAddEmployee.setBounds(607, 480, 163, 39);
        add(btnAddEmployee);
        
        JButton btnDelete = new CustomButton("Delete Employee",150,50,40);
        btnDelete.setFont(new Font("Arial", Font.BOLD, 18));
        btnDelete.addActionListener(e -> openDeletePanel());
        btnDelete.setBounds(425, 480, 172, 39);
        add(btnDelete);  
        
        // add double click listener
        salesStaffTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Double click detected
                    int row = salesStaffTable.getSelectedRow();
                    if (row != -1) {
                        showEmployeeDetails(salesStaffTable, salesTableModel, row);
                    }
                }
            }
        });

        // add double click listener
        marketingStaffTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Double click detected
                    int row = marketingStaffTable.getSelectedRow();
                    if (row != -1) {
                        showEmployeeDetails(marketingStaffTable, marketingTableModel, row);
                    }
                }
            }
        });
        
        salesStaffTable.setToolTipText("Double click edit employee details.");
        marketingStaffTable.setToolTipText("Double click edit employee details.");

        loadDataFromDatabase();

        setVisible(true);
    }

    public void loadDataFromDatabase() {

    	try {
    	HashMap<Integer,Employee> employees = DatabaseController.getAllEmployees();
    	
        // Clear the existing table data before adding new rows
        salesTableModel.clearTable();
        marketingTableModel.clearTable();
        
        for(Employee emp : employees.values()) {
	        if ("Sales".equalsIgnoreCase(emp.getDepartment())) {
	        	salesTableModel.Employee_tblAdd(emp);
	        	} else if ("Marketing".equalsIgnoreCase(emp.getDepartment())) {
	        		marketingTableModel.Employee_tblAdd(emp);
	        		}
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data from database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // open delete employee window
    public void openDeletePanel() {
        JFrame dl = new DeleteEmployee(this);
        dl.setVisible(true);
        
    }

    // open add employee panel
    private void openAddPanel() {
    	new AddEmployee(this);
    }
    

    // Inner class for EmployeeTableModel
    public class EmployeeTableModel extends AbstractTableModel {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String[] columnNames = {"ID", "Name", "Phone Number", "Office Address", "Email", "Employment Date", "Department"};
        private ArrayList<Employee> employees;

        public EmployeeTableModel(ArrayList<Employee> employees) {
            this.employees = employees;
        }
        
        public void Employee_tblAdd(Employee emp) {
        	employees.add(emp);
        }

        @Override
        public int getRowCount() {
            return employees.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
        
        public void clearTable() {
            employees.clear();  // Clear the list
            fireTableDataChanged(); // Notify the table that data has changed
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Employee employee = employees.get(rowIndex);
            switch (columnIndex) {
                case 0 : return employee.getId();
                case 1 : return employee.getName();
                case 2 : return employee.getPhoneNumber();
                case 3 : return employee.getOfficeAddress();
                case 4 : return employee.getEmail();
                case 5 : return employee.getEmployementStartDate();
                case 6 : return employee.getDepartment();           
            };
            return null;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }




	public JTable getSalesStaffTable() {
		return salesStaffTable;
	}

	public void setSalesStaffTable(JTable salesStaffTable) {
		this.salesStaffTable = salesStaffTable;
	}

	public JTable getMarketingStaffTable() {
		return marketingStaffTable;
	}

	public void setMarketingStaffTable(JTable marketingStaffTable) {
		this.marketingStaffTable = marketingStaffTable;
	}

	public EmployeeTableModel getSalesTableModel() {
		return salesTableModel;
	}

	public void setSalesTableModel(EmployeeTableModel salesTableModel) {
		this.salesTableModel = salesTableModel;
	}

	public EmployeeTableModel getMarketingTableModel() {
		return marketingTableModel;
	}

	public void setMarketingTableModel(EmployeeTableModel marketingTableModel) {
		this.marketingTableModel = marketingTableModel;
	}
	
    private void showEmployeeDetails(JTable table, EmployeeTableModel model, int rowIndex) {
        Employee selectedEmployee = model.employees.get(rowIndex);
        new EditEmployee(this, selectedEmployee);
        
    }
    
}
