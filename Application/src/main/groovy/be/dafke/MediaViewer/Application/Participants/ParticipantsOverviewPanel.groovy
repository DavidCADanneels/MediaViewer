package be.dafke.MediaViewer.Application.Participants

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Interactive.Participant

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class ParticipantsOverviewPanel extends JPanel {
    ParticipantsOverviewDataModel dataModel
    JButton backToStoryOverView
    static JTable overviewTable

    ParticipantsOverviewPanel() {
        setLayout(new BorderLayout())
        dataModel = new ParticipantsOverviewDataModel()
        overviewTable = new JTable(dataModel)
        add new JScrollPane(overviewTable), BorderLayout.CENTER

        backToStoryOverView = new JButton("${getBundle("MediaViewer").getString("BACK_TO_MAIN")}")
        backToStoryOverView.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_OVERVIEW)
        }

        add backToStoryOverView, BorderLayout.SOUTH
    }

    void setParticipants(List<Participant> participants) {
        dataModel.setParticipants(participants)
    }
}
