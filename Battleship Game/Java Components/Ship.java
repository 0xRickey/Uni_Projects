import java.util.*;

public class Ship {

    private String shipType;
    private String shipName;
    private int shipLength;
    private String gridRepr;
    private HashMap<Integer, Segment> segmentMap = new HashMap<>(); 

    private Ship(String type) {
        // construct ship object instance with respect to processed input
        switch(type) {
            case "battleship":
                this.shipType = "battleship";
                this.shipName = "Battleship";
                this.shipLength = 4;
                this.gridRepr = "B";
                for (int i = 0; i < 4; i++) {
                    Segment segmentOfBattleship = new Segment(this);
                    int segmentNumber = i + 1; 
                    this.segmentMap.put(segmentNumber, segmentOfBattleship);
                }
                break;
            case "carrier":
                this.shipType = "carrier";
                this.shipName = "Carrier";
                this.shipLength = 5;
                this.gridRepr = "C";
                for (int i = 0; i < 5; i++) {
                    Segment segmentOfCarrier = new Segment(this);
                    int segmentNumber = i + 1;
                    this.segmentMap.put(segmentNumber, segmentOfCarrier);
                }
                break;
            case "destroyer":
                this.shipType = "destroyer";
                this.shipName = "Destroyer";
                this.shipLength = 3;
                this.gridRepr = "D";
                for (int i = 0; i < 3; i++) {
                    Segment segmentOfDestroyer = new Segment(this);
                    int segmentNumber = i + 1;
                    this.segmentMap.put(segmentNumber, segmentOfDestroyer);
                }
                break;
            case "submarine":
                this.shipType = "submarine";
                this.shipName = "Submarine";
                this.shipLength = 3;
                this.gridRepr = "S";
                for (int i = 0; i < 3; i++) {
                    Segment segmentOfSubmarine = new Segment(this);
                    int segmentNumber = i + 1;
                    this.segmentMap.put(segmentNumber, segmentOfSubmarine);
                }
                break;
            case "patrolboat":
                this.shipType = "patrol boat";
                this.shipName = "Patrol Boat";
                this.shipLength = 2;
                this.gridRepr = "P";
                for (int i = 0; i < 2; i++) {
                    Segment segmentOfPatrolboat = new Segment(this);
                    int segmentNumber = i + 1;
                    this.segmentMap.put(segmentNumber, segmentOfPatrolboat);
                }
                break;
            default:
                break;
        }
    }

    public static Ship createShip(String typeInput) {
        // Declare a ship
        Ship shipInstance;

        // remove all whitespace from inputs
        String objTypeArgument = typeInput.replaceAll("\\s+", "").toLowerCase();

        // turn all inputs into lowercase letters
        // objTypeArgument.toLowerCase();

        // create ship with respect to the processed input
        switch(objTypeArgument){
            case "battleship":
                shipInstance = new Ship(objTypeArgument);
                return shipInstance;
            case "carrier":
                shipInstance = new Ship(objTypeArgument);
                return shipInstance;
            case "destroyer":
                shipInstance = new Ship(objTypeArgument);
                return shipInstance;
            case "submarine":
                shipInstance = new Ship(objTypeArgument);
                return shipInstance;
            case "patrolboat":
                shipInstance = new Ship(objTypeArgument);
                return shipInstance;
            default:
                return null;
        }
    }

    public int length() {
        return this.shipLength;
    }

    public String name() {
        return this.shipName;
    }

    // 1. iterate through the hashmap of segments
    // 2. check to see if each segment has been hit 
    // 3. if all segments are hit, return true
    // 4. if any segment returns false, return false and end the loop
    public boolean sunk() {
        boolean sunkenCheck = true;
        for (int i = 1; i < shipLength + 1; i++) {
            Segment currentSegment = this.segmentMap.get(i);
            if (currentSegment.hit()) {
                continue;
            } else {
                sunkenCheck = false;
            }
        }
        return sunkenCheck;
    }

    public Segment getSegment(int segmentNumber) {
        // check if argument is within the bounds of a ship's segment numbers (0 - x) depending on the ship
        if (segmentNumber > 0 &&  segmentNumber < this.shipLength + 1){

            // for each key value pair in the segment hashmap
            for (Map.Entry<Integer, Segment> entry : segmentMap.entrySet()) {

                // if the argument matches a key return the segment, else continue until end of hashmap
                if (segmentNumber == entry.getKey()) {
                    return entry.getValue();
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return gridRepr;

    }
}
