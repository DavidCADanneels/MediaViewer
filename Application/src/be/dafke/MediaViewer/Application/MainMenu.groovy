package be.dafke.MediaViewer.Application

import javax.swing.JMenu
import javax.swing.JMenuItem

import static java.util.ResourceBundle.getBundle

class MainMenu extends JMenu {
    JMenuItem storyView, chapterView

    MainMenu() {
        super(getBundle("MediaViewer").getString("MAIN_MENU"))
        storyView = new JMenuItem("Stories")
        chapterView = new JMenuItem("Chapter")
        add storyView
        add chapterView
    }
}
