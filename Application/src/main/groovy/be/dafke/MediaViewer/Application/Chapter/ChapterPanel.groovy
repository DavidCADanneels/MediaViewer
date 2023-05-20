package be.dafke.MediaViewer.Application.Chapter

import be.dafke.MediaViewer.ObjectModel.Stories.Chapter

import javax.swing.JLabel
import javax.swing.JPanel

class ChapterPanel extends JPanel {
    JLabel title

    ChapterPanel() {
        title = new JLabel("none")
        add title
    }

    void setChapter(Chapter chapter){
        title.setText(chapter?.getTitle()?:'none')
    }
}
