package com.ageone.nahodka.Modules.Buscket.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import yummypets.com.stevia.*

class BuscketBottomViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewTotal by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#373737")
        textView.text = "Итого"
        textView
    }

    val textViewCount by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#373737")
        textView
    }

    val buttonCheckout by lazy {
        val button = BaseView()
        button.cornerRadius = 8.dp
        button.backgroundColor = Color.parseColor("#21D5BF")
        button.initialize()
        button
    }

    val textViewCheckout by lazy {
        val textView = BaseTextView()
        textView.text = "Оформить заказ"
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16F
        textView
    }

    init {
        renderUI()
    }
}

fun BuscketBottomViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewTotal,
        textViewCount,
        buttonCheckout,
        textViewCheckout
    )

    textViewTotal
        .constrainTopToTopOf(constraintLayout,20)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewCount
        .constrainCenterYToCenterYOf(textViewTotal)
        .constrainRightToRightOf(constraintLayout,15)

    buttonCheckout
        .height(utils.tools.getActualSizeFromDes(46))
        .width(utils.variable.displayWidth - 15)
        .constrainTopToBottomOf(textViewTotal,20)
        .fillHorizontally(15)

    textViewCheckout
        .constrainCenterYToCenterYOf(buttonCheckout)
        .constrainCenterXToCenterXOf(buttonCheckout)
}

fun BuscketBottomViewHolder.initialize(total: Int) {
    textViewCount.text = "${total.toString()} руб."
}
