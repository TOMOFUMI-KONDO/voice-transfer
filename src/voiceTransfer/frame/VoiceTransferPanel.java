package voiceTransfer.frame;

import javax.swing.*;
import java.awt.*;
import java.net.SocketException;

public class VoiceTransferPanel extends JPanel {
    public VoiceTransferPanel() throws SocketException {
        super();

        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(150, 60));
        sendButton.addActionListener(new SendButtonListener());
        this.add(sendButton, BorderLayout.NORTH);

        JButton receiveButton = new JButton("Receive");
        receiveButton.setPreferredSize(new Dimension(150, 60));
        receiveButton.addActionListener(new ReceiveButtonListener());
        this.add(receiveButton, BorderLayout.SOUTH);
    }
}