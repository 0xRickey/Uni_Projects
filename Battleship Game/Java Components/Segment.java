import java.util.*;

public class Segment {

    private Ship shipOfSegment;
    private String gridReprOfSegment;
    private int segmentHealth;

    public Segment(Ship ship) {
        this.shipOfSegment = ship;
        this.gridReprOfSegment = this.shipOfSegment.toString();
        this.segmentHealth = 1;
    }

    public boolean hit() {
        if (this.segmentHealth == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void attack() {
        this.segmentHealth = 0;
    }

    public Ship getShip() {
        return this.shipOfSegment;
    }

    @Override
    public String toString() {
        return gridReprOfSegment;
    }
}
