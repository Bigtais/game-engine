package sound;

/**
 * Base class defining a sound in the game.
 * The file format supported for the sounds are defined in the JavaFX javadoc.
 */
public class Sound {

    private String source;
    private float volume = 0.5f;
    private float speed = 1f;

    /**
     * Creates an instance of a sound from the source string with the others attributes taking the default values.
     * (0.5f for volume, 1f for normal playback speed and looping set to false)
     * @param source The source string.
     */
    public Sound(String source) {
        this.source = source;
    }

    /**
     * Creates an instance of a sound from the source string and the volume requested with the others attributes
     * taking the default values.
     * (1f for normal playback speed and looping set to false)
     * @param source The source string.
     * @param volume The volume of this sound. Must be a value between 0f and 1f
     */
    public Sound(String source, float volume) {
        this.source = source;
        this.volume = volume;
    }

    /**
     * Creates an instance of a sound from the source string, the volume and playback speed requested,
     * and the loop boolean set to false.
     * @param source The source string.
     * @param volume The volume of this sound. Must be a value between 0f and 1f
     * @param speed The playback speed of this sound. Note that changing the default playback speed results
     *              in a different pitch.
     */
    public Sound(String source, float volume, float speed) {
        this.source = source;
        this.volume = volume;
        this.speed = speed;
    }

    /**
     * Creates an instance of a sound from the source string, the volume and playback speed requested,
     * and the loop boolean.
     * @param source The source string.
     * @param volume The volume of this sound. Must be a value between 0f and 1f.
     * @param speed The playback speed of this sound. Note that changing the default playback speed results
     *              in a different pitch.
     * @param looping The looping of this sound. Is true if the sound must loop, false otherwise.
     */
    public Sound(String source, float volume, float speed, boolean looping) {
        this.source = source;
        this.volume = volume;
        this.speed = speed;
    }

    /**
     * Returns the source string of the sound file from which the sound is played.
     * @return The source string of this sound.
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source string of the sound file from which the sound is played.
     * @param source The source string of this sound.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Returns the current volume of this sound.
     * @return The volume of this sound.
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Sets the volume of this sound.
     * @param volume The volume of this sound.
     */
    public void setVolume(float volume) {
        this.volume = volume;
    }

    /**
     * Returns the current playback speed of this sound.
     * @return The playback speed of this sound.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets the current playback speed of this sound.
     * @param speed The playback speed of this sound.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
