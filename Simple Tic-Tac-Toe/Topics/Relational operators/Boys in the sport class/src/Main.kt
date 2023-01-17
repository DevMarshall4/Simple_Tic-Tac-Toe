fun main() {
    // put your code here
    val (x1, y1) = readln().split(" ")
    val (x2, y2) = readln().split(" ")
    val (x3, y3) = readln().split(" ")

    val xList = mutableListOf(x1.toInt(), x2.toInt(), x3.toInt())
    val yList = mutableListOf(y1.toInt(), y2.toInt(), y3.toInt())

    loop@ for (i in 1..5) {
        for (j in 0..2) {
            if (xList[j] == i) {
                continue@loop
            }
        }
        print("$i ")
    }
    println()

    loop@ for (i in 1..5) {
        for (j in 0..2) {
            if (yList[j] == i) {
                continue@loop
            }
        }
        print("$i ")
    }
}