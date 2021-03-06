package com.ageone.nahodka.Modules.Profile

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.R
import com.ageone.nahodka.External.Base.Module.BaseModule
import com.ageone.nahodka.External.Base.RecyclerView.BaseAdapter
import com.ageone.nahodka.External.Base.RecyclerView.BaseViewHolder
import com.ageone.nahodka.External.InitModuleUI
import com.ageone.nahodka.External.RxBus.RxBus
import com.ageone.nahodka.External.Utils.Validation.toBeautifulPhone
import com.ageone.nahodka.Models.RxEvent
import com.ageone.nahodka.Models.User.user
import com.ageone.nahodka.Modules.Profile.rows.ProfileItemViewHolder
import com.ageone.nahodka.Modules.Profile.rows.ProfileTextNameViewHolder
import com.ageone.nahodka.Modules.Profile.rows.initialize
import yummypets.com.stevia.*

class ProfileView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ProfileViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_white)

        toolbar.title = "Профиль"
        toolbar.textColor = Color.WHITE
        toolbar.setBackgroundColor(Color.parseColor("#09D0B8"))
        toolbar.countPush = rxData.selectedItems.size
        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        compositeDisposable.addAll(
            RxBus.listen(RxEvent.EventChangePushCount::class.java).subscribe { pushCount ->
                toolbar.countPush = pushCount.count
            },
            RxBus.listen(RxEvent.EventChangeAddress::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            },
            RxBus.listen(RxEvent.EventChangeNameOrPhone::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val ProfileTextNameType = 0
        private val ProfileItemType  = 1

        override fun getItemCount() = 4//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ProfileTextNameType
            else -> ProfileItemType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ProfileTextNameType -> {
                    ProfileTextNameViewHolder(layout)
                }
                ProfileItemType -> {
                    ProfileItemViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is ProfileTextNameViewHolder -> {
                    holder.initialize(user.data.name, user.data.phone.toBeautifulPhone())
                    holder.textViewChange.setOnClickListener {
                        rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnChangePressed.name)
                    }
                }

                is ProfileItemViewHolder -> {
                    when(position) {
                        1 -> {
                            val text = if (user.info.address.isBlank()) {
                                "Заполните адрес доставки и оформляйте заказ еще быстрее"
                            } else {
                                user.info.address
                            }

                            holder.initialize(R.drawable.ic_location,"Адрес доставки", text)
                            holder.constraintLayout.setOnClickListener {
                                rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnFillAddressPressed.name)
                            }
                        }
                        2 -> {
                            holder.initialize(R.drawable.ic_orders,"Мои заказы", "Здесь Вы сможете отслеживать статус своего заказа и просматривать прошлые")
                            holder.constraintLayout.setOnClickListener {
                                rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnMyOrderPressed.name)
                            }
                        }
                        3 -> {
                            holder.initialize(R.drawable.ic_call,"Связаться с нами", "Если у вас возникли вопросы, можете обратиться в нашу службу поддержки")
                            holder.constraintLayout.setOnClickListener {
                                rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnContactPressed.name)
                            }
                        }
                    }
                }
            }
        }
    }
}

fun ProfileView.renderUIO() {
    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally()
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false
}


