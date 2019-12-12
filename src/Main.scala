import scala.collection.mutable
import scala.sys.process._

object Main extends App {
  val FLOORS = 12;

  var elevators = 1.to(4).map(i => new Elevator(0, 0, i))
  elevators.foreach(el => el.desiredFloor=11)

  runSimulation

  /**
   * @param elves Yeah okay I was feeling a little festive sue me
   */
  def printElevators(elves: Seq[Elevator]) = {
    0.until(FLOORS).foreach(i =>
      //TODO remove individual iterators
      println(String.format("%8s %8s %8s %8s", elves(0).draw(i),
        elves(1).draw(i), elves(2).draw(i), elves(3).draw(i))))

    "clear".!!
    println
  }

  def runSimulation {
    var timeSteps = 0
    while (true) {
      timeSteps += 1
      Thread.sleep(1000)
      elevators.foreach(el => {
        el.simulate
      })
      printElevators(elevators)
    }
  }
}

object Cls extends App {
  print("\033[2J")
}
