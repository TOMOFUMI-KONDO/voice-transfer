package voiceTransfer.sender;

import javax.sound.sampled.*;
import java.io.IOException;

public class VoiceListener extends Thread {
    private static final int HZ = 8000;
    private static final int BITS = 16;
    private static final int MONO = 1;

    private final TargetDataLine target;
    private final AudioInputStream stream;
    private final byte[] voice = new byte[HZ * BITS / 8 * MONO];

    public VoiceListener() throws LineUnavailableException {
        AudioFormat linear = new AudioFormat(HZ, BITS, MONO, true, false);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, linear);
        this.target = (TargetDataLine) AudioSystem.getLine(info);
        this.target.open(linear);
        this.stream = new AudioInputStream(target);

        this.start();
    }

    @Override
    public void start() {
        this.target.start();

        super.start();

        System.out.println("VoiceListenerが起動しました。");
    }

    @Override
    public void run() {
        while (true) {
            if (!this.target.isOpen()) {
                break;
            }

            try {
                this.stream.read(this.voice, 0, this.voice.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] getVoice() {
        return this.voice;
    }

    public void end() throws IOException {
        this.target.stop();
        this.target.close();
        this.stream.close();

        System.out.println("VoiceListenerが停止しました。");
    }
}
