package info.goodlift.superapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import info.goodlift.superapp.database.SuperDataBase
import info.goodlift.superapp.model.Debtor
import info.goodlift.superapp.model.Dossier
import info.goodlift.superapp.model.ListItem
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val superDataBase by lazy { SuperDataBase.getDatabase(application) }
    private val dao by lazy { superDataBase.myDao() }

    private val _itemList = MutableLiveData<ArrayList<ListItem>>()
    val itemList: LiveData<ArrayList<ListItem>> get() = _itemList

    init {
        createData()
    }

    private fun getData() {
        viewModelScope.launch {
            val dataList = ArrayList<ListItem>()
            dataList.addAll(dao.getAllDebtors())
            dataList.addAll(getAllDossiers())
            _itemList.value = dataList
        }
    }

    fun clearData() {
        viewModelScope.launch {
            dao.deleteAllDebtors()
            getData()
        }
    }

    fun createData() {
        viewModelScope.launch {
            dao.deleteAllDebtors()
            dao.deleteAllDossiers()

            var debtor = Debtor(firstName = "Erik", lastName = "1Deb")
            var debtorId = addDebtor(debtor)

            var dossier = Dossier(debtorId = debtorId, firstName = "Bill", lastName = "1Dos")
            dao.insertDossier(dossier)

            dossier = Dossier(debtorId = debtorId, firstName = "John", lastName = "2Dos")
            dao.insertDossier(dossier)

            debtor = Debtor(firstName = "Erik", lastName = "2Deb")
            debtorId = addDebtor(debtor)

            dossier = Dossier(debtorId = debtorId, firstName = "Jane", lastName = "3Dos")
            dao.insertDossier(dossier)

            getData()
        }
    }

    suspend fun addDebtor(debtor: Debtor): Long {
        val debtorId = dao.insertDebtor(debtor)
        getData()
        return debtorId
    }

    fun addDossier(dossier: Dossier) {
        viewModelScope.launch {
            dao.insertDossier(dossier)
            getData()
        }
    }

    fun deleteDebtor(debtor: Debtor) {
        viewModelScope.launch {
            dao.deleteDebtor(debtor)
            getData()
        }
    }

    fun deleteDossier(dossier: Dossier) {
        viewModelScope.launch {
            dao.deleteDossier(dossier)
            getData()
        }
    }

    suspend fun getAllDossiers(): List<Dossier> {
        val dossiers = dao.getAllDossiers()
        for (dossier in dossiers) {
            dossier.debtor = dao.getDebtorById(dossier.debtorId)
        }
        return dossiers
    }
}