/**
 * @author: assignment: JakePower20096517
 * @author:  sdrohan - skeleton code from tutors
 */

package controllers

import models.Game
import persistence.Serializer
import utils.Utilities.formatListString
import java.util.ArrayList

//manages game data and allows methods to use its data
class GameAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType
    private var games = ArrayList<Game>()

    private var lastId = 0
    private fun getId() = lastId++


    // CRUD methods for array
    /**
     * adds new game object which is saved to the array, gives it a unique id
     */
    fun add(game: Game): Boolean {
        game.gameId = getId()
        return games.add(game)
    }
    /**
     *     deletes a game object from the game object to be deleted
     *
     */
    fun delete(id: Int) = games.removeIf { game -> game.gameId == id }

    /**
     * updates a game in the array list by using its unique id
     * boolean declares whether the change was a success or not
     */
    fun update(id: Int, game: Game?): Boolean {
        val gameFound = gameFind(id)
        /**
         * if the game does exist, this will use its details to update the game it belonged to
         */
        if ((gameFound != null) && (game != null)) {
            gameFound.gameName = game.gameName
            gameFound.gameRating = game.gameRating
            gameFound.gameCategory = game.gameCategory
            return true

        }
        return false
    }

    /**
     *     saving methods for games
     */
    fun saveGame(id: Int): Boolean {
        val gameFound = gameFind(id)
        if ((gameFound != null) && (!gameFound.isGameSaved)
        ) {
            gameFound.isGameSaved = true
            return true
        }
        return false
    }

    /**
     * Listing methods for games array
     * returns a string of all games that have been added
     */

    fun listEveryGame() =
        if (games.isEmpty()) "No games currently here...."
        else formatListString(games)

    /**
     * this does the same as the list every game
     * lists all games saved in a string format
     */
    fun listSavedGames() =
        if (savedGamesCount() == 0) "No saved games currently."
        else formatListString(games.filter { game -> !game.isGameSaved })

    // Search methods

    fun gameFind(gameId: Int) = games.find { game -> game.gameId == gameId }

    fun gamesAmount() = games.size

    private fun savedGamesCount(): Int= games.count { game: Game -> game.isGameSaved }


    fun amountOfGameSaves(): Int = games.count { game: Game -> !game.isGameSaved  }
}

/*@Throws(Exception::class)
fun load() {
    val loadedGames = Serializer.read()
    if(loadedGames is ArrayList<*>) {
        games = loadedGames.filterIsInstance<Game>() as ArrayList<Game>
    } else {
        throw Exception("Unable to load games that have saves")
    }
}

@Throws(Exception::class)
fun store() {
    serializer.write(games)
}

*/