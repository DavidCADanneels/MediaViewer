package be.dafke.MediaViewer.Application

import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JScrollPane
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

class MediaViewerPanel extends JPanel implements ActionListener {
    JButton addPicture
    JButton displayPicture

    MediaViewerPanel() {
        setLayout(new BorderLayout())
        JPanel panel = new JPanel()
        JScrollPane scrollPane = new JScrollPane(panel)
        add(scrollPane, BorderLayout.CENTER)

        JPanel north = new JPanel()

        addPicture = new JButton("Add picture ...")
        addPicture.addActionListener(this)
        north.add(addPicture, BorderLayout.NORTH)

        displayPicture = new JButton("Show picture ...")
        displayPicture.addActionListener(this)
        north.add(displayPicture, BorderLayout.NORTH)

        add(north, BorderLayout.NORTH)
    }

    @Override
    void actionPerformed(ActionEvent e) {

    }
}