# xenocom -- Xenomachina's "Common" stuff for Kotlin

[![Maven Central](https://img.shields.io/maven-central/v/com.xenomachina/xenocom.svg)](https://mvnrepository.com/artifact/com.xenomachina/xenocom)
[![Bintray](https://img.shields.io/bintray/v/xenomachina/maven/xenocom.svg)](https://bintray.com/xenomachina/maven/xenocom/%5FlatestVersion)
[![Build Status](https://travis-ci.org/xenomachina/xenocom.svg?branch=master)](https://travis-ci.org/xenomachina/xenocom)
[![codebeat](https://codebeat.co/badges/f09bd6a3-52e2-40af-998d-a4f702a90e3e)](https://codebeat.co/projects/github-com-xenomachina-xenocom-master)
[![Javadocs](https://www.javadoc.io/badge/com.xenomachina/xenocom.svg)](https://www.javadoc.io/doc/com.xenomachina/xenocom)
[![License: LGPL 2.1](https://img.shields.io/badge/license-LGPL--2.1-blue.svg)](https://www.gnu.org/licenses/old-licenses/lgpl-2.1.en.html)

This is a Kotlin library containing things I've needed that are generally
useful. It is *not* an attempt to replace Google Guava or Apache Commons. For
now it is constructed lazily. That is, I only add things to it when I actually
need them.

Packages include:

- com.xenomachina.common — Things so general they don't belong anywhere else.
- com.xenomachina.text — String and general text handling.
- com.xenomachina.text.term — Text handling focused on formatting monospaced plaintext, like in a terminal.
