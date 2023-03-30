package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.NewStory.NewStoryDialog
import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout
import java.awt.Point

import static java.util.ResourceBundle.getBundle

class MediaOverviewPanel extends JPanel {
    MediaOverviewDataModel dataModel
    JButton backToStoryOverViewButton, participantButton, addMediaButton
    static JTable overviewTable

    Story story

    MediaOverviewPanel() {
        setLayout(new BorderLayout())
        dataModel = new MediaOverviewDataModel()
        overviewTable = new JTable(dataModel)
        add new JScrollPane(overviewTable), BorderLayout.CENTER

        backToStoryOverViewButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_MAIN")}")
        backToStoryOverViewButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_OVERVIEW)
        }

        participantButton = new JButton(getBundle("MediaViewer").getString("SHOW_PARTICIPANTS_FOR_STORY"))
        participantButton.addActionListener { e ->
            Main.switchView(Main.VIEW_PARTICIPANTS_FOR_STORY)
        }

        addMediaButton = new JButton(getBundle("MediaViewer").getString("ADD_MEDIA_BUTTON"))
        addMediaButton.addActionListener { e ->
            showDialog()
        }

        JPanel south = new JPanel()
        south.add backToStoryOverViewButton
        south.add participantButton
        south.add addMediaButton

        add south, BorderLayout.SOUTH
    }

    void showDialog(){
        Point locationOnScreen = getLocationOnScreen()
        NewMediaDialog newMediaDialog = new NewMediaDialog()
        newMediaDialog.setLocation(locationOnScreen)
        newMediaDialog.setVisible(true)

    }

    void setStory(Story story) {
        this.story = story
        if(story){
            dataModel.setMediaList(story.getMedia())
        }
    }

//    void setMediaList(List<Media> mediaList) {
//        dataModel.setMediaList(mediaList)
//    }
}
