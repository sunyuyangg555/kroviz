package org.ro.view

import org.ro.core.Utils
import org.ro.to.Action
import org.ro.to.Link
import org.ro.to.Parameter
import pl.treksoft.kvision.core.StringPair
import pl.treksoft.kvision.form.select.Select
import pl.treksoft.kvision.form.text.TextArea

class ActionPrompt(val action: Action) : Command {

    lateinit var form: RoDialog

    fun open() {
        console.log("[ActionPrompt.open] ${action}")
        val formItems = buildFormItems()
        form = RoDialog(
                label = buildLabel(),
                items = formItems,
                command = this)
        form.show()
    }

    override fun execute() {
        console.log("[ActionPrompt.execute]")
        val l = extractUserInput()
        l.invoke()
    }

    private fun buildLabel(): String {
        val label = Utils().deCamel(action.id);
        return "Execute: $label"
    }

    private fun buildFormItems(): List<FormItem> {
        val formItems = mutableListOf<FormItem>()
        val parameterList: Collection<Parameter> = action.parameters.values
        for (p in parameterList) {
            val v = p.name
            var type = "TextArea"
            var content: Any = ""
            if (p.hasChoices()) {
                type = "Select"
                content = buildSelectionList(p)
            }
            val fi = FormItem(v, type, content);
            formItems.add(fi)
        }
        return formItems
    }

    private fun buildSelectionList(parameter: Parameter): List<StringPair> {
        val selectionList = mutableListOf<StringPair>()
        val arguments = parameter.getChoiceListKeys()
        for (s in arguments) {
            val sp = StringPair(s, s);
            selectionList.add(sp)
        }
        return selectionList
    }

    fun extractUserInput(): Link {
        //TODO function has a sideeffect, ie. amends link with arguments 
        val link = action.getInvokeLink()!!
        var value: String? = null
        var key: String? = null
        val formPanel = form.panel
        val kids = formPanel!!.getChildren()
        //iterate over FormItems (0,1) but not Buttons(2,3)
        for (i in kids) {
            when (i) {
                is TextArea -> {
                    key = i.id
                    value = i.getValue()
                }
                is Select -> {
                    key = i.label!!
                    value = i.getValue()!!
//                    val p: Parameter = action.findParameterByName(key.toLowerCase())!!
//                    val href = p.getHrefByTitle(value)!!
                }
            }
            if (key != null)
                link.setArgument(key, value)
        }
        return link
    }

}