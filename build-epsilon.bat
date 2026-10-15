@echo off
setlocal
cd /d "%~dp0"

rem Use the Java 21 SDK configured for this IntelliJ installation.
if defined JAVA_HOME if not exist "%JAVA_HOME%\bin\java.exe" set "JAVA_HOME="
if not defined JAVA_HOME if exist "%USERPROFILE%\.jdks\ms-21.0.12.1\bin\java.exe" set "JAVA_HOME=%USERPROFILE%\.jdks\ms-21.0.12.1"

if not defined JAVA_HOME (
    echo Java 21 was not found. Install or select JDK 21 in IntelliJ, then try again.
    pause
    exit /b 1
)

if not exist "%JAVA_HOME%\bin\java.exe" (
    echo JAVA_HOME does not point to a JDK: "%JAVA_HOME%"
    pause
    exit /b 1
)

set "PATH=%JAVA_HOME%\bin;%PATH%"
echo Building Epsilon Client with:
"%JAVA_HOME%\bin\java.exe" -version
call "%~dp0gradlew.bat" build
set "BUILD_EXIT=%ERRORLEVEL%"

if not "%BUILD_EXIT%"=="0" (
    echo.
    echo Build failed. See the Gradle error above.
    pause
    exit /b %BUILD_EXIT%
)

echo.
echo Build complete. The mod JAR is in:
echo %~dp0build\libs
pause
exit /b 0
