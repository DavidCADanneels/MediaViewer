package be.dafke.MediaViewer.Application.StoryDetails

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JPanel

import static java.util.ResourceBundle.getBundle

class StoryButtonsPanel extends JPanel {
    JButton picturesPerChapterButton, textPerChapterButton, participantButton, mediaButton, backToStoryOverViewButton, backToStoryDetailsButton

    Story story

    StoryButtonsPanel() {
        picturesPerChapterButton = new JButton(getBundle("MediaViewer").getString("SHOW_PICTURES_PER_CHAPTER"))
        picturesPerChapterButton.addActionListener { e ->
            Story story = Main.activeStory
            if(story) {
//                Main.chaptersOverviewPanel.setStory()}
                Main.switchView(Main.VIEW_MEDIA_PER_CHAPTER)
            } else {
                System.err.println("Story is 'null'")
            }
        }

        textPerChapterButton = new JButton(getBundle("MediaViewer").getString("SHOW_TEXT_PER_CHAPTER"))
        textPerChapterButton.addActionListener { e ->
            Story story = Main.activeStory
            if(story) {
//                Main.chaptersOverviewPanel.setStory()}
                Main.switchView(Main.VIEW_TEXT_PER_CHAPTER)
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
            Main.switchView(Main.VIEW_ALL_PICTURES_FOR_STORY)
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
        add picturesPerChapterButton
        add textPerChapterButton
        add participantButton
        add mediaButton
    }

    void enableAllButtons(){
        picturesPerChapterButton.enabled = story!=null
        textPerChapterButton.enabled = story!=null
        participantButton.enabled = story!=null
        mediaButton.enabled = story!=null
        backToStoryOverViewButton.enabled = true
        backToStoryDetailsButton.enabled = story!=null
    }

    void disableAllButtons(){
        picturesPerChapterButton.enabled = false
        textPerChapterButton.enabled = false
        participantButton.enabled = false
        mediaButton.enabled = false
        backToStoryOverViewButton.enabled = true
        backToStoryDetailsButton.enabled = story!=null
    }

    void setStory(Story story) {
        this.story = story
    }
}
