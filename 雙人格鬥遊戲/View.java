import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
public class View extends JPanel implements KeyListener,Runnable
{
    private Controller controller;
    private Image backGroundImage;
    private TimePanel timePanel;
    private StatusPanel statusPanel;
    private Man player1;
    private Man player2;
    private int backGroundHeight;
    private int backGroundWidth;
    private JFrame jframe;
    private Thread me;
    private String nowStandby1;
    private String nowStandby2;
    public View()
    {
        this.controller = null;
    }
    public void setPanel()
    {
        String path = "img/";
        this.backGroundHeight = controller.getModel(). getBackGroundHeight();
        this.backGroundWidth = controller.getModel(). getBackGroundWidtht();
        try {
            this.setLayout(null);
            setPreferredSize(new Dimension(backGroundWidth, backGroundHeight));
            
            timePanel = new TimePanel();
            timePanel.setBounds((int)(backGroundWidth*0.48), 10, 70, 70);
            
            statusPanel = new StatusPanel();
            statusPanel.setBounds(10, 10, (int)(backGroundWidth*0.96), (int)(backGroundHeight*0.9));
            
            player1 = controller.getModel().getPlayer1();
            player1.setStatus("standbyRight");
            nowStandby1="standbyRight";
            player1.setBounds(player1.getX(),player1.getY(),player1.getWidth(),player1.getHeight());
        
            player2 = controller.getModel().getPlayer2();
            player2.setStatus("standbyLeft");
            nowStandby2="standbyLeft";
            player2.setX(1200);
            player2.setBounds(player2.getX(),player2.getY(),player2.getWidth(),player2.getHeight());
            backGroundImage = ImageIO.read(new File(path+"background0.jpg"));//讀取背景圖片
            add(timePanel);//加入Panel
            add(statusPanel);
            add(player1);
            add(player2);
        }
        catch (Exception ex) {
              System.out.println("No found ImgeView!!!!!");
        }
        jframe = new JFrame("G5_JAVA_GAME");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(backGroundWidth,backGroundHeight);
        jframe.setContentPane(this);
        jframe.validate();
        jframe.setVisible(true);
        addKeyListener(this);
        jframe.addWindowListener(new WindowAdapter()
        {
           public void windowActivated( WindowEvent e){ 
               View.this.requestFocusInWindow(); //it is necessary to keep the keyboard event can be accept
            } 
           public void windowOpened( WindowEvent e){ 
               View.this.requestFocusInWindow(); //it is necessary to keep the keyboard event can be accept
           } 
        });
        this.requestFocusInWindow();
        me = new Thread(this);
        me.start();
    }
    public void addFly(Fly fly)
    {
        fly.setBounds(fly.getX(),fly.getY(),fly.getWidth(),fly.getHeight());  
        add(fly);
    }
    public void run()
    {
        while(Thread.currentThread() == me)
        {
            try {
                me.sleep(100);
                display();
            } catch (InterruptedException e) {System.out.println("Role Eorror");}
        }
    }
    public void pause(){me = null;}
    public void display() 
    {
       this.setVisible(true);
       this.repaint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //display the image(src, x, y, width, height, color, observer_call_back)
        g.drawImage(backGroundImage,0,0,backGroundWidth,backGroundHeight,null,null); 
    }
    public void setController(Controller controller)
    {
        this.controller = controller;
    }
    public void refreshTime(int second)
    {
        timePanel.setUp(second);
    }
    public void keyTyped(KeyEvent e){}
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode()) 
        {
            case KeyEvent.VK_D:
                nowStandby1="standbyRight";
                player1.setStatus("moveRight");
                break;
            case KeyEvent.VK_A:
                nowStandby1="standbyLeft";
                player1.setStatus("moveLeft");
                break;
            case KeyEvent.VK_W:
                if(player1.getStatus().equals(nowStandby1) && (player1.getY()!=700)) {break;}
                player1.setStatus(nowStandby1+"jump");
                break;
            case KeyEvent.VK_J:
                player1.setStatus(nowStandby1+"attack");
                break;
            case KeyEvent.VK_RIGHT:
                nowStandby2="standbyRight";
                player2.setStatus("moveRight");
                break;
            case KeyEvent.VK_LEFT:
                nowStandby2="standbyLeft";
                player2.setStatus("moveLeft");
                break;
            case KeyEvent.VK_UP:
                if(player2.getStatus().equals(nowStandby1) && (player2.getY()!=700)) {break;}
                player2.setStatus(nowStandby2+"jump");
                break;
            case KeyEvent.VK_NUMPAD1 :
                player2.setStatus(nowStandby2+"attack");
                break;
        }
    }
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode()) 
        {
            case KeyEvent.VK_D:
                player1.setStatus(nowStandby1);
                break;
            case KeyEvent.VK_A:
                player1.setStatus(nowStandby1);
                break;
            case KeyEvent.VK_W:
                player1.setStatus(nowStandby1);
                break;
            case KeyEvent.VK_J:
                player1.setStatus(nowStandby1);
                break;
            case KeyEvent.VK_RIGHT:
                player2.setStatus(nowStandby2);
                break;
            case KeyEvent.VK_LEFT:
                player2.setStatus(nowStandby2);
                break;
            case KeyEvent.VK_UP:
                player2.setStatus(nowStandby2);
                break;
            case KeyEvent.VK_NUMPAD1:
                player2.setStatus(nowStandby2);
                break;
        }
    }
    public StatusPanel getStatusPanel()
    {
        return statusPanel;
    }
}
