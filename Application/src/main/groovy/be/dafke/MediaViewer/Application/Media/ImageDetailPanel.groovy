package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.Application.DateUtils.DateConverterPanel
import be.dafke.MediaViewer.Application.Main
import be.dafke.MediaViewer.ObjectModel.Media.Picture
import be.dafke.MediaViewer.ObjectModel.Stories.Chapter
import be.dafke.MediaViewer.ObjectModel.Stories.Story

import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JDialog
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.BorderLayout
import java.awt.Point

import static java.util.ResourceBundle.getBundle

class ImageDetailPanel extends JPanel{
    Picture picture
    Story story
    List<Picture> pictures
    boolean singleSelection
    JButton assignOwnerButton, assignIndexButton, assignChapterButton
    JButton addMediaButton, setVoteButton, addVoteButton, setDateButton
    JButton addLabelButton, createLabelButton
    ImageTablePanel imageTablePanel
    static String singleTextOwner = "Assign Owner to Picture"
    static String multiTextOwner = "Assign Owner to Pictures"
    static String singleTextIndex = "Assign Index to Picture"
    static String multiTextIndex = "Assign Index to Pictures"
    static String singleTextChapter = "Assign Chapter to Picture"
    static String multiTextChapter = "Assign Chapter to Pictures"

    ImageDetailPanel(ImageTablePanel imageTablePanel) {
        this.imageTablePanel = imageTablePanel
//        this.mediaOverviewPanel = mediaOverviewPanel
        setLayout new BoxLayout(this, BoxLayout.Y_AXIS)

        addMediaButton = new JButton(getBundle("MediaViewer").getString("ADD_PHOTO_BUTTON"))
        addMediaButton.addActionListener { e -> loadData() }

        singleSelection = true
        assignOwnerButton = new JButton(singleTextOwner)
        assignOwnerButton.addActionListener {e -> assignOwner() }
        assignIndexButton = new JButton(singleTextIndex)
        assignIndexButton.addActionListener {e -> assignIndex() }
        assignChapterButton = new JButton(singleTextChapter)
        assignChapterButton.addActionListener {e -> assignChapter() }

        setVoteButton = new JButton("Set (Initial) Star !")
        setVoteButton.addActionListener { e -> vote(false) }
        addVoteButton = new JButton("Add Stars !")
        addVoteButton.addActionListener { e -> vote(true) }

        setDateButton = new JButton("Correct Date")
        setDateButton.addActionListener { e ->
            pictures.each { Picture p ->
                String trimmed = p.fileName - 'IMG_'
                Calendar cal = DateConverterPanel.calculateFromIndex(trimmed)
                if(cal) {
                    p.creationDate = cal.time
                }
            }
        }

        addLabelButton = new JButton('Add label')
        addLabelButton.addActionListener { e ->
            pictures.each { Picture p ->
                Object [] labels = story.labels.toArray()
                int nr = JOptionPane.showOptionDialog(this, "Select Label", "Assign Label",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                        labels, null)
                if(nr != -1){
                    String label = labels[nr]
                    p.labels.add(label)
                }
            }
        }

        createLabelButton = new JButton('Create new label')
        createLabelButton.addActionListener { e ->
            String response = JOptionPane.showInputDialog('New label')
            if(response && response.trim() != '') {
                story.labels.add response.trim()
            }
        }

        JPanel line3 = new JPanel()
        line3.add addLabelButton
        line3.add createLabelButton

        add line3
        add addMediaButton
        add assignOwnerButton
        add assignChapterButton
        add assignIndexButton
        add setVoteButton
        add addVoteButton
        add setDateButton
    }

    void vote(boolean add){
        JDialog voteDialog = new JDialog()
        voteDialog.modal = true
        voteDialog.defaultCloseOperation = JDialog.DISPOSE_ON_CLOSE

        ImagePanel imagePanel = new ImagePanel()
        imagePanel.setStory(story)
        VotePanel votePanel = new VotePanel(imagePanel, pictures, add, 0)

        // use: this
        imagePanel.add votePanel, BorderLayout.NORTH
        voteDialog.setContentPane imagePanel
        // or: this
//        JPanel contentPanel = new JPanel(new BorderLayout())
//        contentPanel.add votePanel, BorderLayout.NORTH
//        contentPanel.add imagePanel, BorderLayout.CENTER
//        voteDialog.contentPane = contentPanel
//        voteDialog.setContentPane(contentPanel)

        voteDialog.pack()
        voteDialog.setLocation(new Point(0,0))
        voteDialog.visible = true
    }

    void loadData(){
        // FIXME:
        // 1. Ask to Select Chapter as well, use same value for index (for now)
        // 2. Create new Dialog with input fields for:
        //      - Owner
        //      - Chapter
        //      - Index (auto-update proposal == Chapter-index) (editable: 0100 -> 010001)
//        Chapter chapter = null
//        String pictureIndex = ""

        NewMediaDialog newMediaDialog = new NewMediaDialog(story, null)
        newMediaDialog.setLocation(getLocationOnScreen())
        newMediaDialog.visible = true

//        Main.addMedia(story)
    }

    // FIXME:
//    void moveFile(Picture p, String newFolderName){
//        File rootFolder = Main.getSubFolder(story)
//        File sourceFolder = rootFolder
//        String subFolderName = p.getSubFolderName()
//        if (subFolderName != null) {
//            sourceFolder = new File(sourceFolder, subFolderName)
//        }
//        String fileName = "${picture.getFileName()}.${picture.getExtension()}"
//        File sourceFile = new File(sourceFolder, fileName)
//
//        File destinationFolder = new File(rootFolder, newFolderName)
//        File destinationFile = new File(destinationFolder, fileName)
//        sourceFile.renameTo(destinationFile)
//    }

    void assignChapter(){
        Object [] chapters = story.getChapters().toArray()
        int nr = JOptionPane.showOptionDialog(this, "Select Chapter", "Assign Chapter",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                chapters, null)
        if(nr != -1) {
            Chapter chapter = (Chapter) chapters[nr]
            if(singleSelection){
                picture.setChapter(chapter.getPrefix())
            } else {
                pictures.each { it.setChapter(chapter.getPrefix()) }
            }
        }
    }

    void assignOwner(){
        Integer nr = Main.selectParticipant(story, this)
        if(nr != -1) {
            if(singleSelection){
                // FIXME: Main method only contains this:
                // picture.setOwner(nr)
                // do we need to call the Main method here?
                Main.setOwner(picture,nr)
            } else {
                // FIXME: same question here:
                // pictures.each { it.setOwner(nr) }
                Main.setOwners(pictures,nr)
            }
            imageTablePanel.dataModel.fireTableDataChanged()
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
            imageTablePanel.dataModel.fireTableDataChanged()
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
    }

    void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection
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
