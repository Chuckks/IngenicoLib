package com.bbva.devicelib.physical.data

import com.bbva.devicelib.physical.ICard
import com.bbva.devicelib.utilities.IEmpty

private val DEFAULT_FALLBACK: Boolean = false
private val DEFAULT_TIMEOUT: String = ""
private val DEFAULT_SET = emptySet<ICard.EType>()

data class CardConfig(
    var cardSupported: Set<ICard.EType> = DEFAULT_SET,
    var detectedCardTimeout: String = DEFAULT_TIMEOUT,
    var fallBack: Boolean = DEFAULT_FALLBACK
): IEmpty {
    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

}
