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

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec

class StringPadToTest : FunSpec() {
    init {
        test("empty") {
            "".padLinesToWidth(10) shouldBe "          "
        }

        test("one small line") {
            "abc".padLinesToWidth(10) shouldBe "abc       "
        }

        test("one large line") {
            "0123456789abcde".padLinesToWidth(10) shouldBe "0123456789abcde"
        }

        test("one small line with newline") {
            "abc\n".padLinesToWidth(10) shouldBe "abc       \n"
        }

        test("one large line with newline") {
            "0123456789abcde\n".padLinesToWidth(10) shouldBe "0123456789abcde\n"
        }

        test("multiline") {
            "abc\nde fg\n0123456789xyz\nfoo\n".padLinesToWidth(10) shouldBe
                    "abc       \nde fg     \n0123456789xyz\nfoo       \n"
        }

        test("multiline without trailing newline") {
            "abc\nde fg\n0123456789xyz\nfoo".padLinesToWidth(10) shouldBe
                    "abc       \nde fg     \n0123456789xyz\nfoo       "
        }
    }
}

class String_WrapTextTest : FunSpec() {
    init {
        test("empty") {
            "".wrapText(10) shouldBe ""
        }

        test("simple") {
            "hello".wrapText(10) shouldBe "hello"
        }

        test("multi-spaces are smushed") {
            "foo  bar".wrapText(10) shouldBe "foo bar"
        }

        test("leading-spaces are removed") {
            " foo bar".wrapText(10) shouldBe "foo bar"
            "  foo bar".wrapText(10) shouldBe "foo bar"
        }

        test("trailing-spaces are removed") {
            "foo bar ".wrapText(10) shouldBe "foo bar"
            "foo bar  ".wrapText(10) shouldBe "foo bar"
        }

        test("words are wrapped") {
            "foo bar baz quux zarf".wrapText(10) shouldBe
            "foo bar\nbaz quux\nzarf"
        }

        test("words are wrapped at exactly the wrap margin") {
            "foo bar ba z quux zarf".wrapText(10) shouldBe
            "foo bar ba\nz quux\nzarf"
        }

        test("extra spaces don't push past wrap margin") {
            "12  45  78  0  aa  bb  cc  dd".wrapText(10) shouldBe
            "12 45 78 0\naa bb cc\ndd"
        }

        test("double-width chars take up double space") {
            // if we treated 你好 as having a width of 2 then 'hi' would end up on first line.
            "你好 is 'hi' in Chinese".wrapText(10) shouldBe
            "你好 is\n'hi' in\nChinese"
        }
    }
}

class CodePointWidthTest : FunSpec() {
    init {
        test("nulchar == 0") {
            codePointWidth(0) shouldBe 0
        }

        test("control characters == -1") {
            for (i in 1..(' '.toInt() - 1)) {
                // TODO: is there a way to add messaging to shouldBe?
                codePointWidth(i) shouldBe -1
            }
        }

        test("assorted single-width characters") {
            codePointWidth(' '.toInt()) shouldBe 1
            codePointWidth('M'.toInt()) shouldBe 1
            codePointWidth('π'.toInt()) shouldBe 1
            codePointWidth('ʖ'.toInt()) shouldBe 1
        }

        test("assorted double-width characters") {
            codePointWidth('好'.toInt()) shouldBe 2
            codePointWidth('Ｍ'.toInt()) shouldBe 2
        }

        test("assorted non-BMP characters and Emoji") {
            codePointWidth(0x1f01c) shouldBe 1

            // TODO: Should some Emoji return 2?
            codePointWidth(0x1f601) shouldBe 1
            codePointWidth(0x2702) shouldBe 1
            codePointWidth(0x1f680) shouldBe 1
            codePointWidth(0x24c2) shouldBe 1
            codePointWidth(0x1f170) shouldBe 1
        }

        test("string containing a few printing characters") {
            "hello".codePointWidth() shouldBe 5
            "hello = 你好".codePointWidth() shouldBe 12
            // TODO: decide how control characters should be handled
        }
    }
}

class ColumnizeTest : FunSpec() {
    init {
        test("first column shorter, no minWidths") {
            columnize("foo", "bar\nbaz") shouldBe "foobar\n   baz"
        }

        test("second column shorter, no minWidths") {
            columnize("bar\nbaz", "foo") shouldBe "barfoo\nbaz"
        }

        test("first column shorter, with large minWidths") {
            columnize("foo", "bar\nbaz", minWidths = intArrayOf(5, 10)) shouldBe "foo  bar\n     baz"
        }

        test("second column shorter, with large minWidths") {
            columnize("bar\nbaz", "foo", minWidths = intArrayOf(5, 10)) shouldBe "bar  foo\nbaz"
        }

        test("first column shorter, with small minWidths") {
            columnize("foo", "bar\nbaz", minWidths = intArrayOf(2, 2)) shouldBe "foobar\n   baz"
        }

        test("second column shorter, with small minWidths") {
            columnize("bar\nbaz", "foo", minWidths = intArrayOf(2, 2)) shouldBe "barfoo\nbaz"
        }
    }
}
