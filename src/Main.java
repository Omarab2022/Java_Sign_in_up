
import demo.login;
import javax.swing.*;

public class Main {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(Main::getGUI);

	}


	public static void getGUI(){

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