// Copyright © 2017 Laurence Gonsalves
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

import io.kotlintest.specs.FunSpec
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertNotNull

class CommonTest : FunSpec() {
    init {
        var holder : Holder<String?>?
        test("null") {
            holder = null
            assertNull(holder)
            assertNull(holder.orElse { null })
            assertEquals("fallback", holder.orElse { "fallback" })
        }

        test("Holder(null)") {
            holder = Holder(null)
            assertNotNull(holder)
            assertNull(holder.orElse { null })
            assertNull(holder.orElse { "fallback" })
        }

        test("Holder(nonNull)") {
            holder = Holder("value")
            assertNotNull(holder)
            assertEquals("value", holder.orElse { null })
            assertEquals("value", holder.orElse { "fallback" })
        }
    }
}
