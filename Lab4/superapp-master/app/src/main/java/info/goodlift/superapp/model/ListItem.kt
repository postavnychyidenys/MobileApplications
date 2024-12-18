package info.goodlift.superapp.model

interface ListItem {
    fun getItemType(): Int

    companion object{
        const val DEBTOR_TYPE = 1
        const val DOSSIER_TYPE = 2
    }
}