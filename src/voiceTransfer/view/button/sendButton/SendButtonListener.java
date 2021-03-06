package voiceTransfer.view.button.sendButton;

import util.PrintUtil;
import voiceTransfer.sender.VoiceSenderSet;

import javax.sound.sampled.LineUnavailableException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SendButtonListener implements ActionListener {
    private final Supplier<String> getHostName;
    private final Consumer<String> setText;
    private final VoiceSenderSet senderSet;
    private Integer senderId;

    public SendButtonListener(Supplier<String> getHostName, Consumer<String> setText, VoiceSenderSet senderSet) {
        this.getHostName = getHostName;
        this.setText = setText;
        this.senderSet = senderSet;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        // FIXME: 仮の実装↓
        if (!this.senderSet.getSenderIds().contains(this.senderId)) {
            try {
                this.senderId = this.senderSet.createSender(this.getHostName.get());
            } catch (SocketException | LineUnavailableException e) {
                PrintUtil.printException(e);
                return;
            }
            this.setText.accept("Tap to stop Sending");
        } else {
            this.senderSet.removeSender(this.senderId);
            this.setText.accept("Tap to send");
        }
    }
}
