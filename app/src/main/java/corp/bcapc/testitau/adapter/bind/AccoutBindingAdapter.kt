package corp.bcapc.testitau.adapter.bind

import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import corp.bcapc.usecase.UserInfo
import java.text.NumberFormat
import java.util.*


/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 19/08/2019 - 14:27
 */

val locale = Locale("pt", "BR")
val numberFormatter = NumberFormat.getInstance().apply {
    currency = Currency.getInstance(locale)
    isGroupingUsed = true
    maximumFractionDigits = 2
    minimumFractionDigits = 2
}

fun NumberFormat.unFormat(money: String, money_unit: String): Number =
    numberFormatter.parse(money.replace("$money_unit ", ""))


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

    textView.addTextChangedListener(object : TextWatcher {
        var shouldChange = true
        override fun afterTextChanged(et: Editable?) {
            if (shouldChange) {
                shouldChange = false
                et?.let {
                    val text = it.toString()
                    it.replace(0, text.length, "$unit ${numberFormatter.format(text.toDouble())}")
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

//@BindingAdapter("plus1Bt", "plus5Bt", "plus10Bt", "clearBt", "transferValue", requireAll = false)
//fun addMoney(textView: TextView, plus1Bt: Button, plus5Bt: Button, plus10Bt: Button,
//             clearBt: Button) {
//    clearBt.setOnClickListener {
//        textView.text = "0"
//    }
//    plus1Bt.setOnClickListener {
//
//    }
//}