object Passenger {
  val FACE_ARRIVE = "😀"
  val FACE_AWAIT = "😶"
}

class Passenger(var floor: Int, val desiredFloor: Int) {
  val boardedFloor: Int = floor
}
