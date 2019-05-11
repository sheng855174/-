import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
public class View extends JFrame implements KeyListener
{
    private Game game;
    private JPanel jpanel;
    public View(Game game)
    {
        this.game = game;
        initialize();
    }
    public void initialize()
    {
        initializeJPanel();
        initializeJFrame();
    }
    public void initializeJFrame()
    {
        setTitle("G5火柴人戰爭");//標題名稱
        setSize(game.getMapWidth()+16,game.getMapHeight()+49);//視窗大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//退出方式
        setResizable(false);//設定視窗大小可變
        setLocationRelativeTo(this);//設定視窗初始位置在螢幕中心
        add(jpanel);
        addKeyListener(this);//加入監聽器
        this.requestFocusInWindow();
        setVisible(true);
    }
    public void initializeJPanel()
    {
        jpanel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                g.clearRect(0,0,game.getMapWidth()+16,game.getMapHeight()+49);
                game.displayScreen(g);
            }
        };
        jpanel.setBackground(null);
        jpanel.setLayout(new BorderLayout());
        jpanel.setSize(game.getMapWidth(),game.getMapHeight());
        jpanel.setVisible(true);
    }
    public void refresh()
    {
        jpanel.repaint();
    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) 
        {
            case KeyEvent.VK_UP:
                game.soldierJump();
                break;
            case KeyEvent.VK_RIGHT:
                game.soldierMoveRight();
                break;
            case KeyEvent.VK_LEFT:
                game.soldierMoveLeft();
                break;         
            case KeyEvent.VK_Z:
                game.soldierAttack();
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){}
}
