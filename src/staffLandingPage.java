import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class staffLandingPage {
	String user;
	public staffLandingPage(String username) {
		user = username;
	}
	public void showWindow(){
		JFrame frame = new JFrame();
		frame.setSize(450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHiName = new JLabel("Hi, " + user);
		lblHiName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHiName.setBounds(154, 33, 241, 30);
		frame.getContentPane().add(lblHiName);
		
		JButton btnUpdateInformation = new JButton("Update Information");
		btnUpdateInformation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				staffUpdateInformation sui = new staffUpdateInformation();
				try {
					sui.showWindow(user);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnUpdateInformation.setBounds(149, 98, 149, 29);
		frame.getContentPane().add(btnUpdateInformation);
		
		JButton btnUpdateInventory = new JButton("Update Inventory");
		btnUpdateInventory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					inventoryUpdate iu = new inventoryUpdate();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnUpdateInventory.setBounds(149, 139, 149, 29);
		frame.getContentPane().add(btnUpdateInventory);
		
		JButton btnUpcomingAppointments = new JButton("Next Appointment");
		btnUpcomingAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					appointmentViewer av = new appointmentViewer();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpcomingAppointments.setBounds(149, 180, 149, 29);
		frame.getContentPane().add(btnUpcomingAppointments);
		
		JButton btnHolidays = new JButton("Holidays");
		
		btnHolidays.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				holidays h = new holidays(user);
				
			}
		});
		btnHolidays.setBounds(149, 221, 149, 29);
		frame.getContentPane().add(btnHolidays);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
