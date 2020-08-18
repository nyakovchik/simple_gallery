package by.dro.testapp.fileexplorer.ui

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalArgumentException

class ScreenPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int) = when(position){
        0 -> PhotoExplorerFragment()
        1 -> VideoExplorerFragment()
        2 -> AllFilesExplorerFragment()
        else -> throw IllegalArgumentException()
    }
}