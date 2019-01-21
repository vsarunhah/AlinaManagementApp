import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class inventoryUpdate {
	ResultSet rs;
	private JTextField textField;
	public inventoryUpdate() throws ClassNotFoundException, SQLException {
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setSize(500, 400);
		frame.getContentPane().setLayout(null);
		
		JLabel lblInventoryUpdate = new JLabel("Inventory Update");
		lblInventoryUpdate.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblInventoryUpdate.setBounds(159, 19, 287, 46);
		frame.getContentPane().add(lblInventoryUpdate);
		
		JComboBox productName = new JComboBox();
		productName.setBounds(259, 122, 169, 27);
		frame.getContentPane().add(productName);
		
		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblProductName.setBounds(104, 122, 126, 23);
		frame.getContentPane().add(lblProductName);
		
		JLabel lblQuantityUsed = new JLabel("Quantity Used");
		lblQuantityUsed.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblQuantityUsed.setBounds(104, 175, 126, 20);
		frame.getContentPane().add(lblQuantityUsed);
		
		textField = new JTextField();
		textField.setToolTipText("Enter a number");
		textField.setBounds(259, 173, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		frame.setVisible(true);
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","vrocker123");  
		Statement stmt = con.createStatement();
		rs = stmt.executeQuery("select * from inventory");
		productName.setModel(defaultComboboxModel());
		
		JButton btnApplyChanges = new JButton("Apply Changes");
		btnApplyChanges.setBounds(201, 233, 138, 29);
		frame.getContentPane().add(btnApplyChanges);
		btnApplyChanges.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		        boolean numeric = true;
		        String quantity = textField.getText();
		        String product = productName.getSelectedItem().toString();
		        try {
		            Double num = Double.parseDouble(quantity);
		        } catch (NumberFormatException e1) {
		            numeric = false;
		        }

		        if (numeric) {
		        	ResultSet result = rs;
		        	try {
						while(result.next()){
							if (result.getString(1).compareTo(product) == 0) {
								int availableQuantity = result.getInt(2);
								if (Integer.valueOf(quantity) > availableQuantity) {
									JOptionPane.showMessageDialog(null, "Please check the quantity used", "Quantity used is more than available quantity", 0);
								}
								else {
									int newQuantity = availableQuantity - Integer.valueOf(quantity);
									stmt.executeUpdate("UPDATE `mydb`.`inventory` SET `quantity` = '" + newQuantity + "' WHERE (`name` = '" + product + "');");
									if(result.getInt(3) > newQuantity){
										String quantityWanted = JOptionPane.showInputDialog(null, "The quantity of " + product + " has gone below threshold value. How much more would you like to order?", "Quantity deficit", 0);
										if (result.getInt(3) > (Integer.valueOf(quantityWanted) + newQuantity)) {
											JOptionPane.showMessageDialog(null, "More will need to be ordered", "Insufficient order placement", 0);
											quantityWanted = JOptionPane.showInputDialog(null, "The quantity of " + product + " has gone below threshold value. How much more would you like to order?", "Quantity deficit", 0);
										}
										String orderMail = "Good afternoon,\nAlina Beauty Salon wants to place an order for " + product + " of quantity " + quantityWanted;
										emailSender es = new emailSender(result.getString(4), "Alina Beauty Salon Order", orderMail);
									}
								}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		        else {
		            JOptionPane.showMessageDialog(null, "Please enter numbers only", "Entry Error", 0);
		    }
			}
			
		});
	}
	public DefaultComboBoxModel defaultComboboxModel() throws SQLException {
		DefaultComboBoxModel dm = new DefaultComboBoxModel();
		ResultSet list = rs;
		while (list.next()) {
			String name = rs.getString(1);
			dm.addElement(name);
		}
		return dm;
	}
}
