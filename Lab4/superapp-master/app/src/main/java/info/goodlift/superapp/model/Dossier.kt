package info.goodlift.superapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(
    tableName = "dossiers",
    foreignKeys = [
        ForeignKey(
            entity = Debtor::class,
            parentColumns = ["debtor_id"],
            childColumns = ["debtor_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Dossier(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dossier_id")
    var dossierId: Long = 0,

    @ColumnInfo(name = "debtor_id")
    var debtorId: Long,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String

): ListItem {

    @Ignore
    var debtor: Debtor? = null

    override fun getItemType(): Int {
        return ListItem.DOSSIER_TYPE
    }
}
