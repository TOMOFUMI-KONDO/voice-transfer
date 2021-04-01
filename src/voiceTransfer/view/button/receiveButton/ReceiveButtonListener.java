package voiceTransfer.view.button.receiveButton;

import voiceTransfer.receiver.VoiceReceiver;
import voiceTransfer.receiver.VoiceReceiverCreator;
import voiceTransfer.receiver.VoiceReceiverSet;

import javax.sound.sampled.LineUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

public class ReceiveButtonListener implements ActionListener {
    private final VoiceReceiverSet receiverSet;
    private final VoiceReceiverCreator receiverCreator;

    public ReceiveButtonListener() {
        this.receiverSet = new VoiceReceiverSet();
        this.receiverCreator = new VoiceReceiverCreator();
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        // FIXME: 仮の実装↓
        if (this.receiverSet.getReceivers().isEmpty()) {
            VoiceReceiver newReceiver;

            try {
                newReceiver = this.receiverCreator.createReceiver();
            } catch (SocketException | LineUnavailableException e) {
                e.printStackTrace();
                return;
            }

            this.receiverSet.addReceiver(newReceiver);
        } else {
            for (VoiceReceiver receiver : this.receiverSet.getReceivers()) {
                receiver.end();
                this.receiverSet.removeReceiver(receiver);
            }
        }
    }
}
