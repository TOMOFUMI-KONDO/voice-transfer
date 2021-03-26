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
    private final VoiceListener listener;
    private boolean isSending;

    public VoiceSender() throws SocketException, LineUnavailableException {
        this.address = new InetSocketAddress(SERVER_HOST, SERVER_PORT);
        socket = new DatagramSocket();

        this.isSending = true;
        System.out.println("VoiceSenderが起動しました(host=" + SERVER_HOST + ",port= " + SERVER_PORT + ")");
        Runtime.getRuntime().addShutdownHook(new Thread(this::end));

        this.listener = new VoiceListener();
    }

    @Override
    public void run() {
        while (true) {
            final byte[] voice = listener.getVoice();
            final DatagramPacket packet = new DatagramPacket(voice, voice.length, address);

            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!this.isSending) {
                break;
            }
        }
    }

    public void startListening() {
        this.listener.start();
    }

    public void stopListening() {
        this.listener.end();
    }

    public void end() {
        this.stopListening();
        this.isSending = false;

        System.out.println("VoiceSenderを終了しました。");
    }

    public boolean getIsListening() {
        return this.listener.getIsListening();
    }
}