import java.awt.*;
public abstract class Equipment extends Rectangle
{
    private int injure;
    public Equipment()
    {

    }
    public abstract void attack(int x,int y,int dir);
}
