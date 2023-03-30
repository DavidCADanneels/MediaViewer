package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Media

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTable
import java.awt.BorderLayout

import static java.util.ResourceBundle.getBundle

class MediaOverviewPanel extends JPanel {
    MediaOverviewDataModel dataModel
    JButton backToStoryOverViewButton, participantButton
    static JTable overviewTable

    MediaOverviewPanel() {
        setLayout(new BorderLayout())
        dataModel = new MediaOverviewDataModel()
        overviewTable = new JTable(dataModel)
        add new JScrollPane(overviewTable), BorderLayout.CENTER

        backToStoryOverViewButton = new JButton("${getBundle("MediaViewer").getString("BACK_TO_MAIN")}")
        backToStoryOverViewButton.addActionListener { e ->
            Main.switchView(Main.VIEW_STORY_OVERVIEW)
        }

        add backToStoryOverViewButton, BorderLayout.SOUTH

        participantButton = new JButton(getBundle("MediaViewer").getString("SHOW_PARTICIPANTS_FOR_STORY"))
        participantButton.addActionListener { e ->
            Main.switchView(Main.VIEW_PARTICIPANTS_FOR_STORY)
        }

        JPanel south = new JPanel()
        south.add backToStoryOverViewButton
        south.add participantButton

        add south, BorderLayout.SOUTH
    }

    void setMediaList(List<Media> mediaList) {
        dataModel.setMediaList(mediaList)
    }
}
