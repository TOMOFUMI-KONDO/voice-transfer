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
    private VoicePlayer player;
    private boolean isReceiving;

    public VoiceReceiver() throws SocketException {
        this.buffer = new byte[PACKET_SIZE];
        this.packet = new DatagramPacket(buffer, buffer.length);
        this.socket = new DatagramSocket(SERVER_PORT);

        this.isReceiving = true;
        System.out.println("VoiceReceiverが起動しました。(port=" + SERVER_PORT + ")");
        Runtime.getRuntime().addShutdownHook(new Thread(this::end));

        try {
            this.player = new VoicePlayer();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
//            return;
        }
//        this.player.start();
    }

    @Override
    public void run() {
        while (this.isReceiving) {
            try {
                this.socket.receive(this.packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.player.setVoice(this.buffer);
        }
    }

//    @Override
//    public void start() {
//        super.start();

    //    }
    public void startPlaying() {
        this.player.start();
    }

//    public void reStart() {
//        this.isReceiving = true;
//    }

    public void stopPlaying() {
        this.player.end();
    }

    public void end() {
        this.isReceiving = false;

        System.out.println("VoiceReceiverを終了しました。");
    }

    public boolean getIsPlaying() {
        return this.player.getIsPlaying();
    }

//    public boolean getIsReceiving() {
//        return this.isReceiving;
//    }
}