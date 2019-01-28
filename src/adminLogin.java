import java.sql.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
public class adminLogin extends loginChecker{
	private static JTextField textField;
	private static JPasswordField pwdEnterAdminPassword;
	/**
	 * @wbp.parser.entryPoint
	 */
	@Override
	public void showWindow() {
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

		JFrame frame = new JFrame("Alina");
		frame.setSize(480, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setBounds(180, 17, 95, 16);
		frame.getContentPane().add(lblAdminLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(82, 90, 86, 16);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(82, 132, 86, 16);
		frame.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setToolTipText("enter admin username");
		textField.setBounds(180, 85, 176, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		pwdEnterAdminPassword = new JPasswordField();
		pwdEnterAdminPassword.setToolTipText("enter admin password");
		pwdEnterAdminPassword.setBounds(180, 127, 176, 26);
		frame.getContentPane().add(pwdEnterAdminPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(158, 192, 117, 29);
		btnSubmit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if (confirmIdentity(textField.getText(), String.valueOf(pwdEnterAdminPassword.getPassword()), "admin_login")) {
	            	frame.dispose();
	            	adminLandingPage alp = new adminLandingPage();
	            }
	            else {
	            	JOptionPane.showMessageDialog(null, "The username or password is incorrect. Please try again.", "Incorrect Login Information", JOptionPane.INFORMATION_MESSAGE);
	            }
	        }
	    } );
		frame.getContentPane().add(btnSubmit);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}
}