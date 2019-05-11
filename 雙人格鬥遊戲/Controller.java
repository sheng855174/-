import java.awt.*;
import javax.swing.*;
import java.applet.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.*;
public class Controller extends Thread
{
    private Model model;
    private View viewer;
    private Counter counter;    
    private Clip player;//for background audio
    private Thread me;
    private boolean gameStart = false;
    public Controller(Model model,View viewer)
    {
        this.model = model;
        this.viewer = viewer;
        model.setController(this);
        viewer.setController(this);
        viewer.setPanel();
        counter = new Counter(this);
        me = this;
        model.getPlayer1().setController(this);
        model.getPlayer2().setController(this);
        counter.start();
        me.start();
    }
    public void pause(){me=null;}
    public Model getModel()
    {
        return model;
    }
    public View getView()
    {
        return viewer;
    }
    public void notifyTime(Model model)
    {
        viewer.refreshTime(model.getSecond());
    }
    public void run()
    {
        startGame();
        while(Thread.currentThread() == me)
        {
            if(viewer.getStatusPanel().getHp1()<=0 || viewer.getStatusPanel().getHp2()<=0) 
            {
                counter.pause();
                System.out.println("遊戲結束");
            }
            if(model.getFly().isEmpty() == false)
            {
                int size = model.getFly().size();
                for(int i=0;i<size;i++)
                {
                    if(model.getFly().get(i) == null)
                    {
                        System.out.println(i);
                        System.out.println(model.getFly().size());
                    }
                    int x1 = model.getFly().get(i).getX();
                    int x2 = model.getPlayer1().getX();
                    int x3 = model.getPlayer2().getX();
                    int y1 = model.getFly().get(i).getY();
                    int y2 = model.getPlayer1().getY();
                    int y3 = model.getPlayer2().getY();
                    if(x1 == x2 && (y1-200)<=y2)
                    {
                        model.getFly().get(i).setVisible(false);
                        model.getFly().remove(i);
                        viewer.getStatusPanel().setHp1(5);
                        break;
                    }
                    if(x1 == x3 && (y1-200)<=y3)
                    {  
                        model.getFly().get(i).setVisible(false);
                        model.getFly().remove(i);
                        viewer.getStatusPanel().setHp2(5);
                        break;
                    }
                }
            }
        }
    }
    public void startGame() {
       gameStart = true; 
       String fn = "sounds/bgMusic";//<-- for background music
       player =   SoundManager.getPlayer(fn+".wav");
       player.loop(-1);//to play in a loop manner
    } 
}
