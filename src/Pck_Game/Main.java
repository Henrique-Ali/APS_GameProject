package Pck_Game;
import Pck_Menu.Home;

public class Main {
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new Home().setVisible(true);
            }
		});
	}
}