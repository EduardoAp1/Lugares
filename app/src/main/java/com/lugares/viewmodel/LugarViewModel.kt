package com.lugares.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.lugares.data.LugarDatabase
import com.lugares.model.Lugar
import com.lugares.repository.LugarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LugarViewModel(application: Application) : AndroidViewModel(application) {

    //Atributo para acceder al repositorio de lugar
    private val repository: LugarRepository

    //Atributo para obtener la lista de lugares en un ArrayList especial
    //LiveData permite crear hilos que estan observando si un lugar es afectado por un CRUD, esta permite poner observadores
    val getAllData: LiveData<List<Lugar>>

    //Bloque de inicialización de los atributos
    init{
        val lugarDao = LugarDatabase.getDataBase(application).lugarDao()
        repository = LugarRepository(lugarDao)
        getAllData = repository.getAllData
    }

    fun addLugar(lugar: Lugar){
        viewModelScope.launch(Dispatchers.IO){
            repository.addLugar(lugar)
        }
    }

    fun updateLugar(lugar: Lugar){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateLugar(lugar)
        }
    }

    fun deleteLugar(lugar: Lugar){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteLugar(lugar)
        }
    }

}