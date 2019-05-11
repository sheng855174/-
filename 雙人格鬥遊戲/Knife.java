import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
public class Knife extends Fly implements Runnable
{
    private ImgSequence fire_right;
    private ImgSequence fire_left;
    private ImgSequence nowImg;
    private Thread me;
    private String direction;
    public Knife(int x,int y,Controller controller,String direction)
    {
        super(x+10,y,controller);
        width = 300;
        height = 200;
        String path = "img/Archer/";
        fire_right = new ImgSequence();
        fire_left = new ImgSequence();
        try{
            fire_right.addImge(ImageIO.read(new File(path+"knife1.png")));
            fire_right.addImge(ImageIO.read(new File(path+"knife2.png")));            
            fire_right.addImge(ImageIO.read(new File(path+"knife3.png")));            
            fire_right.addImge(ImageIO.read(new File(path+"knife4.png")));            
            fire_left.addImge(ImageIO.read(new File(path+"knife1.png")));
            fire_left.addImge(ImageIO.read(new File(path+"knife2.png")));            
            fire_left.addImge(ImageIO.read(new File(path+"knife3.png")));            
            fire_left.addImge(ImageIO.read(new File(path+"knife4.png")));                       
        }
        catch (Exception ex) {
            System.out.println("No found Magician!!!!!");
        }
        if(direction.equals("右")) {nowImg = fire_right;}
        if(direction.equals("左")) {nowImg = fire_left;this.x-=30;}
        this.direction = direction;
        me = new Thread(this);
        this.setBackground(null);
        this.setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));
        me.start();
    }
    public void pause(){me=null;}
    public void run()
    {
        try {
            while(Thread.currentThread() == me)
            {
                if(direction.equals("右"))
                {
                    x+=1;
                    me.sleep(1);
                }
                if(direction.equals("左"))
                {
                    x-=1;
                    me.sleep(1);
                }
            }
        } catch (InterruptedException e) {System.out.println("Eorror");}
    }
    public void display()
    {
        this.setVisible(true);
        this.repaint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(nowImg.nextImge(),0,0,width,height,null,null); 
    }
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public int getX(){return x;}
    public int getY(){return y;}
}
