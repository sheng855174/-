import java.awt.*;
import java.io.*;
import javax.imageio.*;
public class ImageSequence
{
    private String baseFileName;
    private String fileExt;
    private int numFiles;
    private Image[] imgs ;
    private int index = -1;
    public ImageSequence(String bfname, String fext, int n)  {
       imgs=new Image[n];
       numFiles = n;
       baseFileName =bfname;
       fileExt=fext;
       try {
           for (int i=0; i<numFiles; i++)
              imgs[i] = ImageIO.read(new File(bfname+i+"."+fext));  //load the digits
        } catch (Exception e) {e.printStackTrace();} 
    }
    public Image next(boolean cycle) {
        if (!cycle && index == numFiles-1) {
            index = -1;
            return null;
        }
        index = (index+1) % numFiles;
        return imgs[index];
    }
    public void reset () { index = -1;}
}
