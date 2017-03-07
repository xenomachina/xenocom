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

class String_WrapTextTest : FunSpec() {
    init {
        test("empty") {
            assertEquals("", "".wrapText(10))
        }

        test("simple") {
            assertEquals("hello", "hello".wrapText(10))
        }

        test("multi-spaces are smushed") {
            assertEquals("foo bar", "foo  bar".wrapText(10))
        }

        test("leading-spaces are removed") {
            assertEquals("foo bar", " foo bar".wrapText(10))
            assertEquals("foo bar", "  foo bar".wrapText(10))
        }

        test("trailing-spaces are removed") {
            assertEquals("foo bar", "foo bar ".wrapText(10))
            assertEquals("foo bar", "foo bar  ".wrapText(10))
        }

        test("words are wrapped") {
            assertEquals(
                    "foo bar\nbaz quux\nzarf",
                    "foo bar baz quux zarf".wrapText(10))
        }

        test("words are wrapped at exactly the wrap margin") {
            assertEquals(
                    "foo bar ba\nz quux\nzarf",
                    "foo bar ba z quux zarf".wrapText(10))
        }

        test("extra spaces don't push past wrap margin") {
            assertEquals(
                    "12 45 78 0\naa bb cc\ndd",
                    "12  45  78  0  aa  bb  cc  dd".wrapText(10))
        }

        test("double-width chars take up double space") {
            // if we treated 你好 as having a width of 2 then 'hi' would end up on first line.
            assertEquals(
                    "你好 is\n'hi' in\nChinese",
                    "你好 is 'hi' in Chinese".wrapText(10))
        }
    }
}

class CodePointWidthTest : FunSpec() {
    init {
        test("nulchar == 0") {
            assertEquals(0, codePointWidth(0))
        }

        test("control characters == -1") {
            for (i in 1..(' '.toInt() - 1)) {
                assertEquals("[$i]", -1, codePointWidth(i))
            }
        }

        test("assorted single-width characters") {
            assertEquals(1, codePointWidth(' '.toInt()))
            assertEquals(1, codePointWidth('M'.toInt()))
            assertEquals(1, codePointWidth('π'.toInt()))
            assertEquals(1, codePointWidth('ʖ'.toInt()))
        }

        test("assorted double-width characters") {
            assertEquals(2, codePointWidth('好'.toInt()))
            assertEquals(2, codePointWidth('Ｍ'.toInt()))
        }

        test("assorted non-BMP characters and Emoji") {
            assertEquals(1, codePointWidth(0x1f01c)) // Mahjong tile four of circles

            // TODO: Should some Emoji return 2?
            assertEquals(1, codePointWidth(0x1f601)) // Emoticons
            assertEquals(1, codePointWidth(0x2702)) // Dingbats
            assertEquals(1, codePointWidth(0x1f680)) // Transport & Map
            assertEquals(1, codePointWidth(0x24c2)) // Enclosed
            assertEquals(1, codePointWidth(0x1f170)) // Enclosed
        }

        test("string containing a few printing characters") {
            assertEquals(5, "hello".codePointWidth()) // Mahjong tile four of circles
            assertEquals(12, "hello = 你好".codePointWidth()) // Mahjong tile four of circles
            // TODO: decide how control characters should be handled
        }
    }
}

class TrimNewlineTest : FunSpec() {
    init {
        test("empty") {
            assertEquals("", "".trimNewline())
        }

        test("just newline") {
            assertEquals("", "\n".trimNewline())
        }

        test("no newline") {
            assertEquals("hello world", "hello world".trimNewline())
        }

        test("text + newline") {
            assertEquals("goodbye earth", "goodbye earth\n".trimNewline())
        }

        test("only last newline is removed") {
            assertEquals("what's up moon\n\n", "what's up moon\n\n\n".trimNewline())
        }

        test("only newline at end is removed") {
            assertEquals("so long\nsaturn", "so long\nsaturn".trimNewline())
        }
    }
}

// TODO: test columnize
