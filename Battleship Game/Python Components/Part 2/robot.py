from random import Random
from components import Segment, Ship, Cell, InvalidPlacementException, InvalidPositionException, InvalidShipTypeException, Board
from validPlacements import ValidPlacements

class Robot:

    def __init__(self):
        self.listOfAttacks = []
        self.subBoard = Board()
        self.rowChoices = "abcdefghij"

    def generate_placements(self) -> list[tuple[str, str]]:
        placementValidatorObj = ValidPlacements()
        validatedPlacements = placementValidatorObj.generateValidPlacements()
        return validatedPlacements

    def get_attack(self) -> str:
        randomInstance = Random()
        isAlreadyAttacked = True

        # make sure to only generate unattacked positions
        while isAlreadyAttacked == True:
            randomAttackRow = randomInstance.choice(self.rowChoices)
            randomAttackColumn = randomInstance.randint(1, 10)
            targetedAttackPositionString = randomAttackRow + str(randomAttackColumn)
            if targetedAttackPositionString in self.listOfAttacks:
                continue
            else:
                self.listOfAttacks.append(targetedAttackPositionString)
                isAlreadyAttacked = False
        return targetedAttackPositionString

    def give_result(self, result: str, board_state: list[list[str]]) -> None:
        pass

















