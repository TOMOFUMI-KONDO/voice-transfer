package voiceTransfer.receiver;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class VoiceReceiver {
    private static final int SERVER_PORT = 10007;
    private static final int PACKET_SIZE = (int) Math.pow(2, 14);

    public static void main(String[] args) {
        final byte[] buffer = new byte[PACKET_SIZE];
        final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        System.out.println("VoiceReceiverが起動しました。(port=" + SERVER_PORT + ")");

        VoicePlayer player;
        try {
            player = new VoicePlayer();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }
        player.start();

        Runtime.getRuntime().addShutdownHook(new Thread(player::end));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("VoiceReceiverが終了しました。")));

        try (final DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {
            while (true) {
                socket.receive(packet);
                player.setVoice(buffer);

                if (!player.getIsPlaying()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}