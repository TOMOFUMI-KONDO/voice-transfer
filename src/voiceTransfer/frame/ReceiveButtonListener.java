package voiceTransfer.frame;

import voiceTransfer.receiver.VoiceReceiver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

public class ReceiveButtonListener implements ActionListener {
    private final VoiceReceiver receiver;

    public ReceiveButtonListener() throws SocketException {
        this.receiver = new VoiceReceiver();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(this.receiver.getIsReceiving());
        if (this.receiver.getIsReceiving()) {
            this.receiver.end();
        } else {
            this.receiver.start();
        }
    }
}
