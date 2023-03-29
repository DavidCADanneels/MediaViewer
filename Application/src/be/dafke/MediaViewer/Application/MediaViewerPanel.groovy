package be.dafke.MediaViewer.Application

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import java.awt.BorderLayout

class MediaViewerPanel extends JPanel {
    JButton addPicture
    JButton displayPicture

    MediaViewerPanel() {
        setLayout(new BorderLayout())
        JPanel panel = new JPanel()
        JScrollPane scrollPane = new JScrollPane(panel)
        add(scrollPane, BorderLayout.CENTER)

        JPanel north = new JPanel()

        addPicture = new JButton("Add picture ...")
        addPicture.addActionListener { e ->

        }
        north.add(addPicture, BorderLayout.NORTH)

        displayPicture = new JButton("Show picture ...")
        displayPicture.addActionListener { e ->

        }
        north.add(displayPicture, BorderLayout.NORTH)

        add(north, BorderLayout.NORTH)
    }
}