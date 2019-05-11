import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.lang.Thread;
import javax.imageio.ImageIO;
public class Map
{
    private static final int width = Block.getBlockWidth()*40;
    private static final int height = Block.getBlockHeight()*40;
    private Block[][] blocks;
    private int blockX;
    private int blockY;
    public Map()
    {
        initializeMap();
    }
    public void initializeMap()
    {
        blockX = width/Block.getBlockWidth();
        blockY = height/Block.getBlockHeight();
        blocks = new Block[blockY][blockX];//將地圖用方塊填滿
        for(int y=0;y<blockY;y++)
        {
            for(int x=0;x<blockX;x++)
            {
                if(x==0 || y==0 || x==blockX-1 || y==blockY-1)
                {
                    blocks[y][x] = new GrassLand(x*Block.getBlockWidth(),y*Block.getBlockHeight());
                }
                else
                {
                    blocks[y][x] = new Air(x*Block.getBlockWidth(),y*Block.getBlockHeight());
                }
            }
        }
        blocks[39][20] = new Air(20*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][21] = new Air(21*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][22] = new Air(22*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][3] = new Air(20*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][4] = new Air(21*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][5] = new Air(22*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][10] = new Air(20*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][11] = new Air(20*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][12] = new Air(20*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][21] = new Air(21*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][22] = new Air(22*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][30] = new Air(20*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][31] = new Air(21*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[39][32] = new Air(22*Block.getBlockWidth(),39*Block.getBlockHeight());
        blocks[20][17] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[20][14] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[20][13] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[30][10] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[30][11] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[30][12] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[30][25] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[30][26] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[30][27] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[25][30] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[25][31] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[25][32] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[18][36] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[18][37] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[18][38] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[12][30] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[12][31] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[12][32] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[10][25] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[10][26] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[10][27] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[25][3] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[25][4] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[25][5] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[10][8] = new GrassLand(17*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[10][9] = new GrassLand(14*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[10][10] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[11][10] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[11][11] = new GrassLand(13*Block.getBlockWidth(),20*Block.getBlockHeight());
        blocks[38][12] = new GrassLand(12*Block.getBlockWidth(),38*Block.getBlockHeight());
        blocks[38][13] = new GrassLand(13*Block.getBlockWidth(),38*Block.getBlockHeight());
        blocks[37][13] = new GrassLand(13*Block.getBlockWidth(),37*Block.getBlockHeight());
    }
    public void display(Graphics g)
    {
        //g.fillRect(int x, int y, int width, int height)
        //g.setColor(new Color(int r, int g, int b, int a));a代表透明度
        g.setColor(new Color(0,219,0,255));
        for(int y=0;y<blockY;y++)
        {
            for(int x=0;x<blockX;x++)
            {
                if(blocks[y][x] instanceof GrassLand)
                {
                    g.fillRect(x*Block.getBlockWidth(),y*Block.getBlockHeight(),Block.getBlockWidth(),Block.getBlockHeight());
                }
            }
        }
    }
    public int getWidth(){return width;}
    public int getHeight(){return height;}
    public Block[][] getBlock(){return blocks;}
}
