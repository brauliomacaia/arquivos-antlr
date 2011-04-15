@echo off
java antlr.Tool expr2.g
IF NOT ERRORLEVEL 1 javac *.java