package utilities;


import java.io.IOException;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.http.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
 

//@WebServlet("/captcha-servlet") 
public class CaptchaGenServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;
		public static final String FILE_TYPE = "jpeg";
 
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
 
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Max-Age", 0);
            response.setContentType("image/jpeg");
 
            String captchaStr="";
 
        //captchaStr=Util.generateCaptchaTextMethod();
 
            try {
				captchaStr=Util.generateCaptchaTextMethod2(6);
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
 
            try {
 
                int width=100;      int height=40;
 
                Color bg = new Color(255,255,255);
                Color fg = new Color(178,70,70);
 
                Font font = new Font("Courier", Font.BOLD, 20);
                BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.OPAQUE);
                Graphics g = cpimg.createGraphics();
 
                g.setFont(font);
                g.setColor(bg);
                g.fillRect(0, 0, width, height);
                g.setColor(fg);
                g.drawString(captchaStr,10,25);   
 
				/*
				 * if(ESAPI.validator().isValidInput("Captcha", captchaStr, "WordS", 6, false)
				 * == false){ throw new ValidationException("Captcha must be in valid format.");
				 * }
				 */	
                
                HttpSession session = request.getSession(true);
                System.out.println(captchaStr);
               // session.setAttribute("CAPTCHA", captchaStr);
 
               OutputStream outputStream = response.getOutputStream();
               //ByteArrayOutputStream  outputStream
               ImageIO.write(cpimg, FILE_TYPE, outputStream);
				/*
				 * ByteArrayOutputStream bos = new ByteArrayOutputStream(); ImageIO.write(cpimg,
				 * "FILE_TYPE", bos ); byte [] data = bos.toByteArray();
				 */
               
               outputStream.close();
 
            } catch (Exception e) {
                    e.printStackTrace();
            }
        }
 
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
        	System.out.println("11111111111111111111111111");
            doPost(request, response);
        }
 
 }
