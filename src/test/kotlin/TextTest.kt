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

internal fun <T> Sequence<T>.shouldContain(vararg expected: T) {
    assertEquals(expected.toList(), toList())
}

class LineSequenceTest : FunSpec() {
    init {
        test("empty") {
            "".lineSequence().shouldContain()
        }

        test("one line, no newline") {
            "foo".lineSequence().shouldContain("foo")
        }

        test("one line, with newline") {
            "foo\n".lineSequence().shouldContain("foo\n")
        }

        test("multi-line, no trailing newline") {
            "foo\nbar\nbaz".lineSequence().shouldContain("foo\n", "bar\n", "baz")
        }

        test("multi-line, trailing newline") {
            "foo\nbar\nbaz\n".lineSequence().shouldContain("foo\n", "bar\n", "baz\n")
        }
        test("empty") {
            "".lineSequence().shouldContain()
        }

        test("one line, no newline") {
            "foo".lineSequence().shouldContain("foo")
        }

        test("one line, with newline") {
            "foo\n".lineSequence().shouldContain("foo\n")
        }

        test("multi-line, no trailing newline") {
            "foo\nbar\nbaz".lineSequence().shouldContain("foo\n", "bar\n", "baz")
        }

        test("multi-line, trailing newline") {
            "foo\nbar\nbaz\n".lineSequence().shouldContain("foo\n", "bar\n", "baz\n")
        }

        test("can iterate multiple times") {
            val lineSequence = "foo\nbar\nbaz\n".lineSequence()
            lineSequence.shouldContain("foo\n", "bar\n", "baz\n")
            lineSequence.shouldContain("foo\n", "bar\n", "baz\n")
        }
    }
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

// TODO: test StringBuilder.clear
// TODO: test String.padTo
// TODO: test String.wrapText
// TODO: test codePointWidth
// TODO: test String.codePointWidth
// TODO: test String.trimNewline
// TODO: test columnize
