package be.dafke.MediaViewer.Application.StoryDetails

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.Media.NewMediaDialog
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JPanel

import static java.util.ResourceBundle.getBundle

class StoryButtonsPanel extends JPanel {
    JButton chaptersButton, participantButton, mediaButton, backToStoryOverViewButton, backToStoryDetailsButton, addMediaButton

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

        participantButton = new JButton(getBundle("MediaViewer").getString("SHOW_PARTICIPANTS_FOR_STORY"))
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

        addMediaButton = new JButton(getBundle("MediaViewer").getString("ADD_MEDIA_BUTTON"))
        addMediaButton.addActionListener { e -> loadData() }

        add backToStoryOverViewButton
        add backToStoryDetailsButton
        add chaptersButton
        add participantButton
        add mediaButton
        add addMediaButton
    }

    void loadData(){
        // FIXME:
        // 1. Ask to Select Chapter as well, use same value for index (for now)
        // 2. Create new Dialog with input fields for:
        //      - Owner
        //      - Chapter
        //      - Index (auto-update proposal == Chapter-index) (editable: 0100 -> 010001)
//        Chapter chapter = null
//        String pictureIndex = ""

        NewMediaDialog newMediaDialog = new NewMediaDialog(story)
        newMediaDialog.setLocation(getLocationOnScreen())
        newMediaDialog.visible = true

//        Main.addMedia(story)
    }

    void enableAllButtons(){
        chaptersButton.enabled = story!=null
        participantButton.enabled = story!=null
        mediaButton.enabled = story!=null
        backToStoryOverViewButton.enabled = true
        backToStoryDetailsButton.enabled = story!=null
        addMediaButton.enabled = false
    }

    void disableAllButtons(){
        chaptersButton.enabled = false
        participantButton.enabled = false
        mediaButton.enabled = false
        backToStoryOverViewButton.enabled = true
        backToStoryDetailsButton.enabled = story!=null
        addMediaButton.enabled = false
    }

    void setStory(Story story) {
        this.story = story
    }
}
