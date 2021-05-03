package voiceTransfer.view.button.sendButton;


import voiceTransfer.sender.VoiceSenderSet;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class SendButton extends JButton {
    public SendButton(VoiceSenderSet senderSet, Supplier<String> getHostName) {
        this.setPreferredSize(new Dimension(100, 60));
        this.setText("Tap to send");
        this.addActionListener(new SendButtonListener(getHostName, this::setText, senderSet));
    }
}
