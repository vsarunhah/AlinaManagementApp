import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingConstants;

public class adminLandingPage {
	private JButton btnRunOffers;
	public adminLandingPage() {
		JFrame frame = new JFrame();
		frame.setSize(450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHello = new JLabel("Hello");
		lblHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblHello.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblHello.setBounds(131, 6, 151, 34);
		frame.getContentPane().add(lblHello);
		
		btnRunOffers = new JButton("Run Offers");
		btnRunOffers.setBounds(151, 70, 117, 29);
		btnRunOffers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					runOffers ro = new runOffers();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		frame.getContentPane().add(btnRunOffers);
		
		JButton btnAppointments = new JButton("Appointments");
		btnAppointments.setBounds(151, 193, 117, 29);
		btnAppointments.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					appointmentViewer av = new appointmentViewer();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		frame.getContentPane().add(btnAppointments);
		
		JButton btnCustomers = new JButton("Customers");
		btnCustomers.setBounds(151, 111, 117, 29);
		btnCustomers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					customerInformationList cil = new customerInformationList();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		frame.getContentPane().add(btnCustomers);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setBounds(151, 152, 117, 29);
		btnInventory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					inventoryUpdate iu = new inventoryUpdate();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
			
		});
		frame.getContentPane().add(btnInventory);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
