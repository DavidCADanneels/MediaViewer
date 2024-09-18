package be.dafke.MediaViewer.Application.Boxes

import be.dafke.MediaViewer.ObjectModel.Media.Book
import be.dafke.MediaViewer.ObjectModel.Media.Media
import be.dafke.MediaViewer.ObjectModel.Media.MediaBox
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JSplitPane
import java.awt.BorderLayout

class BoxesOverviewPanel extends JPanel {

    Story story
    BoxesOverviewTablePanel boxesOverviewTablePanel
    BoxContentTablePanel boxContentTablePanel

    BoxesOverviewPanel() {
        setLayout(new BorderLayout())

        boxContentTablePanel = new BoxContentTablePanel()
        boxesOverviewTablePanel = new BoxesOverviewTablePanel(boxContentTablePanel)

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT)
        splitPane.add boxesOverviewTablePanel, JSplitPane.TOP
        splitPane.add boxContentTablePanel, JSplitPane.BOTTOM

        add splitPane, BorderLayout.CENTER

        JPanel east = new JPanel()
        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS))
        JButton addBoxButton = new JButton('Add box')
        addBoxButton.addActionListener { e -> addBox() }
        JButton addBookButton = new JButton('Add book to box')
        addBookButton.addActionListener { e -> addBook() }
//        JButton addAuthorButton = new JButton('Add author')
//        addAuthorButton.addActionListener { e -> addAuthor() }
        east.add addBoxButton
        east.add addBookButton

        add east, BorderLayout.EAST
    }

    void setStory(Story story) {
        this.story = story
        boxesOverviewTablePanel.dataModel.setMediaBoxes(story.mediaBoxes)
//        boxContentTablePanel.setStory(story)
    }

    void addBox(){
        String answerNr = JOptionPane.showInputDialog("Give Nr")
        try {
            Integer nr = Integer.parseInt(answerNr)
            MediaBox box = new MediaBox()
            box.nr = nr
            box.name = JOptionPane.showInputDialog("Give Name")
            story.mediaBoxes.add(box)
            boxesOverviewTablePanel.dataModel.fireTableDataChanged()
        } catch (NumberFormatException nfe){
            // do nothing
        }
    }

    void addBook(){
        String title = JOptionPane.showInputDialog("Give Title")
        Book book = new Book()
        book.title = title
        MediaBox mediaBox = boxesOverviewTablePanel.getSingleSelectedMediaBox()
        if(mediaBox) {
            mediaBox.content.add book
            book.boxNr = mediaBox.nr
        }
    }

//    void addAuthor(){
//        Media media = boxContentTablePanel.getSelectedMedia()
//        if(media instanceof Book){
//            Book book = (Book)media
//            book.author = JOptionPane.showInputDialog("Give Author")
////            book.addAuthor(author)
//        }
//    }
}
