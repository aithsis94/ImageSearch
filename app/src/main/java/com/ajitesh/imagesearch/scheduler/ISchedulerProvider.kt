package com.ajitesh.imagesearch.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface ISchedulerProvider {

    val io: Scheduler
    val computation: Scheduler
    val newThread: Scheduler
    val trampoline: Scheduler
    val mainThread: Scheduler
}

class SchedulerProviderImpl : ISchedulerProvider {
    override val io = Schedulers.io()
    override val computation = Schedulers.computation()
    override val newThread = Schedulers.newThread()
    override val trampoline = Schedulers.trampoline()
    override val mainThread = AndroidSchedulers.mainThread()
}