package be.dafke.MediaViewer.Application

import javax.swing.JMenu
import javax.swing.JMenuItem
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import static java.util.ResourceBundle.getBundle

class MainMenu extends JMenu implements ActionListener {
    JMenuItem storyView, chapterView

    MainMenu() {
        super(getBundle("MediaViewer").getString("MAIN_MENU"))

        storyView = new JMenuItem("Stories")
        storyView.addActionListener this

        chapterView = new JMenuItem("Chapter")
        chapterView.addActionListener this

        add storyView
        add chapterView
    }

    void actionPerformed(ActionEvent e) {
        Object source = e.getSource()
        if(source == storyView){
            Main.switchView(Main.STORIES)
        } else if(source == chapterView){
            Main.switchView(Main.CHAPTERS)
        }
    }
}
