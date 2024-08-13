package be.dafke.MediaViewer.Application.NewParticipant

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.People.Person
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

import static java.util.ResourceBundle.getBundle

class AddParticipantPanel extends JPanel {
    JTextField firstNameField, lastNameField
    JButton createButton

    AddParticipantPanel() {
        firstNameField = new JTextField(20)
        lastNameField = new JTextField(30)
        createButton = new JButton("Create")
        createButton.addActionListener({ e -> showDialog() })

        JPanel line1 = new JPanel()
        line1.add new JLabel("${getBundle("MediaViewer").getString("FIRST_NAME")}:")
        line1.add firstNameField

        JPanel line2 = new JPanel()
        line2.add new JLabel("${getBundle("MediaViewer").getString("LAST_NAME")}:")
        line2.add lastNameField
        line2.add createButton

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS))
        add line1
        add line2
    }

    void showDialog(){
        String firstName = firstNameField.text.trim()
        String lastName = lastNameField.text.trim()
        if(firstName && lastName) {
            Person participant = new Person()
            participant.setFirstName(firstName)
            participant.setLastName(lastName)
            Story story = Main.activeStory
            if (story!=null && story.getPersons()!=null) {
                List<Person> participants = story.getPersons()
                participants.add(participant)
                Main.participantsOverviewPanel.dataModel.fireTableDataChanged()
                firstNameField.text = ''
                lastNameField.text = ''
            }
        }
    }
}
