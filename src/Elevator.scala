import scala.collection.mutable

class Elevator(var currentFloor: Int, var desiredFloor: Int, index: Int) {
  def draw(index: Int): String = {
    if (index == currentFloor) s"|${index}|" else "|"
  }


  def simulate = {
    if (currentFloor != desiredFloor) move
  }

  def move = {
    if (currentFloor < desiredFloor) currentFloor += 1
    else currentFloor -= 1
  }
}