package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JPanel
import javax.swing.JSplitPane
import java.awt.BorderLayout

class MediaOverviewPanel extends JPanel {
    ImageDetailPanel imageDetailPanel
    ImagePanel imagePanel
    ImageTablePanel imageTablePanel
    ImageShowOptionsPanel imageShowOptionsPanel
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
        imagePanel.setPictures(pictures)
        imageDetailPanel.setPictures(pictures)
    }

    void setStory(Story story) {
        this.story = story
        imageTablePanel.setStory(story)
        imagePanel.setStory(story)
        imageDetailPanel.setStory(story)
    }
}
