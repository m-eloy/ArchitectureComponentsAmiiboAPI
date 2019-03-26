package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.detail

import android.os.Bundle
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.databinding.ActivityDetailAmiiboBinding
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailAmiiboActivity : BaseActivity<DetailAmiiboViewModel, ActivityDetailAmiiboBinding>() {

    override val layout: Int = R.layout.activity_detail_amiibo

    override val viewModel: DetailAmiiboViewModel by viewModel()

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.amiiboId.value = intent.getIntExtra("id", 0)
        setupToolbar()
    }


    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}