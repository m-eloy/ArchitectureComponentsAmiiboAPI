package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.locale

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data.model.Amiibo

@Dao
interface AmiiboDao {

    @Query("SELECT * FROM amiibo WHERE id= :id")
    fun getById(id:Int): LiveData<Amiibo>

    @Query("SELECT DISTINCT * FROM amiibo ORDER BY character")
    fun getAllLive(): LiveData<List<Amiibo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(amiibo: Amiibo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Amiibo>)

    @Delete
    fun delete(amiibo: Amiibo)
}