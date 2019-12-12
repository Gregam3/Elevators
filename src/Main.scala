import Simulator.FLOORS

import scala.util.Random

object Main extends App {
  val ELEVATOR_COUNT = 4



  Simulator.runSimulation(1.to(ELEVATOR_COUNT).map(new Elevator(Simulator.randomFloor, Simulator.randomFloor, _)))
}
