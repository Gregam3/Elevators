import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, Map}
import scala.sys.process._
import scala.util.Random

object Simulator {
  val FLOORS = 12
  val ELEVATOR_COUNT = 4
  val ELEVATOR_FORMAT = "%15s"
  val P_FORMAT_NUM = 12
  var timeSteps = 0f

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
      print(s"  Ԥ${String.format(s"%${P_FORMAT_NUM}s", formatPassengers(ElevatorController.waitingPassengers, i))}Ԥ  ")
      println(String.format(ELEVATOR_FORMAT * 2, elves(2).draw(i), elves(3).draw(i)))
    })

    println(s"Offboarded=${ElevatorController.passengersOffboarded} timesteps=$timeSteps " +
      s"Throughput = ${ElevatorController.passengersOffboarded / timeSteps}")
    "clear".!!
  }

  def generatePassengers(passengerCount: Int) = {
    mutable.Map(
      1.to(passengerCount).map(_ => new Passenger(randomFloor, randomFloor))
        .groupBy(_.floor)
        .to: _*
    ).map(e => e._1 -> e._2.to[ListBuffer])
  }

  def runSimulation() {

    while (timeSteps < 5000) {
      timeSteps += 1
      ElevatorController.run()

      if (Random.nextFloat() > 0.5) ElevatorController.waitingPassengers ++= generatePassengers(Random.nextInt(3))

      printElevators(ElevatorController.elevators)
      Thread.sleep(0)
    }
  }

  private[this] def formatPassengers(floorPassengers: Map[Int, ListBuffer[Passenger]], index: Int): String = {
    if (!floorPassengers.keys.exists(_ == index)) " " else floorPassengers(index).map(_ => Passenger.FACE_AWAIT).mkString
  }
}