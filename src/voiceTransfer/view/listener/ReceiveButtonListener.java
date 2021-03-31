package voiceTransfer.view.listener;

import voiceTransfer.receiver.VoiceReceiver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

public class ReceiveButtonListener implements ActionListener {
    private final VoiceReceiver receiver;

    public ReceiveButtonListener() throws SocketException {
        this.receiver = new VoiceReceiver();
        this.receiver.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.receiver.isPlaying()) {
            this.receiver.stopPlaying();
        } else {
            this.receiver.startPlaying();
        }
    }
}
