package by.dro.testapp.fileexplorer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import by.dro.testapp.fileexplorer.util.getInternalStoragePath
import by.dro.testapp.fileexplorer.util.getSDCardPath
import java.util.*

abstract class FileViewModel(application: Application) : AndroidViewModel(application){

    val internalPath : String = application.getInternalStoragePath()
    val sdCardPath : String = application.getSDCardPath()

    private val _path = MutableLiveData<String?>()

    val path: LiveData<String?>
    get() = _path

    private val stack = Stack<String>()

    fun openInternal(path: String){
        stack.push(path)
        _path.value = path
    }

    fun back(){
        if (stack.size > 1){
            stack.pop()
            _path.value = stack.peek()
        }
        else if (stack.size <= 1){
            stack.clear()
            _path.value = null
        }

    }


}