package voiceTransfer.view;

import voiceTransfer.view.button.SendButtonListener;
import voiceTransfer.view.button.receiveButton.ReceiveButton;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.net.SocketException;

public class VoiceTransferPanel extends JPanel {
    public VoiceTransferPanel() throws SocketException, LineUnavailableException {
        super();

        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(150, 60));
        sendButton.addActionListener(new SendButtonListener());
        this.add(sendButton);

        this.add(new ReceiveButton("Receive", new Dimension(150, 60)));
    }
}
