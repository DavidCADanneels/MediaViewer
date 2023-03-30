package be.dafke.MediaViewer.Application.Menu

import javax.swing.JMenu
import javax.swing.JMenuItem

import static java.util.ResourceBundle.getBundle

class StoryMenu extends JMenu {
    JMenuItem openStory, saveStory

    StoryMenu() {
        super(getBundle("MediaViewer").getString('STORIES'))
        openStory = new JMenuItem()
//        openStory.addActionListener { e -> readFile() }
        saveStory = new JMenuItem()
//        saveStory.addActionListener { e -> writeFile() }
    }

//    void readFile(){
//        JFileChooser chooser = new JFileChooser()
//        chooser.setMultiSelectionEnabled(false)
//        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//            File file = chooser.getSelectedFile()
//            // TODO: parse xml to Story object and and switch to Chapter View for that Story
//        }
//    }
//
//    void writeFile(){
//
//        StoryOverviewPanel.saveStory()
//    }

//    void actionPerformed(ActionEvent e) {
//
//    }
}
