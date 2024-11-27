package com.example.lab3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(
    private val data: List<ListItem>
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    abstract class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: ListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return when (viewType) {
            ListItem.DEBTOR_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.debtor_list_item, parent, false)
                DebtorHolder(view)
            }
            ListItem.DOSSIER_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.dossier_list_item, parent, false)
                DossierHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getItemType()
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class DebtorHolder(itemView: View) : DataViewHolder(itemView) {
        private val firstNameTextView: TextView = itemView.findViewById(R.id.firstNameTextView)
        private val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)

        override fun bind(item: ListItem) {
            if (item is Debtor) {
                firstNameTextView.text = item.firstName
                lastNameTextView.text = item.lastName
            }
        }
    }

    class DossierHolder(itemView: View) : DataViewHolder(itemView) {
        private val firstNameTextView: TextView = itemView.findViewById(R.id.firstNameTextView)
        private val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)

        override fun bind(item: ListItem) {
            if (item is Dossier) {
                firstNameTextView.text = item.firstName
                lastNameTextView.text = item.lastName
            }
        }
    }
}
