package voiceTransfer.frame;

import javax.swing.*;
import java.awt.*;
import java.net.SocketException;

public class VoiceTransferFrame extends JFrame {
    public VoiceTransferFrame() throws SocketException {
        super();

        this.setTitle("VoiceTransfer");
        this.setBounds(100, 100, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new VoiceTransferPanel();
        this.getContentPane().add(panel, BorderLayout.SOUTH);
    }
}
