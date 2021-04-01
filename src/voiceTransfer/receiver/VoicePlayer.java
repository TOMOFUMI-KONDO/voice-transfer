package voiceTransfer.receiver;

import javax.sound.sampled.*;

public class VoicePlayer extends Thread {
    private static final int HZ = 8000;
    private static final int BITS = 16;
    private static final int MONO = 1;

    private final SourceDataLine source;
    private byte[] voice;

    public VoicePlayer() throws LineUnavailableException {
        this.voice = new byte[HZ * BITS / 8 * MONO];
        AudioFormat linear = new AudioFormat(HZ, BITS, MONO, true, false);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, linear);
        this.source = (SourceDataLine) AudioSystem.getLine(info);
        this.source.open(linear);

        this.start();
    }

    @Override
    public void start() {
        this.source.start();

        super.start();

        System.out.println("VoicePlayerが起動しました。");
    }

    @Override
    public void run() {
        while (true) {
            this.source.write(this.voice, 0, this.voice.length);

            if (!this.source.isOpen()) {
                break;
            }
        }
    }

    public void setVoice(byte[] voice) {
        this.voice = voice;
    }

    public void end() {
        this.source.stop();
        this.source.close();

        System.out.println("VoicePlayerが停止しました。");
    }
}