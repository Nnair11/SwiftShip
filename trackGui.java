package Guis;

import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class trackGui extends Form {

    private JTextField trackField;
    private JPanel detailsPanel;

    public trackGui() {
        super("Package Details");
        addGuiComponent();
    }

    private void addGuiComponent() {
        JLabel mainLabel = new JLabel("SwiftShip");
        mainLabel.setBounds(110, 25, 520, 100);
        mainLabel.setForeground(CommonConstants.Text_Color);
        mainLabel.setFont(new Font("Dialog", Font.BOLD, 50));
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(mainLabel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(75, 100, 100, 25);
        backButton.setForeground(CommonConstants.Text_Color);
        backButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                trackGui.this.dispose();

                new LoginGui().setVisible(true);
            }
        });
        add(backButton);


        JLabel trackNumber = new JLabel("Tracking Number");
        trackNumber.setBounds(75, 200, 400, 50);
        trackNumber.setForeground(CommonConstants.Text_Color);
        trackNumber.setFont(new Font("Dialog", Font.PLAIN, 35));
        add(trackNumber);

        trackField = new JTextField();
        trackField.setBounds(75, 250, 600, 60);
        trackField.setBackground(CommonConstants.Secondary_Color);
        trackField.setForeground(CommonConstants.Text_Color);
        trackField.setFont(new Font("Dialog", Font.PLAIN, 40));
        add(trackField);

        detailsPanel = new JPanel();
        detailsPanel.setBounds(75, 380, 600, 300);
        detailsPanel.setBackground(CommonConstants.Secondary_Color);
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        add(detailsPanel);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Dialog", Font.BOLD, 30));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBackground(CommonConstants.Text_Color);
        searchButton.setForeground(Color.WHITE);
        searchButton.setBounds(525, 320, 150, 50);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trackingId = trackField.getText().trim().toUpperCase();
                System.out.println("Tracking ID entered: " + trackingId);
                fetchAndDisplayShipmentDetails(trackingId);
            }
        });
        add(searchButton);
    }

    private void fetchAndDisplayShipmentDetails(String trackingId) {
        detailsPanel.removeAll();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM " + CommonConstants.db_tblname1 + " WHERE TRIM(PACKAGE_ID) = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, trackingId);
            System.out.println("Executing query with: " + trackingId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                addDetailLabel("Tracking ID: " + resultSet.getString("PACKAGE_ID"));
                addDetailLabel("Status: " + resultSet.getString("STATUS"));
                addDetailLabel("Estimated Delivery: " + resultSet.getString("ESTIMATED_DELIVERY"));
                addDetailLabel("Sender Name: " + resultSet.getString("SENDER_NAME"));
                addDetailLabel("Shipping Address: " + resultSet.getString("SHIPPING_ADDRESS"));
                addDetailLabel("Receiver Name: " + resultSet.getString("RECEIVER_NAME"));
                addDetailLabel("Receiver Address: " + resultSet.getString("RECEIVER_ADDRESS"));
                addDetailLabel("Type: " + resultSet.getString("TYPE"));
                addDetailLabel("Cost: " + resultSet.getString("COST"));
            } else {
                JLabel errorLabel = new JLabel("Tracking ID not found: " + trackingId);
                errorLabel.setForeground(Color.RED);
                errorLabel.setFont(new Font("Dialog", Font.ITALIC, 20));
                detailsPanel.add(errorLabel);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JLabel errorLabel = new JLabel("Error fetching details.");
            errorLabel.setForeground(Color.RED);
            errorLabel.setFont(new Font("Dialog", Font.ITALIC, 20));
            detailsPanel.add(errorLabel);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        detailsPanel.revalidate();
        detailsPanel.repaint();
    }

    private void addDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(CommonConstants.Text_Color);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
        detailsPanel.add(label);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CommonConstants.db_url1, CommonConstants.db_username, CommonConstants.db_password);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new trackGui().setVisible(true);
        });
    }
}