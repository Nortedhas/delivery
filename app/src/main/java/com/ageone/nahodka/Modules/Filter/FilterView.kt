package com.ageone.nahodka.Modules.Filter

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.Models.Filter
import com.ageone.nahodka.Modules.Filter.rows.FilterTextViewHolder
import com.ageone.nahodka.Modules.Filter.rows.initialize
import yummypets.com.stevia.*

class FilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = FilterViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Фильтры"
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        toolbar.textColor = Color.WHITE

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

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
        private val FilterTextType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {

            else -> FilterTextType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                FilterTextType -> {
                    FilterTextViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            var isPressedPrice = false
            var isPressedAround = false

            when (holder) {
                is FilterTextViewHolder -> {
                    holder.initialize()
                    when(rxData.currentFilter){
                        Filter.distance -> {
                            holder.checkPrice.isChecked = true
                            isPressedPrice = true
                        }
                        Filter.price -> {
                            holder.checkAround.isChecked = true
                            isPressedAround = true
                        }
                    }

                    holder.checkPrice.setOnClickListener {

                        holder.checkAround.isChecked = false
                        holder.checkPrice.isChecked = true
                        isPressedAround = false

                        if(isPressedPrice) {
                            holder.checkPrice.isChecked = false
                            isPressedPrice = false

                            rxData.currentFilter = Filter.none
                        } else if(!isPressedPrice) {
                            isPressedPrice = true

                            rxData.currentFilter = Filter.distance
                        }
                    }

                    holder.checkAround.setOnClickListener {
                        holder.checkPrice.isChecked = false
                        holder.checkAround.isChecked = true
                        isPressedPrice = false

                        if(isPressedAround) {
                            holder.checkAround.isChecked = false
                            isPressedAround = false

                            rxData.currentFilter = Filter.none
                        } else if(!isPressedAround) {
                            isPressedAround = true

                            rxData.currentFilter = Filter.price
                        }
                    }
                }
            }
        }
    }
}

fun FilterView.renderUIO() {
    renderBodyTable()
}


