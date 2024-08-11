package be.dafke.MediaViewer.Application.ParticipantsOverview

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.People.Participant
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.table.DefaultTableModel

import static java.util.ResourceBundle.getBundle

class ParticipantsOverviewDataModel extends DefaultTableModel {
    static int FIRST_NAME_COL = 0
    static int LAST_NAME_COL = 1
    static int NR_OF_COL = 2

    HashMap<Integer,String> columnNames = [:]
    HashMap<Integer,Class> columnClasses = [:]

    Story story
    List<Participant> participants

    ParticipantsOverviewDataModel() {
        participants = []
        columnClasses.put(FIRST_NAME_COL, String.class)
        columnClasses.put(LAST_NAME_COL, String.class)
        columnNames.put(FIRST_NAME_COL, getBundle("MediaViewer").getString("FIRST_NAME"))
        columnNames.put(LAST_NAME_COL, getBundle("MediaViewer").getString("LAST_NAME"))
    }

    void setStory(Story story) {
        this.story = story
        participants = story.getParticipants()
        fireTableDataChanged()
    }

    @Override
    int getRowCount() {
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
    }

    @Override
    void setValueAt(Object value, int rowIndex, int columnIndex) {
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
