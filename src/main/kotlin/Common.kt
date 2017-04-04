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

package com.xenomachina.common

/**
 * A `Holder<T>?` can be used where one needs to be able to distinguish between
 * having a T or not having a T, even when T is a nullable type.
 *
 * @property value the value being held
 */
data class Holder<out T>(val value: T)

/**
 * Dereferences the [Holder] if non-null, otherwise returns the result of calling [fallback].
 */
inline fun <T> Holder<T>?.orElse(fallback: () -> T): T =
    if (this == null) fallback() else value
