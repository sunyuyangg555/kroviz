package org.ro.ui

import org.ro.core.Utils

object IconManager {
    val DEFAULT_ICON = "fa-cube"

    /* Recreate from configuration value*/
    val word2Icon = mapOf<String, String>(
            "Create" to "fa-plus",
            "Edit" to "fa-pencil",
            "Delete" to "fa-trash",
            "Find" to "fa-search",
            "List" to "fa-list",
            "Download" to "fa-download",
            "Open" to "fa-book",
            "Run" to "fa-rocket",
            "Simple" to "fa-cubes",
            "Configuration" to "fa-wrench",
            "Manager" to "fa-manager",
            "Switch" to "fa-power-off",
            "Hsql" to "fa-database",
            "Prototyping" to "fa-object-group",
            "Objects" to "fa-cubes",
            "Log" to "fa-history",
            "Connect" to "fa-plug",
            "Close" to "fa-times")

    fun find(query: String): String {
        val actionTitle = Utils.deCamel(query)
        val mixedCaseList = actionTitle.split(" ")
        for (w in mixedCaseList) {
            val hit = word2Icon.get(w)
            if (hit != null) {
                return hit
            }
        }
        return DEFAULT_ICON
    }
}