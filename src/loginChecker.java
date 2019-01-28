import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class loginChecker {

	public void showWindow() {
		JFrame frame = new JFrame();
		
	}
	
	public boolean confirmIdentity(String username, String password, String database) {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","vrocker123");  
			//here mydb is database name, root is username and password  
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from " + database);  
			while(rs.next()) {
				
			String user = rs.getString(2);
			String pass = rs.getString(3);
			
			if (username.compareTo(user) == 0 && password.compareTo(pass) == 0) {
				System.out.println("Login Successful");
				JOptionPane.showMessageDialog(null, "Login Successful");
				return true;
			}
			else if (rs.isLast()){
				JOptionPane.showMessageDialog(null, "Login Unsuccessful. Verify login credentials");
			}
			
			}
			con.close();
			return false;
			}catch(Exception e){ System.out.println(e);}
		System.out.println("Connection did not work. Try some other shit");
		return false;  
	}

}
