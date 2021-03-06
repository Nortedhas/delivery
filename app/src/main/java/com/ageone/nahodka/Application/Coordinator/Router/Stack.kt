package com.ageone.nahodka.Application.Coordinator.Router

import com.ageone.nahodka.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.nahodka.Application.Coordinator.Flow.Stack.*
import com.ageone.nahodka.Application.Coordinator.Router.TabBar.Stack
import timber.log.Timber

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    Stack.flows.clear()

    // MARK: in order like in navigation


    Timber.i("Bottom create stack flows")

    runFlowMain()
    runFlowStock()
    runFlowProfile()

    Stack.flows[startFlow].start()
}