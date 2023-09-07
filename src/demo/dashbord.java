package demo;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class dashbord {

	ResultSet resultSet;
	private JPanel panel1;
	private JLabel label1;
	private JTable table1;
	private JPanel tabelpanel;
	private JPanel infopanel;
	private JButton logOutButton;

	private DefaultTableModel tableModel;


	//------------------------------------ return GUI dashbord  -------------------------------//
	public JPanel getdash(String username , String password) throws Exception {
		label1.setText(" Welcome "+ username);
		return panel1;
	}


	//------------------------------------ CONSTRUCTOR -------------------------------//
	dashbord( ) throws Exception {

		//------------------------------------ log out button -------------------------------//

		logOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JComponent component = (JComponent) e.getSource();
				Window window = SwingUtilities.getWindowAncestor(component);
				window.dispose();

				login ui = new login();

				JPanel root = ui.getloginpan();

				JFrame frame =new JFrame();
				frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				frame.setSize(500,500);
				frame.setContentPane(root);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);

			}
		});
	}

	//------------------------  Function to show infos in Table  -------------------------------//

	public void showinfo( String username , String password) throws Exception {

		Object[] columntitle = {"id","first name", "middle name", "last name","adress" , "username","password"};

		tableModel = new DefaultTableModel(null,columntitle);
		table1.setModel(tableModel);

		//Connection from database

		Connection connection = connector.ConnectDB();
		Statement st =connection.createStatement();
		tableModel.getDataVector().removeAllElements();

		//initial result white sql query

		resultSet = st.executeQuery("SELECT * FROM users WHERE username LIKE '%"+username+"%' AND password LIKE '%"+password+"%' ");
		while (resultSet.next()){
			Object[] data ={
					resultSet.getString("id"),
					resultSet.getString("firstname"),
					resultSet.getString("middlename"),
					resultSet.getString("lastname"),
					resultSet.getString("adress"),
					resultSet.getString("username"),
					resultSet.getString("password"),

			};
			tableModel.addRow(data);


			if (username.equals("admin") && password.equals("admin")) {

				//initial result white sql query

				resultSet = st.executeQuery("SELECT * FROM users ");
				while (resultSet.next()){
					Object[] data2 ={
							resultSet.getString("id"),
							resultSet.getString("firstname"),
							resultSet.getString("middlename"),
							resultSet.getString("lastname"),
							resultSet.getString("adress"),
							resultSet.getString("username"),
							resultSet.getString("password"),

					};
					tableModel.addRow(data2);

			}}

		}

	}


}
