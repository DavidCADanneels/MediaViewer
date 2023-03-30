import be.dafke.MediaViewer.ObjectModel.Interactive.GlobalPosition
import org.junit.Test

class GlobalPositionTest {

    @Test
    void 'distance between Brugge and Marrakesh'(){
        GlobalPosition brugge = new GlobalPosition(51.208130, 3.249124)
        GlobalPosition marrakesh = new GlobalPosition(31.635505, -7.989912)
        double distance = brugge.calculateDistance(marrakesh)
        assert distance == 2361787.915406026
        double bearing = brugge.calculateBearing(marrakesh)
        assert bearing == 207.32029597449008
    }
}
