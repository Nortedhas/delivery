package com.ageone.nahodka.Application.Coordinator.Router

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.nahodka.Application.Coordinator.Flow.setBottomNavigationVisible
import com.ageone.nahodka.Application.currentActivity
import com.ageone.nahodka.Application.router
import com.ageone.nahodka.External.Extensions.Activity.hideKeyboard
import com.ageone.nahodka.External.Extensions.Function.guard
import timber.log.Timber
import yummypets.com.stevia.style

class Router {
    lateinit var layout: ConstraintLayout

    fun onBackPressed() {

        val current = currentFlow.guard {
            Timber.e("Current flow is null")
            return
        }

        if (current!!.stack.size > 1) {

            // MARK: in flow - pop module from flow
            Timber.i("pop module")

            current.pop()

        } else {

            // MARK: pop flow - change current flow to previous if it exists
            Timber.i("pop flow")

            val previousFlow = current.previousFlow.guard {
                Timber.e("Previous flow is null")
                return
            }

            //change current flow
            val flowToDelete = currentFlow
            currentFlow = previousFlow

            flowToDelete?.onFinish?.invoke()

            viewFlipperFlow.displayFlow(previousFlow!!.settingsCurrentFlow.indexOnFlipperFlow)
//            viewFlipperFlow.displayedChild = previousFlow!!.settingsCurrentFlow.indexOnFlipperFlow

            //correct visible bottom bar
            val isBottomBarVisible = previousFlow.settingsCurrentFlow.isBottomNavigationVisible
            setBottomNavigationVisible(isBottomBarVisible)
            currentFlow?.settingsCurrentFlow?.isBottomNavigationVisible = isBottomBarVisible

            currentActivity?.hideKeyboard()

        }

    }


    fun initialize() {
        // MARK: app's root layout
        layout = ConstraintLayout(currentActivity)

        router.layout.style {
            isFocusable = true
            isFocusableInTouchMode = true

        }

    }
}

// MARK: settings for correcting routing (pop function)

data class DataFlow(
    val indexOnFlipperFlow: Int = 0,
    var isBottomNavigationVisible: Boolean = false
)