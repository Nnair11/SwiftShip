package Guis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Vector;
import java.util.Random;
import java.util.Date;

import constants.CommonConstants;

public class packGui extends Form {
    private JTextField nameData;
    private JTextField addressData;
    private JTextField dateField;
    private JTable table1;
    private JButton ADDRECORDButton;
    private JButton UPDATERECORDButton;
    private JButton DELETEButton;
    private JPanel mainPanel;
    private JComboBox<String> status;
    private JLabel total;
    private JComboBox<String> type;
    private JTextField cost;
    private JTextField receiverNameData;
    private JTextField receiverAddressData;
    private static final String PACKAGE_ID_PREFIX = "PKG-";
    private static final int PACKAGE_ID_LENGTH = 10;
    private int clientIDCounter = 1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public packGui() {
        super("SwiftShip");

        // Initialize components
        nameData = new JTextField(20);
        nameData.setBackground(Color.WHITE);
        nameData.setForeground(CommonConstants.Text_Color);
        nameData.setFont(new Font("Dialog", Font.PLAIN, 16));

        addressData = new JTextField(20);
        addressData.setBackground(Color.WHITE);
        addressData.setForeground(CommonConstants.Text_Color);
        addressData.setFont(new Font("Dialog", Font.PLAIN, 16));

        dateField = new JTextField(10);
        dateField.setText("YYYY-MM-DD");
        dateField.setBackground(Color.WHITE);
        dateField.setForeground(CommonConstants.Text_Color);
        dateField.setFont(new Font("Dialog", Font.PLAIN, 16));

        cost = new JTextField(10);
        cost.setBackground(Color.WHITE);
        cost.setForeground(CommonConstants.Text_Color);
        cost.setFont(new Font("Dialog", Font.PLAIN, 16));

        type = new JComboBox<>();
        type.addItem("Standard");
        type.addItem("Express");
        type.addItem("Priority");
        type.setBackground(Color.WHITE);
        type.setForeground(CommonConstants.Text_Color);
        type.setFont(new Font("Dialog", Font.PLAIN, 16));

        status = new JComboBox<>();
        status.addItem("Pending");
        status.addItem("Shipped");
        status.addItem("Delivered");
        status.setBackground(Color.WHITE);
        status.setForeground(CommonConstants.Text_Color);
        status.setFont(new Font("Dialog", Font.PLAIN, 16));

        receiverNameData = new JTextField(20);
        receiverNameData.setBackground(Color.WHITE);
        receiverNameData.setForeground(CommonConstants.Text_Color);
        receiverNameData.setFont(new Font("Dialog", Font.PLAIN, 16));

        receiverAddressData = new JTextField(20);
        receiverAddressData.setBackground(Color.WHITE);
        receiverAddressData.setForeground(CommonConstants.Text_Color);
        receiverAddressData.setFont(new Font("Dialog", Font.PLAIN, 16));

        ADDRECORDButton = new JButton("Add Record");
        ADDRECORDButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ADDRECORDButton.setBackground(CommonConstants.Text_Color);
        ADDRECORDButton.setForeground(Color.WHITE);
        ADDRECORDButton.setFont(new Font("Dialog", Font.BOLD, 16));

        UPDATERECORDButton = new JButton("Update Record");
        UPDATERECORDButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        UPDATERECORDButton.setBackground(CommonConstants.Text_Color);
        UPDATERECORDButton.setForeground(Color.WHITE);
        UPDATERECORDButton.setFont(new Font("Dialog", Font.BOLD, 16));

        DELETEButton = new JButton("Delete Record");
        DELETEButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        DELETEButton.setBackground(CommonConstants.Text_Color);
        DELETEButton.setForeground(Color.WHITE);
        DELETEButton.setFont(new Font("Dialog", Font.BOLD, 16));

        total = new JLabel("Total: 0");
        total.setForeground(CommonConstants.Text_Color);
        total.setFont(new Font("Dialog", Font.BOLD, 20));

        table1 = new JTable();
        table1.setBackground(Color.WHITE);
        table1.setForeground(CommonConstants.Text_Color);
        table1.setFont(new Font("Dialog", Font.PLAIN, 16));
        table1.getTableHeader().setBackground(Color.WHITE);
        table1.getTableHeader().setForeground(CommonConstants.Text_Color);
        table1.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to mainPanel using GridBagLayout
        addComponent(new JLabel("Sender Name:"), 0, 0, gbc);
        addComponent(nameData, 1, 0, gbc);

        addComponent(new JLabel("Receiver Name:"), 0, 1, gbc);
        addComponent(receiverNameData, 1, 1, gbc);

        addComponent(new JLabel("Receiver Address:"), 0, 2, gbc);
        addComponent(receiverAddressData, 1, 2, gbc);

        addComponent(new JLabel("Shipping Address:"), 0, 3, gbc);
        addComponent(addressData, 1, 3, gbc);

        addComponent(new JLabel("Delivery Date (YYYY-MM-DD):"), 0, 4, gbc);
        addComponent(dateField, 1, 4, gbc);

        addComponent(new JLabel("Type:"), 0, 5, gbc);
        addComponent(type, 1, 5, gbc);

        addComponent(new JLabel("Cost:"), 0, 6, gbc);
        addComponent(cost, 1, 6, gbc);

        addComponent(new JLabel("Status:"), 0, 7, gbc);
        addComponent(status, 1, 7, gbc);

        addComponent(ADDRECORDButton, 0, 8, gbc);
        addComponent(UPDATERECORDButton, 1, 8, gbc);
        addComponent(DELETEButton, 0, 9, gbc);

        addComponent(new JLabel("Total Cost:"), 0, 10, gbc);
        addComponent(total, 1, 10, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(new JScrollPane(table1), gbc);

        // Set the content pane of the Form (JFrame)
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setSize(750, 750);
        initializeClientId(); // Initialize the client ID
        refreshTable();
        // Add action listeners to buttons
        ADDRECORDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        UPDATERECORDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });

        DELETEButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });

        // Add mouse listener to table
        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    // Adjusted indices
                    nameData.setText(table1.getValueAt(selectedRow, 0).toString());
                    receiverNameData.setText(table1.getValueAt(selectedRow, 2).toString());
                    receiverAddressData.setText(table1.getValueAt(selectedRow, 3).toString());
                    addressData.setText(table1.getValueAt(selectedRow, 1).toString());
                    dateField.setText(table1.getValueAt(selectedRow, 4).toString()); // Get it as String

                    type.setSelectedItem(table1.getValueAt(selectedRow, 5).toString());
                    cost.setText(table1.getValueAt(selectedRow, 6).toString());
                    status.setSelectedItem(table1.getValueAt(selectedRow, 7).toString());
                }
            }
        });

        setVisible(true);
    }

    // Helper method to add components with GridBagConstraints
    private void addComponent(Component comp, int x, int y, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(comp, gbc);
    }

    // Method to generate a random Package ID
    private String generatePackageId() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(PACKAGE_ID_PREFIX);
        for (int i = 0; i < PACKAGE_ID_LENGTH - PACKAGE_ID_PREFIX.length(); i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // Method to add a new record to the database
    private void addRecord() {
        if (nameData.getText().trim().equals("") || receiverNameData.getText().trim().equals("") || receiverAddressData.getText().trim().equals("") ||
                addressData.getText().trim().equals("") || dateField.getText().trim().equals("YYYY-MM-DD") ||
                cost.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            // Validate the date *before* using it
            try {
                sdf.parse(dateField.getText());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please useторе-MM-DD.");
                return; // Stop the addRecord operation
            }

            int costValue = Integer.parseInt(cost.getText());
            String packageId = generatePackageId();

            String sql = "INSERT INTO courier (SENDER_NAME, SHIPPING_ADDRESS, RECEIVER_NAME, RECEIVER_ADDRESS, ESTIMATED_DELIVERY, TYPE, COST, STATUS, CLIENT_ID, PACKAGE_ID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, nameData.getText());
            statement.setString(2, addressData.getText());
            statement.setString(3, receiverNameData.getText());
            statement.setString(4, receiverAddressData.getText());
            statement.setString(5, dateField.getText()); // Store as String
            statement.setString(6, type.getSelectedItem().toString());
            statement.setDouble(7, costValue);
            statement.setString(8, status.getSelectedItem().toString());
            statement.setInt(9, clientIDCounter);
            statement.setString(10, packageId);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record added successfully! Client ID: " + clientIDCounter + " Package ID: " + packageId);
            clearFields();
            refreshTable();
            incrementClientIDCounter(); // Increment
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to update an existing record in the database
    private void updateRecord() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a record to update!");
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "UPDATE courier SET SENDER_NAME = ?, SHIPPING_ADDRESS = ?, RECEIVER_NAME = ?, RECEIVER_ADDRESS = ?, ESTIMATED_DELIVERY = ?, TYPE = ?, COST = ?, STATUS = ? WHERE PACKAGE_ID = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            String packageId = table1.getValueAt(selectedRow, 9).toString();

            //Date Validation
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            try {
                sdf.parse(dateField.getText());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please useторе-MM-DD.");
                return;
            }

            statement.setString(1, nameData.getText());
            statement.setString(2, addressData.getText());
            statement.setString(3, receiverNameData.getText());
            statement.setString(4, receiverAddressData.getText());
            statement.setString(5, dateField.getText()); // Store as String
            statement.setString(6, type.getSelectedItem().toString());
            statement.setDouble(7, Double.parseDouble(cost.getText()));
            statement.setString(8, status.getSelectedItem().toString());
            statement.setString(9, packageId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "No records were updated!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to delete a record from the database
    private void deleteRecord() {
        int selectedRow = table1.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a record to delete!");
            return;
        }
        String packageId = table1.getValueAt(selectedRow, 9).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the selected record?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            String sql = "DELETE FROM courier WHERE PACKAGE_ID = ?";
            connection = getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, packageId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully!");
                refreshTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Record not found or could not be deleted.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to refresh the table data from the database
    private void refreshTable() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT SENDER_NAME, SHIPPING_ADDRESS, RECEIVER_NAME, RECEIVER_ADDRESS, ESTIMATED_DELIVERY, TYPE, COST, STATUS, CLIENT_ID, PACKAGE_ID FROM courier";
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            table1.setModel(buildTableModel(rs));
            updateTotal();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to update the total cost label
    private void updateTotal() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT SUM(COST) FROM courier";
            connection = getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                total.setText(" " + rs.getDouble(1));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error calculating total: " + ex.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to clear input fields
    private void clearFields() {
        nameData.setText("");
        receiverNameData.setText("");
        receiverAddressData.setText("");
        addressData.setText("");
        dateField.setText("YYYY-MM-DD");
        cost.setText("");
        type.setSelectedIndex(0);
        status.setSelectedIndex(0);
    }

    // Method to establish database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/courier",
                "root",
                "password");
    }

    // Method to build table model from ResultSet
    private DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                Object value = rs.getObject(i);
                row.add(value.toString()); // Convert all values to String for display
            }
            data.add(row);
        }
        return new DefaultTableModel(data, columnNames);
    }

    private void initializeClientId() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            String sql = "SELECT MAX(CLIENT_ID) FROM courier";
            resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                clientIDCounter = resultSet.getInt(1);
                if (clientIDCounter == 0) {
                    clientIDCounter = 1; // start from 1 if the table is empty
                }
                else{
                    clientIDCounter++;
                }
            }
            else {
                clientIDCounter = 1; // Default value if no records exist
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error initializing Client ID: " + e.getMessage());
            e.printStackTrace();
            clientIDCounter = 1; //start from 1 to avoid errors
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void incrementClientIDCounter() {
        clientIDCounter++;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(packGui::new);
    }
}

