package be.dafke.MediaViewer.Application.StoryOverview


import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout

class StoryOverviewPanel extends JPanel {
    static JTable overviewTable
    static StoryOverviewDataModel dataModel

    StoryOverviewPanel() {
        setLayout(new BorderLayout())
        dataModel = new StoryOverviewDataModel()
        overviewTable = new JTable(dataModel)
        add new JScrollPane(overviewTable), BorderLayout.CENTER
//        add new StoryButtonsPanel(), BorderLayout.SOUTH
    }

    // TODO: add listener on Table entry and open STORY_DETAILS of Selection

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
