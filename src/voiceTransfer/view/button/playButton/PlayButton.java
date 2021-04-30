package voiceTransfer.view.button.playButton;

import javax.swing.*;
import java.awt.*;

public class PlayButton extends JButton {
    public PlayButton(Dimension dimension) {
        this.setText("Tap to play");
        this.setPreferredSize(dimension);
        this.addActionListener(new PlayButtonListener(this::setText));
    }
}
