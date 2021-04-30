package voiceTransfer.view;

import voiceTransfer.view.Panel.PlayPanel;
import voiceTransfer.view.Panel.SendPanel;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.net.SocketException;

public class VoiceTransferFrame extends JFrame {
    public VoiceTransferFrame() throws SocketException, LineUnavailableException {
        super();

        this.setTitle("VoiceTransfer");
        this.setBounds(0, 0, 1000, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.getContentPane().add(new SendPanel(), BorderLayout.NORTH);
        this.getContentPane().add(new PlayPanel(), BorderLayout.SOUTH);
    }
}
