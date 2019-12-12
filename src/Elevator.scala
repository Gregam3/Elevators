import scala.collection.mutable

class Elevator(var currentFloor: Int, var desiredFloor: Int, val elevatorNumber: Int) {
  def draw(index: Int): String =
    if (index == currentFloor) s"${elevatorNumber}" else " "

  def simulate(): Unit = if (currentFloor != desiredFloor) move

  def move(): Unit =
    currentFloor += (if (currentFloor < desiredFloor) 1 else -1)
}