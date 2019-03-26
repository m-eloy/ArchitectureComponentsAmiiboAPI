package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.detail

import android.os.Bundle
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.databinding.ActivityDetailAmiiboBinding
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.base.BaseActivity

class DetailAmiiboActivity : BaseActivity<DetailAmiiboViewModel, ActivityDetailAmiiboBinding>() {

    override val layout: Int = R.layout.activity_detail_amiibo

    override fun setViewModel(): Class<DetailAmiiboViewModel> = DetailAmiiboViewModel::class.java

    override fun initView(savedInstanceState: Bundle?) {
        viewModel.amiiboId.value = intent.getIntExtra("id", 0)
        setupToolbar()
    }


    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}