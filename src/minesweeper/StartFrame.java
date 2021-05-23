package minesweeper;

import components.GridComponent;

import javax.swing.*;

public class StartFrame extends JFrame {
    public StartFrame(){
        String pictureMainFrame = "/pictureMainFrame.png";
        ImageIconDemo imageIconDemo = new ImageIconDemo(pictureMainFrame,100,100,1000,1000);
    }

    public static void main(String[] args) {
        StartFrame startFrame = new StartFrame();
    }
}
