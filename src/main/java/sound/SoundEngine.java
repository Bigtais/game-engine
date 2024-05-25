package sound;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The sound engine handles everything related to audio and sounds.
 * Its purpose is to play (on loop or not), pause and stop sounds,
 * but also handling the volume and playback speed of a sound.
 */
public class SoundEngine {

    private final JFXPanel jfxPanel = new JFXPanel();
    private int counterIndex = 0;
    private final Map<Integer, MediaPlayer> indexMediaPlayerHashMap = new ConcurrentHashMap<>();
    private final Map<Integer, Sound> indexSoundMap = new ConcurrentHashMap<>();

    /**
     * Creates a new instance of this engine. What it essentially does is initiate a JavaFX toolkit for use later on.
     */
    public SoundEngine(){
//        new Thread(() -> {
//        }).start();
    }

    /**
     * Adds the sound instance to the instances of sound playing.
     * @param sound The sound currently playing.
     * @param player The MediaPlayer object currently playing the sound.
     * @return The index of the instance of the playing sound.
     */
    private int addSoundInstance(Sound sound, MediaPlayer player){
        int index = counterIndex;
        indexMediaPlayerHashMap.put(counterIndex, player);
        indexSoundMap.put(index, sound);

        Runnable runnable = () -> {
            indexMediaPlayerHashMap.remove(counterIndex);
            indexSoundMap.remove(index);
        };
        player.setOnError(runnable);
        player.setOnEndOfMedia(runnable);

        counterIndex = (counterIndex + 1) % (Integer.MAX_VALUE - 1);
        return index;
    }

    /**
     * Plays a sound.
     * Each call of this method creates a new instance of this sound to play.
     * Thus, calling this method multiple times on the same sound object makes the sound play multiple times by
     * overlapping each other.
     * @param sound The sound to play.
     * @return An index value of the sound playing.
     */
    public int playSound(Sound sound){
//        Media media = new Media(new File(sound.getSource()).toURI().toString());
        try {
            Media media = new Media(getClass().getResource(sound.getSource()).toURI().toString());
            MediaPlayer player = new MediaPlayer(media);
            player.setVolume(sound.getVolume());
            player.setRate(sound.getSpeed());
            player.play();

            synchronized (this) {
                return addSoundInstance(sound, player);
            }
        }
        catch (URISyntaxException e){
            return -1;
        }
    }

    /**
     * Plays a sound with the requested playback speed and volume.
     * Each call of this method creates a new instance of this sound to play.
     * Thus, calling this method multiple times on the same sound object makes the sound play multiple times by
     * overlapping each other.
     * The playback speed and volume parameters do not change the default attributes of the original sound object.
     * @param sound The sound to play.
     * @return An index value of the sound playing.
     */
    public int playSound(Sound sound, float volume, float speed){
        Media media = new Media(new File(sound.getSource()).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setVolume(volume);
        player.setRate(speed);
        player.play();

        synchronized (this) {
            return addSoundInstance(sound, player);
        }
    }

    /**
     * Plays a sound endlessly (in a loop).
     * Each call of this method creates a new instance of this sound to play.
     * Thus, calling this method multiple times on the same sound object makes the sound play multiple times by
     * overlapping each other.
     * @param sound The sound to play.
     * @return An index value of the sound playing.
     */
    public int playSoundLoop(Sound sound){
        Media media = new Media(new File(sound.getSource()).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setVolume(sound.getVolume());
        player.setRate(sound.getSpeed());
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();

        synchronized (this) {
            return addSoundInstance(sound, player);
        }
    }

    /**
     * Plays a sound endlessly (in a loop).
     * Each call of this method creates a new instance of this sound to play.
     * Thus, calling this method multiple times on the same sound object makes the sound play multiple times by
     * overlapping each other.
     * The playback speed and volume parameters do not change the default attributes of the original sound object.
     * @param sound The sound to play.
     * @return An index value of the sound playing.
     */
    public int playSoundLoop(Sound sound, float volume, float speed){
        Media media = new Media(new File(sound.getSource()).toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
        player.setVolume(volume);
        player.setRate(speed);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();

        synchronized (this) {
            return addSoundInstance(sound, player);
        }
    }

    /**
     * Stops playing all instances of this sound.
     * @param sound The instance of the sound to stop.
     */
    public synchronized void stopSound(Sound sound){
        indexSoundMap.forEach((key, value) -> {
            if (value.equals(sound)) {
                indexSoundMap.remove(key);
                stopSound(key);
            }
        });
    }

    /**
     * Stops playing the instance of the sound related to the given index.
     * @param index The index of the sound to stop.
     */
    public void stopSound(int index){
        indexSoundMap.remove(index);
        MediaPlayer player = indexMediaPlayerHashMap.remove(index);
        if (player != null) {
            player.dispose();
        }
    }

    /**
     * Pauses the playback of the sound related to the given index.
     * This method does nothing if the sound was never played before.
     * @param index The index of the sound to stop.
     */
    public void pauseSound(int index){
        MediaPlayer player = indexMediaPlayerHashMap.get(index);
        if (player != null)
            player.pause();
    }

    /**
     * Resumes the playback of a previously paused sound related to the given index.
     * This method does nothing if the sound was never played before.
     * @param index The index of the sound to stop.
     */
    public void resumeSound(int index){
        MediaPlayer player = indexMediaPlayerHashMap.get(index);
        if (player != null)
            player.play();
    }

    /**
     * Sets the volume of the playing sound related to the given index.
     * This does not change the default volume of the original sound object.
     * @param index The index of the sound to change the volume.
     * @param volume The new volume value.
     */
    public void setSoundVolume(int index, float volume){
        MediaPlayer player = indexMediaPlayerHashMap.get(index);
        if (player != null)
            player.setVolume(volume);
    }

    /**
     * Sets the playback speed of the playing sound related to the given index.
     * This does not change the default playback speed of the original sound object.
     * @param index The index of the sound to change the volume.
     * @param speed The new playback speed value.
     */
    public void setPlaybackSpeed(int index, float speed){
        MediaPlayer player = indexMediaPlayerHashMap.get(index);
        if (player != null)
            player.setRate(speed);
    }
}
