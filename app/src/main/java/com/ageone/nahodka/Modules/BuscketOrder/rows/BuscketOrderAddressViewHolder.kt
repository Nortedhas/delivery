package com.ageone.nahodka.Modules.BuscketOrder.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import yummypets.com.stevia.*

class BuscketOrderAddressViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputAddress by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params

        textInput.boxStrokeColor = Color.parseColor("#D7D7D7")
        textInput.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        textInput.defineType(InputEditTextType.TEXT)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 16F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    init {
        renderUI()
    }
}

fun BuscketOrderAddressViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputAddress
    )

    textInputAddress
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(14)
}

fun BuscketOrderAddressViewHolder.initialize(hint: String) {
    textInputAddress.hint = hint
}
