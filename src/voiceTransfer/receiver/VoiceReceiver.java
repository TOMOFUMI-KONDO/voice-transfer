package voiceTransfer.receiver;

import util.PrintUtil;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class VoiceReceiver extends Thread {
    private static final int SERVER_PORT = 10007;
    private static final int PACKET_SIZE = (int) Math.pow(2, 14);

    private final VoicePlayer player;
    private final byte[] buffer;
    private final DatagramPacket packet;
    private final DatagramSocket socket;

    public VoiceReceiver() throws SocketException, LineUnavailableException {
        this.buffer = new byte[PACKET_SIZE];
        this.packet = new DatagramPacket(buffer, buffer.length);
        this.socket = new DatagramSocket(SERVER_PORT);
        this.player = new VoicePlayer();

        this.start();

        Runtime.getRuntime().addShutdownHook(new Thread(this::end));
    }

    @Override

    public void start() {
        super.start();
        System.out.println("VoiceReceiverが起動しました。(port=" + SERVER_PORT + ")");
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
                PrintUtil.printException(e);
            }

            this.player.setVoice(this.buffer);
        }

    }

    public void end() {
        this.player.end();
        this.socket.close();

        System.out.println("VoiceReceiverが停止しました。(port=" + SERVER_PORT + ")");
    }
}