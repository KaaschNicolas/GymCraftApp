package com.example.myapplication.repositories

import com.example.myapplication.daos.CourseTagMappingDao
import com.example.myapplication.daos.TagDao
import com.example.myapplication.models.CourseTagMapping
import com.example.myapplication.models.Tag
import javax.inject.Inject

class TagRepository
@Inject constructor(
    private val tagDao: TagDao
) {
    fun save(tag: Tag)
            = tagDao.save(tag)

    fun delete(tag: Tag)
            = tagDao.delete(tag)

    fun getAll() = tagDao.getAll()

    fun getOneById(id :Int) = tagDao.getOneById(id)

    fun getOneByName(name: String) = tagDao.getOneByName(name)
}
