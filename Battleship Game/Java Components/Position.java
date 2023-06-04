public class Position {

    private char letterCoord;
    private int numberCoord;

    private Position(String processedTargetPos, char letterCoord, int numberCoord) {
        this.letterCoord = letterCoord;
        this.numberCoord = numberCoord;
    }

    public static Position createPosition(String targetPosition) throws InvalidPositionException {
        String processedTargetPos = Position.processPosition(targetPosition);
        if (isOnBoard(processedTargetPos)) {
            Position position = new Position(processedTargetPos, letterOfPosAsChar(processedTargetPos),
                    numberOfPosAsInt(processedTargetPos));
            return position;
        } else {
            throw new InvalidPositionException("Invalid Position! Thrown by createPosition");
        }
    }

    private static char letterOfPosAsChar(String targetPosition) throws InvalidPositionException {
        String processedTargetPos = Position.processPosition(targetPosition);
        if (isOnBoard(processedTargetPos)) {
            char letterCoord = processedTargetPos.charAt(0);
            return letterCoord;
        } else {
            throw new InvalidPositionException("Invalid Letter!, Thrown by letterOfPosAsChar");
        }
    }

    private static int numberOfPosAsInt(String targetPosition) throws InvalidPositionException {
        String processedTargetPos = Position.processPosition(targetPosition);
        if (isOnBoard(processedTargetPos)) {
            if (processedTargetPos.length() == 3) {
                String numberCoord = Character.toString(processedTargetPos.charAt(1))
                        + Character.toString(processedTargetPos.charAt(2));
                return Integer.parseInt(numberCoord);
            } else if (processedTargetPos.length() == 2) {
                String numberCoord = Character.toString(processedTargetPos.charAt(1));
                return Integer.parseInt(numberCoord);
            }
        } else {
            throw new InvalidPositionException("Position is not on board. numberOfPosAsInt err1");
        }
        throw new InvalidPositionException("Position is not on board. numberOfPosAsInt err2");
    }

    public static String processPosition(String targetString) {
        String processedPos = targetString.replaceAll("\\s", "").toUpperCase();
        return processedPos;
    }

    public static boolean isOnBoard(String targetPosition) throws InvalidPositionException {
        // Process targetPosition string
        String processedPos = Position.processPosition(targetPosition);

        boolean letterIsValid = false;
        boolean numberIsValid = false;
        if (processedPos.length() == 3) {
            char letterCoord = processedPos.charAt(0);
            String numberCoord = Character.toString(processedPos.charAt(1))
                    + Character.toString(processedPos.charAt(2));
            // Check if the letter of the target position argument is valid
            for (char letter = 'A'; letter <= 'K'; letter++) {
                if (letter != 'K') {
                    if (letterCoord == letter) {
                        letterIsValid = true;
                        break;
                    } else {
                        continue;
                    }
                } else {
                    throw new InvalidPositionException("Invalid Position, invalid letter! isOnBoard1");
                }
            }
            // Check if the int of the starting position argument is valid
            for (int column = 1; column <= 11; column++) {
                String numberCheck = Integer.toString(column);
                if (column != 11) {
                    if (numberCheck.equals(numberCoord)) {
                        numberIsValid = true;
                        break;
                    } else {
                        continue;
                    }
                } else {
                    throw new InvalidPositionException("Invalid Position, invalid board number isOnBoard2");
                }
            }
        } else if (processedPos.length() == 2) {
            char letterCoord = processedPos.charAt(0);
            String numberCoord = Character.toString(processedPos.charAt(1));
            // Check if the letter in the starting position argument is valid
            for (char letter = 'A'; letter <= 'K'; letter++) {
                if (letter != 'K') {
                    if (letterCoord == letter) {
                        letterIsValid = true;
                        break;
                    } else {
                        continue;
                    }
                } else {
                    throw new InvalidPositionException("Invalid Position, invalid letter! isOnBoard3");
                }
            }
            // Check if the int of the starting position argument is valid
            for (int column = 1; column <= 11; column++) {
                String numberCheck = Integer.toString(column);
                if (column != 11) {
                    if (numberCheck.equals(numberCoord)) {
                        numberIsValid = true;
                        break;
                    } else {
                        continue;
                    }
                } else {
                    throw new InvalidPositionException(
                            "Invalid Position, invalid board number isOnBoard4 " + numberCoord);
                }
            }
        }
        // if co-ordinate length not 2 or 3 throw exceptions
        else {
            throw new InvalidPositionException("Position too long, does not exist! isOnBoard5");
        }
        if (numberIsValid && letterIsValid) {
            return true;
        } else {
            return false;
        }
    }

    public int getRowAsInt() throws InvalidPositionException {
        return switch (this.letterCoord) {
            case 'A' -> 0;
            case 'B' -> 1;
            case 'C' -> 2;
            case 'D' -> 3;
            case 'E' -> 4;
            case 'F' -> 5;
            case 'G' -> 6;
            case 'H' -> 7;
            case 'I' -> 8;
            case 'J' -> 9;
            default -> throw new InvalidPositionException("Letter is invalid! getRowAsInt");
        };
    }

    public int getColumn() {
        return this.numberCoord;
    }
}
