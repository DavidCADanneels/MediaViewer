package be.dafke.MediaViewer.Application.Participants

import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.*

import static java.util.ResourceBundle.getBundle

class NewParticipantDialog extends JDialog {
    NewParticipantDialog(Story story) {
        setTitle(getBundle("MediaViewer").getString("ADD_PARTICIPANT_TITLE"))
        setModal(true)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        AddParticipantPanel contentPanel = new AddParticipantPanel(story)
        setContentPane(contentPanel)
        pack()
    }
}
