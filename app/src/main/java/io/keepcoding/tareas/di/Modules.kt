package io.keepcoding.tareas.di

import androidx.room.Room
import io.keepcoding.tareas.data.repository.FakeRepository
import io.keepcoding.tareas.data.repository.TaskRepositoryImpl
import io.keepcoding.tareas.data.repository.local.TaskDatabase
import io.keepcoding.tareas.data.repository.mapper.TaskEntityMapper
import io.keepcoding.tareas.data.repository.mapper.TaskMapper
import io.keepcoding.tareas.domain.TaskRepository
import io.keepcoding.tareas.presentation.tasks.TasksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            TaskDatabase::class.java,
            "tasks.db"
        ).build()
    }

    factory {
        get<TaskDatabase>().getTaskDao()
    }

    single<TaskRepository>(qualifier = named("real")) {
        TaskRepositoryImpl(get(), get(), get())
    }

    single<TaskRepository>(qualifier = named("fake")) {
        FakeRepository()
    }

    single {
        TaskMapper()
    }

    single {
        TaskEntityMapper()
    }

    viewModel {
        TasksViewModel(get(qualifier = named("fake")))
    }

}