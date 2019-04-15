#!/usr/bin/env zsh

# for testing local builds
# not for production usage

./gradlew clean fatJar && java -jar ./build/libs/datascrapper-all-1.0-SNAPSHOT.jar