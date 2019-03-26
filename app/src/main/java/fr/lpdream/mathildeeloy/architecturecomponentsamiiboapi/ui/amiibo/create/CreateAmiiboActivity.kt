package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.databinding.ActivityCreateAmiiboBinding
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.base.BaseActivity
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class CreateAmiiboActivity : BaseActivity<CreateAmiiboViewModel, ActivityCreateAmiiboBinding>() {

    override val layout: Int = R.layout.activity_create_amiibo

    override val viewModel: CreateAmiiboViewModel by viewModel()

    private var datePickerDialog: DatePickerDialog? = null

    override fun initView(savedInstanceState: Bundle?) {
        setupDatePicker()
        setupToolbar()
        setupViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_amiibo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.confirm -> {
            viewModel.insert()
            ActivityCompat.finishAfterTransition(this@CreateAmiiboActivity)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViews() {
        binding.characterEditText.requestFocus()

        binding.releaseEditText.onClick { datePickerDialog?.show() }
    }

    private fun setupDatePicker() {
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = viewModel.release.value ?: Date()
        datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                viewModel.release.value = calendar.time
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
    }
}