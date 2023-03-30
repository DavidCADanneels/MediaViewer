package be.dafke.MediaViewer.Application.Menu

import be.dafke.MediaViewer.Application.Main

import javax.swing.JMenu
import javax.swing.JMenuItem

import static java.util.ResourceBundle.getBundle

class MainMenu extends JMenu  {
    JMenuItem storyView, chapterView

    MainMenu() {
        super(getBundle("MediaViewer").getString("MAIN_MENU"))

        storyView = new JMenuItem("Stories")
        storyView.addActionListener { e -> Main.switchView(Main.STORIES) }

        chapterView = new JMenuItem("Chapter")
        chapterView.addActionListener  { e -> Main.switchView(Main.CHAPTERS) }

        add storyView
        add chapterView
    }
}
