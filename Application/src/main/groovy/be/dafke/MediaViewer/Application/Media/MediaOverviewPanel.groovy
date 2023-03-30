package be.dafke.MediaViewer.Application.Media

import javax.swing.JPanel
import java.awt.BorderLayout

class MediaOverviewPanel extends JPanel {

    MediaOverviewPanel() {
        setLayout(new BorderLayout())
        MediaOverviewDataModel dataModel = new MediaOverviewDataModel()
    }
}
