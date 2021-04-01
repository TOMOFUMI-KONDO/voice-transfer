package voiceTransfer.receiver;

import javax.sound.sampled.LineUnavailableException;
import java.net.SocketException;

public class VoiceReceiverCreator {
    public VoiceReceiver createReceiver() throws SocketException, LineUnavailableException {
        // TODO ソケットのポート番号が被らないように調整する
        return new VoiceReceiver(10007);
    }
}
