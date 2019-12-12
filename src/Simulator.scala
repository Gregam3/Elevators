

import scala.collection.mutable
import scala.sys.process._
import scala.util.Random

object Simulator {
  val FLOORS = 12
  val ELEVATOR_COUNT = 4
  val ELEVATOR_FORMAT = "%1s"
  val P_FORMAT_NUM = 6

  val passengers: Seq[String] = generatePassengers

  def randomFloor = {
    Random.nextInt(FLOORS - 1) + 1
  }


  /**
   * @param elves Yeah okay I was feeling a little festive sue me
   */
  def printElevators(elves: Seq[Elevator]): Unit = {
    //TODO remove individual indexes
    0.until(FLOORS).foreach(i => {
      print(String.format(ELEVATOR_FORMAT * 2, elves(0).draw(i), elves(1).draw(i)))
      print(s"|${String.format(s"%${P_FORMAT_NUM}s", formatPassengers(passengers(i)))}|")
      println(String.format(ELEVATOR_FORMAT * 2, elves(2).draw(i), elves(3).draw(i)))
    })

    "clear".!!
  }

  def generatePassengers: Seq[String] = {
    0.until(FLOORS).map(_ => {
      val passengerFloorBuilder = new StringBuilder
      while (Random.nextFloat() > 0.6) {
        passengerFloorBuilder.append("p")
      }
      passengerFloorBuilder.toString
    })
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

  private[this] def formatPassengers(passengerStr: String) = {
    passengerStr + (" " * ((P_FORMAT_NUM - 1) - passengerStr.length))
  }
}
