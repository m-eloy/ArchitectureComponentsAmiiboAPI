package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui

import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create.CreateAmiiboViewModel
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.detail.DetailAmiiboViewModel
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list.AmiibosViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModule = module {

    viewModel { AmiibosViewModel(androidApplication()) }

    viewModel { CreateAmiiboViewModel(androidApplication()) }

    viewModel { DetailAmiiboViewModel(androidApplication()) }
}