package me.nalab.gallery.domain

fun gallery(
    id: Long = 1,
    user: User = user(),
    survey: Survey = survey()
): Gallery = Gallery(id, user, survey)

fun user(
    id: Long = 2,
    name: String = "DEFAULT_NAME",
    position: String = "DEFAULT_POSITION",
): User = User(id, name, position)

fun survey(
    id: Long = 3,
    feedbackCount: Int = 0,
    saveCount: Int = 0,
) = Survey(id, feedbackCount, saveCount)
