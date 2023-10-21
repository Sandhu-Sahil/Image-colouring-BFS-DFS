file ?= csc-med.png

run:
	javac *.java
	java A6Tester $(file)
	rm *.class

clean:
	rm *.class