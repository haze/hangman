package pw.haze.hangman

import java.util.*

/**
 * |> Author: haze
 * |> Since 4/28/16
 */

val input: Scanner = Scanner(System.`in`)
var hangman: Hangman = Hangman("n/a")
var another = false
var words = arrayOf("words", "television", "monty python", "terraria")
val rand = Random()

fun main(args: Array<String>) {
    println("Be warned!-- Only the first letter will be accepted.")
    do {
        play()
        println("Want to play another game? [y/n]: ")
        val inChar = input.nextLine()[0].toLowerCase()
        another = inChar == 'y'
    } while (another)
}

fun play() {
    hangman = Hangman(words[rand.nextInt(words.size)])
    var isOver = false
    var winComplete = false
    do {
        hangman.drawMan()
        val line = input.nextLine()
        if(line.length == 0) { println("You entered nothing!"); continue }
        val inChar = line[0].toLowerCase()
        if (inChar.isDigit()) { println("Only Letters!"); continue }
        isOver = hangman.progress(inChar)
        winComplete = hangman.isComplete()
    } while (!winComplete  && !isOver)
    hangman.drawMan()
    println(if(isOver) "You Lost!" else "You Win!")
}