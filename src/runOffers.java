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

import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import java.awt.Color;

public class runOffers {
	private JTextField textField;
	public runOffers() throws ClassNotFoundException, SQLException {
		JFrame frame = new JFrame();
		frame.setSize(450, 300);
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","vrocker123");  
		//here mydb is database name, root is username and password
		Statement stmt = con.createStatement();  
		ResultSet rs = stmt.executeQuery("select * from activeOffers");
		rs.next();
		int activeOffers = rs.getInt(1);
		JLabel lblRunOffers = new JLabel("Run Offers");
		lblRunOffers.setHorizontalAlignment(SwingConstants.CENTER);
		lblRunOffers.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblRunOffers.setBounds(81, 6, 334, 35);
		frame.getContentPane().add(lblRunOffers);
		boolean offersactive = false;
		JToggleButton tglbtnActivateOffer = new JToggleButton("Activate Offer");
		tglbtnActivateOffer.setBounds(144, 188, 161, 29);
		frame.getContentPane().add(tglbtnActivateOffer);
		JLabel lblPercentDiscount = new JLabel("Percent Discount");
		lblPercentDiscount.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPercentDiscount.setBounds(35, 107, 133, 19);
		frame.getContentPane().add(lblPercentDiscount);
		
		textField = new JTextField();
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setBounds(203, 104, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		if (activeOffers == 1) {
			tglbtnActivateOffer.setSelected(true);
			offersactive = true;
			textField.setEnabled(false);
			lblRunOffers.setText("Offer Already Being Run");
		}
		final boolean bob = offersactive;
		tglbtnActivateOffer.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				if (bob) {
					try {
						stmt.executeUpdate("UPDATE `mydb`.`activeOffers` SET `offerActive` = '0' WHERE (`offerActive` = '1');");
						JOptionPane.showMessageDialog(null, "Offer has been deactivated", "Running Offers Update", 0);
						frame.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						stmt.executeUpdate("UPDATE `mydb`.`activeOffers` SET `offerActive` = '1' WHERE (`offerActive` = '0');");
						JOptionPane.showMessageDialog(null, "Offer has been activated", "Running Offers Update", 0);
						frame.dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);	
	}
}
