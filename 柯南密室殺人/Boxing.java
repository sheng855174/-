import java.awt.*;
public class Boxing extends Equipment
{
    private static final int width = 20;
    private static final int height = 20;
    public Boxing()
    {
        
    }
    public void attack(int x ,int y,int dir)
    {
        if(dir == 1)
        {
            this.setRect(x-20,y+20,width,height);
        }
        else if(dir == 2)
        {
            this.setRect(x+40,y+20,width,height);
        }
    }
}
