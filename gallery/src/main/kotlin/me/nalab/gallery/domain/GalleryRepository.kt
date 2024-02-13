package me.nalab.gallery.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface GalleryRepository: JpaRepository<Gallery, Long> {

    @Query("select g from Gallery g where g.user.id = :userId")
    fun findByUserId(@Param("userId") userId: Long): Gallery?
}
