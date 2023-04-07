package be.dafke.MediaViewer.Application.Media

import javax.swing.BoxLayout
import javax.swing.JCheckBox
import javax.swing.JPanel

class ImageShowOptionsPanel extends JPanel {

    JCheckBox checkBox
    ImagePanel imagePanel

    ImageShowOptionsPanel(ImagePanel imagePanel) {
        this.imagePanel = imagePanel
        checkBox = new JCheckBox("Full size pictures")
        checkBox.setSelected(false)
        checkBox.addActionListener { e ->
            imagePanel.setFullSize(checkBox.isSelected())
        }
        setLayout new BoxLayout(this, BoxLayout.Y_AXIS)
        add checkBox
    }
}
