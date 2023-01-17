/*
Tic-Tac-Toe game made as a project in course "Kotlin Basics" by Hyper-Skill.org
Finished in 17/01/2023

By Marcelo Machado
*/

package tictactoe

// Display game board on the console
fun printBoard(boardMatrix: List<List<Char>>) {
    println("---------")
    println("| " + boardMatrix[0][0] + " " + boardMatrix[0][1] + " " + boardMatrix[0][2] + " |")
    println("| " + boardMatrix[1][0] + " " + boardMatrix[1][1] + " " + boardMatrix[1][2] + " |")
    println("| " + boardMatrix[2][0] + " " + boardMatrix[2][1] + " " + boardMatrix[2][2] + " |")
    println("---------")
}

// Returns the number of pieces on the game board
fun amountPiecesInBoard(matrix: List<List<Char>>): Int {
    var numberPieces = 0
    for(i in 0..2){
        for(j in 0..2) {
            if (matrix[i][j] != ' ') {
                numberPieces++
            }
        }
    }
    return numberPieces
}

// Returns player Char, 'X' or 'O', if there is a winner or '_', otherwise
fun checkVerticalWinner(matrix: List<List<Char>>): Char {
    var player = ' '
    for (i in 0..2) {
        if (matrix[i][0] == matrix[i][1] && matrix[i][1] == matrix[i][2]) {
            player = matrix[i][0]
        }
    }
    return player
}

// Returns player Char, 'X' or 'O', if there is a winner, empty string, '', otherwise
fun checkHorizontalWinner(matrix: List<List<Char>>): Char {
    var player = ' '
    for (i in 0..2) {
        if (matrix[0][i] == matrix[1][i] && matrix[1][i] == matrix[2][i]) {
            player = matrix[0][i]
        }
    }
    return player
}

// Returns player Char, 'X' or 'O', if there is a winner, empty string, '', otherwise
fun checkDiagonalWinner(matrix: List<List<Char>>): Char {
    var player = ' '
    if (matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]) {
        player = matrix[0][0]
    }
    if (matrix[2][0] == matrix[1][1] && matrix[2][0] == matrix[0][2]) {
        player = matrix[2][0]
    }
    return player
}

// Gets user input, return x and y coordinates
fun getAndCheckInput(boardMatrix: List<List<Char>>): Pair<Int, Int> {
    var xPosition = 0
    var yPosition = 0
    while (true) {
        try {
            val (xAsString, yAsString) = readln().split(" ")
            xPosition = xAsString.toInt()
            yPosition = yAsString.toInt()
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            continue
        } /*catch (e: IndexOutOfBoundsException) {
            println("You should enter numbers!")
            continue
        }*/
        if (xPosition in 1..3 && yPosition in 1..3) {
            if (boardMatrix[xPosition - 1][yPosition - 1] == ' ') {
                return Pair(xPosition, yPosition)
            }
            println("This cell is occupied! Choose another one!")
        } else {
            println("Coordinates should be from 1 to 3!")
        }
    }
}

fun main() {
    var boardMatrix = mutableListOf(
            mutableListOf(' ', ' ', ' '),
            mutableListOf(' ', ' ', ' '),
            mutableListOf(' ', ' ', ' '),
    )
    printBoard(boardMatrix)

    var nPieces = 0
    var horizontalWinner: Char
    var verticalWinner: Char
    var diagonalWinner: Char
    var numberTurn = 1
    while(true) {
        val (xPosition, yPosition) = getAndCheckInput(boardMatrix)
        boardMatrix[xPosition - 1][yPosition - 1] = if (numberTurn % 2 == 1) 'X' else 'O'
        printBoard(boardMatrix)

        horizontalWinner = checkHorizontalWinner(boardMatrix)
        verticalWinner = checkVerticalWinner(boardMatrix)
        diagonalWinner = checkDiagonalWinner(boardMatrix)

        if(horizontalWinner != ' ') {
            println("$horizontalWinner wins")
            break
        }
        if(verticalWinner != ' ') {
            println("$verticalWinner wins")
            break
        }
        if(diagonalWinner != ' ') {
            println("$diagonalWinner wins")
            break
        }

        nPieces = amountPiecesInBoard(boardMatrix)
        if(nPieces == 9)
        {
            println("Draw")
            break
        }
        numberTurn++
    }
}