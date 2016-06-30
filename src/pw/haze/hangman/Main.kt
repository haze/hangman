package pw.haze.hangman

import java.io.File
import java.nio.file.Files.readAllLines
import java.util.*

/**
 * |> Author: haze
 * |> Since 4/28/16
 */

val input: Scanner = Scanner(System.`in`)
var hangman: Hangman = Hangman("n/a")
var another = false
var words = lazy { readAllLines(File("res/words.txt").toPath()) }
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
    hangman = Hangman(words.value[rand.nextInt(words.value.size)])
    var isOver = false
    var winComplete = false
    do {
        hangman.drawMan()
        val line = input.nextLine()
        if (line.length == 0) { println("You entered nothing!"); continue }
        val inChar = line[0].toLowerCase()
        if (hangman.blacklist.contains(inChar) || hangman.knownChars.contains(inChar)) { println("You've already tried that letter!"); continue }
        if (!inChar.isLetter()) { println("Only Letters!"); continue }
        isOver = hangman.progress(inChar)
        winComplete = hangman.isComplete()
    } while (!winComplete  && !isOver)
    hangman.drawMan()
    println(if(isOver) "You Lost! The word was ${hangman.word}" else "You Win!")
}