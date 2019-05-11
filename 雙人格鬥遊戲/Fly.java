import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
public abstract class Fly extends JPanel
{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Counter counter;
    public Fly(int x,int y,Controller controller)
    {
        this.x = x;
        this.y = y;
        this.counter = counter;
    }
    public abstract int getHeight();
    public abstract int getWidth();
    public abstract int getX();
    public abstract int getY();
}
