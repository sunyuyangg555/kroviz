# TODO's
## Implement Features
* List<Member> ^= TObject?
* direct Accessors ^= Methods  / indirect Accessors ^= Map.get(byName), sufficient for use in tables 
* Form Layout

## Fix Bugs   
* Sequence of menu entries is random. Use layout.xml
* LogEntries search/filter always shows first entry 
* On startup Configuration is added as separate menu entry
* LogEntries sometimes have negative offset

## Request kvision Features
-[x] Tooltips (#28)
-[ ] table, filterable by column
-[ ] (Google)Maps Integration
-[ ] Tree/TreeTable
-[ ] (Month/Week/Year)Calender with Icons
-[ ] D3.js 
    * https://github.com/unosviluppatore/kotlin-js-D3js-example
    * https://github.com/hnakamur/d3.js-class-diagram-example)

## Next 
* Integrate Calendar View (for ToDo App)
* Fullblown GanttChart (incl. birds eye view, cf. http://bl.ocks.org/bunkat/2338034 (d3.js))
* Display SVG's (cf. https://kotlinlang.org/api/latest/jvm/stdlib/org.w3c.dom.svg/index.html)
* Use SQL lite as store for url/credentials, customized layouts
* Visualize DomainModel (reconstruct from DomainObjects, Attributes, etc.)
* Hook up to the internal event bus of the server to have change notifications pushed. 
Callback server before display/edit. 

# Readings
http://petersommerhoff.com/dev/kotlin/kotlin-for-java-devs/

#Toolchain
intellij ultimate 2018
JetBrains IDE Support (plugin for chrome)