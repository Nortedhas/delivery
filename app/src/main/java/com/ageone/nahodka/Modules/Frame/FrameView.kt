package com.ageone.nahodka.Modules.Frame

import android.graphics.Color
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Modules.Frame.rows.FrameTextInputViewHolder
import com.ageone.nahodka.Modules.Frame.rows.initialize
import yummypets.com.stevia.*

class Frameiew(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = FrameViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = ""
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        onDeInit = {
            rxData.comment = viewModel.model.comment
        }

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.Event::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val FrameEditTextType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> FrameEditTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                FrameEditTextType -> {
                    FrameTextInputViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is FrameTextInputViewHolder -> {
                    holder.initialize("Комментарий к заказу")
                    Handler().postDelayed({
                        holder.textInputFrame.editText?.requestFocus()
                    }, 300)

                    if (rxData.comment.isNotBlank()) {
                        holder.textInputFrame.editText?.setText(rxData.comment)
                    }

                    holder.textInputFrame.editText?.doOnTextChanged { text, start, count, after ->
                        /*RxBus.publish(RxEvent.EventChangeMark(text.toString()))*/
                        viewModel.model.comment = text.toString()
                    }
                }
            }
        }
    }
}

fun Frameiew.renderUIO() {
    renderBodyTable()
}


