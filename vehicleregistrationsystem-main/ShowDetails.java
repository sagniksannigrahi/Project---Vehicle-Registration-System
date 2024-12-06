import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class ShowDetails extends JFrame {

    private JTextField mobileNumberTextField;
    private JTable dataTable;

    public ShowDetails() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Vehicle Details");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Form panel for search
        JPanel formPanel = new JPanel(new FlowLayout());
        JLabel mobileLabel = new JLabel("Mobile Number:");
        mobileNumberTextField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDatabase();
            }
        });

        formPanel.add(mobileLabel);
        formPanel.add(mobileNumberTextField);
        formPanel.add(searchButton);

        // Table for displaying results
        dataTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(dataTable);

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void searchDatabase() {
        String mobileNumber = mobileNumberTextField.getText().trim();

        // Perform a database search based on the mobile number
        String sql = "SELECT * FROM vehicle WHERE mobile = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, mobileNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            dataTable.setModel(buildTableModel(resultSet));

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while searching the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/rtovehicle";
        String username = "root";
        String password = "1234";
        return DriverManager.getConnection(jdbcUrl, username, password);
    }

    // Utility method to convert ResultSet to TableModel
    private TableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        // Names of columns
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int column = 1; column <= columnCount; column++) {
            columnNames[column - 1] = metaData.getColumnName(column);
        }

        // Data of the table
        Object[][] data = new Object[100][columnCount]; // Assuming a maximum of 100 rows

        int row = 0;
        while (resultSet.next() && row < 100) {
            for (int column = 1; column <= columnCount; column++) {
                data[row][column - 1] = resultSet.getObject(column);
            }
            row++;
        }

        return new DefaultTableModel(data, columnNames);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShowDetails frame = new ShowDetails();
            frame.setVisible(true);
        });
    }
}
