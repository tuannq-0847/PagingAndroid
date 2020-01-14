package com.rikkeisoft.pagindandroid.ui

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rikkeisoft.pagindandroid.R
import com.rikkeisoft.pagindandroid.data.model.FileModel
import kotlinx.android.synthetic.main.activity_view_test.tabLayout
import kotlinx.android.synthetic.main.activity_view_test.viewPager2
import kotlinx.android.synthetic.main.file_activity.recyclerFile
import java.io.File

class FileActivity : AppCompatActivity() {
    private var count = 0

    private val fileAdapter by lazy {
        FileAdapter(mutableListOf()) {
            onItemClick(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_activity)
        recyclerFile.adapter = fileAdapter
        val path = Environment.getExternalStorageDirectory().absolutePath
        //readFiles(path)
        setContentView(R.layout.activity_view_test)

        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(BlankFragment())
        fragments.add(BlankFragment())
        fragments.add(BlankFragment())
        fragments.add(BlankFragment())
//        val pagerAdapter = PagerAdapter(supportFragmentManager, fragments)
//        viewPager2.adapter = pagerAdapter
//        tabLayout.setupWithViewPager(viewPager2)
        handleLogic("/storage/emulated/0/test")
        Log.d("test", count.toString())
    }

    private fun handleLogic(rootPath: String) {
        val files = File(rootPath)
        val listFile = files.listFiles()
        listFile?.forEach {
            if (it.isDirectory) {
                handleLogic(it.path)
            } else {
                count++
            }
        }
    }

    private fun readFiles(path: String) {
        val files = File(path)
        val listFile = files.listFiles()
        val resuls: MutableList<FileModel> = mutableListOf()
        listFile?.forEach {
            if (it.isDirectory) {
                resuls.add(FileModel(it.name, true, it.absolutePath))
            } else {
                resuls.add(FileModel(it.name, false, it.absolutePath))
            }
        }
        fileAdapter.update(resuls)
    }

    private fun onItemClick(fileModel: FileModel) {
        readFiles(fileModel.path)
    }
}
