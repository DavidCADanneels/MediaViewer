package be.dafke.MediaViewer.ObjectModel.Stories

import be.dafke.MediaViewer.ObjectModel.Media.Picture

class Chapter{
//    Story story = null
    String prefix = '00'
    String title = ''
    String parentChapter
    List<String> subChapters = []
//    List<Picture> mediaList = []
//    String description = ''
//    Chapter parentChapter = null
//    String path
//    List<Picture> pictures = []
//    HashMap<String, Chapter> subChapters = []

    /*
    https://www.google.com/maps/dir/Natural+bridge+of+Imi+N+Ifri,+Marokko/Ait+Ain+Ito+Bridge,+Ait+ain+ito,+Marokko/Skoura,+Marokko/Nkob,+Marokko/Jbel+Saghro,+Marokko/Tizi+n'+A%C3%AFt+Imi,+Marokko/Adazen+Ecolodge,+Douar+R'bat,+Tabant+22450,+Marokko/31.7233996,-6.9717292/@31.2493864,-6.7265712,165499m/data=!3m1!1e3!4m45!4m44!1m5!1m1!1s0xda4ddaa018981d1:0xb4e4278c83be354f!2m2!1d-6.9714773!2d31.7235755!1m5!1m1!1s0xdbb34e49d480cb3:0x9505ef6c0f36b124!2m2!1d-6.7976716!2d31.4160797!1m5!1m1!1s0xdbb67b796faf4b1:0x1fb864688fbc92b8!2m2!1d-6.555654!2d31.0604911!1m5!1m1!1s0xdbcf8fa3fc4c11b:0x25acb4d72200b014!2m2!1d-5.8644018!2d30.8694027!1m5!1m1!1s0xdbd21f9e6b9128f:0x328fb0cff5908518!2m2!1d-5.65!2d31.15!1m5!1m1!1s0xda357b5ab1bf1d3:0x7ed050c350469e0f!2m2!1d-6.3838889!2d31.6088889!1m5!1m1!1s0xda359ca970953c3:0xaaf79f5091efe51d!2m2!1d-6.3634914!2d31.6675269!1m0!3e0

https://marvinproject.sourceforge.net/en/howto/howToAddTransparentBackground.html
https://marvinproject.sourceforge.net/javadoc/marvin/MarvinPluginCollection.html

    MarvinImage image = MarvinImageIO.loadImage("./res/cryptex.jpg");
boundaryFill(image.clone(), image, 0, 0, Color.white, 150);
image.setAlphaByColor(0, 0xFFFFFFFF);
alphaBoundary(image, 5);
MarvinImageIO.saveImage(image, "./res/cryptex_out.png");

========================

https://anilfilenet.wordpress.com/2011/01/22/how-to-overlay-one-image-over-another-using-graphics2d/

======================

https://stackoverflow.com/questions/13425797/overlay-a-transparent-color-on-top-of-a-png-with-alpha-channel

@Override
public void paintComponent(Graphics g) {
    BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D gbi = result.createGraphics();
    BufferedImage x = null;
    try {
        x = ImageIO.read(getClass().getResource("/resources/someimage.png"));
    } catch (IOException ex) {
        Logger.getLogger(CanvasPanel.class.getName()).log(Level.SEVERE, null, ex);
    }
    gbi.drawImage(x, 0, 0, this);
    gbi.setColor(selectedColor);
    gbi.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.75f));
    gbi.fillRect(0, 0, width, height);
    g.drawImage(result, 0, 0, this);
}

     */

    Chapter() {
//        story = null
        title = ""
        prefix = ""
    }

    String getPrefix() {
        return prefix
    }
//    Chapter(Story story, String title, String prefix) { //}, String description) {//}, String path) {
//        this.story = story
//        this.title = title
//        this.prefix = prefix
////        this.description = description
////        this.path = path
//    }

    String toString(){
        // TODO: add prefices from parent Chapters
//        return prefix + '/' + title
        prefix
    }

//    Story getStory() {
//        return story
//    }

//    Chapter getParentChapter() {
//        return parentChapter
//    }

//    List<Picture> getPictures() {
//        return pictures
//    }

//    List<Picture> getPictures(boolean recursive) {
//        if(recursive){
//            // TODO
//        } else {
//            pictures
//        }
//    }

//    HashMap<String, Chapter> getSubChapters() {
//        return subChapters
//    }
//
//    void addSubChapter(Chapter chapter){
//        subChapters.put(chapter.prefix, chapter)
//    }
}