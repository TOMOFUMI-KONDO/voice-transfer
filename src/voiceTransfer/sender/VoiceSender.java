package voiceTransfer.sender;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class VoiceSender extends Thread {
    private static final int WAIT = 100;

    private final VoiceListener listener;
    private final String serverHost;
    private final int serverPort;
    private final DatagramSocket socket;
    private final InetSocketAddress address;

    public VoiceSender(String serverHost, int serverPort) throws SocketException, LineUnavailableException {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.address = new InetSocketAddress(serverHost, serverPort);
        this.socket = new DatagramSocket();

        this.start();

        this.listener = new VoiceListener();

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    @Override
    public void start() {
        super.start();
        System.out.println("VoiceSenderが起動しました(host=" + this.serverHost + ",port= " + this.serverPort + ")");
    }

    @Override
    public void run() {
        while (true) {
            if (this.socket == null || this.socket.isClosed()) {
                break;
            }

            try {
                Thread.sleep(WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            byte[] voice = this.listener.getVoice();
            DatagramPacket packet = new DatagramPacket(voice, voice.length, this.address);

            try {
                this.socket.send(packet);
            } catch (SocketException e) {
                // ソケットが強制的にcloseされるのは正常な挙動
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void end() {
        try {
            this.listener.end();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.socket.close();

        System.out.println("VoiceSenderが停止しました。(host=" + this.serverHost + ", port=" + this.serverPort + ")");
    }
}