package be.dafke.MediaViewer.Application.Menu

import be.dafke.MediaViewer.Application.MainMenu

import javax.swing.JMenu
import javax.swing.JMenuBar

class MediaMenuBar extends JMenuBar {
    JMenu mainMenu

    MediaMenuBar() {
        mainMenu = new MainMenu()
        add mainMenu
    }
}
