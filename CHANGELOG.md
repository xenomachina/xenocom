# Change Log

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

## 0.0.6 - 2018-03-19

### Changed

- Releasing to Maven Central instead of Bintray. This is probably the only
  really externally visible change.
- Releases are now signed
- Gradle wrapper (gradlew) now updated to 4.5.1
- Dependencies updated:
    dokka = 0.9.16
    kotlintest = 2.0.7
    kotlin = 1.2.30
- Switched to using kotlinter to simplify build (also picks up a newer ktlint
  now)
- Cleaned up lint errors caught by newer version of ktlint
- Fixed formatting of CHANGELOG.md


## 0.0.5 - 2017-06-12

### Changed

- Don't use `SequenceBuilder`. It's buggy and causes NPEs in 1.1.2-4.
- Bump kotlin version to 1.1.2-5
- Fix format of this CHANGELOG.md


## 0.0.4 - 2017-04-04

### Fixed

- variance of Holder should be `<out T>`, not `<T>`


## 0.0.3 - 2017-03-15

### Changed

- Set githubReleaseNotesFile for bintray upload
- Update kotlin to 1.1.1, ktlint to 0.6.1
- Add missing newline to `src/main/kotlin/TextTerm.kt`
- Add `.travis.yml`


## 0.0.2 - 2017-03-07

### Changed
- Rename `padLinesToWidth`
- Use `maxOf`
- Make `NBSP_CODEPOINT` public

## 0.0.1 - 2017-03-07

### Added
- Initial release
