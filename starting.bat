@echo OFF
title mvn自动打包

:loop
cls
echo ----------------------------------------
echo 请选择你要进行的操作，然后按回车
echo ----------------------------------------
echo 1，打包BASE
echo 2，打包WMS
echo 3，打包TMS
echo 4，获取最新代码
echo.

set SPATH=C:\Users\superlwj\Desktop\脚本工厂\log

set /p action=请选择：
if %action%==1 goto dobase
if %action%==2 goto dowms
if %action%==3 goto dotms
if %action%==4 goto dopull
goto loop

:end
pause & exit

:dobase
echo ========== begin BASE模块 ...
cd /d C:\Users\superlwj\git\youhaodongxi-java-base
git checkout master
git pull
git clean -fd
rem mvn clean install eclipse:eclipse -Dmaven.test.skip=true  > %SPATH%\base.txt
mvn clean install -DskipTests > %SPATH%\base.txt
goto end


:dowms
echo ========== begin WMS模块 ...
cd /d C:\Users\superlwj\git\youhaodongxi-wms
git checkout master
git pull
git clean -fd
mvn clean install -DskipTests > %SPATH%\wms.txt
rem mvn clean install eclipse:eclipse -Dmaven.test.skip=true  > %SPATH%\wms.txt
goto end

:dotms
echo ========== begin TMS模块 ...
cd /d C:\Users\superlwj\git\youhaodongxi-tms
git checkout master
git pull
git clean -fd
mvn clean install -DskipTests > %SPATH%\tms.txt
rem mvn clean install eclipse:eclipse -Dmaven.test.skip=true  > %SPATH%\tms.txt
goto end

:dopull
echo Hello World
goto end