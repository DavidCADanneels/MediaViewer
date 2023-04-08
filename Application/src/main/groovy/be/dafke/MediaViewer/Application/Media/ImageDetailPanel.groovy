package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JTextField

class ImageDetailPanel extends JPanel{
    JTextField sizeField
    JLabel sizeLabel
    JPanel line1
    Picture picture
    Story story
    List<Picture> pictures
    boolean singleSelection
    JButton assignOwnerButton
    MediaOverviewPanel mediaOverviewPanel
    static String singleText = "Assign Owner to Picture"
    static String multiText = "Assign Owner to Pictures"

    ImageDetailPanel(MediaOverviewPanel mediaOverviewPanel) {
        this.mediaOverviewPanel = mediaOverviewPanel
        setLayout new BoxLayout(this, BoxLayout.Y_AXIS)

        sizeField = new JTextField(20)
        sizeField.setEnabled false
        line1 = new JPanel()
        sizeLabel = new JLabel("Size:")
        line1.add sizeLabel
        line1.add sizeField

        singleSelection = true
        assignOwnerButton = new JButton(singleText)
        assignOwnerButton.addActionListener {e ->
            assignOwner()
        }

        add line1
        add assignOwnerButton
    }

    void assignOwner(){
        Object [] participants = story.getParticipants().toArray()
        int nr = JOptionPane.showOptionDialog(this, "Select Owner", "Assign Owner",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                participants, null)
        if(nr != -1) {
            if(singleSelection){
                picture.setOwner(nr)
            } else {
                pictures.each {Picture picture ->
                    picture.setOwner(nr)
                }
            }
            mediaOverviewPanel.imageTablePanel.dataModel.fireTableDataChanged()
        }
    }

    void setStory(Story story) {
        this.story = story
    }

    void setPictures(List<Picture> pictures){
        this.pictures = pictures
    }

    void setPicture(Picture picture){
        this.picture = picture
        if(picture) {
            sizeField.setText "${picture.getWidth()} x ${picture.getHeight()}"
        } else {
            sizeField.setText ''
        }
    }

    void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection
        line1.setVisible(singleSelection)
        if(singleSelection){
            assignOwnerButton.setText(singleText)
        } else {
            assignOwnerButton.setText(multiText)
        }
    }
}
