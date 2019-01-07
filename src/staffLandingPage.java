import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class staffLandingPage {
	/**
	 * @wbp.parser.entryPoint
	 */
	String user;
	public staffLandingPage(String username) {
		user = username;
	}
	public void showWindow(){
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblHiName = new JLabel("Hi, " + user);
		lblHiName.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblHiName.setBounds(137, 33, 241, 30);
		frame.getContentPane().add(lblHiName);
		
		JButton btnUpdateInformation = new JButton("Update Information");
		btnUpdateInformation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Open the information page
			}
		});
		btnUpdateInformation.setBounds(149, 98, 149, 29);
		frame.getContentPane().add(btnUpdateInformation);
		
		JButton btnUpdateInventory = new JButton("Update Inventory");
		btnUpdateInventory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Open Inventory page
				
			}
		});
		btnUpdateInventory.setBounds(149, 139, 149, 29);
		frame.getContentPane().add(btnUpdateInventory);
		
		JButton btnUpcomingAppointments = new JButton("Next Appointment");
		btnUpcomingAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Open Upcoming Appointments
			}
		});
		btnUpcomingAppointments.setBounds(149, 180, 149, 29);
		frame.getContentPane().add(btnUpcomingAppointments);
		
		JButton btnHolidays = new JButton("Holidays");
		//TODO Open Holidays Page
		btnHolidays.setBounds(149, 221, 149, 29);
		frame.getContentPane().add(btnHolidays);
		frame.setSize(800, 520);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
