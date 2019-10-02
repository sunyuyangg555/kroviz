package org.ro.ui

import org.ro.ui.uicomp.FormItem
import pl.treksoft.kvision.core.StringPair
import pl.treksoft.kvision.form.FormPanel
import pl.treksoft.kvision.form.FormPanel.Companion.formPanel
import pl.treksoft.kvision.form.select.Select
import pl.treksoft.kvision.form.text.Password
import pl.treksoft.kvision.form.text.Text
import pl.treksoft.kvision.form.text.TextArea
import pl.treksoft.kvision.html.Button
import pl.treksoft.kvision.html.ButtonStyle
import pl.treksoft.kvision.modal.Modal
import pl.treksoft.kvision.modal.ModalSize
import pl.treksoft.kvision.utils.ENTER_KEY

class RoDialog(
        val label: String,
        val items: List<FormItem>,
        val command: Command) :
        Modal(caption = label,
                closeButton = true,
                animation = true,
                size = ModalSize.LARGE
        ) { //TODO  #18 in order to have it draggable&resizeable use Window

    var panel: FormPanel<String>? = null
    private val loginButton = Button("OK", "fa-check", ButtonStyle.SUCCESS).onClick {
        execute()
    }
    private val cancelButton = Button("Cancel", "fa-times", ButtonStyle.INFO).onClick {
        close()
    }

    init {
        panel = formPanel {
            for (fi: FormItem in items) {
                when (fi.type) {
                    "Text" -> {
                        add(Text(label = fi.label, value = fi.content as String))
                    }
                    "Password" -> {
                        add(Password(label = fi.label, value = fi.content as String))
                    }
                    "TextArea" -> {
                        var rowCnt = 3
                        if (fi.size > rowCnt) rowCnt = fi.size
                        add(TextArea(label = fi.label, value = fi.content as String, rows = rowCnt))
                    }
                    "Select" -> {
                        @Suppress("UNCHECKED_CAST")
                        val list = fi.content as List<StringPair>
                        val preSelectedValue = list.first().first
                        add(Select(label = fi.label, options = list, value = preSelectedValue))
                    }
                }
            }
            setEventListener {
                keydown = {
                    if (it.keyCode == ENTER_KEY) {
                        execute()
                    }
                }
            }
        }
        addButton(cancelButton)
        addButton(loginButton)
    }

    private fun execute() {
        command.execute()
        close()
    }

    private fun close() {
        toggle()
        super.remove(this)
        dispose()
    }

}