import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
import javax.imageio.ImageIO;
import java.util.*;
public class Boy extends Soldier
{
    public Boy(int x,int y,int dir,int camp,Equipment equipment)
    {
        super(x,y,dir,camp,equipment);
        try {
            soldierImage = ImageIO.read(new File("img/soldierRight.png"));
            optionalImage[0] = ImageIO.read(new File("img/optionalSoldierLeft.png"));
            optionalImage[1] = ImageIO.read(new File("img/optionalSoldierLeft1.png"));
            optionalImage[2] = ImageIO.read(new File("img/optionalSoldierLeft2.png"));
            optionalImage[3] = ImageIO.read(new File("img/optionalSoldierRight.png"));
            optionalImage[4] = ImageIO.read(new File("img/optionalSoldierRight1.png"));
            optionalImage[5] = ImageIO.read(new File("img/optionalSoldierRight2.png"));
        } catch (IOException ex) {ex.printStackTrace();}
    }
    public void display(Graphics g)
    {
        g.drawImage(soldierImage,x,y,null);
    }
}
