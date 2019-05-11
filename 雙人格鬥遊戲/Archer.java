import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
import java.util.*;
public class Archer extends Man implements Runnable
{
    private ImgSequence standbyRight;
    private ImgSequence standbyLeft;    
    private ImgSequence moveRight;
    private ImgSequence moveLeft;
    private ImgSequence attackRight;
    private ImgSequence attackLeft;
    private ImgSequence jumpRight;
    private ImgSequence fallRight;
    private ImgSequence jumpLeft;
    private ImgSequence fallLeft;
    private ImgSequence nowImg;
    private String path;
    private String status;
    private int height;
    private int width;
    private int x;
    private int y;
    private Thread me;
    private Model model;
    private Controller controller;
    public Archer(Model model)
    {
        path = "img/Archer/";
        status = "standbyRight";
        standbyRight = new ImgSequence();
        standbyLeft = new ImgSequence();        
        moveRight = new ImgSequence();
        moveLeft = new ImgSequence();
        attackRight = new ImgSequence();
        attackLeft = new ImgSequence();
        jumpRight = new ImgSequence();
        jumpLeft = new ImgSequence();
        fallRight = new ImgSequence();
        fallLeft = new ImgSequence();
        height = 300;
        width = 300;
        x = 200;
        y = 700;
        this.model = model;
        this.setBackground(null);
        this.setOpaque(false);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(width, height));
        try{
            standbyRight.addImge(ImageIO.read(new File(path+"standby1_right.png")));
            standbyLeft.addImge(ImageIO.read(new File(path+"standby1_left.png")));
            moveRight.addImge(ImageIO.read(new File(path+"walk1_right.png")));
            moveRight.addImge(ImageIO.read(new File(path+"walk1_right.png")));
            moveRight.addImge(ImageIO.read(new File(path+"walk2_right.png")));
            moveRight.addImge(ImageIO.read(new File(path+"walk2_right.png")));
            moveLeft.addImge(ImageIO.read(new File(path+"walk1_left.png")));
            moveLeft.addImge(ImageIO.read(new File(path+"walk1_left.png")));
            moveLeft.addImge(ImageIO.read(new File(path+"walk2_left.png")));
            moveLeft.addImge(ImageIO.read(new File(path+"walk2_left.png")));
            attackRight.addImge(ImageIO.read(new File(path+"attack1_right.png")));
            attackRight.addImge(ImageIO.read(new File(path+"attack2_right.png")));
            attackRight.addImge(ImageIO.read(new File(path+"attack2_right.png")));
            attackRight.addImge(ImageIO.read(new File(path+"attack3_right.png")));
            attackRight.addImge(ImageIO.read(new File(path+"attack3_right.png")));
            attackLeft.addImge(ImageIO.read(new File(path+"attack1_left.png")));
            attackLeft.addImge(ImageIO.read(new File(path+"attack2_left.png")));
            attackLeft.addImge(ImageIO.read(new File(path+"attack2_left.png")));
            attackLeft.addImge(ImageIO.read(new File(path+"attack3_left.png")));
            attackLeft.addImge(ImageIO.read(new File(path+"attack3_left.png")));
            jumpRight.addImge(ImageIO.read(new File(path+"jump1_right.png")));
            jumpLeft.addImge(ImageIO.read(new File(path+"jump1_left.png")));
            fallRight.addImge(ImageIO.read(new File(path+"fall1_right.png")));
            fallLeft.addImge(ImageIO.read(new File(path+"fall1_left.png")));
        }
        catch (Exception ex) {
            System.out.println("No found Magician!!!!!");
        }
        nowImg = standbyRight;
        me = new Thread(this);
        me.start();
    }
    public void pause(){me=null;}
    public void run()
    {
        while(Thread.currentThread() == me)
        {
            try {
                Fly k;
                switch(status)
                {
                    case "moveRight":
                        x+=10;
                        me.sleep(100);
                        if(y!=700){y+=50;break;}
                        nowImg = moveRight;
                        break;
                    case "moveLeft":
                        x-=10;
                        me.sleep(100);
                        if(y!=700){y+=50;break;}
                        nowImg = moveLeft;
                        break;
                    case "standbyRightattack":
                        if(y!=700){break;}
                        nowImg = attackRight;
                        k = new Knife(x+5,y,controller,"右");
                        this.model.addFly(k);
                        controller.getView().addFly(k);
                        me.sleep(1000);
                        break;
                    case "standbyLeftattack":
                        if(y!=700){break;}
                        nowImg = attackLeft;
                        k = new Knife(x-5,y,controller,"左");
                        this.model.addFly(k);
                        controller.getView().addFly(k);
                        me.sleep(1000);
                        break;
                    case "standbyRightjump":
                        me.sleep(100);
                        if(y<=700)
                        {
                            y-=50;
                            nowImg = jumpRight;
                        }
                        break;    
                    case "standbyLeftjump":
                        me.sleep(100);
                        if(y<=700)
                        {
                            y-=50;
                            nowImg = jumpLeft;
                        }
                        break;    
                    case "standbyRight": 
                        nowImg = standbyRight;
                        if(y<700)
                        { 
                            y+=50;
                            nowImg = fallRight;
                        }
                        me.sleep(100);
                        break;
                    case "standbyLeft": 
                        nowImg = standbyLeft;
                        if(y<700)
                        { 
                            y+=50;
                            nowImg = fallLeft;
                        }
                        me.sleep(100);
                        break;
                    default: 
                }
                if(this.x < 0){this.x = 0;}
                if(this.x > (int)(model.getBackGroundWidtht()*0.8)){this.x = (int)(model.getBackGroundWidtht()*0.8);}
                if(this.y <0){this.y = 0;}
                display();
            } catch (InterruptedException e) {System.out.println("Role Eorror");}
        }
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
    public String getStatus(){return this.status;}
    public void setHeight(int height){this.height = height;}
    public void setWidth(int width){this.width = width;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public void setStatus(String status)
    {
        this.status = status;
    }
    public void setController(Controller controller){this.controller = controller;}
}
