// Copyright © 2016 Laurence Gonsalves
//
// This file is part of xenocom, a library which can be found at
// http://github.com/xenomachina/xenocom
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published by the
// Free Software Foundation; either version 2.1 of the License, or (at your
// option) any later version.
//
// This library is distributed in the hope that it will be useful, but WITHOUT
// ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
// FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
// for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this library; if not, see http://www.gnu.org/licenses/

package com.xenomachina.common

//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertTrue
//import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

class CommonTest {
    @JvmField @Rule
    val testName: TestName = TestName()

    inline fun <reified X : Throwable> shouldThrow(f: () -> Unit): X {
        val javaClass = X::class.java
        try {
            f()
        } catch (exception: Throwable) {
            if (javaClass.isInstance(exception)) return javaClass.cast(exception)
            throw exception
        }
        throw AssertionError("Expected ${javaClass.canonicalName} to be thrown")
    }

    @Test
    fun testSomething() {
        //shouldThrow<FooException> {
        //    // TODO
        //}.run {
        //    assertEquals("TODO", message)
        //}
    }
}
