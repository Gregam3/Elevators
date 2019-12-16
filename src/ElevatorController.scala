import Simulator.generatePassengers

import scala.collection.mutable.{ListBuffer, Map}

object ElevatorController {
  val ELEVATOR_COUNT = 4

  var elevators = 0.until(ELEVATOR_COUNT)
    .map(i => new ElevatorCar(i * 2, 0, i))
    .to[ListBuffer]
  //waitingPassengers[Floor, Passengers]
  var waitingPassengers: Map[Int, ListBuffer[Passenger]] = generatePassengers(25)

  def run() = {
    elevators.foreach(elevator => {
      if (elevator.state == ElevatorState.WAITING && waitingPassengers.nonEmpty) {
        findPassengers(elevator)
      } else if (passengersAreOnFloor(elevator.floor)) {
        onBoardPassenger(elevator)
      } else if (elevator.desiredFloor == elevator.floor) {
        offBoardPassenger(elevator)
      } else if (elevator.passengers.nonEmpty) {
        elevator.state = ElevatorState.MOVING
      } else {
        elevator.state = ElevatorState.WAITING
      }

      elevator.run
    })
  }

  def nextDestination(elevator: ElevatorCar) = {
    //TODO figure out why underscore does not yield same output as df => df
    if (elevator.passengers.nonEmpty) elevator.desiredFloor =
      elevator.passengers.map(_.desiredFloor).minBy(df => df)
    else findPassengers(elevator)
  }

  def passengersAreOnFloor(floor: Int) = waitingPassengers.keys.exists(_ == floor)

  def onBoardPassenger(elevator: ElevatorCar) = {
    elevator.boardPassenger(waitingPassengers(elevator.floor).head)
    waitingPassengers(elevator.floor).remove(0)

    if(waitingPassengers(elevator.floor).isEmpty) waitingPassengers.remove(elevator.floor)
    elevator.state = ElevatorState.ON_BOARDING
  }

  def offBoardPassenger(elevator: ElevatorCar):Unit= {
    elevator.passengers.indices.foreach(i => {
      if(elevator.passengers(i).desiredFloor == elevator.floor) {
        elevator.passengers.remove(i)
        elevator.state = ElevatorState.OFF_BOARDING
        return;
      }
    })
  }

  def findPassengers(elevator: ElevatorCar) = {
    elevator.desiredFloor = waitingPassengers.head._1
    elevator.state = ElevatorState.MOVING
  }
}
