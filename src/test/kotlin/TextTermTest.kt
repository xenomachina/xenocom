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

package com.xenomachina.text.term

import io.kotlintest.specs.FunSpec
import org.junit.Assert.assertEquals

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

class ColumnizeTest : FunSpec() {
    init {
        test("first column shorter, no minWidths") {
            assertEquals("foobar\n   baz", columnize("foo", "bar\nbaz"))
        }

        test("second column shorter, no minWidths") {
            assertEquals("barfoo\nbaz", columnize("bar\nbaz", "foo"))
        }

        test("first column shorter, with large minWidths") {
            assertEquals("foo  bar\n     baz", columnize("foo", "bar\nbaz", minWidths = intArrayOf(5, 10)))
        }

        test("second column shorter, with large minWidths") {
            assertEquals("bar  foo\nbaz", columnize("bar\nbaz", "foo", minWidths = intArrayOf(5, 10)))
        }

        test("first column shorter, with small minWidths") {
            assertEquals("foobar\n   baz", columnize("foo", "bar\nbaz", minWidths = intArrayOf(2, 2)))
        }

        test("second column shorter, with small minWidths") {
            assertEquals("barfoo\nbaz", columnize("bar\nbaz", "foo", minWidths = intArrayOf(2, 2)))
        }
    }
}
