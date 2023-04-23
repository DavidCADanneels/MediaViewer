package be.dafke.MediaViewer.Application.ParticipantsOverview

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.Application.NewParticipant.NewParticipantDialog
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class ParticipantsOverviewPanel extends JPanel {
    ParticipantsOverviewDataModel dataModel
//    JButton addParticipantButton
    JTable overviewTable
    Story story

    ParticipantsOverviewPanel() {
        setLayout(new BorderLayout())
        dataModel = new ParticipantsOverviewDataModel()
        overviewTable = new JTable(dataModel)
        add new JScrollPane(overviewTable), BorderLayout.CENTER


//        addParticipantButton = new JButton(getBundle("MediaViewer").getString("ADD_PARTICIPANTS_BUTTON"))
//        addParticipantButton.addActionListener({ e ->
//            NewParticipantDialog newParticipantDialog = new NewParticipantDialog()
//            newParticipantDialog.setLocation(getLocationOnScreen())
//            newParticipantDialog.visible = true
//        })
        
//        JPanel south = new JPanel()
//
//        south.add addParticipantButton
//
//        add south, BorderLayout.SOUTH
    }

    void setStory(Story story) {
        this.story = story
        dataModel.setStory(story)
    }
//    ParticipantsOverviewDataModel getDataModel() {
//        return dataModel
//    }
}
