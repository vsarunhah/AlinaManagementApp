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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.joda.time.LocalDate;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class requestHoliday {
	private JTextField textField;
	public requestHoliday(String username) throws SQLException {
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
		frame.setSize(450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRequestingHoidays = new JLabel("Requesting Holiday");
		lblRequestingHoidays.setFont(new Font("Lucida Grande", Font.PLAIN, 26));
		lblRequestingHoidays.setBounds(115, 18, 275, 55);
		frame.getContentPane().add(lblRequestingHoidays);
		
		JLabel lblDateOfHoliday = new JLabel("Date of holiday");
		lblDateOfHoliday.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDateOfHoliday.setBounds(69, 95, 124, 31);
		frame.getContentPane().add(lblDateOfHoliday);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(242, 99, 110, 27);
		frame.getContentPane().add(comboBox);
		comboBox.setModel(comboboxDateAdder());
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblReason.setBounds(69, 149, 124, 20);
		frame.getContentPane().add(lblReason);
		
		textField = new JTextField();
		textField.setBounds(242, 147, 186, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnRequest = new JButton("Request");
		btnRequest.setBounds(158, 207, 117, 29);
		frame.getContentPane().add(btnRequest);
		btnRequest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().compareTo("") == 0) {
						JOptionPane.showMessageDialog(null, "Enter a valid reason for your holiday", "Empty reason box", 0);
					}
					else{
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","vrocker123");  
						Statement stmt = con.createStatement();
						String[] selectedDate = comboBox.getSelectedItem().toString().split("/");
						String dateSelection = selectedDate[2] + "-" + selectedDate[1] + "-" + selectedDate[0];
						stmt.executeUpdate("INSERT INTO `mydb`.`holidayList` (`date`, `reason`, `employeeName`) VALUES ('"+ dateSelection + "', '" + textField.getText() +"', '" + username +"');");}
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				
			}
			
		});
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
	}

public DefaultComboBoxModel comboboxDateAdder() throws SQLException {
	DefaultComboBoxModel dm = new DefaultComboBoxModel();
	Calendar rightnow = Calendar.getInstance();
	for (int i = 0; i < 30; i++) {
	rightnow.add(Calendar.DATE, 1);
	Date date = rightnow.getTime();
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	dm.addElement(df.format(date));
	}
	return dm;
}
}