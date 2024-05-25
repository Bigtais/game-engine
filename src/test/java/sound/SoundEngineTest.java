package sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SoundEngineTest {

    private SoundEngine engine;
    private final Sound beepSound = new Sound("src/test/java/sound/beep.mp3");
    private final Sound sosSound = new Sound("src/test/java/sound/sos.mp3");
    @BeforeEach
    void init(){
        engine = new SoundEngine();
    }

    @Test
    void testPlaySound() {
        engine.playSound(beepSound);
        // Assert the sound is playing correctly
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }
        engine.playSound(beepSound, 0.2f, 1.5f);
        // Assert the sound is playing correctly with the right parameters
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }
    }

    @Test
    void testPlaySoundLoop() {
        engine.playSoundLoop(sosSound);
        // Assert that the sound loops correctly
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }
        engine.playSoundLoop(sosSound, 0.2f, 1.5f);
        // Assert that the sound loops correctly with the right parameters
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }
    }

    @Test
    void testStopSound() {
        // Stopping of one particular instance of a sound
        int index = engine.playSound(beepSound);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            // Do nothing
        }
        engine.stopSound(index);


        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            // Do nothing
        }


        // Stopping of all instances of a sound
        engine.playSound(beepSound);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            // Do nothing
        }

        engine.playSound(beepSound);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            // Do nothing
        }

        engine.stopSound(beepSound);

    }

    @Test
    void testPauseResumeSound() {
        int index = engine.playSound(beepSound);
        // Assert the sound is playing correctly
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }
        engine.pauseSound(index);
        // Assert the sound is paused
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }
        engine.resumeSound(index);

        // Assert the sound is resumed
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }

    }

    @Test
    void testSetSoundVolume() {
        int index = engine.playSound(beepSound);
        // Assert the sound is playing correctly
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }
        engine.setSoundVolume(index, 1f);
        // Assert the sound is playing louder
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }

    }

    @Test
    void testSetPlaybackSpeed() {
        int index = engine.playSound(beepSound);
        // Assert the sound is playing correctly
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }
        engine.setPlaybackSpeed(index, 1.5f);
        // Assert the sound is playing faster
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            // Do nothing
        }

    }
}