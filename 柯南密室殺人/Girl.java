import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
import javax.imageio.ImageIO;
import java.util.*;
public class Girl extends Soldier
{
    public Girl(int x,int y,int dir,int camp,Equipment equipment)
    {
        super(x,y,dir,camp,equipment);
        try {
            soldierImage = ImageIO.read(new File("img/soldierLeftGG.png"));
            optionalImage[0] = ImageIO.read(new File("img/optionalSoldierLeftGG.png"));
            optionalImage[1] = ImageIO.read(new File("img/optionalSoldierLeft1GG.png"));
            optionalImage[2] = ImageIO.read(new File("img/optionalSoldierLeft2GG.png"));
            optionalImage[3] = ImageIO.read(new File("img/optionalSoldierRightGG.png"));
            optionalImage[4] = ImageIO.read(new File("img/optionalSoldierRight1GG.png"));
            optionalImage[5] = ImageIO.read(new File("img/optionalSoldierRight2GG.png"));
        } catch (IOException ex) {ex.printStackTrace();}
    }
    public void display(Graphics g)
    {
        g.drawImage(soldierImage,x,y,null);
    }
}
