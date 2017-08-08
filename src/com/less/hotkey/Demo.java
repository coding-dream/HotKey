package com.less.hotkey;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;

import javax.imageio.ImageIO;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class Demo {
	private static final int KEY_1 = 1;
	private static final int KEY_2 = 2;
	private static final int KEY_3 = 3;

	public static void main(String[] args) {
		init();
	}

	private static void init() {
		 //����KEY_1��ʾ�����ȼ���ϵı�ʶ���ڶ���������ʾ��ϼ������û����Ϊ0�����ȼ���Ӧ Ctrl + Alt + I
		JIntellitype.getInstance().registerHotKey(KEY_1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'I');
		JIntellitype.getInstance().registerHotKey(KEY_2, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'O');
		JIntellitype.getInstance().registerHotKey(KEY_3, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'P');
		
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			
			@Override
			public void onHotKey(int flag) {
				
				switch (flag) {
				case KEY_1:
					 if(Desktop.isDesktopSupported()){
						 if(Desktop.getDesktop().isSupported(Action.BROWSE)){
							 
							 try {
								 System.out.println("Ctrl + Alt + I ����........."); 
								 URI uri = URI.create("http://www.csdn.net");  
								 Desktop desktop = Desktop.getDesktop();
								 desktop.browse(uri);
								} catch (Exception e) {
									e.printStackTrace();
								}  
						 }
					 }
					break;
				case KEY_2:
					System.out.println("Ctrl + Alt + O ����........."); 
					capture();
					break;
				case KEY_3:
					System.out.println("Ctrl + Alt + P ����.........");  
					break;
				default:
					break;
				}
				
			}
		});  
	}
	
	protected static void capture() {
		try {
			 Robot robot = new Robot();
//			 Rectangle rectangle = new Rectangle(300, 90, 1000, 720);
			 Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			 BufferedImage image = robot.createScreenCapture(rectangle);
			 File outputfile = new File("F:/test.jpg");  
			 ImageIO.write(image, "jpg", outputfile);  
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy(){
		// ���ע��
		JIntellitype.getInstance().unregisterHotKey(KEY_1);  
        JIntellitype.getInstance().unregisterHotKey(KEY_2);  
        JIntellitype.getInstance().unregisterHotKey(KEY_3);  
        System.exit(0);  
	}
	
}
