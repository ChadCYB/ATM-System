package Animation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PickUpLabel extends JLabel implements Runnable {
	private Properties properties = new Properties();
	private ImageIcon imgIcon[];
	private JLabel thisJlb = this;
	public boolean loop = true;
	
	public PickUpLabel(String title) {
		this.setText(title);
		getPhoto();
	}
	
	public void run() {
		while(loop){
			for(ImageIcon imgC:imgIcon){					//ForEach跑過全部圖檔
				new delay(1000);							//Delay(單位 1/1000秒)
				thisJlb.setIcon(imgC);
//				System.out.println(imgC);
			}
		}
	}

	private void getPhoto() {
		String configFile = "config.properties";
		String imgNum = "0";
		try {
			properties.load(new FileInputStream(configFile));	//讀取設定檔
			imgNum = properties.getProperty("imgNum", "0");
			imgIcon = new ImageIcon[Integer.valueOf(imgNum)];
			for(int i=1 ; i<=Integer.valueOf(imgNum) ; i++){
				imgIcon[i-1] = new ImageIcon(properties.getProperty("img"+i));
//				System.out.println("<Read Img>"+imgIcon[i-1]);	//CheckPoint
			}
		} catch (FileNotFoundException ex) {					//找不到檔案
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
