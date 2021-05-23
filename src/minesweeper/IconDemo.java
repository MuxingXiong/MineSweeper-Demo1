package minesweeper;

import javax.swing.*;
import java.awt.*;

public class IconDemo extends JFrame implements Icon {

    private int width;
    private int height;

    public IconDemo(){}

    public IconDemo(int width,int height){
        this.width = width;
        this.height = height;
    }

    public void init(int width,int height){
        IconDemo iconDemo = new IconDemo(width,height);
        JLabel label = new JLabel(iconDemo,SwingConstants.CENTER);

        Container container = getContentPane();
        container.add(label);

        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.fillOval(x,y,width,height);
    }

    @Override
    public int getIconWidth() {
        return this.width;
    }

    @Override
    public int getIconHeight() {
        return this.height;
    }
}
