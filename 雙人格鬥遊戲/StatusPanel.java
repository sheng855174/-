import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
public class StatusPanel extends JPanel
{
    Image magicImage;
    Image fullMagicImage;
    private int hp1;
    private int hp2;
    private int att1;
    private int att2;
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public StatusPanel()
    {
        String path = "img/";
        hp1=100;
        hp2=100;
        att1=0;
        att2=0;
        setLayout(new BorderLayout());
        this.setOpaque(false);
        setPreferredSize(new Dimension((int)(screenSize.getWidth()), (int)(screenSize.getHeight())));
        /*try {
            magicImage = ImageIO.read(new File(path+"magic.png"));
            fullMagicImage = ImageIO.read(new File(path+"full_magic.png"));
        }
        catch (Exception ex) {
              System.out.println("No found Image!!!!!");
        }*/
        this.setVisible(true);
        this.repaint();
    }
     void display() {
       try {            
            
       }
       catch (Exception e)
       {
              e.printStackTrace();
       }

    }
    public void paintComponent(Graphics g)
    {          
          Insets ins = getInsets();
          g.drawImage(magicImage,(int)(screenSize.getWidth()*0.01),(int)(screenSize.getHeight()*0.09),(int)(screenSize.getWidth()*0.01),(int)(screenSize.getHeight()*0.01),null,null);
          
          g.setColor(Color.GREEN);
          g.fillRect((int)(screenSize.getWidth()*0.01),(int)(screenSize.getHeight()*0.01), (int)(screenSize.getWidth()*0.43), (int)(screenSize.getHeight()*0.03)); 
          g.setColor(Color.GREEN);
          g.fillRect((int)(screenSize.getWidth()*0.56),(int)(screenSize.getHeight()*0.01),(int)(screenSize.getWidth()*0.43),(int)(screenSize.getHeight()*0.03));
          g.setColor(Color.RED);
          g.fillRect((int)(screenSize.getWidth()*0.44-screenSize.getWidth()*0.43*(100-hp1)/100),(int)(screenSize.getHeight()*0.01),(int)(screenSize.getWidth()*0.43*(100-hp1)/100),(int)(screenSize.getHeight()*0.03));          
          g.setColor(Color.RED);
          g.fillRect((int)(screenSize.getWidth()*0.56),(int)(screenSize.getHeight()*0.01),(int)(screenSize.getWidth()*0.43*(100-hp2)/100),(int)(screenSize.getHeight()*0.03));           
    }
    public void setHp1(int hp1)
    {
        this.hp1-=hp1;
    }
    public void setHp2(int hp2)
    {
        this.hp2-=hp2;
    }
    public int getHp1(){return hp1;}
    public int getHp2(){return hp2;}
}
