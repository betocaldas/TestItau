package corp.bcapc.testitau.adapter.bind

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import corp.bcapc.usecase.UserInfo
import java.text.NumberFormat
import java.util.*


/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 19/08/2019 - 14:27
 */

@BindingAdapter("account_group", "userinfo", requireAll = true)
fun getAccountSelected(textView: TextView, accountGroup: RadioGroup, userInfo: UserInfo?) {
    accountGroup.setOnCheckedChangeListener { radioGroup, _ ->
        when (radioGroup.checkedRadioButtonId) {
            corp.bcapc.testitau.R.id.balance_bt -> textView.text = userInfo?.balance
            corp.bcapc.testitau.R.id.savings_bt -> textView.text = userInfo?.savings
            else -> textView.text = userInfo?.balance
        }
    }
}

@BindingAdapter("money_unit")
fun getMoneyFormatter(textView: TextView, unit: String) {
    val formatter = NumberFormat.getInstance()
    val locale = Locale("pt", "BR")
    formatter.currency = Currency.getInstance(locale)
    formatter.isGroupingUsed = true
    formatter.maximumFractionDigits = 2
    formatter.minimumFractionDigits = 2
    textView.addTextChangedListener(object : TextWatcher {
        var shouldChange = true
        override fun afterTextChanged(et: Editable?) {
            if (shouldChange) {
                shouldChange = false
                et?.let {
                    val text = it.toString()
                    it.replace(0, text.length, "$unit ${formatter.format(text.toDouble())}")
                }
            } else {
                shouldChange = true
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    })
}

@BindingAdapter("plus1Bt", "plus5Bt", "plus10Bt", "clearBt", "transferValue", requireAll = true)
fun addMoney(textView: TextView, plus1Bt: Button, plus5Bt: Button, plus10Bt: Button,
             clearBt: Button, transferValue: String) {

}