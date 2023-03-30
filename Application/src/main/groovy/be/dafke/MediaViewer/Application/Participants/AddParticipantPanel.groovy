package be.dafke.MediaViewer.Application.Participants

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

import static java.util.ResourceBundle.getBundle

class AddParticipantPanel extends JPanel {
    JTextField firstNameField, lastNameField
    JButton createButton
    Story story

    AddParticipantPanel(Story story) {
        this.story = story
        firstNameField = new JTextField(20)
        lastNameField = new JTextField(30)
        createButton = new JButton("Create")
        createButton.addActionListener { e ->
            showDialog()
        }
        JPanel line1 = new JPanel()
        line1.add new JLabel("${getBundle("MediaViewer").getString("FIRST_NAME")}:")
        line1.add firstNameField
//        line1.add createButton

//        JPanel line2 = new JPanel()
        line1.add new JLabel("${getBundle("MediaViewer").getString("LAST_NAME")}:")
        line1.add lastNameField
        line1.add createButton

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS))
        add line1
//        add line2
    }

    void showDialog(){
        Participant participant = new Participant()
        participant.setFirstName(firstNameField.getText().trim())
        participant.setLastName(lastNameField.getText().trim())
        story.addParticipant(participant)
        Main.participantsOverviewPanel.getDataModel().fireTableDataChanged()

    }
}
