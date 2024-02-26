package me.nalab.gallery.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import javax.persistence.LockModeType

interface GalleryRepository : JpaRepository<Gallery, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select g from Gallery as g where g.target.targetId = :targetId")
    fun findByTargetIdOrNull(@Param("targetId") targetId: Long): Gallery?
}
