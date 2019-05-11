import java.awt.*;
import java.util.*;
public class Model
{
    private Controller controller;
    private int second;
    private Man player1;
    private Man player2;
    private int backGroundHeight;
    private int backGroundWidth;
    private ArrayList<Fly> fly;
    private Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public Model(int second)
    {
        this.second = second;
        this.controller = null;
        backGroundHeight = (int)(screenSize.getHeight());
        backGroundWidth = (int)screenSize.getWidth();
        player1 = new Archer(this);
        player2 = new Magician(this);
        fly = new ArrayList<Fly>();
    }
    public int getSecond()
    {
        return second;
    }
    public void setSecond(int second)
    {
        this.second = second;
        controller.notifyTime(this);
    }
    public void setController(Controller controller)
    {
        this.controller = controller;
    }
    public Man getPlayer1(){return this.player1;}
    public Man getPlayer2(){return this.player2;}
    public int getBackGroundHeight(){return this.backGroundHeight;}
    public int getBackGroundWidtht(){return this.backGroundWidth;}
    public ArrayList<Fly> getFly(){return this.fly;}
    public void addFly(Fly fly){this.fly.add(fly);}
}
