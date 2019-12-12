

import scala.sys.process._
import scala.util.Random

object Simulator {
  val FLOORS = 12
  val ELEVATOR_COUNT = 4
  val STR_FORMAT = "%1s"

  /**
   * @param elves Yeah okay I was feeling a little festive sue me
   */
  def printElevators(elves: Seq[Elevator]) = {
    //TODO remove individual indexes
    0.until(FLOORS).foreach(i => {
      print(String.format(STR_FORMAT * 2, elves(0).draw(i), elves(1).draw(i)))
      print("|         |")
      println(String.format(STR_FORMAT * 2, elves(2).draw(i), elves(3).draw(i)))
    })

    "clear".!!
    println
  }

  def runSimulation(elevators: Seq[Elevator]) {
    var timeSteps = 0
    while (true) {
      timeSteps += 1
      elevators.foreach(el => el.simulate)
      printElevators(elevators)
      Thread.sleep(3000)
    }
  }
}
