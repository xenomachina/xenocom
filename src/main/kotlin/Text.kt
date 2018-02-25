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

package com.xenomachina.text

const val NBSP_CODEPOINT = 0xa0
const val LINE_FEED_CODEPOINT = 0x0a

/**
 * Produces a [Sequence] of the Unicode code points in the given [String].
 */
fun String.codePointSequence(): Sequence<Int> = object : Sequence<Int> {
    private val length = this@codePointSequence.length

    override fun iterator() = object : Iterator<Int> {
        private var offset = 0

        override fun hasNext(): Boolean {
            return offset < length
        }

        override fun next(): Int {
            if (offset < length) {
                val codePoint = codePointAt(offset)
                offset += Character.charCount(codePoint)
                return codePoint
            } else {
                throw NoSuchElementException()
            }
        }
    }
}

fun StringBuilder.clear() {
    this.setLength(0)
}

fun String.trimNewline(): String {
    if (endsWith('\n')) {
        return substring(0, length - 1)
    } else {
        return this
    }
}
