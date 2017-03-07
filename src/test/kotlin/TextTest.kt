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

class StringBuilderClearTest : FunSpec() {
    init {
        test("empty") {
            val sb = StringBuilder()
            sb.clear()
            assertEquals("", sb.toString())
        }

        test("non-empty") {
            val sb = StringBuilder()
            sb.append("hello")
            sb.clear()
            assertEquals("", sb.toString())
        }
    }
}

class StringPadToTest : FunSpec() {
    init {
        test("empty") {
            assertEquals("          ", "".padTo(10))
        }

        test("one small line") {
            assertEquals("abc       ", "abc".padTo(10))
        }

        test("one large line") {
            assertEquals("0123456789abcde", "0123456789abcde".padTo(10))
        }

        test("one small line with newline") {
            assertEquals("abc       \n", "abc\n".padTo(10))
        }

        test("one large line with newline") {
            assertEquals("0123456789abcde\n", "0123456789abcde\n".padTo(10))
        }

        test("multiline") {
            assertEquals(
                    "abc       \nde fg     \n0123456789xyz\nfoo       \n",
                    "abc\nde fg\n0123456789xyz\nfoo\n".padTo(10))
        }

        test("multiline without trailing newline") {
            assertEquals(
                    "abc       \nde fg     \n0123456789xyz\nfoo       ",
                    "abc\nde fg\n0123456789xyz\nfoo".padTo(10))
        }
    }
}

// TODO: test String.wrapText
// TODO: test codePointWidth
// TODO: test String.codePointWidth
// TODO: test String.trimNewline
// TODO: test columnize
