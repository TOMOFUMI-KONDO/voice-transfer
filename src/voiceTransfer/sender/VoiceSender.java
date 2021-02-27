package voiceTransfer.sender;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class VoiceSender {
    private static final String DEFAULT_SERVER_HOST = "localhost";
    private static final int DEFAULT_SERVER_PORT = 10007;
    private static final int TIME_OUT_SECOND = 60;
    private static final int WAIT = 100;

    public static void main(String[] args) {
        final String host = args.length > 0 ? args[0] : DEFAULT_SERVER_HOST;
        final int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_SERVER_PORT;
        final InetSocketAddress address = new InetSocketAddress(host, port);

        VoiceListener listener;
        try {
            listener = new VoiceListener();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }
        listener.start();

        try (final DatagramSocket socket = new DatagramSocket()) {
            System.out.println("VoiceSenderが起動しました(host=" + host + ",port= " + port + ")");

            int count = 0;
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

                count++;
                if (count > (1000 / WAIT) * TIME_OUT_SECOND) {
                    listener.end();
                }

                if (!listener.getIsListening()) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}