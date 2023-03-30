package be.dafke.MediaViewer.Application.StoryOverview

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class StoryOverviewPanel extends JPanel {
    JButton chaptersButton, participantButton, mediaButton
    static JTable overviewTable
    static StoryOverviewDataModel dataModel

    StoryOverviewPanel() {
        setLayout(new BorderLayout())
        add createCenterPanel(), BorderLayout.CENTER
        add createBottomPanel(), BorderLayout.SOUTH
    }

    JPanel createBottomPanel(){
        JPanel panel = new JPanel()
        chaptersButton = new JButton(getBundle("MediaViewer").getString("SHOW_CHAPTERS_FOR_STORY"))
        chaptersButton.addActionListener { e ->
            Story story = getSelectedItem()
            if(story) {
                Main.switchView(Main.VIEW_STORY_DETAILS)
            } else {
                System.err.println("Story is 'null'")
            }
        }

        participantButton = new JButton(getBundle("MediaViewer").getString("SHOW_PARTICIPANTS_FOR_STORY"))
        participantButton.addActionListener { e ->
            Story story = getSelectedItem()
            if(story) {
                Main.setActiveStory(story)
                Main.switchView(Main.VIEW_PARTICIPANTS_FOR_STORY)
//                Main.participantsOverviewPanel.setStory(story)
//                Main.participantsOverviewPanel.setParticipants(story.getParticipants())
            } else {
                System.err.println("Story is 'null'")
            }
        }

        mediaButton = new JButton(getBundle("MediaViewer").getString("SHOW_MEDIA_FOR_STORY"))
        mediaButton.addActionListener { e ->
            Story story = getSelectedItem()
            if(story) {
                Main.switchView(Main.VIEW_MEDIA_FOR_STORY)
                Main.mediaOverviewPanel.setStory(story)
//                Main.mediaOverviewPanel.setMediaList(story.getMedia())
            } else {
                System.err.println("Story is 'null'")
            }
        }


//        panel.add chaptersButton
        panel.add participantButton
        panel.add mediaButton

        panel
    }

    JScrollPane createCenterPanel(){
        dataModel = new StoryOverviewDataModel()
        overviewTable = new JTable(dataModel)
        new JScrollPane(overviewTable)
    }

    Story getSelectedItem(){
        int row = overviewTable.getSelectedRow()
        if(row == -1){
            // TODO: disable button of none is selected
            return null
        } else {
            Main.stories.get(row)
        }
    }

}
