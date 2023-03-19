fun main(args: Array<String>) {


    var xoSwitch = 1
    var game = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' ')
    )

    //sout
    displayGame(game)


    //prompt the user to make a move
    while (xoSwitch < 10 && gameLogic(game)) {
        game = usrInputCheck(game, xoSwitch)
        xoSwitch++
    }

    //if there is no winner up until this point the game is draw
    xoSwitch++
    if (xoSwitch == 11) {
        println("Draw")
    }
}


//formats and outputs game field
fun displayGame(game: MutableList<MutableList<Char>>) {
    println("---------")
    for (i in game.indices) {
        print("| ")
        for (j in game[i].indices) {
            print("${game[i][j]} ")
        }
        println("|")
    }
    println("---------")
}

//prompt the user to make a move
//user should input 2 numbers that represent the coordinates of where they want to place
//their X e.g. (1 1)
fun usrInputCheck(game: MutableList<MutableList<Char>>, xoSwitch: Int): MutableList<MutableList<Char>> {
    var firstCoordinate: Int
    var secondCoordinate: Int


    while (true) {
        println("Enter coordinates")
        val usrInput = readln()

        if (usrInput.length == 3) {
            val usrInputList = usrInput.split(" ")
            if (usrInputList[0].toInt() > 3 || usrInputList[1].toInt() > 3) {
                println("Coordinates should be from 1 to 3!")
            } else {
                firstCoordinate = usrInputList[0].toInt() - 1
                secondCoordinate = usrInputList[1].toInt() - 1
                //check if cell is occupied
                if (game[firstCoordinate][secondCoordinate] != ' ') {
                    println("This cell is occupied! Choose another one!")
                    continue
                }
                break
            }
        } else {
            println("You should enter numbers!")
        }
    }

    if (xoSwitch % 2 != 0) game[firstCoordinate][secondCoordinate] = 'X'
    else game[firstCoordinate][secondCoordinate] = 'O'
    displayGame(game)
    return game
}

//game logic
fun gameLogic(game: MutableList<MutableList<Char>>): Boolean {
    for (i in game.indices) {
        for (j in game[i].indices) {
            //check row for winner
            if (j == 2) {
                if (game[i][2] == game[i][j - 1] && game[i][2] == game[i][0] && (game[i][0] != ' ')) {
                    println("${game[i][0]} wins")
                    return false
                }
            }
            //check column for winner
            if (i == 2) {
                if (game[2][j] == game[i - 1][j] && game[2][j] == game[0][j] && (game[0][j] != ' ')) {
                    println("${game[0][j]} wins")
                    return false
                }
            }
        }
    }

    //check for winner in left-to-right diagonal
    if (game[2][2] == game[0][0] && game[2][2] == game[1][1] && (game[1][1] != ' ')) {
        println("${game[1][1]} wins")
        return false
    }

    //check for winner in right-to-left diagonal
    if (game[0][2] == game[1][1] && game[0][2] == game[2][0] && (game[1][1] != ' ')) {
        println("${game[1][1]} wins")
        return false
    }
    return true
}
