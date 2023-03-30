package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.NewStory.NewStoryPanel

import javax.swing.*

import static java.util.ResourceBundle.getBundle

class NewMediaDialog extends JDialog {
    NewMediaDialog() {
        setTitle(getBundle("MediaViewer").getString("ADD_MEDIA_TITLE"))
        setModal(true)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        AddMediaPanel contentPanel = new AddMediaPanel()
        setContentPane(contentPanel)
        pack()
    }
}
