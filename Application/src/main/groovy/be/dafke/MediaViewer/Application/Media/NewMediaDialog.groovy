package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.*

import static java.util.ResourceBundle.getBundle

class NewMediaDialog extends JDialog {
    AddMediaPanel contentPanel

    NewMediaDialog(Story story, Chapter chapter) {
        setTitle(getBundle("MediaViewer").getString("ADD_MEDIA_TITLE"))
        setModal(true)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        contentPanel = new AddMediaPanel(story, chapter)
        setContentPane(contentPanel)
        pack()
    }
}
