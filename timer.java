package components;

import javax.swing.*;


public class timer {
    int second = 30;

    Timer timer = new Timer(1000,this);
    JLabel label3 = new JLabel("用时："+second+"s");

    if(e.getSource() instanceof Timer){
        second--;
        label3.setText("用时："+second+"s");
        timer.start();
        return;
    }

}
