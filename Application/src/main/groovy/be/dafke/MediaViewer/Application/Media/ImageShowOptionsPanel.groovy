package be.dafke.MediaViewer.Application.Media

import javax.swing.JCheckBox
import javax.swing.JPanel

class ImageShowOptionsPanel extends JPanel {

    JCheckBox displaySelectedImages, singleSelectionCheckBox, fullSizeCheckBox, showDetailsCheckBox
    MediaOverviewPanel mediaOverviewPanel

    ImageShowOptionsPanel(MediaOverviewPanel mediaOverviewPanel) {
        this.mediaOverviewPanel = mediaOverviewPanel
        displaySelectedImages = new JCheckBox("Display Selection")
        displaySelectedImages.setSelected(true)
        displaySelectedImages.addActionListener { e ->
            checkShowSelection()
        }

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

        add displaySelectedImages
        add singleSelectionCheckBox
        add fullSizeCheckBox
        add showDetailsCheckBox
    }

    void fullSizeImage(){
        boolean fullSize = fullSizeCheckBox.isSelected()
        mediaOverviewPanel.setFullSize(fullSize)
    }

    void checkShowDetails(){
        boolean showDetails = showDetailsCheckBox.isSelected()
        mediaOverviewPanel.showImageDetails(showDetails)
    }

    void checkShowSelection(){
        boolean selected = displaySelectedImages.isSelected()
        singleSelectionCheckBox.selected = !(!selected || !singleSelectionCheckBox.selected)
//        checkSingleSelection()

        fullSizeCheckBox.selected = fullSizeCheckBox.selected && selected
        fullSizeCheckBox.enabled = selected
//        fullSizeImage()

        showDetailsCheckBox.selected = showDetailsCheckBox.selected || !selected
        checkShowDetails()

        mediaOverviewPanel.setShowSelection(selected)
    }

    void checkSingleSelection() {
        boolean single = singleSelectionCheckBox.isSelected()
        // FIXME: re-validate which checkboxes to enable/select
        if (single) {
            fullSizeCheckBox.enabled = true
            fullSizeImage()

            showDetailsCheckBox.enabled = true
            checkShowDetails()
        } else {
            fullSizeCheckBox.selected = false
            fullSizeCheckBox.enabled = false
            fullSizeImage()
        }
        mediaOverviewPanel.setSingleSelection(single)
    }
}
