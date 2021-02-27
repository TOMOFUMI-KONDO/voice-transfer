package voiceTransfer.receiver;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class VoiceReceiver {
    private static final int SERVER_PORT = 10007;
    private static final int PACKET_SIZE = (int) Math.pow(2, 14);
    private static final int TIME_OUT_SECOND = 600;
    private static final int WAIT = 100;

    public static void main(String[] args) {
        final byte[] buffer = new byte[PACKET_SIZE];
        final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        VoicePlayer player;
        try {
            player = new VoicePlayer();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }
        player.start();

        try (final DatagramSocket socket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("VoiceReceiverが起動しました。(port=" + socket.getLocalPort() + ")");

            int count = 0;
            while (true) {
                // fixme(kondo):
                //  packetを受信しないとcountが増加しないようになっているので、packetの受信に関係なくcountを増加させる。
                socket.receive(packet);
                player.setVoice(buffer);

                try {
                    Thread.sleep(WAIT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                count++;
                if (count > (1000 / WAIT) * TIME_OUT_SECOND) {
                    player.end();
                }

                if (!player.getIsPlaying()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}