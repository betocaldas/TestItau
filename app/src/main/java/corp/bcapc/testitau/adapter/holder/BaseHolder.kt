package corp.bcapc.testitau.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import corp.bcapc.testitau.databinding.ItemContactBinding

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 19/08/2019 - 00:53
 */

abstract class BaseHolder(bind: ItemContactBinding) : RecyclerView.ViewHolder(bind.root) {
    abstract fun bind(name: String, listener:(String)-> Unit)
}