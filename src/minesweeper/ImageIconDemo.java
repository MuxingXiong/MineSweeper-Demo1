package minesweeper;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageIconDemo extends JFrame {
    public ImageIconDemo(String fileName,int x,int y,int width,int height){
        JLabel label = new JLabel("text");
        URL url = ImageIconDemo.class.getResource(fileName);

        ImageIcon imageIcon = new ImageIcon(url);
        label.setIcon(imageIcon);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        Container container = getContentPane();
        container.add(label);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(x,y,width,height);
    }

    public static void main(String[] args) {
        String pictureMainFrame = "pictureMainFrame.png";
        new ImageIconDemo(pictureMainFrame,100,100,1000,1000);
    }
}
