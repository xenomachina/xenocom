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

import io.kotlintest.matchers.Matcher
import io.kotlintest.matchers.Result
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec

fun beNonNull(): Matcher<Any?> = object : Matcher<Any?> {
    override fun test(value: Any?) = Result(value !== null, "$value should be non-null")
}

class CommonTest : FunSpec() {
    init {
        var holder : Holder<String?>?
        test("null") {
            holder = null
            holder shouldBe null
            holder.orElse { null } shouldBe null
            holder.orElse { "fallback" } shouldBe "fallback"
        }

        test("Holder(null)") {
            holder = Holder(null)
            holder should beNonNull()
            holder.orElse { null } shouldBe null
            holder.orElse { "fallback" } shouldBe null
        }

        test("Holder(nonNull)") {
            holder = Holder("value")
            holder should beNonNull()
            holder.orElse { null } shouldBe "value"
            holder.orElse { "fallback" } shouldBe "value"
        }
    }
}
