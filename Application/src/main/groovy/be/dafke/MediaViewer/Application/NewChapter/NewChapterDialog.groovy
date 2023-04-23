package be.dafke.MediaViewer.Application.NewChapter

import javax.swing.JDialog

import static java.util.ResourceBundle.getBundle

class NewChapterDialog extends JDialog {
    AddChapterPanel contentPanel

    NewChapterDialog() {
        setTitle(getBundle("MediaViewer").getString("ADD_CHAPTER_TITLE"))
        setModal(true)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        contentPanel = new AddChapterPanel()
        setContentPane(contentPanel)
        pack()
    }
}
