package models

import utils.Utilities

data class Game(
    var gameId: Int = 0,
    var gameName: String,
    var gameRating: Int,
    var gameCategory: String,
    var isGameSaved: Boolean = false,
    var saves: MutableSet<Savefile> = mutableSetOf()
) {
    private var lastSaveId = 0
    private fun getSaveId() = lastSaveId++

    fun addSave(save: Savefile): Boolean {
        save.saveId = getSaveId()
        return saves.add(save)
    }

    fun amountOfSaves() = saves.size

    fun findOne(id: Int): Savefile? {
        return saves.find { savefile -> savefile.saveId == id }
    }

    fun delete(id: Int): Boolean {
        return saves.removeIf { savefile -> savefile.saveId == id }
    }

    fun update(id: Int, newSavefile: Savefile): Boolean {
        val foundSavefile = findOne(id)

        if (foundSavefile != null) {
            foundSavefile.saveContents = newSavefile.saveContents
            foundSavefile.isSaveComplete = newSavefile.isSaveComplete
            return true
        }
        return false
    }

    fun listSaves() =
        if (saves.isEmpty()) "\tPlays have yet to be added, get to it!"
        else Utilities.formatSetString(saves)

    override fun toString(): String {
        val archived = if (isGameSaved) 'Y' else 'N'
        return "$gameId: $gameName, Rating($gameRating), Genre($gameCategory, Game saved($isGameSaved) \n${listSaves()}"
    }
}