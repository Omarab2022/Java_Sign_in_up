package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class signup {
	private JPanel mainsinguppanel;
	private JPanel titlepanel;
	private JTextField textFieldfirstname;
	private JTextField textFieldmiddname;
	private JTextField textFieldlastname;
	private JTextField textFieldadress;
	private JTextField textFieldusername;
	private JPasswordField passwordField1;
	private JPanel infopanel;
	private JPanel buttonpanel;
	private JButton cancelButton;
	private JButton signUpButton;
	private JButton backToLoginButton;

	//------------------------------------ return signup GUI -------------------------------//
	public JPanel getsignupGUI() {
		return mainsinguppanel;
	}

	//------------------------------------ Constructor -------------------------------//

	signup(){


		//------------------------------------ Cancel button -------------------------------//

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JComponent component = (JComponent) e.getSource();
				Window window = SwingUtilities.getWindowAncestor(component);
				window.dispose();
			}
		});

		//------------------------------------ signup button -------------------------------//

		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String fname = textFieldfirstname.getText();
				String mname = textFieldmiddname.getText();
				String lname = textFieldlastname.getText();
				String adress = textFieldadress.getText();
				String username = textFieldusername.getText();
				String password = String.valueOf(passwordField1.getPassword());

				if (password.isEmpty() || fname.isEmpty() || mname.isEmpty() || lname.isEmpty() || adress.isEmpty() || username.isEmpty() ) {
					JOptionPane.showMessageDialog(null ,  " Fields Is Impty !!" , "error",JOptionPane.ERROR_MESSAGE);
				}else{

					try {
						Connection con = connector.ConnectDB();

						PreparedStatement ps = con.prepareStatement("INSERT INTO users (firstname,middlename,lastname,adress,username,password) VALUES (?,?,?,?,?,?)");

						ps.setString(1,fname);
						ps.setString(2,mname);
						ps.setString(3,lname);
						ps.setString(4,adress);
						ps.setString(5,username);
						ps.setString(6,password);

						ps.executeUpdate();

						JOptionPane.showMessageDialog(null ,  " You Sign Up succesfuly !!" , "sucses",JOptionPane.INFORMATION_MESSAGE);


					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}

				}


			}
		});

		//------------------------------------back to login page button -------------------------------//


		backToLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JComponent component = (JComponent) e.getSource();
				Window window = SwingUtilities.getWindowAncestor(component);
				window.dispose();

				OpenLoginGUI();

			}
		});
	}

	//----------------------- function return login Gui for back to .. ---------------------//

	public void OpenLoginGUI(){
		login ui = new login();

		JPanel root = ui.getloginpan();

		JFrame frame =new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(500,500);
		frame.setContentPane(root);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
