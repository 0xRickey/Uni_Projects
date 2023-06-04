from components import Segment, Ship, Cell, InvalidPlacementException, InvalidPositionException, InvalidShipTypeException, Board

if __name__ == "__main__":
    # Create board object
    board = Board()

    # Create Ship objects
    battleship = Ship.createShip("battleship")
    carrier = Ship.createShip("carrier")
    destroyer = Ship.createShip("destroyer")
    submarine = Ship.createShip("submarine")
    patrolBoat = Ship.createShip("patrol boat")


    # First print outs
    print("Welcome to BATTLESHIP!\n" + "\n" +
          "Setup Phase\n" +
          "Player 1 place your ships, Player 2 - no peeking.\n" + "\n" +
          board.__str__())
    
    # Placement loop begins
    shipMatcher = 0
    loopState = True
    while loopState:
        match shipMatcher:
            case 0: # Place Battleship
                try:
                    print("Where would you like to place your Battleship?")
                    battleshipPosition = input("Position: ")
                    battleshipDirection = input("Direction: ")
                    board.place_ship(battleship, battleshipPosition, battleshipDirection)
                    print(board.display_setup())
                    shipMatcher = shipMatcher + 1
                except InvalidPlacementException:
                    print("That is not a valid placement for that ship.")
                except InvalidPositionException:
                    print("That position does not exist.")
            case 1: # Place Carrier
                try:
                    print("Where would you like to place your Carrier?")
                    carrierPosition = input("Position: ")
                    carrierDirection = input("Direction: ")
                    board.place_ship(carrier, carrierPosition, carrierDirection)
                    print(board.display_setup())
                    shipMatcher = shipMatcher + 1
                except InvalidPlacementException:
                    print("That is not a valid placement for that ship.")
                except InvalidPositionException:
                    print("That position does not exist.")
            case 2: # Place Destroyer
                try:
                    print("Where would you like to place your Destroyer?")
                    destroyerPosition = input("Position: ")
                    destroyerDirection = input("Direction: ")
                    board.place_ship(destroyer, destroyerPosition, destroyerDirection)
                    print(board.display_setup())
                    shipMatcher = shipMatcher + 1
                except InvalidPlacementException:
                    print("That is not a valid placement for that ship.")
                except InvalidPositionException:
                    print("That position does not exist.")
            case 3: # Place Submarine
                try:
                    print("Where would you like to place your Submarine?")
                    submarinePosition = input("Position: ")
                    submarineDirection = input("Direction: ")
                    board.place_ship(submarine, submarinePosition, submarineDirection)
                    print(board.display_setup())
                    shipMatcher = shipMatcher + 1
                except InvalidPlacementException:
                    print("That is not a valid placement for that ship.")
                except InvalidPositionException:
                    print("That position does not exist.")
            case 4: # Place Patrol Boat
                try:
                    print("Where would you like to place your Patrol Boat?")
                    patrolBoatPosition = input("Position: ")
                    patrolBoatDirection = input("Direction: ")
                    board.place_ship(patrolBoat, patrolBoatPosition, patrolBoatDirection)
                    print(board.display_setup())
                    shipMatcher = shipMatcher + 1
                    loopState = False
                except InvalidPlacementException:
                    print("That is not a valid placement for that ship.")
                except InvalidPositionException:
                    print("That position does not exist.")

    # Attack Phase begins
    print("Attack Phase\n" + "Player 2, sink the fleet!\n")

    # Things to keep track of
    shipArray = [battleship, carrier, destroyer, submarine, patrolBoat]
    allSunk = False
    totalShots = 0;

    # While all ships aren't sunk
    while allSunk == False:
        try:
            sunkCounter = 0
            for ship in shipArray: # Check for all ships if sunk
                if ship.sunk() == False:
                    continue
                else:
                    sunkCounter= sunkCounter + 1
            
            if sunkCounter == 5: 
                # This executes if all ships are sunk; the end of the game
                print(board.__str__())
                print("The fleet has been sunk in " + str(totalShots) + " shots!\n" + "GAME OVER")
                break

            print(board.__str__()) # print current state of the board (if a previous valid attack is done, this will print after)

            attacked = False # if allSunk == False, then there should be more attacks until it is True, thus set attacked == false

            # Enter while loop until a valid attack is made on an unattacked cell
            while attacked == False: 
                posToAttack = input("Enter a grid coordinate to attack: ")
                if board.has_been_hit(posToAttack) == False: #if the targeted position is valid and not hit
                    totalShots = totalShots + 1
                    board.attack(posToAttack) 
                    attacked = True # Valid attack made, break while loop, only reached if no errors are thrown

                else: 
                    print("That position has already been attacked. Try again.") 
                    print(board.__str__())
                    # Valid position but already hit, return to line 117
        except InvalidPositionException:
            print("That is not a valid position to attack.")
            

    
    


    
        
    


    
    

    
    
