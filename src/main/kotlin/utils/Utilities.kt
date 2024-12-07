package utils

import models.Game
import models.Savefile

object Utilities {

    /**
     * Will return a string of a list of Game objects which are already formatted
     *@param games, this will list the Game objects to be formatted
     * @return a formatted string of all the list of games
     */


    @JvmStatic
    fun formatListString(games: List<Game>): String {
        val sbd = StringBuilder()
        for (game in games) {
            sbd.append(game.toString()).append("\n")
        }
        return sbd.toString().trim()
    }


    @JvmStatic
    fun formatSetString(savesToFormat: Set<Savefile>): String =
        savesToFormat
            .joinToString(separator = "\n") { Savefile -> "\t$Savefile" }
}