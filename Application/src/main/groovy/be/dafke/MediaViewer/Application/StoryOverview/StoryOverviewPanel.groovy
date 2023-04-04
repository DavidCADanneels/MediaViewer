package be.dafke.MediaViewer.Application.StoryOverview

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.DefaultListSelectionModel
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

        DefaultListSelectionModel selection = new DefaultListSelectionModel()
        selection.addListSelectionListener({ e ->
            if (!e.getValueIsAdjusting()) {
                Set<Story> set = Main.storyMap.keySet()
                int rowIndex = overviewTable.getSelectedRow()
                Story story = set.getAt(rowIndex)
                if (story != null) {
                    Main.activeStory = story
                    Main.switchView(Main.VIEW_STORY_DETAILS)
                }
            }
        })
        overviewTable.setSelectionModel(selection)

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
