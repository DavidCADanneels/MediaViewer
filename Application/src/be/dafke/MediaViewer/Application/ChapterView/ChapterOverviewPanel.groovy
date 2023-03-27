package be.dafke.MediaViewer.Application.ChapterView

import be.dafke.MediaViewer.ObjectModel.Chapter
import be.dafke.MediaViewer.ObjectModel.Story

import javax.swing.JPanel

class ChapterOverviewPanel extends JPanel {
    Story mainStory
    Chapter parentChapter

    ChapterOverviewPanel() {

    }

    void setMainStory(Story mainStory) {
        this.mainStory = mainStory
    }

    void setParentChapter(Chapter parentChapter) {
        this.parentChapter = parentChapter
        mainStory = parentChapter.story
    }
//    ChapterOverviewPanel(Story mainStory) {
//        this.mainStory = mainStory
//    }
//
//    ChapterOverviewPanel(Chapter parentChapter) {
//        this.parentChapter = parentChapter
//        parentChapter.getStory()
//    }
}
