import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener {

    final int PANEL_WIDTH = 550;
    final int PANEL_HEIGHT = 550;
    Image enemy;
    Image backgroundImage;
    Timer timer;
    int l1=125,l2=125,m1=10,m2=10,xStart=PANEL_WIDTH/2,yStart=PANEL_HEIGHT/2,x1,x2,y1,y2;
    double teta1=Math.PI,teta2=Math.PI/2,a1_v,a2_v,a1_a,a2_a,g=0.4;
    int xVelocity = 3;
    int yVelocity = 1;
    int x = 0;
    int y = 0;


    MyPanel(){
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        enemy = new ImageIcon("src/img_1.png").getImage();
        timer = new Timer(10, this);
        timer.start();


    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        // g2D.drawImage(enemy, x, y, null);
        g2D.setPaint(Color.red);
        g2D.setStroke(new BasicStroke(4));
        g2D.drawLine(xStart,yStart,x1,y1);
        g2D.drawLine(x1,y1,x2,y2);
        g2D.drawOval(x1-5,y1-5,10,10);
        g2D.drawOval(x2-5,y2-5,10,10);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

       // if (x>+PANEL_WIDTH-enemy.getWidth(null) || x<0){
       //     xVelocity=xVelocity*-1;
       // }
       // x = x+xVelocity;
//
       // if (y>+PANEL_HEIGHT-enemy.getWidth(null) || y<0){
       //     yVelocity=yVelocity*-1;
       // }
       // y = y+yVelocity;
        x1 = (int)(xStart+l1*Math.sin(teta1));
        y1 = (int)(yStart+l1*Math.cos(teta1));
        x2 = (int)(x1+l2*Math.sin(teta2));
        y2 = (int)(y1+l2*Math.cos(teta2));

        double num1 = -g * (2 * m1 + m2) * Math.sin(teta1);
        double num2 = -m2 * g * Math.sin(teta1 - 2 * teta2);
        double num3 = -2 * Math.sin(teta1 - teta2) * m2;
        double num4 = a2_v * a2_v * l2 + a1_v * a1_v * l1 * Math.cos(teta1 - teta2);
        double den = l1 * (2 * m1 + m2 - m2 * Math.cos(2 * teta1 - 2 * teta2));
        a1_a = (num1 + num2 + num3 * num4) / den;

        num1 = 2 * Math.sin(teta1 - teta2);
        num2 = (a1_v * a1_v * l1 * (m1 + m2));
        num3 = g * (m1 + m2) * Math.cos(teta1);
        num4 = a2_v * a2_v * l2 * m2 * Math.cos(teta1 - teta2);
        den = l2 * (2 * m1 + m2 - m2 * Math.cos(2 * teta1 - 2 * teta2));
        a2_a = (num1 * (num2 + num3 + num4)) / den;

        a1_v += a1_a;
        a2_v += a2_a;
        teta1 += a1_v;
        teta2 += a2_v;

        repaint();


    }


}
