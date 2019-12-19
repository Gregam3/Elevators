import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class ElevatorCar(var floor: Int, var desiredFloor: Int, val index: Int) {
  val capacity: Int = 6
  var passengers: ListBuffer[Passenger] = ListBuffer[Passenger]()
  var state: ElevatorState.Value = ElevatorState.WAITING

  def boardPassenger(passenger: Passenger) = {
    passengers += passenger
  }

  def updateDesiredFloor: Unit = {
    if (passengers.isEmpty) state = ElevatorState.WAITING
    else desiredFloor = passengers.groupBy(_.desiredFloor).maxBy(_._2.size)._1
  }

  def draw(floorIndex: Int): String = if (floorIndex == floor) s"${index + 1}|${Passenger.FACE_AWAIT * passengers.size}|" else " "

  def run(): Unit = {
    state match {
      case ElevatorState.MOVING => move(if (desiredFloor > floor) 1 else -1)
      case ElevatorState.ON_BOARDING => updateDesiredFloor
      case ElevatorState.OFF_BOARDING => updateDesiredFloor
      case ElevatorState.WAITING => move(0)
    }
    if(floor == 0) desiredFloor = 11
    else if(floor == 11) desiredFloor = 0
  }

  def move(moveValue: Int): Unit = {
    floor += moveValue
  }
}