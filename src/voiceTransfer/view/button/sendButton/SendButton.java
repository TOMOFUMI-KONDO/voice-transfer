package voiceTransfer.view.button.sendButton;

import javax.swing.*;
import java.awt.*;

public class SendButton extends JButton {
    public SendButton(String text, Dimension dimension) {
        this.setText(text);
        this.setPreferredSize(dimension);
        this.addActionListener(new SendButtonListener());
    }
}
