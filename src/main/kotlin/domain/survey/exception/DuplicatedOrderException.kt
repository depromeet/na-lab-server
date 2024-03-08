package domain.survey.exception

class DuplicatedOrderException internal constructor(duplicated: Int, orders: HashSet<Int>) :
    RuntimeException(
        "Duplicated order detected duplicated \"$duplicated\" ordinary \"$orders\""
    )
