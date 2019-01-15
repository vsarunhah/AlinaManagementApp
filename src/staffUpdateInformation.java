import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;

public class staffUpdateInformation {
	private static JTextField txtName;
	private static JTextField txtPhone;
	private static JTextField txtEmail;
	private static JTextField txtUsername;
	private static JPasswordField pwdPassword;
	
	String username; 
	public void showWindow(String username) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");  
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","vrocker123");  
		//here mydb is database name, root is username and password
		Statement stmt = con.createStatement();  
		ResultSet rs = stmt.executeQuery("select * from employee_login where (`username` = '" + username + "')");
		rs.next();
		String name = rs.getString(4);
		String phone = rs.getString(5);
		String email = rs.getString(6);
		String password = rs.getString(3);
		
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		JLabel lblAlinaBeautySalon = new JLabel("Alina Beauty Salon");
		lblAlinaBeautySalon.setBounds(91, 5, 359, 25);
		lblAlinaBeautySalon.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlinaBeautySalon.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		frame.getContentPane().add(lblAlinaBeautySalon);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(91, 40, 92, 16);
		frame.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(216, 35, 234, 26);
		txtUsername.setText(username);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(91, 71, 92, 16);
		frame.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(216, 66, 234, 26);
		txtName.setText(name);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(91, 102, 92, 16);
		frame.getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(216, 97, 234, 26);
		txtEmail.setText(email);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(91, 133, 92, 16);
		frame.getContentPane().add(lblPhoneNumber);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(216, 128, 234, 26);
		txtPhone.setText(phone);
		frame.getContentPane().add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(91, 164, 92, 16);
		frame.getContentPane().add(lblPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(216, 159, 234, 26);
		pwdPassword.setText(password);
		frame.getContentPane().add(pwdPassword);
		
		JButton btnApplyChanges = new JButton("Apply Changes");
		btnApplyChanges.setBounds(216, 250, 234, 29);
		btnApplyChanges.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// breaking up SQL query into individual update lines to help simplify code
					String finalQuery = "UPDATE `mydb`.`employee_login` SET ";
					finalQuery = finalQuery + "`username` = '" + txtUsername.getText() + "', ";
					finalQuery = finalQuery + "`password` = '"+ String.valueOf(pwdPassword.getPassword())+ "', ";
					finalQuery = finalQuery + "`name` = '"+ txtName.getText() +"', ";
					finalQuery = finalQuery + "`phone` = '" + txtPhone.getText() +"', ";
					finalQuery = finalQuery + "`email` = '" + txtEmail.getText() +"' ";
					finalQuery = finalQuery + "WHERE (`username` = '"+ username +"');";
					stmt.executeUpdate(finalQuery);
					frame.dispose();
					JOptionPane.showMessageDialog(frame, "Changes successfully applied");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(btnApplyChanges);
		
		frame.setSize(800, 520);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);		
		
	}
}
