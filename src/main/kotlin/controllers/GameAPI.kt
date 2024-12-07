/**
 * @author: assignment: JakePower20096517
 * @author:  sdrohan - skeleton code from tutors
 */

package controllers

import ie.setu.models.Game
import persistence.Serializer
import utils.Utilities.formatListString
import java.sql.RowId
import java.util.ArrayList

class GameAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType
    private var games = ArrayList<Game>()

    private var lastId = 0
    private fun getId() = lastId++


    // CRUD methods for array

    fun add(game: Game): Boolean {
        game.gameId = getId()
        return games.add(game)
    }

    fun delete(id: Int) = games.removeIf { game -> game.gameId == id }

    fun update(id: Int, game: Game?): Boolean {
        val gameFound = gameFind(id)

        if ((gameFound != null) && (game != null)) {
            gameFound.gameName = game.gameName
            gameFound.gameRating = game.gameRating
            gameFound.gameCategory = game.gameCategory
            return true

        }
        return false
    }

    // Listing methods for games array

    fun listEveryGame() =
        if (games.isEmpty()) "No games currently here...."
        else formatListString(games)


    fun listSavedGames() =
        if (savedGamesCount() == 0) "No saved games currently."
        else formatListString(games.filter { game -> game.isGameSaved })

    // Search methods

    fun gameFind(gameId: Int) = games.find { game -> game.gameId == gameId }

    fun gamesAmount() = games.size

    private fun savedGamesCount(): Int= games.count { game: Game -> game.isGameSaved }

}

