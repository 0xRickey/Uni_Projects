import java.util.*;

public class Board {

	private Cell[][] cellMatrix;
	private String[][] coordNums;
	private String[][] coordLetters;
	private String setUpDisplay = "";

	public Board() {
		// Generate the board
		this.cellMatrix = new Cell[10][10];
		this.coordNums = new String[1][11];
		this.coordLetters = new String[10][1];

		// place numbers into co-ordinate row
		coordNums[0][0] = "  ";
		coordNums[0][10] = "10\n";
		for (int column = 1; column < 10; column++) {
			coordNums[0][column] = Integer.toString(column) + " ";
		}

		// place letters into a column for use later in toString() and displaySetup()
		// methods
		int row = 0;
		for (char letter = 'A'; letter <= 'J'; letter++) {
			this.coordLetters[row][0] = Character.toString(letter) + " ";
			row++;
		}

		// populating the cell matrix with empty cells
		for (int rowIncrement = 0; rowIncrement < 10; rowIncrement++) {
			for (int column = 0; column < 10; column++) {
				this.cellMatrix[rowIncrement][column] = new Cell();
			}
		}
		// after setting up empty board you can assign setUp the board
		this.setUpDisplay = this.setUpDisplay + this.displaySetup();
	}

	public void placeShip( // Method params
			Ship shipToBePlaced,
			String startingPos,
			String direction)

			throws InvalidPlacementException, // Method exceptions
			InvalidPositionException,
			InvalidShipTypeException {

		// check if the ship type is valid
		if (shipToBePlaced == null) {
			throw new InvalidShipTypeException("Invalid ship type!");
		}

		// Create starting position Object
		Position startingPositionObj = Position.createPosition(startingPos);
		/*
		 * The object only exists if the input is correct, otherwise it throws
		 * exceptions
		 * this means that the code beyond here assumes that the position is valid
		 */

		// process the direction String
		String processedDirection = direction.replaceAll("\\s", "").toLowerCase();

		// Access starting position on the grid
		int startPosRow = startingPositionObj.getRowAsInt();
		int startPosColumn = startingPositionObj.getColumn();

		// Extract all cells in the direction specified
		if (processedDirection == "across") {
			// check if position is on the board before extracting
			if (!Position.isOnBoard(startingPos)) {
						throw new InvalidPositionException("New exception");
			}
			ArrayList<Cell> slicedCellArray = new ArrayList<>();

			// Extract row and put them into an ArrayList
			for (int column = startPosColumn; column < 11; column++) {
				slicedCellArray.add(cellMatrix[startPosRow][column - 1]);
			}

			// If ship size is bigger than array then you can't place it there using that
			// orientation
			if (shipToBePlaced.length() <= slicedCellArray.size()) {
				for (int i = 0; i < shipToBePlaced.length(); i++) {

					Segment currentSegment = shipToBePlaced.getSegment(i + 1);
					Cell currentCell = slicedCellArray.get(i);
					boolean cellOccupenceCheck = slicedCellArray.get(i).isOccupied();

					// Check to see if cell is occupied, if it isn't, place segment, replace array
					// and then replace in matrix, if occupied, error.
					if (!cellOccupenceCheck) {
						currentCell.placeSegment(currentSegment);
						slicedCellArray.set(i, currentCell);
						// replace matrix row with array row that has placed segments
						int iSliced = 0;
						for (int column = startPosColumn; column < 11; column++) {
							if (iSliced < slicedCellArray.size()) {
								cellMatrix[startPosRow][column - 1] = slicedCellArray.get(iSliced);
								iSliced++;
							}
						}
					} else {
						throw new InvalidPlacementException("Invalid placement, another ship in the way! placeShip1");
					}
				}
			} else {
				throw new InvalidPlacementException("Invalid position, ship too long to be placed here! placeShip2");
			}
		}

		else if (processedDirection == "down") {
			ArrayList<Cell> slicedCellArray = new ArrayList<>();

			// check if position is on the board before doing anything else
			if (!Position.isOnBoard(startingPos)) {
				throw new InvalidPositionException("New exception");
			}

			// Extract column and put them into an ArrayList
			for (int row = startPosRow; row < 10; row++) {
				slicedCellArray.add(cellMatrix[row][startPosColumn - 1]);
			}

			// If ship size is bigger than array then you can't place it there using that
			// orientation
			if (shipToBePlaced.length() <= slicedCellArray.size()) {
				for (int i = 0; i < shipToBePlaced.length(); i++) {

					Segment currentSegment = shipToBePlaced.getSegment(i + 1);
					Cell currentCell = slicedCellArray.get(i);
					boolean cellOccupenceCheck = slicedCellArray.get(i).isOccupied();

					// Check to see if cell is occupied, if it isn't, place segment, replace array
					// and then replace in matrix, if occupied, error.
					if (!cellOccupenceCheck) {
						currentCell.placeSegment(currentSegment);
						slicedCellArray.set(i, currentCell);
						int jSliced = 0;
						for (int row = startPosRow; row < 10; row++) {
							if (jSliced < slicedCellArray.size()) {
								cellMatrix[row][startPosColumn - 1] = slicedCellArray.get(jSliced);
								jSliced++;
							}
						}
					} else {
						throw new InvalidPlacementException("Invalid placement, another ship in the way! placeShip3");
					}
				}
			} else {
				throw new InvalidPlacementException("Invalid placement, ship too long to be placed here! placeShip4");
			}
		} else {
			throw new InvalidPlacementException("Invalid direction! placeShip5");
		}
		// Ships are placed before the game begins, so at the end of setup, this will be
		// called
		this.setUpDisplay = this.setUpDisplay + this.displaySetup();
	}

