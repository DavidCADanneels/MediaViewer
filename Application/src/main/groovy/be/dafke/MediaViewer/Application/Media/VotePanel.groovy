package be.dafke.MediaViewer.Application.Media

import be.dafke.MediaViewer.ObjectModel.Media.Picture

import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.Dimension
import java.awt.event.KeyEvent

class VotePanel extends JPanel {
    JButton delete, zeroStar, oneStar, twoStar, threeStar, nextButton, previousButton

    boolean addVotes
    Picture currentPicture
    int currentIndex
    ImagePanel view
    List<Picture> pictures
    int minimumLevel

    JTextField currentStars, initialStars
    JLabel initialLabel, currentLabel, starsLabel

    VotePanel(ImagePanel imagePanel, List<Picture> pictures, boolean addVotes, int minimumLevel) {
        view = imagePanel
        this.addVotes = addVotes
        this.minimumLevel = minimumLevel
        this.pictures = pictures.findAll { Picture picture ->
            picture.stars >= minimumLevel
        }

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

        if(addVotes) {
            add initialLabel
            add initialStars
        } else {
            add currentLabel
            add currentStars
        }
        add starsLabel
        add threeStar
        add twoStar
        add oneStar
        add zeroStar
        add delete
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
        showNext()
    }

    void addVote(Integer nr){
        currentPicture.stars += nr
        showNext()
    }

    void showNext(){
        currentIndex++
        if(currentIndex>=pictures.size()) {
            currentIndex = 0
        }
        currentPicture = pictures.get(currentIndex)
        setCurrentPicture(currentPicture)
    }

    void showPrevious(){
        currentIndex--
        if(currentIndex<0){
            currentIndex = pictures.size() - 1
        }
        currentPicture = pictures.get(currentIndex)
        setCurrentPicture(currentPicture)
    }
}
