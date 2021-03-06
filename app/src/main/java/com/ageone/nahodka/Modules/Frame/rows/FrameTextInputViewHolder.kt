package com.ageone.nahodka.Modules.Frame.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import yummypets.com.stevia.*

class FrameTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputFrame by lazy {
        val editText = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        editText.layoutParams = params

        editText.boxStrokeColor = Color.parseColor("#D7D7D7")
        editText.setInactiveUnderlineColor(Color.rgb(215, 215, 215))
        editText.defineType(InputEditTextType.TEXT)

        editText.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 16F
        }
        editText
    }

    init {
        renderUI()
    }
}

fun FrameTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputFrame
    )

    textInputFrame
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(14)
}

fun FrameTextInputViewHolder.initialize(hint: String) {
    textInputFrame.hint = hint
}
