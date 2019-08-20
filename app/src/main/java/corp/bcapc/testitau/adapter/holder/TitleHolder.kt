package corp.bcapc.testitau.adapter.holder

import corp.bcapc.testitau.databinding.ItemContactBinding

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 19/08/2019 - 00:48
 */

class TitleHolder(private val bind: ItemContactBinding) : BaseHolder(bind) {

    override fun bind(name: String, listener: (String) -> Unit) {
        bind.name = "${name.first()}"
    }

}