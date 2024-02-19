package me.nalab.gallery.domain

fun gallery(
    id: Long = 1,
    user: User = user(),
    survey: Survey = survey()
): Gallery = Gallery(id, user, survey)

fun user(
    id: Long = 2,
    name: String = "DEFAULT_NAME",
    nickname: String = "DEFAULT_POSITION",
    userImageUrl: String = "DEFAULT_USER_IMAGE_URL",
): User = User(id, name, nickname, userImageUrl)

fun survey(
    id: Long = 3,
    feedbackCount: Int = 0,
    saveCount: Int = 0,
    feedback: Feedback = feedback(),
    tendencies: MutableList<Tendency> = tendencies(),
): Survey = Survey(id, feedbackCount, saveCount, feedback, tendencies)

fun feedback(
    reply: String = "DEFAULT_REPLY",
): Feedback = Feedback(reply)

fun tendencies(
    tendencyPair: List<Pair<String, Int>> = listOf("열정적인" to 10, "경청하는" to 10),
): MutableList<Tendency> = tendencyPair.map { (tendencyName, tendencyCount) ->
    Tendency(tendencyName, tendencyCount)
}.toMutableList()
