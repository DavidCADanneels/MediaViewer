package be.dafke.MediaViewer.Application.Boxes

import be.dafke.MediaViewer.ObjectModel.Media.Book
import be.dafke.MediaViewer.ObjectModel.Media.CD
import be.dafke.MediaViewer.ObjectModel.Media.DVD
import be.dafke.MediaViewer.ObjectModel.Media.MediaBox
import be.dafke.MediaViewer.ObjectModel.Media.VHS
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

        JButton addCdButton = new JButton('Add CD to box')
        addCdButton.addActionListener { e -> addCd() }

        JButton addDvdButton = new JButton('Add DVD to box')
        addDvdButton.addActionListener { e -> addDvd() }

        JButton addVhsButton = new JButton('Add VHS to box')
        addVhsButton.addActionListener { e -> addVhs() }

        east.add addBoxButton
        east.add addBookButton
        east.add addCdButton
        east.add addDvdButton
        east.add addVhsButton

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
        String author = JOptionPane.showInputDialog("Give Author")
        book.author = author
        MediaBox mediaBox = boxesOverviewTablePanel.getSingleSelectedMediaBox()
        if(mediaBox) {
            mediaBox.books.add book
            book.boxNr = mediaBox.nr
            boxContentTablePanel.boxContentDataModel.fireTableDataChanged()
        }
    }


    void addCd(){
        String title = JOptionPane.showInputDialog("Give Album Title")
        CD cd = new CD()
        cd.albumTitle = title
        String author = JOptionPane.showInputDialog("Give Artist")
        cd.artist = author
        MediaBox mediaBox = boxesOverviewTablePanel.getSingleSelectedMediaBox()
        if(mediaBox) {
            mediaBox.cds.add cd
            cd.boxNr = mediaBox.nr
//            boxesOverviewTablePanel.dataModel.fireTableDataChanged()
            boxContentTablePanel.boxContentDataModel.fireTableDataChanged()
        }
    }

    void addDvd(){
        String title = JOptionPane.showInputDialog("Give Title")
        DVD dvd = new DVD()
        dvd.title = title
        String author = JOptionPane.showInputDialog("Give Author")
        dvd.author = author
        MediaBox mediaBox = boxesOverviewTablePanel.getSingleSelectedMediaBox()
        if(mediaBox) {
            mediaBox.dvds.add dvd
            dvd.boxNr = mediaBox.nr
            boxContentTablePanel.boxContentDataModel.fireTableDataChanged()
        }
    }

    void addVhs(){
        String title = JOptionPane.showInputDialog("Give Title")
        VHS vhs = new VHS()
        vhs.title = title
        String author = JOptionPane.showInputDialog("Give Author")
        vhs.author = author
        MediaBox mediaBox = boxesOverviewTablePanel.getSingleSelectedMediaBox()
        if(mediaBox) {
            mediaBox.vhss.add vhs
            vhs.boxNr = mediaBox.nr
            boxContentTablePanel.boxContentDataModel.fireTableDataChanged()
        }
    }
}
