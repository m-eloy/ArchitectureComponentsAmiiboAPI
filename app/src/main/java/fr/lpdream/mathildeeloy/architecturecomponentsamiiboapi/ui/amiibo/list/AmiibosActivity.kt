package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.remote.AmiibosResponseCallback
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.databinding.ActivityAmiibosBinding
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension.showAction
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension.showError
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension.startAnimatedActivity
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create.CreateAmiiboActivity
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.detail.DetailAmiiboActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AmiibosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAmiibosBinding

    private val viewModel: AmiibosViewModel by lazy { ViewModelProviders.of(this).get(AmiibosViewModel::class.java) }

    private var amiibosAdapter = AmiibosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_amiibos)
        binding.setVariable(BR.viewModel, viewModel)
        binding.setLifecycleOwner(this)

        setupAdapter()
        setupFab()
        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    private fun setupAdapter() {
        viewModel.amiibos.observe(this, Observer {
            amiibosAdapter.submitList(it)
        })

        amiibosAdapter.apply {
            onClick = { startAnimatedActivity(intentFor<DetailAmiiboActivity>("id" to it.id)) }
            onLongClick = { showDeletePopup(it) }
        }
    }

    private fun setupFab() {
        binding.fab.onClick { startAnimatedActivity(intentFor<CreateAmiiboActivity>()) }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this@AmiibosActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@AmiibosActivity)
            adapter = amiibosAdapter
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.apply {

            fun refresh() {
                isRefreshing = true
                viewModel.refresh(object: AmiibosResponseCallback{
                    override fun onSuccess() {
                        binding.root.showAction(getString(R.string.amiibos_loaded))
                        isRefreshing = false
                    }

                    override fun onError(throwable: Throwable) {
                        binding.root.showError(getString(R.string.amiibos_loading_error))
                        isRefreshing = false
                    }
                })
            }
            setOnRefreshListener { refresh() }
            post { refresh() }
        }
    }

    private fun showDeletePopup(amiibo: Amiibo) {
        alert(getString(R.string.delete_amiibo_warning, amiibo.character)) {
            yesButton { viewModel.delete(amiibo) }
            noButton { }
        }.show()
    }
}
