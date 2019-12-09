package org.ro.core

import org.ro.to.Argument
import org.ro.to.Link
import org.ro.to.Method
import kotlin.test.Test
import kotlin.test.assertEquals

// UnitTest
class UtilsTest {

    // @Test
    fun test_argumentsAsBody() {
        //given
        val href = "http://localhost:8080/restful/services/isisApplib.FixtureScriptsDefault/actions/runFixtureScript/invoke"
        val rel = "urn:org.restfulobjects:rels/invoke;action='runFixtureScript'"
        val type = "application/json;profile='urn:org.restfulobjects:repr-types/object-action'"
        val link = Link(method = Method.POST.operation, href = href, rel = rel, type = type)
        val arguments = LinkedHashMap<String, String>() //
 //       link.setArgument(arguments)

        // when
        val body = Utils.argumentsAsBody(link)

        // then
        console.log("[UtilsTest.test_argumentsAsBody]")
        console.log(body)
    }

    @Test
    fun testScriptAsBody() {
        //given
        val expected = """"script": {"value": {"href": "http://localhost:8080"}}"""
        val value = "http://localhost:8080"
        val arg = Argument("script", value)
        // when
        val actual = Utils.asBody(arg)
        // then
        assertEquals(expected, actual)
    }

    @Test
    fun testParametersAsBody() {
        //given
        val expected = """"parameters": {"value": ""}"""
        val value = ""
        val arg = Argument("parameters", value)
        // when
        val actual = Utils.asBody(arg)
        // then
        assertEquals(expected, actual)
    }

}