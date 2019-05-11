import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
public abstract class Man extends JPanel
{
   public abstract void setX(int x);
   public abstract void setY(int y);
   public abstract void setStatus(String status);
   public abstract String getStatus();
   public abstract void setController(Controller controller);
   public abstract void pause();
}