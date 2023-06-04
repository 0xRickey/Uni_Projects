from components_p1 import Segment, Ship, Cell, InvalidPlacementException, InvalidPositionException, InvalidShipTypeException, Board
from random import Random

class ValidPlacements:
    # Placement loop begins
    def generateValidPlacements(self) -> list[tuple[str,str]]:
        # Create necessary objects and variables
        board = Board()
        randomInstance = Random()
        listOfValidPlacements = []

        # restrict random choice range 
        randomRowChoices = "abcdefghij"
        randomDirectionChoices = ["across", "down"]

        # Create Ship objects
        battleship = Ship.createShip("battleship")
        carrier = Ship.createShip("carrier")
        destroyer = Ship.createShip("destroyer")
        submarine = Ship.createShip("submarine")
        patrolBoat = Ship.createShip("patrol boat")

        # Simulate placements to make sure that they are all valid
        shipMatcher = 0
        loopState = True
        while loopState:
            match shipMatcher:
                case 0: # Place Battleship
                    try:
                        # print("Checking random Battleship position...")
                        battleshipPosition = randomInstance.choice(randomRowChoices) + str(randomInstance.randint(1,10))
                        battleshipDirection = randomInstance.choice(randomDirectionChoices)
                        board.place_ship(battleship, battleshipPosition, battleshipDirection)
                        listOfValidPlacements.append((battleshipPosition, battleshipDirection))
                        # print("Battleship Placed Successfully, moving onto Carrier")
                        shipMatcher = shipMatcher + 1
                    except InvalidPlacementException:
                        # print("Generated invalid placement for Battleship, trying again")
                        continue
                    except InvalidPositionException:
                        # print("Generated invalid position for Battleship, trying again.")
                        continue
                case 1: # Place Carrier
                    try:
                        # print("Checking random Carrier position...")
                        carrierPosition = randomInstance.choice(randomRowChoices) + str(randomInstance.randint(1,10))
                        carrierDirection = randomInstance.choice(randomDirectionChoices)
                        board.place_ship(carrier, carrierPosition, carrierDirection)
                        listOfValidPlacements.append((carrierPosition, carrierDirection))
                        # print("Carrier Placed Successfully, moving onto Destroyer")
                        shipMatcher = shipMatcher + 1
                    except InvalidPlacementException:
                        # print("Generated invalid placement for Carrier, trying again.")
                        continue
                    except InvalidPositionException:
                        # print("Generated invalid position for Carrier, trying again")
                        continue
                case 2: # Place Destroyer
                    try:
                        # print("Checking random Destroyer position...")
                        destroyerPosition = randomInstance.choice(randomRowChoices) + str(randomInstance.randint(1,10))
                        destroyerDirection = randomInstance.choice(randomDirectionChoices)
                        board.place_ship(destroyer, destroyerPosition, destroyerDirection)
                        listOfValidPlacements.append((destroyerPosition, destroyerDirection))
                        # print("Destroyer Placed Successfully, moving onto Submarine")
                        shipMatcher = shipMatcher + 1
                    except InvalidPlacementException:
                        # print("Generated invalid placement for Destroyer, trying again")
                        continue
                    except InvalidPositionException:
                        # print("Generated invalid position for Destroyer, trying again.")
                        continue
                case 3: # Place Submarine
                    try:
                        # print("Checking random Submarine position...")
                        submarinePosition = randomInstance.choice(randomRowChoices) + str(randomInstance.randint(1,10))
                        submarineDirection = randomInstance.choice(randomDirectionChoices)
                        board.place_ship(submarine, submarinePosition, submarineDirection)
                        listOfValidPlacements.append((submarinePosition, submarineDirection))
                        # print("Submarine Placed Successfully, moving onto Patrol Boat")
                        shipMatcher = shipMatcher + 1
                    except InvalidPlacementException:
                        # print("Generated invalid placement for Submarine, trying again")
                        continue
                    except InvalidPositionException:
                        # print("Generated invalid position for Submarine, trying again")
                        continue
                case 4: # Place Patrol Boat
                    try:
                        # print("Checking random Patrol Boat position...")
                        patrolBoatPosition = randomInstance.choice(randomRowChoices) + str(randomInstance.randint(1,10))
                        patrolBoatDirection = randomInstance.choice(randomDirectionChoices)
                        board.place_ship(patrolBoat, patrolBoatPosition, patrolBoatDirection)
                        listOfValidPlacements.append((patrolBoatPosition, patrolBoatDirection))
                        # print("Patrol Boat Placed Successfully, returning list of placement pairs")
                        loopState = False
                    except InvalidPlacementException:
                        # print("Generated invalid placement for Patrol Boat, trying again")
                        continue
                    except InvalidPositionException:
                        # print("Generated invalid position for Patrol Boat, trying again")
                        continue
        # print(listOfValidPlacements)
        # print(board.display_setup())
        return listOfValidPlacements
            
