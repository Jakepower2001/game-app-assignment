/**
 * @author: assignment: JakePower20096517
 * @author:  sdrohan - skeleton code from tutors
 */

package controllers

import ie.setu.models.Game
import persistence.Serializer
import utils.Utilities.formatListString
import java.util.ArrayList

class GameAPI(serializerType: Serializer) {
    private var serializer: Serializer = serializerType
    private var games = ArrayList<Game>()

    private var lastId = 0
    private fun getId() = lastId++

    fun add(game: Game): Boolean {
        game.gameId = getId()
        return games.add(game)
    }

    fun delete(id: Int) = games.removeIf { game -> game.gameId == id }


}
