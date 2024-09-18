package be.dafke.MediaViewer.Application.Boxes

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JSplitPane
import java.awt.BorderLayout

class BoxesOverviewPanel extends JPanel {

    BoxesOverviewTablePanel boxesOverviewTablePanel
    BoxContentTablePanel boxContentTablePanel

    BoxesOverviewPanel() {
        setLayout(new BorderLayout())

        boxesOverviewTablePanel = new BoxesOverviewTablePanel()
        boxContentTablePanel = new BoxContentTablePanel()

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
        east.add addBoxButton
        east.add addBookButton

        add east, BorderLayout.EAST
    }

    void addBox(){

    }

    void addBook(){

    }
}
