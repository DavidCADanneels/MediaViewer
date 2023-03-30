package be.dafke.MediaViewer.Application.NewStoryView

import javax.swing.JDialog

import static java.util.ResourceBundle.getBundle

class NewStoryDialog extends JDialog {
    NewStoryDialog() {
        setTitle(getBundle("MediaViewer").getString("NEW_STORY_TITLE"))
        setModal(true)
        setDefaultCloseOperation(DISPOSE_ON_CLOSE)
        NewStoryPanel contentPanel = new NewStoryPanel()
        setContentPane(contentPanel)
        pack()
    }
}
