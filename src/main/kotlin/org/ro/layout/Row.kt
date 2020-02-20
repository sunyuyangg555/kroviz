package org.ro.layout

import kotlinx.serialization.Serializable

@Serializable
data class Row(val cols: List<Cols> = emptyList(),
               val metadataError: String? = null,
               val cssClass: String? = null,
               val id: String? = null
)
//    private val maxSpan = 12