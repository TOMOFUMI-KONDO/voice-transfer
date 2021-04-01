package voiceTransfer.sender;

import javax.sound.sampled.LineUnavailableException;
import java.net.SocketException;

public class VoiceSenderCreator {
    public VoiceSender createSender() throws SocketException, LineUnavailableException {
        // TODO: ソケットのホストとポート番号の組が被らないように調整する
        return new VoiceSender("localhost", 10007);
    }
}