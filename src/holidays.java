import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

public class holidays {
	public holidays(String empName) {
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
		
		JLabel lblStaffHolidays = new JLabel("Staff Holidays");
		lblStaffHolidays.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblStaffHolidays.setBounds(130, 19, 239, 36);
		frame.getContentPane().add(lblStaffHolidays);
		
		JButton btnRequestHoliday = new JButton("Request Holiday");
		btnRequestHoliday.setBounds(148, 92, 163, 36);
		frame.getContentPane().add(btnRequestHoliday);
		btnRequestHoliday.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					requestHoliday rh = new requestHoliday(empName);
					frame.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Please try again later", "Unable to connect", 0);
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton btnHolidayList = new JButton("View Holidays");
		btnHolidayList.setBounds(148, 180, 163, 36);
		btnHolidayList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					viewHoliday vh = new viewHoliday();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.dispose();
				
			}
			
		});
		frame.getContentPane().add(btnHolidayList);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
