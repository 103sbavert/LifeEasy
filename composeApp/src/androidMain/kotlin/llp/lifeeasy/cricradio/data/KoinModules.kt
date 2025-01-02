package llp.lifeeasy.cricradio.data

import llp.lifeeasy.cricradio.data.repository.Repository
import llp.lifeeasy.cricradio.data.repository.RepositoryImpl
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object KoinModules {
    val module = module {
        single { KtorClient() }
        single<Repository> { RepositoryImpl(get()) }
        viewModel { MainViewModel(get()) }
    }
}