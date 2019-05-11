import java.awt.*;
public abstract class Block extends Rectangle
{
    private static final int width = 20;
    private static final int height = 10;
    public Block(int x,int y)
    {
        this.setRect(x, y,width,height);
        System.out.println(this);
    }
    public static int getBlockWidth(){return width;}
    public static int getBlockHeight(){return height;}
}
