package corp.bcapc.testitau.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import corp.bcapc.testitau.R
import corp.bcapc.testitau.adapter.ContactsAdapter
import corp.bcapc.testitau.databinding.FragmentContactBinding
import corp.bcapc.testitau.model.ContactsData
import corp.bcapc.testitau.viewmodel.UserInfoViewModel
import corp.bcapc.usecase.UserInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class ContactFragment : Fragment() {

    private lateinit var bind: FragmentContactBinding
    private val userinfoVM: UserInfoViewModel by viewModel()
    private var adapter : ContactsAdapter? = null
    private var userInfo: UserInfo? = null
    private val navOpt = configNavigationOptions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = setContactAdapter()
        setObservers()
        userinfoVM.getUserInfo()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentContactBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.vm = userinfoVM
        bind.contactsRv.layoutManager = object : LinearLayoutManager(this@ContactFragment.context) {
            override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
                return RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
        bind.contactsRv.adapter = adapter
    }

    private fun setContactAdapter() = this.activity?.let {
        ContactsAdapter(it) { name ->
            userInfo?.let { userInfo ->
                val valor = getRadioAccountMoney(userInfo)
                val action = ContactFragmentDirections.actionContactFragmentToTransferFragment(ContactsData(name, valor))
                this.findNavController().navigate(action, navOpt)
            }
        }
    }

    private fun setObservers() {
        activity?.let { activity ->
            userinfoVM.userInfoLiveData.observe(activity, Observer { response ->
                userInfo = response
                bind.userInfo = userInfo
                userInfo?.let {
                    adapter?.contacts = it.contacts
                    adapter?.notifyDataSetChanged()
                }
            })
        }
    }

    private fun configNavigationOptions() = NavOptions.Builder().build()

    private fun getRadioAccountMoney(userInfo: UserInfo) =
        if (bind.accountRadioGroup.checkedRadioButtonId == R.id.savings_bt) {
            userInfo.savings
        } else userInfo.balance
}
