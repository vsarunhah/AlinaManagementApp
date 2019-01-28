import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;
import java.util.Date;
import java.util.Timer;
import java.time.*;
public class MainWindow {

	public static void main(String[] args) throws Exception {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			System.out.println("Nimbus cannot be used here, reverting to non-Nimbus l&f");
		}
		

		JFrame frame = new JFrame("Alina");
		frame.setSize(800, 520);
		JLabel lblAlinaBeautySalon = new JLabel("Alina Beauty Salon");
		lblAlinaBeautySalon.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlinaBeautySalon.setFont(new Font("Avenir", Font.PLAIN, 30));
		frame.getContentPane().add(lblAlinaBeautySalon, BorderLayout.NORTH);
		
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				adminLogin al = new adminLogin();
			    al.showWindow();
			    frame.dispose();
			}
			
		});
		btnAdminLogin.setFont(new Font("Avenir", Font.PLAIN, 25));
		frame.getContentPane().add(btnAdminLogin, BorderLayout.WEST);
		
		JButton btnStaffLogin = new JButton("Staff Login");
		btnStaffLogin.setFont(new Font("Avenir Next", Font.PLAIN, 25));
		btnStaffLogin.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            staffLogin sl = new staffLogin();
	            sl.showWindow();
	            frame.dispose();
	        }
	    } );
		frame.getContentPane().add(btnStaffLogin, BorderLayout.EAST);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
		TimerTask newTask = new MyTimerTask();
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(newTask, 0, 86400000);
	}
}

class MyTimerTask extends TimerTask {
	  public void run() {
		  Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","vrocker123");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM `mydb`.`cust_info`");
			while(rs.next()) {
				Date lastVisit = rs.getDate(5);
				YearMonth today = YearMonth.now();
				YearMonth date = YearMonth.parse(lastVisit.toString().substring(0, 7)); 
				long diff = date.until(today, ChronoUnit.MONTHS);
				if (diff == 3) {
					emailSender missingMail = new emailSender(rs.getString(1), "We miss you " + rs.getString(3), "It's been 3 months since we saw you " + rs.getString(3) + "." + " Come over to Alina Beauty Salon soon, we have new offers running!");
				}
				Date bday = rs.getDate(4);
				String birthday = bday.toString().substring(5);
				String todayYear = LocalDate.now().toString().substring(5);
				if (todayYear.compareTo(birthday) == 0){
					emailSender birthdayEmail = new emailSender(rs.getString(1), "Happy Birthday " + rs.getString(3), "Happy Birthday from Alina,\n We wish you have a super birthday. To look good for your birthday, use the code " + rs.getString(3).replaceAll(" ", "") + LocalDate.now().toString().substring(0, 3)+ " to get 10% off on your total bill this week!");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
			  
	  }
	}
	