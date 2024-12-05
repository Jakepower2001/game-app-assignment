package ie.setu.models

data class Game(
    var gameId: Int = 0,
    var gameName: String,
    var gameRating: Int,
    var gameCategory: String,
    var isGameSaved: Boolean = false,
    var saves: MutableSet<Savefile> = mutableSetOf(),
){
    private var lastSaveId = 0
    private fun getSaveId() = lastSaveId++

    fun addSave(save: Savefile): Boolean{
        save.saveId = getSaveId()
        return saves.add(save)
    }


}