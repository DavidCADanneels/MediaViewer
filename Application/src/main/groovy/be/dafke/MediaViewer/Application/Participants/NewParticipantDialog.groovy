package be.dafke.MediaViewer.Application.Participants

import javax.swing.*

import static java.util.ResourceBundle.getBundle

class NewParticipantDialog extends JDialog {
    AddParticipantPanel contentPanel

    NewParticipantDialog() {
        setTitle(getBundle("MediaViewer").getString("ADD_PARTICIPANT_TITLE"))
        setModal(true)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        contentPanel = new AddParticipantPanel()
        setContentPane(contentPanel)
        pack()
    }
}
