package models

data class Savefile( var saveId: Int = 0, var saveContents: String, var isSaveComplete: Boolean = false){
    override fun toString() =
    if (isSaveComplete)
        "$saveId: $saveContents (Saved)"
    else
        "$saveId: $saveContents (To-Be-Saved)"
}