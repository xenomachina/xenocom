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

class TextTest : FunSpec() {
    init {
        test("forEachLine, empty") {
            val lines = mutableListOf<String>()
            "".forEachLine { lines.add(it) }
            assertEquals(listOf<String>(), lines)
        }

        test("forEachLine, one line, no newline") {
            val lines = mutableListOf<String>()
            "foo".forEachLine { lines.add(it) }
            assertEquals(listOf("foo"), lines)
        }

        test("forEachLine, one line, with newline") {
            val lines = mutableListOf<String>()
            "foo\n".forEachLine { lines.add(it) }
            assertEquals(listOf("foo\n"), lines)
        }

        test("forEachLine, multi-line, no trailing newline") {
            val lines = mutableListOf<String>()
            "foo\nbar\nbaz".forEachLine { lines.add(it) }
            assertEquals(listOf("foo\n", "bar\n", "baz"), lines)
        }

        test("forEachLine, multi-line, trailing newline") {
            val lines = mutableListOf<String>()
            "foo\nbar\nbaz\n".forEachLine { lines.add(it) }
            assertEquals(listOf("foo\n", "bar\n", "baz\n"), lines)
        }
    }

    // TODO: test String.forEachCodePoint
    // TODO: test StringBuilder.clear
    // TODO: test String.padTo
    // TODO: test String.wrapText
    // TODO: test codePointWidth
    // TODO: test String.codePointWidth
    // TODO: test String.trimNewline
    // TODO: test columnize
}
