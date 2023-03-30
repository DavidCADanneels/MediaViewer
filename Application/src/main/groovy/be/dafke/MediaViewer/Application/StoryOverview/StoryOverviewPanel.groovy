package be.dafke.MediaViewer.Application.StoryOverview

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Story

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class StoryOverviewPanel extends JPanel {
    JButton detailsButton
    static JTable overviewTable
    static StoryOverviewDataModel dataModel

    StoryOverviewPanel() {
        setLayout(new BorderLayout())
        add createCenterPanel(), BorderLayout.CENTER
        add createBottomPanel(), BorderLayout.SOUTH
    }

    JPanel createBottomPanel(){
        JPanel panel = new JPanel()
        detailsButton = new JButton(getBundle("MediaViewer").getString("SHOW_CHAPTERS_FOR_STORY"))
        detailsButton.addActionListener { e ->
            Story story = getSelectedItem()
            if(story) {
                Main.switchView(Main.STORY_DETAILS)
            } else {
                System.err.println("Story is 'null'")
            }
        }
        panel.add detailsButton
        panel
    }

    JScrollPane createCenterPanel(){
        dataModel = new StoryOverviewDataModel()
        overviewTable = new JTable(dataModel)
        new JScrollPane(overviewTable)
    }

    Story getSelectedItem(){
        int row = overviewTable.getSelectedRow()
        if(row == -1){
            // TODO: disable button of none is selected
            return null
        } else {
            Main.stories.get(row)
        }
    }

}
