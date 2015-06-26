package Animation;

import javax.swing.JLabel;

public class WelcomeLable extends JLabel implements Runnable {
	public boolean loop = true;
	private boolean appar = true;
	
	public WelcomeLable(String title) {
		this.setText(title);
	}
	
	public void run() {
		while(loop){
			new delay(500);
			this.setVisible(appar);
			appar = !appar;
		}
	}

}
