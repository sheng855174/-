import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.sound.sampled.*;
public class Game implements Runnable
{
    private View view;
    private Map map;
    private ArrayList<Soldier> soldierList;
    private int optionalSoldiers;//正在操作的士兵
    private Thread myThread;
    private Thread jumpThread;
    private Thread attackThread;
    private int soldierCount;//士兵數量
    private SoldierFactory soldierFactory;
    private boolean gameStart = false;
    private Clip player;//for background audio
    public Game()
    {
        initialize();
    }
    public void initialize()
    {
        soldierFactory = new SoldierFactory();//工廠產生
        produceSoldier();//產生士兵
        optionalSoldiers = 0;//操作中的士兵
        map = new Map();//產生地圖
        view = new View(this);//產生View
        produceThread();//產生Thread
    }
    public void displayScreen(Graphics game)
    {
        try{
            map.display(game);
            for(int i=0;i<soldierList.size();i++)
            {
                if(i != optionalSoldiers)
                {
                    soldierList.get(i).display(game);
                }
            }
            if(soldierList.get(optionalSoldiers)!=null)
            {
                soldierList.get(optionalSoldiers).displayOptional(game);
            }
        }catch(Exception e){}
    }
    @Override
    public void run()
    {
        startGame();
        while(Thread.currentThread() == myThread)
        {    
            soldierCheck();
            instruction();
            for(int i=0;i<soldierList.size();i++)
            {
                Soldier soldier = soldierList.get(i);
                if(soldierBottom_check(i) == false)
                {
                    soldier.gravity();
                }
            }
            view.refresh();//刷新畫面
            if(checkEnd()){end();System.out.println("Game Over!");}
            try{new Thread().sleep(100);}catch(InterruptedException e){}//遊戲速度，畫面保留100ms}
        }
    }
    public void produceSoldier()
    {
        soldierList = new ArrayList<Soldier>();
        soldierList.add(soldierFactory.produceSoldier(50,0,2,1,"男拳頭士兵"));
        soldierList.add(soldierFactory.produceSoldier(450,0,1,2,"女拳頭士兵"));
        soldierList.add(soldierFactory.produceSoldier(150,0,2,1,"男拳頭士兵"));
        soldierList.add(soldierFactory.produceSoldier(600,0,1,2,"女拳頭士兵"));
    }
    public void produceThread()
    {
        this.myThread = new Thread(this);
        this.jumpThread = null;
        this.attackThread = null;
        myThread.setPriority(Thread.MAX_PRIORITY);
        myThread.start();
    }
    public void soldierCheck()
    {
        optionalSoldiers = optionalSoldiers%soldierList.size();
        if(soldierList.get(optionalSoldiers).getStepCount() <= 0)
        {
            nextOptionalSoldiers();
        }
        for(int i=0;i<soldierList.size();i++)
        {
            if(soldierList.get(i).getHp() == 0)
            {
                death(i);
            }
        }
    }
    public void nextOptionalSoldiers()
    {
        optionalSoldiers = (optionalSoldiers+1)%soldierList.size();
        soldierList.get(optionalSoldiers).setStepCount(30);
    }
    public boolean soldierBottom_check(int index)
    {
        Block[][] blocks = map.getBlock();
        int x = (int)soldierList.get(index).getX();
        int y = (int)soldierList.get(index).getY();
        y = (int)((y+soldierList.get(index).getHeight())/Block.getBlockHeight());
        x = ((x)/Block.getBlockWidth());
        if(y>(blocks.length-1))//死亡
        {
            death(index);
            return true;
        }
        else if(blocks[y][x] instanceof GrassLand || blocks[y][x+1] instanceof GrassLand
        || blocks[y][(((int)soldierList.get(index).getX()-1)/Block.getBlockWidth())+2] instanceof GrassLand)
        {
            soldierList.get(index).setStatus("站立");
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean soldierRighTop_check(int index)
    {
        Block[][] blocks = map.getBlock();
        int x = (int)soldierList.get(index).getX();
        int y = (int)soldierList.get(index).getY();
        y = (y/Block.getBlockHeight())-1;
        x = (x/Block.getBlockWidth());
        if(blocks[y][x] instanceof GrassLand || blocks[y][x+1] instanceof GrassLand)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean soldierLeft_check(int index)
    {
        Block[][] blocks = map.getBlock();
        int x = (int)soldierList.get(index).getX();
        int y = (int)soldierList.get(index).getY();
        y = (y/Block.getBlockHeight());
        x = ((x-1)/Block.getBlockWidth());
        boolean check = false;
        for(int i=0;i<5;i++)
        {
            if(blocks[y+i][x] instanceof GrassLand)
            {
                check =true;
            }
        }
        return check;
    }
    public boolean soldierRight_check(int index)
    {
        Block[][] blocks = map.getBlock();
        int x = (int)soldierList.get(index).getX();
        int y = (int)soldierList.get(index).getY();
        y = (y/Block.getBlockHeight());
        x = ((x+(int)soldierList.get(index).getWidth())/Block.getBlockWidth());
        boolean check = false;
        for(int i=0;i<5;i++)
        {
            if(blocks[y+i][x] instanceof GrassLand)
            {
                check =true;
            }
        }
        return check;
    }
    public int soldierAttack_check()
    {
        Rectangle rectangle = soldierList.get(optionalSoldiers).getEquipment();
        for(int i=0;i<soldierList.size();i++)
        {
            if(rectangle.intersects(soldierList.get(i)))
            {
                return i;
            }
        }
        return -1;
    }
    public void soldierMoveLeft()
    {
        soldierCheck();
        String status = soldierList.get(optionalSoldiers).getStatus();
        if(soldierLeft_check(optionalSoldiers) == false
        && (status.equals("站立") || status.equals("跳躍")))
        {
            soldierList.get(optionalSoldiers).moveLeft();
        }
    }
    public void soldierMoveRight()
    {
        soldierCheck();
        String status = soldierList.get(optionalSoldiers).getStatus();
        if(soldierRight_check(optionalSoldiers) == false
        && (status.equals("站立") || status.equals("跳躍")))
        {
            soldierList.get(optionalSoldiers).moveRight();
        }
    }
    public void soldierJump()
    {
        if(soldierList.get(optionalSoldiers).getStatus().equals("站立"))
        {
            soldierList.get(optionalSoldiers).setStatus("跳躍");
            jumpThread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    for(int i=0;i<10;i++)
                    {
                        if(soldierRighTop_check(optionalSoldiers) == false)
                        {
                            soldierList.get(optionalSoldiers).jump();
                        }
                        view.refresh();
                        try{new Thread().sleep(100);}catch(InterruptedException e){}
                    }
                }
            });
        }
    }
    public void soldierAttack()
    {
        if(soldierList.get(optionalSoldiers).getStatus().equals("站立"))
        {
            soldierList.get(optionalSoldiers).setStatus("攻擊");
            attackThread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    soldierList.get(optionalSoldiers).attack();
                    view.refresh();
                    int index = soldierAttack_check();
                    if(index != -1)
                    {
                        int random = soldierList.get(index).injured();
                        for(int i=0;i<random;i++)
                        {
                            if(soldierList.get(optionalSoldiers).getDir() == 2 && !(soldierRight_check(index)))
                            {
                                soldierList.get(index).repelRight();
                            }
                            else if(soldierList.get(optionalSoldiers).getDir() == 1 && !(soldierLeft_check(index)))
                            {
                                soldierList.get(index).repelLift();
                            }
                        }
                    }
                    try{new Thread().sleep(200);}catch(InterruptedException e){}
                    soldierList.get(optionalSoldiers).setStatus("站立");
                }
            });
        }
    }
    public void death(int index)
    {
        try{new Thread().sleep(200);}catch(InterruptedException e){}
        soldierList.get(index).death();
        soldierList.remove(index);
    }
    public void instruction()
    {
        if(jumpThread!=null)//跳躍動畫觸發
        {
            jumpThread.start();
            try{jumpThread.join();}catch(InterruptedException e){System.out.println("jumpThread error");}
            jumpThread = null;
        }
        else if(attackThread!=null)
        {
            attackThread.start();
            try{attackThread.join();}catch(InterruptedException e){System.out.println("attackThread error");}
            attackThread = null;
            nextOptionalSoldiers();
        }
    }
    public int getMapWidth(){return map.getWidth();}
    public int getMapHeight(){return map.getHeight();}
    public void startGame() {
       gameStart = true; 
       String fn = "BGMUSIC/QQ";//<-- for background music
       player =   SoundManager.getPlayer(fn+".wav");
       player.loop(-1);//to play in a loop manner
    } 
    public boolean checkEnd()
    {
        int number = soldierList.get(0).getCamp();
        for(int i=0;i<soldierList.size();i++)
        {
            if(soldierList.get(i).getCamp()!=number)
            {
                return false;//繼續遊戲
            }
        }
        return true;//結束遊戲
    }
    public void end()
    {
        myThread=null;
        setWarningMsg("開始遊戲吧!");
    }
    public void setWarningMsg(String text)
    {
        Toolkit.getDefaultToolkit().beep();
        JOptionPane optionPane = new JOptionPane(text,JOptionPane.WARNING_MESSAGE);
        JDialog dialog = optionPane.createDialog("Warning!");
        dialog.setAlwaysOnTop(true);
        JFrame f;//顯示開始繼續遊戲ㄎㄎ
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        f=new JFrame("JOptionPane Test");
        f.setSize(400,300);
        f.setLocationRelativeTo(null);
        Container cp=f.getContentPane();
        cp.setLayout(null);
        f.setVisible(false);
        int mType=JOptionPane.QUESTION_MESSAGE;
        int oType=JOptionPane.YES_NO_CANCEL_OPTION;
        String[] options={"繼續","關閉遊戲"};
        int opt=JOptionPane.showOptionDialog(f,"要繼續遊戲嗎?",
          "請選擇",oType,mType,null,options,"接受");
        if (opt==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(f,"您選擇的是 : 繼續 ");
            soldierList=null;
            view.dispose();
            initialize();
          }
        if (opt==JOptionPane.NO_OPTION){
          JOptionPane.showMessageDialog(f,"您選擇的是 : 關閉遊戲 ");
          System.exit(0);
          } 
    }
}
