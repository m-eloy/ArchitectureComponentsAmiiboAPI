package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.Amiibo
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.AmiiboRepository
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.extension.startAnimatedActivity
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create.CreateAmiiboActivity
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.detail.DetailAmiiboActivity
import kotlinx.android.synthetic.main.activity_amiibo.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class AmiibosActivity : AppCompatActivity() {

    private val viewModel: AmiibosViewModel by lazy { ViewModelProviders.of(this).get(AmiibosViewModel::class.java) }

    private var amiibosAdapter = AmiibosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amiibo)

        setupAdapter()
        setupFab()
        setupRecyclerView()
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
        fab.onClick { startAnimatedActivity(intentFor<CreateAmiiboActivity>()) }
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this@AmiibosActivity, DividerItemDecoration.VERTICAL))
            layoutManager = LinearLayoutManager(this@AmiibosActivity)
            adapter = amiibosAdapter
        }
    }

    private fun showDeletePopup(amiibo: Amiibo) {
        alert(getString(R.string.delete_amiibo_warning, amiibo.character)) {
            yesButton {
                doAsync { AmiiboRepository.delete(amiibo) }
            }
            noButton { }
        }.show()
    }
}
