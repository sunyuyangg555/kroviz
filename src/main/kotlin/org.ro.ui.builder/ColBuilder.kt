package org.ro.ui.builder

import org.ro.layout.ColLayout
import org.ro.to.TObject
import org.ro.ui.kv.RoDisplay
import pl.treksoft.kvision.core.Component
import pl.treksoft.kvision.panel.HPanel

class ColBuilder() {

    fun create(colLayout: ColLayout, tObject: TObject, dsp: RoDisplay): HPanel {
        val result = HPanel()
        var cpt: Component?
        for (tgl in colLayout.tabGroup) {
            cpt = TabGroupBuilder().create(tgl, tObject, dsp)
            result.add(cpt)
        }
        for (fsl in colLayout.fieldSet) {
            cpt = FieldSetBuilder().create(fsl, tObject, dsp)
            result.add(cpt!!)
        }
        return result
    }
}