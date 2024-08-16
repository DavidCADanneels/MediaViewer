package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.*

import static java.util.ResourceBundle.getBundle

class NewTextDialog extends JDialog {
    AddTextPanel contentPanel

    NewTextDialog(Story story, Chapter chapter) {
        setTitle(getBundle("MediaViewer").getString("ADD_TEXT_TITLE"))
        setModal(true)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        contentPanel = new AddTextPanel(story, chapter)
        setContentPane(contentPanel)
        pack()
    }
}
