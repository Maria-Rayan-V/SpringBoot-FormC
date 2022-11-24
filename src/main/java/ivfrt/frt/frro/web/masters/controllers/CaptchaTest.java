package ivfrt.frt.frro.web.masters.controllers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import utilities.Util;



@CrossOrigin (origins = "http://localhost:4200")
@RestController
public class CaptchaTest {

	 @GetMapping("/captcha11")
	  public  Map<String,Object> fetchCaptcha(){
		 String captchaStr="";
		 Map<String, Object> ret=new HashMap<String, Object>();
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
             
             ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ImageIO.write(cpimg, "jpg", bos );
             // byte [] data = bos.toByteArray();
             ret.put("captcha", cpimg);
             ret.put("captchaStr",captchaStr);
             
         } catch (Exception e) {
             e.printStackTrace();
     }
      
		 
		 return ret;
	 }
	 
	 @GetMapping(path = "/captcha1")
	 public Map<String, Object> retrnCap(){
		
		 Map<String, Object> ret=new HashMap<String, Object>();
		 
		 try {
	            Color backgroundColor = Color.white;
	            Color borderColor = Color.black;
	            Color textColor = Color.black;
	            Color circleColor = new Color(190, 160, 150);
	            Font textFont = new Font("Verdana", Font.BOLD, 20);
	            int charsToPrint = 6;
	            int width = 160;
	            int height = 50;
	            int circlesToDraw = 25;
	            float horizMargin = 10.0f;
	            double rotationRange = 0.7; 
	            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
	            g.setColor(backgroundColor);
	            g.fillRect(0, 0, width, height);

	            // lets make some noisey circles
	            g.setColor(circleColor);
	            for (int i = 0; i < circlesToDraw; i++) {
	                int L = (int) (Math.random() * height / 2.0);
	                int X = (int) (Math.random() * width - L);
	                int Y = (int) (Math.random() * height - L);
	                g.draw3DRect(X, Y, L * 2, L * 2, true);
	            }
	            g.setColor(textColor);
	            g.setFont(textFont);
	            FontMetrics fontMetrics = g.getFontMetrics();
	            int maxAdvance = fontMetrics.getMaxAdvance();
	            int fontHeight = fontMetrics.getHeight();

	            // i removed 1 and l and i because there are confusing to users...
	            // Z, z, and N also get confusing when rotated
	            // this should ideally be done for every language...
	            // 0, O and o removed because there are confusing to users...
	            // i like controlling the characters though because it helps prevent confusion
	            String elegibleChars = "ABCDEFGHJKLMNPQRSTUVWXYabcdefghjkmnpqrstuvwxy23456789";
	            char[] chars = elegibleChars.toCharArray();
	            float spaceForLetters = -horizMargin * 2 + width;
	            float spacePerChar = spaceForLetters / (charsToPrint - 1.0f);
	            StringBuffer finalString = new StringBuffer();
	            for (int i = 0; i < charsToPrint; i++) {
	                double randomValue = Math.random();
	                int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
	                char characterToShow = chars[randomIndex];
	                finalString.append(characterToShow);

	                // this is a separate canvas used for the character so that
	                // we can rotate it independently
	                int charWidth = fontMetrics.charWidth(characterToShow);
	                int charDim = Math.max(maxAdvance, fontHeight);
	                int halfCharDim = (int) (charDim / 2);
	                BufferedImage charImage = new BufferedImage(charDim, charDim, BufferedImage.TYPE_INT_ARGB);
	                Graphics2D charGraphics = charImage.createGraphics();
	                charGraphics.translate(halfCharDim, halfCharDim);
	                double angle = (Math.random() - 0.5) * rotationRange;
	                charGraphics.transform(AffineTransform.getRotateInstance(angle));
	                charGraphics.translate(-halfCharDim, -halfCharDim);
	                charGraphics.setColor(textColor);
	                charGraphics.setFont(textFont);
	                int charX = (int) (0.5 * charDim - 0.5 * charWidth);
	                charGraphics.drawString("" + characterToShow, charX, (int) ((charDim - fontMetrics.getAscent()) / 2 + fontMetrics.getAscent()));
	                float x = horizMargin + spacePerChar * (i) - charDim / 2.0f;
	                int y = (int) ((height - charDim) / 2);
	                g.drawImage(charImage, (int) x, y, charDim, charDim, null, null);
	                charGraphics.dispose();
	            }
	            g.setColor(borderColor);
	            g.drawRect(0, 0, width - 1, height - 1);
	            g.dispose();
	           String captchaString = finalString.toString();
	            System.out.println(captchaString);
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write( bufferedImage, "jpg", baos );
	            baos.flush();
	            byte[] imageInByte = baos.toByteArray();
	            
	            //Test code
				/*
				 * ByteArrayInputStream bis = new ByteArrayInputStream(imageInByte);
				 * BufferedImage bImage2 = ImageIO.read(bis); ImageIO.write(bImage2, "jpg", new
				 * File("E:/TEST_IMG/output3.jpg") );
				 */
	            
	            
	            baos.close();
	            ret.put("CapString", captchaString);
	            ret.put("Captcha", imageInByte);
	            
	           // return bufferedImage;
	 } catch (Exception ioe) {
         throw new RuntimeException("Unable to build image", ioe);
     }
	 return ret;
	 }
}
