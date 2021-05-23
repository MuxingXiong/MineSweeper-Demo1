package minesweeper;

import components.GridComponent;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ImageIconJComponent extends JPanel {
    public ImageIconJComponent(String fileName,int x,int y,int width,int height){
        JLabel label = new JLabel("text");
        URL url = ImageIconDemo.class.getResource(fileName);

        ImageIcon imageIcon = new ImageIcon(url);
        label.setIcon(imageIcon);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        setVisible(true);
        setBounds(x,y,width,height);
    }

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("2021 CS102A Project Demo 2");
        window.setLayout(null);
        window.setSize(1000,1000);
        window.setLocationRelativeTo(null);
        String pictureMainFrame = "/pictureMainFrame.png";
        ImageIconJComponent imageIconJComponent =new ImageIconJComponent(pictureMainFrame,0,0,1000,1000);
        window.add(imageIconJComponent);
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}


