package com.example.ageone.Modules.Restaurant

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.ImageView.BaseImageView
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.Models.RxEvent
import com.ageone.nahodka.Modules.RestaurantList.rows.RestaurantListImageViewHolder
import com.ageone.nahodka.Modules.RestaurantList.rows.initialize
import com.example.ageone.Modules.Restaurant.rows.RestaurantListItemViewHolder
import com.example.ageone.Modules.Restaurant.rows.initialize
import yummypets.com.stevia.*

class RestaurantListView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = RestaurantListViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    var foodList = listOf(
        R.drawable.pic_food1,
        R.drawable.pic_food2,
        R.drawable.pic_food3,
        R.drawable.pic_food4,
        R.drawable.pic_food1,
        R.drawable.pic_food2
    )

    val imageViewFAB by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundColor(Color.TRANSPARENT)
        imageView.width(25.dp)
        imageView.height(25.dp)
        imageView.setImageResource(R.drawable.button_filter)
        imageView.initialize()
        imageView
    }

    init {
        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Рестораны"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))

        renderToolbar()


        imageViewFAB.setOnClickListener {
            emitEvent?.invoke(RestaurantListViewModel.EventType.OnFilterPressed.name)
        }

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        compositeDisposable.add(
            RxBus.listen(RxEvent.EventChangePushCount::class.java).subscribe { pushCount ->
                toolbar.countPush = pushCount.count
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val RestaurantListImageType = 0
        private val RestaurantListItemType = 1

        override fun getItemCount() = 1 + viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RestaurantListImageType
            else -> RestaurantListItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RestaurantListImageType -> {
                    RestaurantListImageViewHolder(layout)
                }
                RestaurantListItemType -> {
                    RestaurantListItemViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is RestaurantListImageViewHolder -> {
                    holder.initialize()

                    holder.onTap = {
                        rootModule.emitEvent?.invoke(RestaurantListViewModel.EventType.OnBannerPressed.name)
                    }
                }
                is RestaurantListItemViewHolder -> {
                    val pos = position - 1
                    if (pos in viewModel.realmData.indices) {
                        val company = viewModel.realmData[pos]

                        holder.initialize(
                            company.image?.original ?: "",
                            company.name,
                            "Итальянская, Мексиканская, Кавказ...",//todo: add tags
                            "Заказ от ${company.deliveryFrom} руб.",//todo: check in create order
                            R.drawable.ic_star,
                            company.rating
                        )
                        holder.constraintLayout.setOnClickListener {
                            rxData.currentCompany = company
                            rootModule.emitEvent?.invoke(RestaurantListViewModel.EventType.OnRestaurantPressed.name)
                        }
                    }

                }
            }
        }
    }
}

fun RestaurantListView.renderUIO() {

    innerContent.subviews(
        bodyTable,
        imageViewFAB
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false

    imageViewFAB
        .constrainRightToRightOf(innerContent,5)
        .constrainBottomToBottomOf(innerContent,30)

}


