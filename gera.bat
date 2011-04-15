
@echo off
java antlr.Tool projeto.g
IF NOT ERRORLEVEL 1 javac *.java
IF NOT ERRORLEVEL 1 java Main
