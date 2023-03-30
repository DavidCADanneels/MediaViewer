package be.dafke.MediaViewer.Application.Menu


import javax.swing.JMenu
import javax.swing.JMenuBar

class MediaMenuBar extends JMenuBar {
    JMenu fileMenu, mainMenu

    MediaMenuBar() {
//        mainMenu = new MainMenu()
//        add mainMenu
        fileMenu = new FileMenu()
        add fileMenu
    }
}
