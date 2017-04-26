import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MainView {
	// some queries I declared to test the program
	static String vipSelectionQuery = "SELECT `customers`.`name`, `orders`.`order_id`FROM `customers` JOIN `orders` ON(customers.id= orders.customer_id) "
			+ "where customers.promocode=25253";
	static String expressOrdersQuery = "SELECT `customers`.`name`, `orders`.`order_id`FROM `customers` JOIN `orders` ON(customers.id= orders.customer_id)"
			+ "where orders.deadline=3;";
	static String pricesShowQuery = "SELECT `customers`.`name`,  orders.price FROM `customers` JOIN `orders` ON(customers.id= orders.customer_id)";

	public static void main(String[] args) {
		JFrame frame = new JFrame("Laundry Selector");
		frame.setVisible(true);
		frame.setSize(1280, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label = new JLabel("Welcome to the Public Laundry control panel"
				+ " Click the corresponding button to get latest updates.");
		JPanel p1 = new JPanel();
		frame.add(p1);
		p1.add(label);

		/*
		 * Creating the Buttons and attaching ActionListeners. I call the static
		 * method DatabaseConnector.queryLaunch giving it the query as a string
		 * This way I can change the query and the name of the button without
		 * affecting the architecture.
		 */

		JButton jb1 = new JButton("VIP users");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatabaseConnector.queryLaunch(vipSelectionQuery);

				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		JButton jb2 = new JButton("EXPRESS orders");
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatabaseConnector.queryLaunch(expressOrdersQuery);

				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		JButton jb3 = new JButton("Price");
		jb3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatabaseConnector.queryLaunch(pricesShowQuery);

				} catch (SQLException e1) {

					e1.printStackTrace();
				}
			}
		});
		p1.add(jb1);
		p1.add(jb2);
		p1.add(jb3);

		final JTextArea txtConsole = new JTextArea();
		p1.add(txtConsole);

		/*
		 * Now creating a new TextAreaOutputStream to write to our JTextArea
		 * control and wrap a PrintStream around it to support the
		 * println/printf methods.
		 */
		PrintStream out = new PrintStream(new TextAreaOutputStream(txtConsole));

		// redirect standard output stream to the TextAreaOutputStream
		System.setOut(out);

		// redirect standard error stream to the TextAreaOutputStream
		System.setErr(out);
	}

}
