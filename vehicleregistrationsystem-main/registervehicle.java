import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.toedter.calendar.JDateChooser;

public class registervehicle extends JFrame {

    private JTextField nameTextField;
    private JTextField mobileTextField;
    private JTextField addressTextField;
    private JTextField parentageTextField;
    private JTextField aadharNumberTextField;
    private JTextField vehicleNameTextField;
    private JTextField vehicleMakeTextField;
    private JTextField vehicleModelTextField;
    private JTextField insuranceNameTextField;
    private JTextField financeDetailsTextField;
    private JTextField engineNumberTextField;
    private JTextField chassisNumberTextField;
    private JTextField vehicleCapacityTextField;
    private JTextField engineCapacityTextField;

    private Connection getConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/rtovehicle";
        String username = "root";
        String password = "1234";

        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public registervehicle() {
        setTitle("Register Your Vehicle");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        mainPanel.add(formPanel, BorderLayout.CENTER);

        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        // Define and add all text fields to the form
        nameTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Name:", nameTextField);
        mobileTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Mobile:", mobileTextField);

        addressTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Address:", addressTextField);

        parentageTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Parentage:", parentageTextField);

        aadharNumberTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Aadhar Number:", aadharNumberTextField);

        vehicleNameTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Vehicle Name:", vehicleNameTextField);

        vehicleMakeTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Vehicle Make:", vehicleMakeTextField);

        vehicleModelTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Vehicle Model:", vehicleModelTextField);

        insuranceNameTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Insurance Name:", insuranceNameTextField);

        // Replace the "Registration Date" JTextField with a JDateChooser date picker
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setFont(fieldFont);
        addFormField(formPanel, "Registration Date:", dateChooser);

        financeDetailsTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Finance Details:", financeDetailsTextField);

        engineNumberTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Engine Number:", engineNumberTextField);

        chassisNumberTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Chassis Number:", chassisNumberTextField);

        vehicleCapacityTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Vehicle Capacity:", vehicleCapacityTextField);

        engineCapacityTextField = createTextField(20, fieldFont);
        addFormField(formPanel, "Engine Capacity:", engineCapacityTextField);

        // ... (previous code)

// ... (previous code)

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            try {
                Connection connection = getConnection();
                if (connection != null) {
                    String tableName = "vehicle";
                    String sql = "INSERT INTO " + tableName + " (name, mobile, address, parentage, aadhar_number, vehicle_name, vehicle_make, vehicle_model, insurance_name, finance_details, engine_number, chassis_number, vehicle_capacity, engine_capacity, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                    // Set the values from your text fields
                    statement.setString(1, nameTextField.getText());
                    statement.setString(2, mobileTextField.getText());
                    statement.setString(3, addressTextField.getText());
                    statement.setString(4, parentageTextField.getText());
                    statement.setString(5, aadharNumberTextField.getText());
                    statement.setString(6, vehicleNameTextField.getText());
                    statement.setString(7, vehicleMakeTextField.getText());
                    statement.setString(8, vehicleModelTextField.getText());
                    statement.setString(9, insuranceNameTextField.getText());
                    statement.setString(10, financeDetailsTextField.getText());
                    statement.setString(11, engineNumberTextField.getText());
                    statement.setString(12, chassisNumberTextField.getText());
                    statement.setString(13, vehicleCapacityTextField.getText());
                    statement.setString(14, engineCapacityTextField.getText());

                    // Get the selected date from the JDateChooser
                    java.util.Date selectedDate = dateChooser.getDate();
                    if (selectedDate != null) {
                        // Convert the selected date to a java.sql.Date
                        java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
                        statement.setDate(15, sqlDate); // Set the registration date
                    }

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Registration successful");
                    } else {
                        JOptionPane.showMessageDialog(this, "Registration failed");
                    }

                    statement.close();
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
// ... (rest of the code)

// ... (rest of the code)


        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = GridBagConstraints.RELATIVE;
        buttonConstraints.insets = new Insets(10, 0, 0, 0);

        formPanel.add(registerButton, buttonConstraints);

        // Add padding around the form
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add the main panel to the frame
        add(mainPanel);

        // Center the frame
        setLocationRelativeTo(null);
        registerButton.setEnabled(true); // Make sure it's enabled

    }

    private JTextField createTextField(int columns, Font font) {
        JTextField textField = new JTextField(columns);
        textField.setFont(font);
        return textField;
    }

    private void addFormField(JPanel formPanel, String labelText, JComponent component) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        JLabel label = new JLabel(labelText);
        formPanel.add(label, constraints);

        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(component, constraints);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            registervehicle frame = new registervehicle();
            frame.setVisible(true);
        });
    }
}
