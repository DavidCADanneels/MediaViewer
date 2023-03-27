package be.dafke.MediaViewer.Application.ChapterView

import be.dafke.MediaViewer.ObjectModel.Chapter
import be.dafke.MediaViewer.ObjectModel.Story

import javax.swing.JPanel

class ChapterOverviewPanel extends JPanel {
    Story mainStory
    Chapter parentChapter

    ChapterOverviewPanel(Story mainStory) {
        this.mainStory = mainStory
    }

    ChapterOverviewPanel(Chapter parentChapter) {
        this.parentChapter = parentChapter
        parentChapter.getStory()
    }
}
