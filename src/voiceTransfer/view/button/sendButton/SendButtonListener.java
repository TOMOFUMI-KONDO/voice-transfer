package voiceTransfer.view.button.sendButton;

import voiceTransfer.sender.VoiceSender;
import voiceTransfer.sender.VoiceSenderCreator;
import voiceTransfer.sender.VoiceSenderSet;

import javax.sound.sampled.LineUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

public class SendButtonListener implements ActionListener {
    private final VoiceSenderSet senderSet;
    private final VoiceSenderCreator senderCreator;

    public SendButtonListener() {
        this.senderSet = new VoiceSenderSet();
        this.senderCreator = new VoiceSenderCreator();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // FIXME: 仮の実装↓
        if (this.senderSet.getSenders().isEmpty()) {
            VoiceSender newSender;

            try {
                newSender = this.senderCreator.createSender();
            } catch (SocketException | LineUnavailableException e) {
                e.printStackTrace();
                return;
            }

            this.senderSet.addSender(newSender);
        } else {
            for (VoiceSender sender : this.senderSet.getSenders()) {
                sender.end();
                this.senderSet.removeSender(sender);
            }
        }
    }
}
