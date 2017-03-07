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

import kotlin.coroutines.experimental.buildSequence

/**
 * Produces a [Sequence] of the Unicode code points in the given [String].
 */
fun String.codePointSequence() : Sequence<Int> = buildSequence {
    val length = length
    var offset = 0
    while (offset < length) {
        val codePoint = codePointAt(offset)
        yield(codePoint)
        offset += Character.charCount(codePoint)
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

