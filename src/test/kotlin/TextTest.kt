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

package com.xenomachina.text

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec

// TODO does kotlintest have a matcher for this?
internal fun <T> Sequence<T>.shouldContain(vararg expected: T) {
    toList() shouldBe expected.toList()
}

class CodepointSequenceTest : FunSpec() {
    init {
        test("empty") {
            "".codePointSequence().shouldContain()
        }

        test("ASCII characters") {
            "foo".codePointSequence().shouldContain(0x66, 0x6f, 0x6f)
        }

        test("non-ASCII, BMP characters") {
            "你好吗".codePointSequence().shouldContain(0x4f60, 0x597d, 0x5417)
        }

        test("non-BMP characters") {
            "\ud83c\udc1c\ud83d\ude4f".codePointSequence().shouldContain(0x1f01c, 0x1f64f)
        }
    }
}

class StringBuilderClearTest : FunSpec() {
    init {
        test("empty") {
            val sb = StringBuilder()
            sb.clear()
            sb.toString() shouldBe ""
        }

        test("non-empty") {
            val sb = StringBuilder()
            sb.append("hello")
            sb.clear()
            sb.toString() shouldBe ""
        }
    }
}

class TrimNewlineTest : FunSpec() {
    init {
        test("empty") {
            "".trimNewline() shouldBe ""
        }

        test("just newline") {
            "\n".trimNewline() shouldBe ""
        }

        test("no newline") {
            "hello world".trimNewline() shouldBe "hello world"
        }

        test("text + newline") {
            "goodbye earth\n".trimNewline() shouldBe "goodbye earth"
        }

        test("only last newline is removed") {
            "what's up moon\n\n\n".trimNewline() shouldBe "what's up moon\n\n"
        }

        test("only newline at end is removed") {
            "so long\nsaturn".trimNewline() shouldBe "so long\nsaturn"
        }
    }
}
