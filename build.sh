#!/bin/bash
mvn -Dmaven.test.skip=true package
mvn source:jar
mvn deploy
