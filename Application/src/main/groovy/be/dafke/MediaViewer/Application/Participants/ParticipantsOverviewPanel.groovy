package be.dafke.MediaViewer.Application.Participants

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.Media.NewMediaDialog
import be.dafke.MediaViewer.Application.NewStory.NewStoryDialog
import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class ParticipantsOverviewPanel extends JPanel {
    ParticipantsOverviewDataModel dataModel
    JButton backToStoryDetailsButton, backToStoryOverViewButton, mediaButton, addParticipantButton
    JTable overviewTable

    ParticipantsOverviewPanel() {
        setLayout(new BorderLayout())
        dataModel = new ParticipantsOverviewDataModel()
        overviewTable = new JTable(dataModel)
        add new JScrollPane(overviewTable), BorderLayout.CENTER

        backToStoryOverViewButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_MAIN")}")
        backToStoryOverViewButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_OVERVIEW)
        }

        backToStoryDetailsButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_MAIN")}")
        backToStoryDetailsButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_DETAILS)
        }

        mediaButton = new JButton(getBundle("MediaViewer").getString("SHOW_MEDIA_FOR_STORY"))
        mediaButton.addActionListener { e ->
            Main.switchView(Main.VIEW_MEDIA_FOR_STORY)
        }

        addParticipantButton = new JButton(getBundle("MediaViewer").getString("ADD_PARTICIPANTS_BUTTON"))
        addParticipantButton.addActionListener({ e ->
            NewParticipantDialog newParticipantDialog = new NewParticipantDialog()
            newParticipantDialog.setLocation(getLocationOnScreen())
            newParticipantDialog.visible = true
        })
        
        JPanel south = new JPanel()
//        south.add backToStoryOverViewButton
        south.add backToStoryDetailsButton
        south.add mediaButton
//        south.add addParticipantButton

        add south, BorderLayout.SOUTH
    }

//    ParticipantsOverviewDataModel getDataModel() {
//        return dataModel
//    }
}
