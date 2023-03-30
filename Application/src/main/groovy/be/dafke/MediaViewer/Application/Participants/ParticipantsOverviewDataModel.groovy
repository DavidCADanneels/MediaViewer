package be.dafke.MediaViewer.Application.Participants

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Interactive.Participant
import be.dafke.MediaViewer.ObjectModel.Media.Story

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class ParticipantsOverviewDataModel extends DefaultTableModel {
    static int FIRST_NAME_COL = 0
    static int LAST_NAME_COL = 1
    static int NR_OF_COL = 2

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    ParticipantsOverviewDataModel() {
        columnClasses.put(FIRST_NAME_COL, String.class)
        columnClasses.put(LAST_NAME_COL, String.class)
        columnNames.put(FIRST_NAME_COL, getBundle("MediaViewer").getString("FIRST_NAME"))
        columnNames.put(LAST_NAME_COL, getBundle("MediaViewer").getString("LAST_NAME"))
    }

    List<Participant> getParticipants(){
        Story story = Main.activeStory
        if(story){
            story.getParticipants()
        } else null
    }

    @Override
    int getRowCount() {
        List<Participant> participants = getParticipants()
        participants?.size()?:0
    }

    @Override
    int getColumnCount() {
        return NR_OF_COL
    }

    @Override
    String getColumnName(int columnIndex) {
        columnNames.get(columnIndex)
    }

    @Override
    Class<?> getColumnClass(int columnIndex) {
        columnClasses.get(columnIndex)
    }

    @Override
    boolean isCellEditable(int rowIndex, int columnIndex) {
        return true
    }

    @Override
    Object getValueAt(int rowIndex, int columnIndex) {
        List<Participant> participants = getParticipants()
        if(participants) {
            Participant participant = participants.get(rowIndex)
            if (participant != null) {
                if (columnIndex == FIRST_NAME_COL) {
                    participant.getFirstName()
                } else if (columnIndex == LAST_NAME_COL) {
                    participant.getLastName()
                } else null
            } else {
                null
            }
        } else null
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {
        List<Participant> participants = getParticipants()
        Participant participant = participants.get(rowIndex)
        if(participant != null) {
            if (columnIndex == FIRST_NAME_COL) {
                String firstName = (String) value
                participant.setFirstName(firstName)
            } else if (columnIndex == LAST_NAME_COL) {
                String lastName = (String) value
                participant.setLastName(lastName)
            }
        }
    }
}
