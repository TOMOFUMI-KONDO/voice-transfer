package voiceTransfer.sender;

import util.PrintUtil;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class VoiceSender extends Thread {
    private static final int SERVER_PORT = 10007;
    private static final int INTERVAL = 100;

    private final VoiceListener listener;
    private final String serverHost;
    private final DatagramSocket socket;
    private final InetSocketAddress address;

    public VoiceSender(String serverHost) throws SocketException, LineUnavailableException {
        this.serverHost = serverHost;
        this.address = new InetSocketAddress(serverHost, SERVER_PORT);
        this.socket = new DatagramSocket();
        this.listener = new VoiceListener();

        this.start();

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    @Override
    public void start() {
        super.start();
        System.out.println("VoiceSenderが起動しました(host=" + this.serverHost + ",port=" + SERVER_PORT + ")");
    }

    @Override
    public void run() {
        while (true) {
            if (this.socket == null || this.socket.isClosed()) {
                break;
            }

            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                PrintUtil.printException(e);
            }

            byte[] voice = this.listener.getVoice();
            DatagramPacket packet = new DatagramPacket(voice, voice.length, this.address);

            try {
                this.socket.send(packet);
            } catch (SocketException e) {
                // ソケットが強制的にcloseされるのは正常な挙動
                System.out.println(e.getMessage());
            } catch (IOException e) {
                PrintUtil.printException(e);
            }
        }
    }

    public void end() {
        try {
            this.listener.end();
        } catch (IOException e) {
            PrintUtil.printException(e);
        }
        this.socket.close();

        System.out.println("VoiceSenderが停止しました。(host=" + this.serverHost + ", port=" + SERVER_PORT + ")");
    }
}