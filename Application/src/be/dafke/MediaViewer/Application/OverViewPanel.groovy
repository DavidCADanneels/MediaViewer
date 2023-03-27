package be.dafke.MediaViewer.Application

import javax.swing.*
import javax.swing.table.TableModel
import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class OverViewPanel extends JPanel {

    OverViewPanel(TableModel tableModel) {
        JTable table = new JTable(tableModel)
        JScrollPane scrollPane = new JScrollPane(table)
        setLayout(new BorderLayout())
        add(scrollPane, BorderLayout.CENTER)
    }

}