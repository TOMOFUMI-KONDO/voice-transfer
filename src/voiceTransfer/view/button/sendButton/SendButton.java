package voiceTransfer.view.button.sendButton;


import voiceTransfer.sender.VoiceSenderSet;

import javax.swing.*;
import java.awt.*;

public class SendButton extends JButton {
    public SendButton(Dimension dimension, VoiceSenderSet senderSet) {
        this.setPreferredSize(dimension);
        this.setText("Tap to send");
        this.addActionListener(new SendButtonListener(this::setText, senderSet));
    }
}
