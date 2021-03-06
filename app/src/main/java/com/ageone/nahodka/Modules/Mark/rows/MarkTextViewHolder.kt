package com.ageone.nahodka.Modules.Mark.rows

import android.graphics.Color
import android.graphics.Typeface
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Button.BaseButton
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.nahodka.External.Base.TextInputLayout.InputEditTextType
import com.ageone.nahodka.External.Base.TextView.BaseTextView
import com.ageone.nahodka.External.Base.View.BaseView
import yummypets.com.stevia.*

class MarkTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewEstimate by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#242839")
        textView.text = "Оцените заведение"
        textView
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textSize = 20F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textColor = Color.parseColor("#242839")
        textView
    }

    val imageViewRating1 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val imageViewRating2 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val imageViewRating3 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val imageViewRating4 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val imageViewRating5 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.ic_star)
        imageView.initialize()
        imageView
    }

    val textInputL by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params

        textInput.boxStrokeColor = Color.parseColor("#C1C1C1")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 7F.dp
        }
        textInput
    }

    val buttonSend by lazy {
        val button = BaseButton()
        button.cornerRadius = 6.dp
        button.backgroundColor = Color.parseColor("#21D5BF")
        button.textSize = 16F
        button.text = "Отправить"
        button.textColor = Color.WHITE
        button.initialize()
        button
    }

    val viewStar1 by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

    val viewStar2 by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

    val viewStar3 by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

    val viewStar4 by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

    val viewStar5 by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

    init {
        renderUI()
    }
}

fun MarkTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewEstimate,
        textViewName,
        viewStar1.subviews(imageViewRating1),
        viewStar2.subviews(imageViewRating2),
        viewStar3.subviews(imageViewRating3),
        viewStar4.subviews(imageViewRating4),
        viewStar5.subviews(imageViewRating5),
        textInputL,
        buttonSend
    )

    textViewEstimate
        .constrainTopToTopOf(constraintLayout,26)
        .constrainLeftToLeftOf(constraintLayout,15)

    textViewName
        .constrainTopToBottomOf(textViewEstimate, 26)
        .constrainLeftToLeftOf(constraintLayout,15)

    viewStar1
        .width(32)
        .height(32)
        .constrainTopToBottomOf(textViewName, 4)
        .constrainLeftToLeftOf(constraintLayout,7)

    imageViewRating1
        .width(18)
        .height(18)
        .constrainCenterXToCenterXOf(viewStar1)
        .constrainCenterYToCenterYOf(viewStar1)

    viewStar2
        .width(32)
        .height(32)
        .constrainTopToBottomOf(textViewName, 4)
        .constrainLeftToRightOf(viewStar1)

    imageViewRating2
        .width(18)
        .height(18)
        .constrainCenterXToCenterXOf(viewStar2)
        .constrainCenterYToCenterYOf(viewStar2)

    viewStar3
        .width(32)
        .height(32)
        .constrainTopToBottomOf(textViewName, 4)
        .constrainLeftToRightOf(viewStar2)

    imageViewRating3
        .width(18)
        .height(18)
        .constrainCenterXToCenterXOf(viewStar3)
        .constrainCenterYToCenterYOf(viewStar3)

    viewStar4
        .width(32)
        .height(32)
        .constrainTopToBottomOf(textViewName, 4)
        .constrainLeftToRightOf(viewStar3)

    imageViewRating4
        .width(18)
        .height(18)
        .constrainCenterXToCenterXOf(viewStar4)
        .constrainCenterYToCenterYOf(viewStar4)

    viewStar5
        .width(32)
        .height(32)
        .constrainTopToBottomOf(textViewName, 4)
        .constrainLeftToRightOf(viewStar4)

    imageViewRating5
        .width(18)
        .height(18)
        .constrainCenterXToCenterXOf(viewStar5)
        .constrainCenterYToCenterYOf(viewStar5)

    textInputL
        .constrainTopToBottomOf(viewStar1,4)
        .fillHorizontally(14)

    buttonSend
        .constrainTopToBottomOf(textInputL,28)
        .fillHorizontally(16)
}

fun MarkTextViewHolder.initialize(name:String, hint: String, type: InputEditTextType) {
    textViewName.text = name
    textInputL.hint = hint
    textInputL.defineType(type)
}

