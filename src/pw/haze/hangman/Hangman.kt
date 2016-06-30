package pw.haze.hangman

/**
 * |> Author: haze
 * |> Since 4/28/16
 * |> The one, the only.
 */
class Hangman(val word: String) {

    var hasHead = false
    var hasTorso = false
    var hasLeftLeg = false
    var hasRightLeg = false
    var hasLeftArm = false
    var hasRightArm = false

    val knownChars = arrayListOf<Char>()
    val blacklist = arrayListOf<Char>()
    private val alphaet = "abcdefghijklmnopqrstuvwxyz".toCharArray()

    fun progress(guess: Char): Boolean {

        if (this.knownChars.contains(guess)) return false
        else if (this.word.contains(guess)) {
            this.knownChars.add(guess); return false
        }

        this.blacklist.add(guess)
        if (!hasHead) hasHead = true
        else if (!hasTorso) hasTorso = true
        else if (!hasLeftArm) hasLeftArm = true
        else if (!hasRightArm) hasRightArm = true
        else if (!hasLeftLeg) hasLeftLeg = true
        else if (!hasRightLeg) hasRightLeg = true
        else return true
        return false
    }

    fun isComplete(): Boolean = this.knownChars.size == this.word.count()

    fun wordToSpaces(): String = buildString {
        chars@ for (char in word) {
            if (knownChars.contains(char)) append(char)
            else append("_")
            append(" ")
        }
    }

    fun restOfAlphabet(): String {
        val builder = StringBuilder()
        chars@ for(char in alphaet) {
            if(!blacklist.contains(char) && !knownChars.contains(char)) {
                builder.append("$char ")
            }
        }
        return builder.toString()
    }


    fun drawMan() {
        println("    ____  ")
        println("   |   ${if (hasHead) "O" else ""}   ")
        println("   |  ${if (hasLeftArm) "/" else " "}" +
                "${if (hasTorso) "|" else ""}" +
                "${if (hasRightArm) "\\" else ""}   ")
        println("   |  ${if (hasLeftLeg) "/ " else " "}${if (hasRightLeg) "\\" else ""}  ")
        println("   |      ")
        println("   |      ")
        println("   |     selection = ${restOfAlphabet()} ")
        println("   |      ")
        println("   |     ${wordToSpaces()} ")
        println("  _|___   ")

    }

}