import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class staffUpdateInformation {
	private static JTextField txtName;
	private static JTextField txtPhone;
	private static JTextField txtEmail;
	private static JTextField txtUsername;
	private static JPasswordField pwdPassword;
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblAlinaBeautySalon = new JLabel("Alina Beauty Salon");
		lblAlinaBeautySalon.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlinaBeautySalon.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		frame.getContentPane().add(lblAlinaBeautySalon, "8, 2, 5, 1");
		
		JLabel lblUsername = new JLabel("Username");
		frame.getContentPane().add(lblUsername, "8, 4");
		
		txtUsername = new JTextField();
		txtUsername.setText("username");
		frame.getContentPane().add(txtUsername, "12, 4, fill, default");
		txtUsername.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		frame.getContentPane().add(lblName, "8, 6");
		
		txtName = new JTextField();
		txtName.setText("name");
		frame.getContentPane().add(txtName, "12, 6, fill, default");
		txtName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		frame.getContentPane().add(lblEmail, "8, 8");
		
		txtEmail = new JTextField();
		txtEmail.setText("email");
		frame.getContentPane().add(txtEmail, "12, 8, fill, default");
		txtEmail.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		frame.getContentPane().add(lblPhoneNumber, "8, 10");
		
		txtPhone = new JTextField();
		txtPhone.setText("phone");
		frame.getContentPane().add(txtPhone, "12, 10, fill, default");
		txtPhone.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		frame.getContentPane().add(lblPassword, "8, 12");
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("password");
		frame.getContentPane().add(pwdPassword, "12, 12, fill, default");
		
		JButton btnApplyChanges = new JButton("Apply Changes");
		frame.getContentPane().add(btnApplyChanges, "12, 18");
		
	}
}
