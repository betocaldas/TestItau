package corp.bcapc.testitau.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import corp.bcapc.testitau.databinding.FragmentTransferBinding

class TransferFragment : Fragment() {

    private lateinit var bind: FragmentTransferBinding
    private val data: TransferFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentTransferBinding.inflate(inflater, container, false)
        bind.transferValue = "0"
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.data = data.ContactData
    }
}
