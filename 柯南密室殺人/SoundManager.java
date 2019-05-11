import javax.sound.sampled.*;
import java.io.*;

/**
 * Write a description of class SoundManager here.
 * 
 * @author (FHWANG) 
 * @version (a version 1 or a 2015-05-19)
 */
public class SoundManager { //Fro MP3 etc.
    private static SoundManager instance= null;
    private static Clip player;
    private static  AudioInputStream sound;
    private SoundManager(String fn) { 
     try {
        File file = new File(fn);
        sound = AudioSystem.getAudioInputStream(file);
        //into clip
        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        //get the Clip player
        player = (Clip)AudioSystem.getLine(info);
        player.open(sound);
      } catch (Exception e) {e.printStackTrace();player=null; sound=null;}
    }
    
    public static Clip getPlayer(String fn) {//just call SoundManager.getPlayer(fn)
        if (instance==null)
            instance = new SoundManager(fn);
        if ( (instance.player!=null) && (instance.sound!=null) ) {
           return player;
        }
        else
          return null;
     
    }
    
}
