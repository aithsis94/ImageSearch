package com.ajitesh.imagesearch

import com.ajitesh.imagesearch.scheduler.ISchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProviderImpl(
    override val io: Scheduler = Schedulers.trampoline(),
    override val computation: Scheduler = Schedulers.trampoline(),
    override val newThread: Scheduler = Schedulers.trampoline(),
    override val trampoline: Scheduler = Schedulers.trampoline(),
    override val mainThread: Scheduler = Schedulers.trampoline()

) : ISchedulerProvider