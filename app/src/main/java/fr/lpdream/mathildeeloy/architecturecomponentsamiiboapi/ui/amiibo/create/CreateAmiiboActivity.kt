package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.ui.amiibo.create

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProviders
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.R
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.databinding.ActivityCreateAmiiboBinding
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class CreateAmiiboActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAmiiboBinding

    private val viewModel: CreateAmiiboViewModel by lazy { ViewModelProviders.of(this).get(CreateAmiiboViewModel::class.java) }

    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_amiibo)
        binding.setVariable(BR.viewModel, viewModel)
        binding.setLifecycleOwner(this)

        setupDatePicker()
        setupToolbar()
        setupViews()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_create_amiibo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        android.R.id.home -> {
            ActivityCompat.finishAfterTransition(this)
            true
        }
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