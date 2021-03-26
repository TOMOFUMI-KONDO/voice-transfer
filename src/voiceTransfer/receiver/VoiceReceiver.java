// 1. ボタンインスタンス作成時に、VoicePlayerとVoiceReceiverのインスタンスを作成する。この時点でVoiceReceiverのスレッド処理は開始する。
// 2. ボタンが押されたら、VoicePlayerのスレッド処理を開始する。
// 3. 再度ボタンが押されたら、VoicePlayerのスレッド処理を停止する。VoiceReceiverのスレッド処理は継続したまま
// 4. 2,3を繰り返す。

package voiceTransfer.receiver;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class VoiceReceiver extends Thread {
    private static final int SERVER_PORT = 10007;
    private static final int PACKET_SIZE = (int) Math.pow(2, 14);

    private final byte[] buffer;
    private final DatagramPacket packet;
    private final DatagramSocket socket;
    private final VoicePlayer player;
    private boolean isReceiving;

    public VoiceReceiver() throws SocketException, LineUnavailableException {
        this.buffer = new byte[PACKET_SIZE];
        this.packet = new DatagramPacket(buffer, buffer.length);
        this.socket = new DatagramSocket(SERVER_PORT);

        this.isReceiving = true;
        System.out.println("VoiceReceiverが起動しました。(port=" + SERVER_PORT + ")");
        Runtime.getRuntime().addShutdownHook(new Thread(this::end));

        this.player = new VoicePlayer();
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.socket.receive(this.packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.player.setVoice(this.buffer);

            if (!this.isReceiving) {
                break;
            }
        }
    }

    public void startPlaying() {
        this.player.start();
    }

    public void stopPlaying() {
        this.player.end();
    }

    public void end() {
        this.stopPlaying();
        this.isReceiving = false;

        System.out.println("VoiceReceiverを終了しました。");
    }

    public boolean getIsPlaying() {
        return this.player.getIsPlaying();
    }
}