package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture

import javax.swing.JButton
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.Dimension
import java.awt.event.KeyEvent

class VotePanel extends JPanel {
    JButton delete, zeroStar, oneStar, twoStar, threeStar, nextButton, previousButton

    boolean addVotes, nextOnVote
    Picture currentPicture
    int currentIndex
    ImagePanel view
    List<Picture> pictures
    Integer minimumLevel, totalNr, selectedNr
    JCheckBox nextOnVoteCheckBox

    JTextField currentStars, initialStars, minimumStars
    JLabel initialLabel, currentLabel, starsLabel, selectionLabel, pictureLabel

    VotePanel(ImagePanel imagePanel, List<Picture> pictures, boolean addVotes, Integer minimumLevel) {
        view = imagePanel
        nextOnVote = true
        this.addVotes = addVotes
        this.minimumLevel = minimumLevel
        totalNr = pictures.size()
        this.pictures = pictures.findAll { Picture picture ->
            picture.stars >= minimumLevel
        }
        selectedNr = this.pictures.size()

        currentStars = new JTextField(2)
        currentStars.enabled = false

        initialStars = new JTextField(2)
        initialStars.enabled = false

        initialLabel = new JLabel("Initial Stars: ")
        currentLabel = new JLabel("Current Stars: ")

        nextButton = new JButton("Next")
        previousButton = new JButton("Previous")
        delete = new JButton("-1")
        zeroStar = new JButton("0")
        oneStar = new JButton("1")
        twoStar = new JButton("2")
        threeStar = new JButton("3")
        if(addVotes) {
            starsLabel = new JLabel("Add Stars: ")
            zeroStar = new JButton("0")
            oneStar = new JButton("+1")
            twoStar = new JButton("+2")
            threeStar = new JButton("+3")
            delete.addActionListener { e -> addVote(-1) }
            zeroStar.addActionListener { e -> addVote(0) }
            oneStar.addActionListener { e -> addVote(1) }
            twoStar.addActionListener { e -> addVote(2) }
            threeStar.addActionListener { e -> addVote(3) }
        } else {
            starsLabel = new JLabel("Set Stars: ")
            zeroStar = new JButton("0")
            oneStar = new JButton("1")
            twoStar = new JButton("2")
            threeStar = new JButton("3")
            delete.addActionListener { e -> setVote(-1) }
            zeroStar.addActionListener { e -> setVote(0) }
            oneStar.addActionListener { e -> setVote(1) }
            twoStar.addActionListener { e -> setVote(2) }
            threeStar.addActionListener { e -> setVote(3) }
        }
        nextButton.addActionListener { e -> showNext() }
        previousButton.addActionListener { e -> showPrevious() }

        delete.setMnemonic(KeyEvent.VK_DELETE)
        zeroStar.setMnemonic(KeyEvent.VK_0)
        oneStar.setMnemonic(KeyEvent.VK_1)
        twoStar.setMnemonic(KeyEvent.VK_2)
        threeStar.setMnemonic(KeyEvent.VK_3)
        nextButton.setMnemonic(KeyEvent.VK_RIGHT)
        previousButton.setMnemonic(KeyEvent.VK_LEFT)

        nextOnVoteCheckBox = new JCheckBox()
        nextOnVoteCheckBox.setSelected(true)
        nextOnVoteCheckBox.addActionListener { e -> nextOnVote = nextOnVoteCheckBox.selected }

        minimumStars = new JTextField(2)
        minimumStars.text = minimumLevel.toString()
        minimumStars.enabled = addVotes
        minimumStars.addActionListener { e ->
            try {
                this.minimumLevel = Integer.parseInt(minimumStars.text.trim())
                this.pictures = pictures.findAll { Picture picture ->
                    picture.stars >= this.minimumLevel
                }
                selectedNr = this.pictures.size()
                selectionLabel.text = "(${selectedNr}/${totalNr})"
                setCurrentPicture(this.pictures[0])
                pictureLabel.text = "(0/${selectedNr})"
            } catch (NumberFormatException nfe) {
            }
        }
        selectionLabel = new JLabel("(${selectedNr}/${totalNr})")
        pictureLabel = new JLabel("(0/${selectedNr})")

        add new JLabel("Minimum stars")
        add minimumStars
        add selectionLabel
        if(addVotes) {
            add initialLabel
            add initialStars
        } else {
            add currentLabel
            add currentStars
        }
        add pictureLabel
        add starsLabel
        add threeStar
        add twoStar
        add oneStar
        add zeroStar
        add delete
        add nextOnVoteCheckBox
        add previousButton
        add nextButton
        if(addVotes) {
            add currentLabel
            add currentStars
        }
        setCurrentPicture(this.pictures.get(0))
    }

    void setCurrentPicture(Picture currentPicture) {
        this.currentPicture = currentPicture
        view.scrollPane.size = new Dimension(1200,800)
        view.setPicture(currentPicture)
        if(addVotes){
            initialStars.text = currentPicture.stars
            currentStars.text = currentPicture.stars
        } else {
            currentStars.text = currentPicture.stars
        }
    }

    void setVote(Integer nr){
        currentPicture.stars = nr
        if(nextOnVote) {
            showNext()
        }
    }

    void addVote(Integer nr){
        currentPicture.stars += nr
        if(nextOnVote) {
            showNext()
        }
    }

    void showNext(){
        currentIndex++
        if(currentIndex>=pictures.size()) {
            currentIndex = 0
        }
        currentPicture = pictures.get(currentIndex)
        setCurrentPicture(currentPicture)
        pictureLabel.text = "(${currentIndex}/${selectedNr})"
    }

    void showPrevious(){
        currentIndex--
        if(currentIndex<0){
            currentIndex = pictures.size() - 1
        }
        currentPicture = pictures.get(currentIndex)
        setCurrentPicture(currentPicture)
        pictureLabel.text = "(${currentIndex}/${selectedNr})"
    }
}
