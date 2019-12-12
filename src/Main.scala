import Simulator.FLOORS

import scala.collection.mutable
import scala.sys.process._
import scala.util.Random

object Main extends App {
  val ELEVATOR_COUNT = 4

  def randomFloor = {
    Random.nextInt(FLOORS - 1) + 1
  }

  Simulator.runSimulation(1.to(ELEVATOR_COUNT).map(new Elevator(randomFloor, randomFloor, _)))
}
