import javax.swing.JFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class appointmentViewer {
	private JTable table;
	public appointmentViewer() throws SQLException {
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
		frame.setSize(600, 400);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","vrocker123");  
		Statement stmt = con.createStatement();  
		ResultSet rs = stmt.executeQuery("select `date`, `customer`, `servicesWanted` from appointments");
		
		table = new JTable();
		table.setModel(buildTableModel(rs));
		scrollPane.setViewportView(table);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData rsMeta = rs.getMetaData();

	    // names of columns
	    Vector<String> columns = new Vector<String>();
	    int numColumns = rsMeta.getColumnCount();
	    for (int column = 1; column <= numColumns; column++) {
	        columns.add(rsMeta.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> columnData = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= numColumns; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        columnData.add(vector);
	    }

	    return new DefaultTableModel(columnData, columns);

	}
}
