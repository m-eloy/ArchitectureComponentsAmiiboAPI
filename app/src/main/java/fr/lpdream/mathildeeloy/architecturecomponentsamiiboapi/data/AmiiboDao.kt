package fr.lpdream.mathildeeloy.architecturecomponentsamiiboapi.data

import androidx.room.*

@Dao
interface AmiiboDao {

    @Query("SELECT * FROM amiibo WHERE id= :id")
    fun getById(id:Int): Amiibo

    @Query("SELECT * FROM amiibo ORDER BY character")
    fun getAll(): List<Amiibo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(amiibo: Amiibo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Amiibo>)

    @Delete
    fun delete(amiibo: Amiibo)
}