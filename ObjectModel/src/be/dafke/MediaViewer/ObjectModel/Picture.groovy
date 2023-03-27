package be.dafke.MediaViewer.ObjectModel

class Picture {
    String path
    Person author
    Size2D size
    String index

    Picture(String path) {
        this.path = path
    }

    Picture(String path, Person author) {
        this.path = path
        this.author = author
    }

    Picture(String path, Person author, Size2D size) {
        this.path = path
        this.author = author
        this.size = size
    }

    Picture(String path, Person author, Size2D size, String index) {
        this.path = path
        this.author = author
        this.size = size
        this.index = index
    }

    String getIndex() {
        return index
    }

    String getPath() {
        return path
    }

    Integer getWidth() {
        return size?.width?:0
    }

    Size2D getSize() {
        return size
    }

    Integer getHeight() {
        return size?.height?:0
    }

    void setIndex(String index) {
        this.index = index
    }

    void setPath(String path) {
        this.path = path
    }

//    void setWidth(Integer width) {
//        this.width = width
//    }
//
//    void setHeight(Integer height) {
//        this.height = height
//    }

    void setSize(Size2D size) {
        this.size = size
    }
}
