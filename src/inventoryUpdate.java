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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;

public class inventoryUpdate {
	private JTextField textField;
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","vrocker123");  
	Statement stmt = con.createStatement();
	public inventoryUpdate() throws ClassNotFoundException, SQLException {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}

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
		        	try {
		        		ResultSet result = stmt.executeQuery("select * from inventory");
						while(result.next()){
							String name = result.getString(1);
							if (name.compareTo(product) == 0) {
								int availableQuantity = result.getInt(2);
								if (Integer.valueOf(quantity) > availableQuantity) {
									JOptionPane.showMessageDialog(null, "Quantity used is more than available quantity", "Please check the quantity used", 0);
								}
								else {
									int minQuantity = result.getInt(3);
									String supplier = result.getString(4);
									int newQuantity = availableQuantity - Integer.valueOf(quantity);
									Statement statement = con.createStatement();
									statement.executeUpdate("UPDATE `mydb`.`inventory` SET `quantity` = '" + newQuantity + "' WHERE (`name` = '" + product + "');");
										if(minQuantity > newQuantity){
										String quantityWanted = JOptionPane.showInputDialog(null, "The quantity of " + product + " has gone below threshold value. How much more would you like to order?", "Quantity deficit", 0);
										if (minQuantity >= (Integer.valueOf(quantityWanted) + newQuantity)) {
											JOptionPane.showMessageDialog(null, "More will need to be ordered", "Insufficient order placement", 0);
											quantityWanted = JOptionPane.showInputDialog(null, "The quantity of " + product + " has gone below threshold value. How much more would you like to order?", "Quantity deficit", 0);
										}
										String orderMail = "Good afternoon,\nAlina Beauty Salon wants to place an order for " + product + " of quantity " + quantityWanted;
										emailSender es = new emailSender(supplier, "Alina Beauty Salon Order", orderMail);
										frame.dispose();
										
									}
								}
							}
						}
					} catch (SQLException e1) {
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
		Statement state = con.createStatement();
		ResultSet list = state.executeQuery("select * from inventory");
		while (list.next()) {
			String name = list.getString(1);
			dm.addElement(name);
		}
		return dm;
	}
}
