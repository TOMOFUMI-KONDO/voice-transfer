package voiceTransfer.sender;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class VoiceSender extends Thread {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 10007;
    private static final int WAIT = 100;

    private final DatagramSocket socket;
    private final InetSocketAddress address;
    private boolean isSending;
    private VoiceListener listener;

    public VoiceSender() throws SocketException, LineUnavailableException {
        this.socket = new DatagramSocket();
        this.address = new InetSocketAddress(SERVER_HOST, SERVER_PORT);

        this.isSending = true;
        System.out.println("VoiceSenderが起動しました(host=" + SERVER_HOST + ",port= " + SERVER_PORT + ")");
        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    @Override
    public void run() {
        while (true) {
            if (!this.isSending) {
                break;
            }

            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.listener == null) {
                continue;
            }

            final byte[] voice = this.listener.getVoice();
            final DatagramPacket packet = new DatagramPacket(voice, voice.length, this.address);

            try {
                this.socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void startListening() {
        try {
            this.listener = new VoiceListener();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

        this.listener.start();
    }

    public void stopListening() {
        if (this.listener == null) {
            return;
        }

        this.listener.end();
        this.listener = null;
    }

    public boolean isListening() {
        return this.listener != null;
    }

    public void end() {
        this.stopListening();
        this.isSending = false;

        System.out.println("VoiceSenderを終了しました。");
    }
}