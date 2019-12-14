import Simulator.FLOORS

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Main extends App {
  val ELEVATOR_COUNT = 4

  lazy val elevators: IndexedSeq[ElevatorCar] = 0.until(ELEVATOR_COUNT).map(new ElevatorCar(Simulator.randomFloor, Simulator.randomFloor, _))

  Simulator.runSimulation(elevators.to[ListBuffer])
}
