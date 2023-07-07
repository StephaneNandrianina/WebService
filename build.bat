setlocal
cd Framework/classes
javac -d . *.java
jar -cf Framework.jar ./
move Framework.jar ../../test_Framework/WEB-INF/lib
cd ../../
jar -cvf testFramework.war test_Framework 
move testFramework.war ../  
pause 

