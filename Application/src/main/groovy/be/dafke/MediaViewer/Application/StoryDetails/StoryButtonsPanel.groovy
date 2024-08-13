package be.dafke.MediaViewer.Application.StoryDetails

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.Media.NewMediaDialog
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JPanel

import static java.util.ResourceBundle.getBundle

class StoryButtonsPanel extends JPanel {
    JButton chaptersButton, participantButton, mediaButton, backToStoryOverViewButton, backToStoryDetailsButton

    Story story

    StoryButtonsPanel() {
        chaptersButton = new JButton(getBundle("MediaViewer").getString("SHOW_CHAPTERS_FOR_STORY"))
        chaptersButton.addActionListener { e ->
            Story story = Main.activeStory
            if(story) {
//                Main.chaptersOverviewPanel.setStory()}
                Main.switchView(Main.VIEW_CHAPTERS_FOR_STORY)
            } else {
                System.err.println("Story is 'null'")
            }
        }

        participantButton = new JButton(getBundle("MediaViewer").getString("SHOW_PERSONS_FOR_STORY"))
        participantButton.addActionListener { e ->
            if(story) {
                Main.participantsOverviewPanel.setStory(story)
                Main.switchView(Main.VIEW_PARTICIPANTS_FOR_STORY)
            } else {
                System.err.println("Story is 'null'")
            }
        }

        mediaButton = new JButton(getBundle("MediaViewer").getString("SHOW_MEDIA_FOR_STORY"))
        mediaButton.addActionListener { e ->
            Main.switchView(Main.VIEW_MEDIA_FOR_STORY)
        }

        backToStoryOverViewButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_MAIN")}")
        backToStoryOverViewButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_OVERVIEW)
        }

        backToStoryDetailsButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_DETAILS")}")
        backToStoryDetailsButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_DETAILS)
        }

        add backToStoryOverViewButton
        add backToStoryDetailsButton
        add chaptersButton
        add participantButton
        add mediaButton
    }

    void enableAllButtons(){
        chaptersButton.enabled = story!=null
        participantButton.enabled = story!=null
        mediaButton.enabled = story!=null
        backToStoryOverViewButton.enabled = true
        backToStoryDetailsButton.enabled = story!=null
    }

    void disableAllButtons(){
        chaptersButton.enabled = false
        participantButton.enabled = false
        mediaButton.enabled = false
        backToStoryOverViewButton.enabled = true
        backToStoryDetailsButton.enabled = story!=null
    }

    void setStory(Story story) {
        this.story = story
    }
}
