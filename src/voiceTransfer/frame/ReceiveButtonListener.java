package voiceTransfer.frame;

import voiceTransfer.receiver.VoiceReceiver;

import javax.sound.sampled.LineUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

public class ReceiveButtonListener implements ActionListener {
    private final VoiceReceiver receiver;

    public ReceiveButtonListener() throws SocketException, LineUnavailableException {
        this.receiver = new VoiceReceiver();
        this.receiver.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.receiver.getIsPlaying()) {
            this.receiver.stopPlaying();
        } else {
            this.receiver.startPlaying();
        }
    }
}
