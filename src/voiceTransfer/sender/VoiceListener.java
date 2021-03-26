package voiceTransfer.sender;

import javax.sound.sampled.*;
import java.io.IOException;

public class VoiceListener extends Thread {
    private static final int HZ = 8000;
    private static final int BITS = 16;
    private static final int MONO = 1;

    private final TargetDataLine target;
    private final AudioInputStream stream;
    private boolean isListening;
    private final byte[] voice = new byte[HZ * BITS / 8 * MONO];

    public VoiceListener() throws LineUnavailableException {
        AudioFormat linear = new AudioFormat(HZ, BITS, MONO, true, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, linear);

        this.target = (TargetDataLine) AudioSystem.getLine(info);
        this.target.open(linear);

        this.stream = new AudioInputStream(target);
    }

    @Override
    public void start() {
        this.target.start();
        this.isListening = true;

        super.start();

        System.out.println("VoiceListenerが起動しました。");
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(this.isListening);
            try {
                this.stream.read(this.voice, 0, this.voice.length);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!this.isListening) {
                break;
            }
        }
    }

    public byte[] getVoice() {
        return this.voice;
    }

    public void end() {
        this.isListening = false;
        this.target.stop();
        this.target.close();

        System.out.println("VoiceListenerを終了しました。");
    }

    public boolean getIsListening() {
        return this.isListening;
    }
}
