package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {

	//------------------------------------ GUI info -------------------------------//
	private JPanel mainpanel;
	private JPanel secondpanel;
	private JPanel thirdpanel;
	private JTextField textFieldlog;
	private JPasswordField passwordField1;
	private JPanel fourthpanel;
	private JButton cancelButton;
	private JButton loginButton;
	private JPanel titlepanel;
	private JPanel signopbuttpanel;
	private JButton signUpButton;

	//------------------------------------ return GUI  -------------------------------//

	public JPanel getloginpan(){
		return mainpanel;
	}

	//------------------------------------ Constructor -------------------------------//

	public login(){


		//------------------------------------ login button listener -------------------------------//

		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String username = textFieldlog.getText();
				String password = String.valueOf(passwordField1.getPassword());

				Connection connection = null;
				try {
					connection = connector.ConnectDB();
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
				if (password.isEmpty() || username.isEmpty()) {
					JOptionPane.showMessageDialog(null," Field is empty !!!" ,"error " , JOptionPane.ERROR_MESSAGE);
				}

				else if(connection!=null) {

					try {

						PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT * FROM users WHERE username =? AND password=?");
						ps.setString(1,username);
						ps.setString(2,password);

						ResultSet rs = ps.executeQuery();

						if (rs.next()) {

							//display new frame
							dashbord dash = new dashbord();
							JPanel root = dash.getdash(username,password);
							JFrame frame =new JFrame();
							frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
							frame.setSize(800,500);
							frame.setContentPane(root);
							frame.setLocationRelativeTo(null);
							dash.showinfo(username,password);
							frame.setVisible(true);


						}else{
							JOptionPane.showMessageDialog(null," this login is not correct !!" ,"error " , JOptionPane.ERROR_MESSAGE);
						}


					} catch (SQLException ex) {
						throw new RuntimeException(ex);
					} catch (Exception ex) {
						throw new RuntimeException(ex);
					}


				}else {
					JOptionPane.showMessageDialog(null," Connection failed !!" ,"error " , JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		//------------------------------------ Cancel button listener -------------------------------//

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComponent component = (JComponent) e.getSource();
				Window window = SwingUtilities.getWindowAncestor(component);
				window.dispose();
			}
		});

		//------------------------------------ Sign up button listener -------------------------------//


		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JComponent component = (JComponent) e.getSource();
				Window window = SwingUtilities.getWindowAncestor(component);
				window.dispose();

				GetsignupGUI();

			}
		});
	}

	//------------------------------------ function to get signup GUI -------------------------------//

	public void GetsignupGUI(){

		signup ui = new signup();

		JPanel root = ui.getsignupGUI();

		JFrame frame = new JFrame();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setContentPane(root);
		frame.setVisible(true);

	}




}

