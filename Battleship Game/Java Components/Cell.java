import java.util.*;

public class Cell {

    private boolean checkForHits;
    private boolean checkForSegment;
    private ArrayList<Segment> actualCell;

    public Cell() {
        this.checkForHits = false;
        this.checkForSegment = false;
        this.actualCell = new ArrayList<>();
    }

    public void placeSegment(Segment segmentArg) {
        if (this.actualCell.isEmpty() == true && segmentArg != null) {
            actualCell.add(segmentArg);
            checkForSegment = true;
        } else {
            return;
        }
    }

    public void attack() {
        // 1. record that a cell has been hit
        this.checkForHits = true;

        // 2. if the cell is not empty, attack the segment in the cell.
        if (this.actualCell.isEmpty() == false) {
            this.actualCell.get(0).attack();
        } else {
            return;
        }
    }

    public boolean hasBeenHit() {
        // 1. returns true if the cell has been hit
        if (this.checkForHits == true) {
            return true;
        } else {
            return false;// 2. returns false if the cell has not been hit
        }
    }

    public boolean isOccupied() {
        // returns true if there is a segment in the cell and false if not
        if (this.checkForSegment == true && actualCell.get(0) != null) {
            return true;
        } else {
            return false;
        }
    }

    public String displaySetup() {
        // if there is no segment at the specified cell, return "."
        if (this.checkForSegment == false) {
            return ".";
        
        // if there IS a segment, return the gridRepr of the ship the segment is associated with
        } else {
            return actualCell.get(0).toString();
        }
    }

    @Override
    public String toString() {
        if (!this.checkForHits) {
            return ".";// return "." if cell has not been hit
        } else if (checkForHits && !checkForSegment) {
            return "O"; // return "O" if the cell has been hit but there is no segment in the cell
        } else if (checkForHits && checkForSegment && !this.actualCell.get(0).getShip().sunk()){
            return "X"; // return "X" if the cell has been hit and there is a segment in it but ship not sunk.
        } else {
            return this.actualCell.get(0).getShip().toString(); // Otherwise return the gridRepr of the sunken ship
        }
    }
}

