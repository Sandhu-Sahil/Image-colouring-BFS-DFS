file ?= csc-med.png

run:
	javac *.java
	java A6Tester $(file)

clean:
	rm *.class