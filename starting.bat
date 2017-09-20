@echo OFF
title mvn�Զ����

:loop
cls
echo ----------------------------------------
echo ��ѡ����Ҫ���еĲ�����Ȼ�󰴻س�
echo ----------------------------------------
echo 1�����BASE
echo 2�����WMS
echo 3�����TMS
echo 4����ȡ���´���
echo.

set SPATH=C:\Users\superlwj\Desktop\�ű�����\log

set /p action=��ѡ��
if %action%==1 goto dobase
if %action%==2 goto dowms
if %action%==3 goto dotms
if %action%==4 goto dopull
goto loop

:end
pause & exit

:dobase
echo ========== begin BASEģ�� ...
cd /d C:\Users\superlwj\git\youhaodongxi-java-base
git checkout master
git pull
git clean -fd
rem mvn clean install eclipse:eclipse -Dmaven.test.skip=true  > %SPATH%\base.txt
mvn clean install -DskipTests > %SPATH%\base.txt
goto end


:dowms
echo ========== begin WMSģ�� ...
cd /d C:\Users\superlwj\git\youhaodongxi-wms
git checkout master
git pull
git clean -fd
mvn clean install -DskipTests > %SPATH%\wms.txt
rem mvn clean install eclipse:eclipse -Dmaven.test.skip=true  > %SPATH%\wms.txt
goto end

:dotms
echo ========== begin TMSģ�� ...
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