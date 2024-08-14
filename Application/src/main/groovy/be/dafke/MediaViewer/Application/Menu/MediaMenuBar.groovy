package be.dafke.MediaViewer.Application.Menu


import javax.swing.JMenu
import javax.swing.JMenuBar
import java.awt.event.KeyEvent

class MediaMenuBar extends JMenuBar {
    JMenu fileMenu, mainMenu

    MediaMenuBar() {
//        mainMenu = new MainMenu()
//        add mainMenu
        fileMenu = new FileMenu()
        fileMenu.setMnemonic(KeyEvent.VK_F)
        add fileMenu
    }
}
