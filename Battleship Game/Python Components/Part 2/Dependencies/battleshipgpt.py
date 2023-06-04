from components import Segment, Ship, Cell, InvalidPlacementException, InvalidPositionException, InvalidShipTypeException, Board
from robot import Robot

if __name__ == "__main__":
    boardMain = Board()
    robot = Robot()
    ships = [Ship.createShip(name) for name in ["Battleship", "Carrier", "Destroyer", "Submarine", "Patrol Boat"]]
    placements = robot.generate_placements()
    for ship, (position, direction) in zip(ships, placements):
        boardMain.place_ship(ship, position, direction)
    move_count = 0
    while any([not ship.sunk() for ship in ships]) and move_count <= 100:
        print("Move", move_count)
        attack = robot.get_attack()
        print("Attacking", attack)
        result = boardMain.attack(attack)
        print("Result", result)
        print(boardMain)
        robot.give_result(result, boardMain.get_current_board())
        move_count += 1
    print("Finished in", move_count, "moves.")
