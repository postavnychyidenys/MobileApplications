package info.goodlift.superapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import info.goodlift.superapp.MyViewModel
import info.goodlift.superapp.databinding.DebtorListItemBinding
import info.goodlift.superapp.databinding.DossierListItemBinding
import info.goodlift.superapp.model.Debtor
import info.goodlift.superapp.model.Dossier
import info.goodlift.superapp.model.ListItem

class MyAdapter(
    private val itemList: LiveData<ArrayList<ListItem>>,
    private val vm: MyViewModel
) : RecyclerView.Adapter<MyAdapter.DataViewHolder>() {
    abstract class DataViewHolder(itemBinding: ViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        abstract fun bind(item: ListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return when (viewType) {
            ListItem.DEBTOR_TYPE -> DebtorHolder(
                DebtorListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                vm
            )

            ListItem.DOSSIER_TYPE -> DossierHolder(
                DossierListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                vm
            )

            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return itemList.value!![position].getItemType()
    }

    override fun getItemCount(): Int {
        return itemList.value?.size ?: 0
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(itemList.value!![position])
    }

    /*    class DebtorHolder(itemView: View) : DataViewHolder(itemView) {
            private val firstNameTextView: TextView = itemView.findViewById(R.id.firstNameTextView)
            private val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)

            override fun bind(item: ListItem) {
                if (item is Debtor) {
                    firstNameTextView.text = item.firstName
                    lastNameTextView.text = item.lastName
                }
            }
        }*/

    class DebtorHolder(
        private val itemBinding: DebtorListItemBinding,
        private val vm: MyViewModel
    ) :
        DataViewHolder(itemBinding) {
        override fun bind(item: ListItem) {
            item as Debtor
            itemBinding.firstNameTextView.text = item.firstName
            itemBinding.lastNameTextView.text = item.lastName
            itemBinding.debtorDelBtn.setOnClickListener { vm.deleteDebtor(item) }
        }
    }

    class DossierHolder(
        private val itemBinding: DossierListItemBinding,
        private val vm: MyViewModel
    ) :
        DataViewHolder(itemBinding) {
        override fun bind(item: ListItem) {
            item as Dossier
            itemBinding.firstNameTextView.text = item.firstName
            itemBinding.lastNameTextView.text = item.lastName
            itemBinding.dossierDelBtn.setOnClickListener { vm.deleteDossier(item) }
        }
    }

    /*    class DossierHolder(itemView: View) : DataViewHolder(itemView) {
            private val firstNameTextView: TextView = itemView.findViewById(R.id.firstNameTextView)
            private val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)

            override fun bind(item: ListItem) {
                if (item is Dossier) {
                    firstNameTextView.text = item.firstName
                    lastNameTextView.text = item.lastName
                }
            }
        }*/
}
