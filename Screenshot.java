package ru.runa.wf.web.servlet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Screenshot extends HttpServlet {
	
    private static int screenCounter = 0;
    	
    public void makeScreen() {
    	String path = "C:/Sreenshots/screenshot" + screenCounter + ".png";
        File file = new File(path);
        while (file.exists() && file.isFile()) {
            path = "C:/Sreenshots/screenshot" + ++screenCounter + ".png";
            file = new File(path);
        }
       
        UIManager.put("OptionPane.yesButtonText", "\u0414\u0430");
        UIManager.put("OptionPane.noButtonText", "\u041d\u0435\u0442");
        
        try {
               
            Robot robot = new Robot();
            Rectangle screenArea = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(screenArea);
            
            if (JOptionPane.showConfirmDialog(null, "\u0412\u044b\u0020\u0445\u043e\u0442\u0438\u0442\u0435\u0020\u0441\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c\u0020\u0441\u043d\u0438\u043c\u043e\u043a\u0020\u044d\u043a\u0440\u0430\u043d\u0430\u0020\u043d\u0430\u0020\u0441\u0432\u043e\u0435\u043c\u0020\u043a\u043e\u043c\u043f\u044c\u044e\u0442\u0435\u0440\u0435\u003f\n" + path, "\u0421\u043d\u0438\u043c\u043e\u043a\u0020\u044d\u043a\u0440\u0430\u043d\u0430", JOptionPane.YES_NO_OPTION) == 0) { // 0 = Yes --- 1 = No
            	Thread.sleep(250);
            	ImageIO.write(bufferedImage, "png", new File(path));
            }
            
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	makeScreen();
    	resp.sendRedirect("/wfe/");
    }
}