	public void attack(String position) throws InvalidPositionException {
		Position targetPosition = Position.createPosition(position);
		int row = targetPosition.getRowAsInt();
		int column = targetPosition.getColumn();
		if (Position.isOnBoard(position)) {
			cellMatrix[row][column - 1].attack();
		} else {
			throw new InvalidPositionException("Cannot attack invalid position!");
		}
	}

	public boolean hasBeenHit(String position) throws InvalidPositionException {
		Position targetPosition = Position.createPosition(position);
		int row = targetPosition.getRowAsInt();
		int column = targetPosition.getColumn();
		if (Position.isOnBoard(position)) {
			return cellMatrix[row][column - 1].hasBeenHit();
		} else {
			throw new InvalidPositionException("Cannot check if hit, position does not exist!");
		}
	}

	@Override
	public String toString() {
		String firstLine = "";
		String subsequentLines = "";
		// Concatenate everything from column coordinate array onto firstLine variable
		for (int column = 0; column < 11; column++) {
			firstLine = firstLine + this.coordNums[0][column];
		}
		// Concatenate everything from row coordinate array and cellMatrix into
		// subsequent subsequentLines
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 11; column++) {
				if (column == 0) {
					subsequentLines = subsequentLines + this.coordLetters[row][0];
				} else if (column > 0 && column < 10) {
					subsequentLines = subsequentLines + this.cellMatrix[row][column - 1].toString() + " ";
				} else if (column == 10) {
					subsequentLines = subsequentLines + this.cellMatrix[row][column - 1].toString() + "\n";
				}
			}
		}
		return firstLine + subsequentLines;
	}

	public String displaySetup() {
		String firstLine = "";
		String subsequentLines = "";
		// Concatenate everything from column coordinate array onto firstLine variable
		for (int column = 0; column < 11; column++) {
			firstLine = firstLine + this.coordNums[0][column];
		}
		// Concatenate everything from row coordinate array and cellMatrix into
		// subsequent subsequentLines
		for (int row = 0; row < 10; row++) {
			for (int column = 0; column < 11; column++) {
				
				if (column == 0) {
					subsequentLines = subsequentLines + this.coordLetters[row][0];
				} else if (column > 0 && column < 10) {
					subsequentLines = subsequentLines + this.cellMatrix[row][column - 1].displaySetup() + " ";
				} else if (column == 10) {
					subsequentLines = subsequentLines + this.cellMatrix[row][column - 1].displaySetup() + "\n";
				}
			}
		}
		return firstLine + subsequentLines;
	}
}
