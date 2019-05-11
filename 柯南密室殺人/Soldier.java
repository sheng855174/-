import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
import javax.imageio.ImageIO;
import java.util.*;
public abstract class Soldier extends Rectangle implements IGravitation, IMove
{
    protected static final int width = 40;
    protected static final int height = 50;
    protected Image soldierImage;
    protected Image[] optionalImage;//操作中的圖片
    protected int dir;//方向，1代表左邊，2代表右邊
    protected int camp;//陣營
    protected String status;//紀錄角色狀態
    protected Equipment equipment;//一個角色有一個裝備
    protected int hp = 10;
    protected int stepCount = 30;//角色的步數限制
    protected int pic=0;
    public Soldier(int x,int y,int dir,int camp,Equipment equipment)
    {
        this.setRect(x, y,width,height);
        this.dir = dir;
        this.camp = camp;
        this.status = "跳躍";//一開始設定從天而降
        this.equipment = equipment;
        optionalImage=new Image[6];
        pic=0;
        System.out.println(this);
    }
    public abstract void display(Graphics g);
    public void displayOptional(Graphics g)
    {
        pic=pic%3+(dir-1)*3;
        g.drawImage(optionalImage[pic],x,y,null);
        if(status.equals("攻擊"))
        {
            g.setColor(new Color(255,0,0,255));
            g.fillRect((int)equipment.getX(),(int)equipment.getY(),(int)equipment.getWidth(),(int)equipment.getHeight());
        }
    }
    @Override
    public void gravity()
    {
        this.setLocation((int)this.getX(),(int)this.getY()+g);
        status = "跳躍";
        System.out.println(this);
    }
    @Override
    public void moveRight()
    {
        this.setLocation((int)(this.getX()+step),(int)this.getY());
        dir = 2;
        stepCount--;
        System.out.println(this);
    }
    @Override
    public void moveLeft()
    {
        this.setLocation((int)(this.getX()-step),(int)this.getY());
        dir = 1;
        stepCount--;
        System.out.println(this);
    }
    @Override
    public void jump()
    {
        this.setLocation((int)this.getX(),(int)this.getY()-10);
        System.out.println(this);
    }
    public void death()
    {
        this.setBounds(0, 0, 0, 0);
        status = "死亡";
        System.out.println(this);
    }
    public void attack()
    {
        equipment.attack(x,y,dir);
        System.out.println(this);
    }
    public int injured()
    {
        status = "受傷";
        hp--;
        System.out.println(this);
        return (int)(Math.random()*20);//隨機亂數0~20
    }
    public void repelRight()
    {
        this.setLocation((int)(this.getX()+step),(int)this.getY());
        System.out.println(this);
    }
    public void repelLift()
    {
        this.setLocation((int)(this.getX()-step),(int)this.getY());
        System.out.println(this);
    }
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;};
    public Equipment getEquipment(){return equipment;}
    public int getDir(){return dir;}
    public int getStepCount(){return stepCount;}
    public int getHp(){return hp;}
    public int getCamp(){return camp;}
    public void setStepCount(int stepCount){this.stepCount = stepCount;}
    @Override
    public String toString()
    {
        return "Soldier[width="+width+",hieght="+height+",x="+x+",y="+y+",status="+status+",dir="+dir+"\n,camp="+camp+
        ",hp=" + hp + ",stepCount=" + stepCount + "]";
    }
}
