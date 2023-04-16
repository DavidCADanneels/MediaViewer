package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.IoTools
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JPanel
import javax.swing.JSplitPane
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class MediaOverviewPanel extends JPanel {
    JButton backToStoryDetailsButton, backToStoryOverViewButton, participantButton, addMediaButton

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

        backToStoryOverViewButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_MAIN")}")
        backToStoryOverViewButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_OVERVIEW)
        }

        backToStoryDetailsButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_DETAILS")}")
        backToStoryDetailsButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_DETAILS)
        }

        participantButton = new JButton(getBundle("MediaViewer").getString("SHOW_PARTICIPANTS_FOR_STORY"))
        participantButton.addActionListener { e ->
            Main.switchView(Main.VIEW_PARTICIPANTS_FOR_STORY)
        }

        addMediaButton = new JButton(getBundle("MediaViewer").getString("ADD_MEDIA_BUTTON"))
        addMediaButton.addActionListener { e -> loadData() }

        JPanel south = new JPanel()
        south.add backToStoryOverViewButton
        south.add backToStoryDetailsButton
        south.add participantButton
        south.add addMediaButton

        add south, BorderLayout.SOUTH
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

    void loadData(){
        File startFolder = Main.getSubFolder(story)

        JFileChooser chooser = new JFileChooser(startFolder)
        chooser.setMultiSelectionEnabled(true)
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles()
            files.each { File file ->
                String jpgExtension = null
                if(file.name.toLowerCase().endsWith('.jpg')) {
                    jpgExtension = 'jpg'
                } else if(file.name.toLowerCase().endsWith('.jpeg')) {
                    jpgExtension = 'jpeg'
                }
                if(jpgExtension){
                    Picture picture = new Picture()
                    String fileName = file.name - ".${jpgExtension}"
                    picture.setFileName(fileName)
                    picture.setExtension(jpgExtension)
                    picture.setSubFolderName('jpg')
                    // TODO: no need to reassign picture to picture ?
                    picture = IoTools.readAndDisplayMetadata(file, picture)

                    List<Picture> pictures = story.getPictures()
                    pictures.add(picture)

                    // TODO: show popup to set owner
                }
            }
            imageTablePanel.dataModel.fireTableDataChanged()
        }
        // TODO: add support to read Movies, Text, etc. (not only pictures)
//            Point locationOnScreen = getLocationOnScreen()
//            NewMediaDialog newMediaDialog = new NewMediaDialog()
//            newMediaDialog.setLocation(locationOnScreen)
//            newMediaDialog.visible = true

    }
}
