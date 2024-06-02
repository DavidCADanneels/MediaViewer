package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JCheckBox
import javax.swing.JPanel
import javax.swing.JSplitPane
import java.awt.BorderLayout

class MediaOverviewPanel extends JPanel {
    ImageDetailPanel imageDetailPanel
    ImagePanel imagePanel
    ImageTablePanel imageTablePanel
    ImageShowOptionsPanel imageShowOptionsPanel
    // FIXME: use this checkbox iso ImageShowOptionsPanel.displaySelectedImages
    JCheckBox displaySelectedImages
    Story story

    MediaOverviewPanel() {
        setLayout(new BorderLayout())

        imageDetailPanel = new ImageDetailPanel(this)
        imageShowOptionsPanel = new ImageShowOptionsPanel(this)
        imagePanel = new ImagePanel(imageShowOptionsPanel) // dependency only needed to include in Layout
        imageTablePanel = new ImageTablePanel(this)

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT)
        splitPane.add imageTablePanel, JSplitPane.TOP
        splitPane.add imagePanel, JSplitPane.BOTTOM

        // TODO: add imageShowOptionsPanel to NORTH and remove it from ImagePanel
//        add imageShowOptionsPanel, BorderLayout.NORTH
        add splitPane, BorderLayout.CENTER
        add imageDetailPanel, BorderLayout.EAST
    }

    void setFullSize(boolean fullSize){
        imagePanel.setFullSize(fullSize)
    }

    void showImageDetails(boolean showDetails){
        imageDetailPanel.setVisible(showDetails)
    }

    void setShowSelection(boolean selected){
        imageTablePanel.showSelection = selected
        imagePanel.showSelection = selected
    }

    void setSingleSelection(boolean singleSelection) {
        imagePanel.setSingleSelection(singleSelection)
        imageTablePanel.setSingleSelection(singleSelection)
        imageDetailPanel.setSingleSelection(singleSelection)
    }

    void setPicture(Picture picture){
        // TODO: (add option to) show selected image in new ImageFrame
        imagePanel.setPicture(picture)
        imageDetailPanel.setPicture(picture)
    }

    void setPictures(List<Picture> pictures){
        // FIXME: add Checkbox to set 'showSelection' in imagePanel
        // TODO: do not call the setPicture(s) method if showSelection == false
        // and remov the if clause in imagePanel.setPicture(s)
//        if(showSelection) {
            imagePanel.setPictures(pictures)
//        }
        imageDetailPanel.setPictures(pictures)
    }

    void setStory(Story story) {
        this.story = story
        imageTablePanel.setStory(story)
        imagePanel.setStory(story)
        imageDetailPanel.setStory(story)
    }
}
