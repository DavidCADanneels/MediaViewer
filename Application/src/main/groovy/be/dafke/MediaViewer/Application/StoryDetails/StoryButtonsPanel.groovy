package be.dafke.MediaViewer.Application.StoryDetails

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.JButton
import javax.swing.JPanel

import static java.util.ResourceBundle.getBundle

class StoryButtonsPanel extends JPanel {
    JButton chaptersButton, participantButton, mediaButton

    StoryButtonsPanel() {
//        chaptersButton = new JButton(getBundle("MediaViewer").getString("SHOW_CHAPTERS_FOR_STORY"))
//        chaptersButton.addActionListener { e ->
//            Story story = Main.activeStory
//            if(story) {
//                Main.
//                Main.switchView(Main.VIEW_STORY_DETAILS)
//            } else {
//                System.err.println("Story is 'null'")
//            }
//        }

        participantButton = new JButton(getBundle("MediaViewer").getString("SHOW_PARTICIPANTS_FOR_STORY"))
        participantButton.addActionListener { e ->
            Story story = Main.activeStory
            if(story) {
//                Main.participantsOverviewPanel.setStory(story)
                Main.participantsOverviewPanel.dataModel.fireTableDataChanged()
                Main.switchView(Main.VIEW_PARTICIPANTS_FOR_STORY)
            } else {
                System.err.println("Story is 'null'")
            }
        }

        mediaButton = new JButton(getBundle("MediaViewer").getString("SHOW_MEDIA_FOR_STORY"))
        mediaButton.addActionListener { e ->
            Story story = Main.activeStory
            if(story) {
//                Main.mediaOverviewPanel.setStory(story)
                Main.mediaOverviewPanel.dataModel.fireTableDataChanged()
                Main.switchView(Main.VIEW_MEDIA_FOR_STORY)
            } else {
                System.err.println("Story is 'null'")
            }
        }


//        add chaptersButton
        add participantButton
        add mediaButton
    }

//    Story getSelectedItem(){
//        int row = overviewTable.getSelectedRow()
//        if(row == -1){
//            // TODO: disable button of none is selected
//            return null
//        } else {
//            Main.stories.get(row)
//        }
//    }
}
