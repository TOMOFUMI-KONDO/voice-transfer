package voiceTransfer.receiver;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class VoiceReceiver extends Thread {
    private static final int PACKET_SIZE = (int) Math.pow(2, 14);

    private final VoicePlayer player;
    private final int serverPort;
    private final byte[] buffer;
    private final DatagramPacket packet;
    private final DatagramSocket socket;

    public VoiceReceiver(int serverPort) throws SocketException, LineUnavailableException {
        this.serverPort = serverPort;
        this.buffer = new byte[PACKET_SIZE];
        this.packet = new DatagramPacket(buffer, buffer.length);
        this.socket = new DatagramSocket(serverPort);
        this.player = new VoicePlayer();

        this.start();

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    @Override
    public void start() {
        super.start();
        System.out.println("VoiceReceiverが起動しました。(port=" + this.serverPort + ")");
    }

    @Override
    public void run() {
        while (true) {
            if (this.socket == null || this.socket.isClosed()) {
                break;
            }

            try {
                this.socket.receive(this.packet);
            } catch (SocketException e) {
                // ソケットが強制的にcloseされるのは正常な挙動
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.player.setVoice(this.buffer);
        }

    }

    public void end() {
        this.player.end();
        this.socket.close();

        System.out.println("VoiceReceiverが停止しました。(port=" + this.serverPort + ")");
    }
}