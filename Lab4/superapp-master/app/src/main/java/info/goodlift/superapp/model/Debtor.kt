package info.goodlift.superapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "debtors")
data class Debtor(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "debtor_id")
    var debtorId: Long = 0,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String

): ListItem {

    override fun getItemType(): Int {
        return ListItem.DEBTOR_TYPE
    }
}
