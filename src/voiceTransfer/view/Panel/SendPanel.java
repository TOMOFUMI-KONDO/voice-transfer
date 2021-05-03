package voiceTransfer.view.Panel;

import voiceTransfer.sender.VoiceSenderSet;
import voiceTransfer.view.button.sendButton.SendButton;

import javax.swing.*;

public class SendPanel extends JPanel {
    public SendPanel() {
        super();

        VoiceSenderSet senderSet = new VoiceSenderSet();

        for (int i = 0; i < 5; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            JTextField textField = new JTextField("localhost");
            panel.add(textField);

            JButton sendButton = new SendButton(senderSet, textField::getText);
            panel.add(sendButton);

            this.add(panel);
        }
    }
}
