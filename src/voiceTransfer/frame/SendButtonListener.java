package voiceTransfer.frame;

import voiceTransfer.sender.VoiceSender;

import javax.sound.sampled.LineUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

public class SendButtonListener implements ActionListener {
    private final VoiceSender sender;

    public SendButtonListener() throws SocketException, LineUnavailableException {
        this.sender = new VoiceSender();
        this.sender.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.sender.getIsListening()) {
            this.sender.stopListening();
        } else {
            this.sender.startListening();
        }
    }
}
