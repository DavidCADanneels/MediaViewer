package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
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
    JButton assignOwnerButton, assignIndexButton, assignChapterButton
    MediaOverviewPanel mediaOverviewPanel
    static String singleTextOwner = "Assign Owner to Picture"
    static String multiTextOwner = "Assign Owner to Pictures"
    static String singleTextIndex = "Assign Index to Picture"
    static String multiTextIndex = "Assign Index to Pictures"
    static String singleTextChapter = "Assign Chapter to Picture"
    static String multiTextChapter = "Assign Chapter to Pictures"

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
        assignOwnerButton = new JButton(singleTextOwner)
        assignOwnerButton.addActionListener {e ->
            assignOwner()
        }
        assignIndexButton = new JButton(singleTextIndex)
        assignIndexButton.addActionListener {e ->
            assignIndex()
        }
        assignChapterButton = new JButton(singleTextChapter)
        assignChapterButton.addActionListener {e ->
            assignChapter()
        }

        add line1
        add assignOwnerButton
        add assignIndexButton
        add assignChapterButton
    }

    void moveFile(Picture p, String newFolderName){
        File rootFolder = Main.getSubFolder(story)
        File sourceFolder = rootFolder
        String subFolderName = p.getSubFolderName()
        if (subFolderName != null) {
            sourceFolder = new File(sourceFolder, subFolderName)
        }
        String fileName = "${picture.getFileName()}.${picture.getExtension()}"
        File sourceFile = new File(sourceFolder, fileName)

        File destinationFolder = new File(rootFolder, newFolderName)
        File destinationFile = new File(destinationFolder, fileName)
        sourceFile.renameTo(destinationFile)
    }

    void assignChapter(){
        Object [] chapters = story.getChapters().toArray()
        int nr = JOptionPane.showOptionDialog(this, "Select Chapter", "Assign Chapter",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                chapters, null)
        if(nr != -1) {
            Chapter chapter = chapters[nr]
            String prefix = chapter.getPrefix()
            if(singleSelection){
                picture.setChapter(prefix)
//                moveFile(picture, prefix)
                picture.setSubFolderName(prefix)
//                chapter.getMediaList().add(picture)
                // picture
            } else {
                pictures.each {Picture p ->
                    p.setChapter(prefix)
//                    moveFile(p, prefix)
                    p.setSubFolderName(prefix)
//                    chapter.getMediaList().add(p)
                }
            }
//            mediaOverviewPanel.imageTablePanel.dataModel.fireTableDataChanged()
        }
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

    void assignIndex(){
        String reply = JOptionPane.showInputDialog('Enter Index:').trim()
        if(reply != null){
            if(singleSelection){
                picture.setIndexNumber(reply)
            } else {
                pictures.each {Picture picture ->
                    picture.setIndexNumber(reply)
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
            assignOwnerButton.setText(singleTextOwner)
            assignIndexButton.setText(singleTextIndex)
            assignChapterButton.setText(singleTextChapter)
        } else {
            assignOwnerButton.setText(multiTextOwner)
            assignIndexButton.setText(multiTextIndex)
            assignChapterButton.setText(multiTextChapter)
        }
    }
}
