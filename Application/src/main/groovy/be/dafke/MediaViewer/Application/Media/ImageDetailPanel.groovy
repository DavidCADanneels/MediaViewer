package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture

import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class ImageDetailPanel extends JPanel{
    JTextField sizeField
    JLabel sizeLabel
    JPanel line1

    ImageDetailPanel() {
        setLayout new BoxLayout(this, BoxLayout.Y_AXIS)

        sizeField = new JTextField(20)
        sizeField.setEnabled false
        line1 = new JPanel()
        sizeLabel = new JLabel("Size:")
        line1.add sizeLabel
        line1.add sizeField

        add line1
    }

    void setPicture(Picture picture){
        if(picture) {
            sizeField.setText "${picture.getWidth()} x ${picture.getHeight()}"
        } else {
            sizeField.setText ''
        }
    }

    void setSingleSelection(boolean singleSelection) {
        line1.setVisible(singleSelection)
    }
}
