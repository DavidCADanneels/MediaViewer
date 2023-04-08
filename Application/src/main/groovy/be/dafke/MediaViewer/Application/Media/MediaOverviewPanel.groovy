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
    Story story

    MediaOverviewPanel() {
        setLayout(new BorderLayout())

        imageDetailPanel = new ImageDetailPanel()
        imagePanel = new ImagePanel(this, imageDetailPanel)
        imageTablePanel = new ImageTablePanel(imagePanel, imageDetailPanel)

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT)
        splitPane.add imageTablePanel, JSplitPane.TOP
        splitPane.add imagePanel, JSplitPane.BOTTOM
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

    void setStory(Story story) {
        this.story = story
        imageTablePanel.setStory(story)
        imagePanel.setStory(story)
    }

    void loadData(){
        File startFolder = Main.getSubFolder(story)

        JFileChooser chooser = new JFileChooser(startFolder)
        chooser.setMultiSelectionEnabled(true)
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File[] files = chooser.getSelectedFiles()
            files.each { File file ->
                if(file.name.toLowerCase().endsWith('.jpg')) {
                    Picture picture = new Picture()
                    String fileName = file.name - '.jpg'
                    picture.setFileName(fileName)
                    picture.setExtension('jpg')
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
