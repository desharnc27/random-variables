Some classes to implement random laws (probability).

This project is not completed and lacks comments, but has some sparse uses for now.

The initial goal of the project is the following: if you're a student in a first probability math course and you have java knowledge, then you can use this project to implement your random variables and see if your understanding of random variables is ok. Very instructive way to validate your answers!

Using this code, you can implement your own random distribution by extending RandomLaw or NNIRandomLaw. Three (at least, could be more) instance-functions must be overridden: randomExec(), which generate a random value from this distribution, and others (ex:getMean(), getVar()), which computes analytics metrics for the distribution. The usefulness of implementing it is that RandomLaw contains non-overridable functions which can automatically generate a sample of the distribution and verify the likelihood of the sample according to analytic values (getMean(), getVar()..etc) that the you implemented. The goal of all this is to verify your calculations of mean, variance, etc if you are working on a probability problem. 

Even if these non-overridable functions above have practical uses to verify hand calculations, in theory, the metrics used for evaluating the former likelihood are still to be more rigorously defined and implemented.

All described above tools work for only for a single distribution. There is a package, multivar, that was started to manage joint distributions of many dependent variables, but it's a barely started sub-project up to now, therefore that package is unusable at the moment. (lack of time).


To summarize what you CAN do now:
	- defining your own random variables (by extending existent classes)
	- Run comparison tests to see if your code has implementation/calculations error.
	
- Note: you should write your code in "yourcode" package only.
- Note: more details within the java files in "yourcode" package