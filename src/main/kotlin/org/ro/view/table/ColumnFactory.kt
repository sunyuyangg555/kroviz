package org.ro.view.table

import org.ro.core.model.DisplayList
import org.ro.core.model.Exposer
import pl.treksoft.kvision.html.Button
import pl.treksoft.kvision.html.ButtonStyle
import pl.treksoft.kvision.tabulator.Align
import pl.treksoft.kvision.tabulator.ColumnDefinition
import pl.treksoft.kvision.tabulator.Formatter
import pl.treksoft.kvision.utils.obj

/**
 * Create ColumnDefinitions for Tabulator tables
 */
class ColumnFactory {

    private val faFormatterParams = obj {
        allowEmpty = true;
        allowTruthy = true;
        tickElement = "<i class='fa fa-square-o'></i>";
        crossElement = "<i class='fa fa-check'></i>"
    }

    fun buildColumns(displayList: DisplayList, withCheckBox: Boolean = false): List<ColumnDefinition<dynamic>> {

        val columns = mutableListOf<ColumnDefinition<dynamic>>()
        if (withCheckBox) {
            val checkBox = buildCheckBox()
            columns.add(checkBox)
        }

        val model = displayList.getData() as List<dynamic>
        if (model[0].hasOwnProperty("iconName")) {
            val icon = buildLinkIcon()
            columns.add(icon)
        }

        //TODO use propertyLabels for sequence of columns?
        val properties = displayList.getMembers()
        for (m in properties) {
            val columnDefinition = ColumnDefinition<Exposer>(
                    title = m.key,
                    field = m.value)
            columns.add(columnDefinition)
        }
        return columns
    }

    private fun buildLinkIcon(): ColumnDefinition<Exposer> {
        val icon = ColumnDefinition<dynamic>(
                "",
                field = "iconName",
                width = "40",
                formatterComponentFunction = { _, _, data ->
                    Button(text = "", icon = data["iconName"], style = ButtonStyle.LINK).onClick {
                        console.log(data)
                    }
                })
        return icon
    }

    private fun buildCheckBox(): ColumnDefinition<Exposer> {
        return ColumnDefinition<Exposer>(
                title = "selected", //TODO add 'selected' attribute dynamically???
                field = "key",
                formatter = Formatter.TICKCROSS,
                formatterParams = faFormatterParams,
                align = Align.CENTER,
                width = "100",
                headerSort = false,
                cellClick = { evt, cell ->
                    evt.stopPropagation()
                    showDetails(cell)
                })
    }

    private fun showDetails(cell: pl.treksoft.kvision.tabulator.js.Tabulator.CellComponent) {
        val row = cell.getRow()
        val data = row.getData()
        console.log("[CF.showDetails] $data")
    }

}