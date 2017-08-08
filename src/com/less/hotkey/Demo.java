package com.less.hotkey;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
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
	private static final int KEY_4 = 4;

	public static void main(String[] args) {
		init();
	}

	private static void init() {
		// ����KEY_1��ʾ�����ȼ���ϵı�ʶ���ڶ���������ʾ��ϼ������û����Ϊ0�����ȼ���Ӧ Ctrl + Alt + I
		JIntellitype.getInstance().registerHotKey(KEY_1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'I');
		JIntellitype.getInstance().registerHotKey(KEY_2, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'O');
		JIntellitype.getInstance().registerHotKey(KEY_3, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'P');
		JIntellitype.getInstance().registerHotKey(KEY_4, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'W');
		
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			

			@Override
			public void onHotKey(int flag) {
				
				switch (flag) {
				case KEY_1:
					// ����ҳ
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
					// ����
					System.out.println("Ctrl + Alt + O ����........."); 
					capture();
					break;
				case KEY_3:
					// ģ�ⰴ��
					System.out.println("Ctrl + Alt + P ����.........");  
/*
					try {
						Robot robot = new Robot();
						// �ƶ����
						robot.mouseMove(300, 500);
						// ������
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						// ����м�
						robot.mousePress(InputEvent.BUTTON2_MASK);
						robot.mouseRelease(InputEvent.BUTTON2_MASK);
						// ����Ҽ�
						robot.mousePress(InputEvent.BUTTON3_MASK);
						robot.mouseRelease(InputEvent.BUTTON3_MASK);
						robot.delay(3000);
						// ����ESC���˳��Ҽ�״̬
						robot.keyPress(KeyEvent.VK_ESCAPE); // ���ð���  VK_TAB , VK_ALT
			            robot.keyRelease(KeyEvent.VK_ESCAPE);
			            // ����������
			            robot.mouseWheel(5);			
					} catch (Exception e) {
						e.printStackTrace();
					}
*/
					break;
				
				case KEY_4:
					// �����
					try {
//						Runtime.getRuntime().exec("cmd /c start winword");// word ϵͳ����
						Runtime.getRuntime().exec("cmd /c start notepad");
						//�ӻ������ӣ��ȴ����±����������ɹ�
						Robot robot = new Robot();
			            robot.delay(3000);
			            
			            // ģ�ⰴ��"Ctrl + Space" ��ϼ����������뷨
			            robot.keyPress(KeyEvent.VK_CONTROL);
			            robot.keyPress(KeyEvent.VK_SPACE);
			            robot.keyPress(KeyEvent.VK_SPACE);
			            robot.keyRelease(KeyEvent.VK_CONTROL);						
						
			            //ģ���������100����ĸ�����뺺��
			            for(int i = 0; i < 100; i++){
			            	robot.keyPress((int) (Math.random()* 25) + 'A');
			               	robot.keyPress(KeyEvent.VK_SPACE); // �ո�
			            }
			            robot.delay(5000);
			            
			            // �ر�Ӧ��
			            robot.keyPress(KeyEvent.VK_ALT);
			            robot.keyPress(KeyEvent.VK_F4);
			            
			            robot.keyRelease(KeyEvent.VK_ALT);
			            robot.keyRelease(KeyEvent.VK_F4);
			            
			            //ģ�ⰴ��"N"�������ļ��˳����±�����,S���� N������
			            robot.keyPress(KeyEvent.VK_N);
			            robot.keyRelease(KeyEvent.VK_N);
			            
					} catch (Exception e) {
						e.printStackTrace();
					}
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
        JIntellitype.getInstance().unregisterHotKey(KEY_4);  
        System.exit(0);  
	}
	
}
