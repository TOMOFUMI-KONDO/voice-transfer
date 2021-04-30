package voiceTransfer.view.Panel;

import voiceTransfer.sender.VoiceSenderSet;
import voiceTransfer.view.button.sendButton.SendButton;

import javax.swing.*;
import java.awt.*;

public class SendPanel extends JPanel {
    public SendPanel() {
        super();

        VoiceSenderSet senderSet = new VoiceSenderSet();

        for (int i = 0; i < 5; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setPreferredSize(new Dimension(120, 100));

            JButton sendButton = new SendButton(new Dimension(100, 60), senderSet);
            panel.add(sendButton);

            JTextField textField = new JTextField("localhost");
            panel.add(textField);

            this.add(panel);
        }
    }
}
