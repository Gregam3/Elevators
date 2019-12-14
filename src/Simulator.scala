

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, Map}
import scala.collection.parallel.immutable
import scala.sys.process._
import scala.util.Random

object Simulator {
  val FLOORS = 12
  val ELEVATOR_COUNT = 4
  val ELEVATOR_FORMAT = "%10s"
  val P_FORMAT_NUM = 12

  var passengers: Map[Int, ListBuffer[Passenger]] = generatePassengers

  def randomFloor = {
    Random.nextInt(FLOORS - 1) + 1
  }

  /**
   * @param elves Yeah okay I was feeling a little festive sue me
   */
  def printElevators(elves: ListBuffer[ElevatorCar]): Unit = {
    //TODO remove individual indexes
    0.until(FLOORS).foreach(i => {
      print(String.format(ELEVATOR_FORMAT * 2, elves(0).draw(i), elves(1).draw(i)))
      print(s"  Ԥ${String.format(s"%${P_FORMAT_NUM}s", formatPassengers(passengers, i))}Ԥ  ")
      println(String.format(ELEVATOR_FORMAT * 2, elves(2).draw(i), elves(3).draw(i)))
    })

    println
    "clear".!!
  }

  def generatePassengers = {
    mutable.Map(
      1.to(10).map(_ => new Passenger(randomFloor, randomFloor))
        .groupBy(_.floor)
        .to: _*
    ).map(e => e._1 -> e._2.to[ListBuffer])
  }

  def runSimulation(elevators: ListBuffer[ElevatorCar]) {
    var timeSteps = 0
    while (true) {
      timeSteps += 1
      elevators.foreach(el => {
        el.simulate
        if (passengers.keys.exists(_ == el.currentFloor)) {
          passengers(el.currentFloor)
          elevators(el.index).passengers
            = passengers(el.currentFloor)
          passengers.remove(el.currentFloor)
        }
      })

      printElevators(elevators)
      Thread.sleep(3000)
    }
  }

  private[this] def formatPassengers(floorPassengers: Map[Int, ListBuffer[Passenger]], index: Int): String = {
    if (!floorPassengers.keys.exists(_ == index)) " " else floorPassengers(index).map(_ => Passenger.FACE).mkString
  }

}