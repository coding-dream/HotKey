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
		// 参数KEY_1表示改组热键组合的标识，第二个参数表示组合键，如果没有则为0，该热键对应 Ctrl + Alt + I
		JIntellitype.getInstance().registerHotKey(KEY_1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'I');
		JIntellitype.getInstance().registerHotKey(KEY_2, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'O');
		JIntellitype.getInstance().registerHotKey(KEY_3, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'P');
		JIntellitype.getInstance().registerHotKey(KEY_4, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,(int)'W');
		
		JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
			

			@Override
			public void onHotKey(int flag) {
				
				switch (flag) {
				case KEY_1:
					// 打开网页
					 if(Desktop.isDesktopSupported()){
						 if(Desktop.getDesktop().isSupported(Action.BROWSE)){
							 
							 try {
								 System.out.println("Ctrl + Alt + I 按下........."); 
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
					// 截屏
					System.out.println("Ctrl + Alt + O 按下........."); 
					capture();
					break;
				case KEY_3:
					// 模拟按键
					System.out.println("Ctrl + Alt + P 按下.........");  
/*
					try {
						Robot robot = new Robot();
						// 移动鼠标
						robot.mouseMove(300, 500);
						// 鼠标左键
						robot.mousePress(InputEvent.BUTTON1_MASK);
						robot.mouseRelease(InputEvent.BUTTON1_MASK);
						// 鼠标中键
						robot.mousePress(InputEvent.BUTTON2_MASK);
						robot.mouseRelease(InputEvent.BUTTON2_MASK);
						// 鼠标右键
						robot.mousePress(InputEvent.BUTTON3_MASK);
						robot.mouseRelease(InputEvent.BUTTON3_MASK);
						robot.delay(3000);
						// 按下ESC，退出右键状态
						robot.keyPress(KeyEvent.VK_ESCAPE); // 常用按键  VK_TAB , VK_ALT
			            robot.keyRelease(KeyEvent.VK_ESCAPE);
			            // 滚动鼠标滚轴
			            robot.mouseWheel(5);			
					} catch (Exception e) {
						e.printStackTrace();
					}
*/
					break;
				
				case KEY_4:
					// 打开软件
					try {
//						Runtime.getRuntime().exec("cmd /c start winword");// word 系统命令
						Runtime.getRuntime().exec("cmd /c start notepad");
						//延缓几秒钟，等待记事本程序启动成功
						Robot robot = new Robot();
			            robot.delay(3000);
			            
			            // 模拟按下"Ctrl + Space" 组合键，启动输入法
			            robot.keyPress(KeyEvent.VK_CONTROL);
			            robot.keyPress(KeyEvent.VK_SPACE);
			            robot.keyPress(KeyEvent.VK_SPACE);
			            robot.keyRelease(KeyEvent.VK_CONTROL);						
						
			            //模拟随机按下100个字母，输入汉字
			            for(int i = 0; i < 100; i++){
			            	robot.keyPress((int) (Math.random()* 25) + 'A');
			               	robot.keyPress(KeyEvent.VK_SPACE); // 空格
			            }
			            robot.delay(5000);
			            
			            // 关闭应用
			            robot.keyPress(KeyEvent.VK_ALT);
			            robot.keyPress(KeyEvent.VK_F4);
			            
			            robot.keyRelease(KeyEvent.VK_ALT);
			            robot.keyRelease(KeyEvent.VK_F4);
			            
			            //模拟按下"N"不保存文件退出记事本程序,S保存 N不保存
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
		// 解除注册
		JIntellitype.getInstance().unregisterHotKey(KEY_1);  
        JIntellitype.getInstance().unregisterHotKey(KEY_2);  
        JIntellitype.getInstance().unregisterHotKey(KEY_3);  
        JIntellitype.getInstance().unregisterHotKey(KEY_4);  
        System.exit(0);  
	}
	
}
