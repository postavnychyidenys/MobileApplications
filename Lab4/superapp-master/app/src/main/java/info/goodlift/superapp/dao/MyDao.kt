package info.goodlift.superapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import info.goodlift.superapp.model.Debtor
import info.goodlift.superapp.model.Dossier

@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDebtor(debtor: Debtor) : Long

    @Query("Select * From debtors Where debtor_id = :id")
    suspend fun getDebtorById(id: Long): Debtor?

    @Query("Select * From debtors")
    suspend fun getAllDebtors(): List<Debtor>

    @Delete
    suspend fun deleteDebtor(debtor: Debtor)

    @Query("Delete From debtors")
    suspend fun deleteAllDebtors()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDossier(dossier: Dossier)

    @Query("Select * From dossiers Where dossier_id = :id")
    suspend fun getDossierById(id: Long): Dossier?

    @Query("Select * From dossiers")
    suspend fun getAllDossiers(): List<Dossier>

    @Delete
    suspend fun deleteDossier(dossier: Dossier)

    @Query("Delete From dossiers")
    suspend fun deleteAllDossiers()
}