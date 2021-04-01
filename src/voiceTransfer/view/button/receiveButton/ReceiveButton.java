package voiceTransfer.view.button.receiveButton;

import javax.swing.*;
import java.awt.*;

public class ReceiveButton extends JButton {
    public ReceiveButton(String text, Dimension dimension) {
        this.setText(text);
        this.setPreferredSize(dimension);
        this.addActionListener(new ReceiveButtonListener());
    }
}
