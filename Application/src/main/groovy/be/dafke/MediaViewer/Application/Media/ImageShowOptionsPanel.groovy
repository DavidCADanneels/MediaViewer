package be.dafke.MediaViewer.Application.Media


import javax.swing.JCheckBox
import javax.swing.JPanel

class ImageShowOptionsPanel extends JPanel {

    JCheckBox singleSelectionCheckBox, fullSizeCheckBox, showDetailsCheckBox
    ImagePanel imagePanel
    ImageDetailPanel imageDetailPanel

    ImageShowOptionsPanel(ImagePanel imagePanel, ImageDetailPanel imageDetailPanel) {
        this.imagePanel = imagePanel
        this.imageDetailPanel = imageDetailPanel
        singleSelectionCheckBox = new JCheckBox("Single Selection")
        singleSelectionCheckBox.setSelected(true)
        singleSelectionCheckBox.addActionListener { e ->
            checkSingleSelection()
        }

        fullSizeCheckBox = new JCheckBox("Full size pictures")
        fullSizeCheckBox.setSelected(false)
        fullSizeCheckBox.addActionListener { e ->
            fullSizeImage()
        }

        showDetailsCheckBox = new JCheckBox("Show Media Details")
        showDetailsCheckBox.setSelected(true)
        showDetailsCheckBox.addActionListener { e ->
            checkShowDetails()
        }

        add singleSelectionCheckBox
        add fullSizeCheckBox
        add showDetailsCheckBox
    }

    void fullSizeImage(){
        boolean fullSize = fullSizeCheckBox.isSelected()
        imagePanel.setFullSize(fullSize)
    }

    void checkShowDetails(){
        boolean showDetails = showDetailsCheckBox.isSelected()
        imageDetailPanel.setVisible(showDetails)
    }

    void checkSingleSelection(){
        boolean single = singleSelectionCheckBox.isSelected()
        if(single){
            fullSizeCheckBox.setEnabled(true)
        } else {
            fullSizeCheckBox.setSelected(false)
            fullSizeImage()
            fullSizeCheckBox.setEnabled(false)
        }
    }
}
