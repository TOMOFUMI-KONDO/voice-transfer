package voiceTransfer.view;

import voiceTransfer.view.button.receiveButton.ReceiveButton;
import voiceTransfer.view.button.sendButton.SendButton;

import javax.swing.*;
import java.awt.*;

public class VoiceTransferPanel extends JPanel {
    public VoiceTransferPanel() {
        super();

        this.add(new SendButton("Send", new Dimension(150, 60)));
        this.add(new ReceiveButton("Receive", new Dimension(150, 60)));
    }
}
