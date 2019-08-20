package corp.bcapc.testitau.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import corp.bcapc.testitau.adapter.holder.BaseHolder
import corp.bcapc.testitau.adapter.holder.ContactHolder
import corp.bcapc.testitau.adapter.holder.TitleHolder
import corp.bcapc.testitau.databinding.ItemContactBinding

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 19/08/2019 - 00:35
 */

class ContactsAdapter(private val context: Context, private val listener: (String)-> Unit)
    : RecyclerView.Adapter<BaseHolder>() {

    enum class ITEM_TYPE(val type: Int) {
        TITLE(0),
        CONTACT(1)
    }

    private var lastInitial = '/'
    private var updatedList: MutableList<String> = mutableListOf()
    var contacts: List<String> = emptyList()
        set(value) {
            field = value
            value.map { contact: String ->
                val currentInitial = contact.first()
                if ( currentInitial != lastInitial) {
                    updatedList.add("$currentInitial")
                    lastInitial = currentInitial
                }
                updatedList.add(contact)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        val bind = ItemContactBinding.inflate(LayoutInflater.from(context))
        return if (viewType == ITEM_TYPE.TITLE.type) TitleHolder(bind)
        else ContactHolder(bind)
    }

    override fun getItemViewType(position: Int): Int {
        return if ( updatedList[position].length <= 1) {
            ITEM_TYPE.TITLE.type
        } else {
            ITEM_TYPE.CONTACT.type
        }
    }

    override fun getItemCount(): Int = updatedList.size

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.bind(updatedList[position], listener)
    }


}