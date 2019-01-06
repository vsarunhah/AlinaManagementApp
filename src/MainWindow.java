import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MainWindow {

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Alina");
		
		JLabel lblAlinaBeautySalon = new JLabel("Alina Beauty Salon");
		lblAlinaBeautySalon.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlinaBeautySalon.setFont(new Font("Avenir", Font.PLAIN, 30));
		frame.getContentPane().add(lblAlinaBeautySalon, BorderLayout.NORTH);
		
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.addActionListener(new adminLoginHandler());
		btnAdminLogin.setFont(new Font("Avenir", Font.PLAIN, 25));
		frame.getContentPane().add(btnAdminLogin, BorderLayout.WEST);
		
		JButton btnStaffLogin = new JButton("Staff Login");
		btnStaffLogin.setFont(new Font("Avenir Next", Font.PLAIN, 25));
		btnStaffLogin.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            staffLogin sl = new staffLogin();
	            sl.showWindow();
	        }
	    } );
		frame.getContentPane().add(btnStaffLogin, BorderLayout.EAST);
		frame.setSize(800, 520);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

}

class adminLoginHandler implements ActionListener{        
	  public void actionPerformed (ActionEvent e) {     
	    adminLogin al = new adminLogin();
	    al.showWindow();
	  }
	}
	