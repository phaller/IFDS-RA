# IFDS-RA
Concurrent implementation of the IFDS [1] algorithm based on Reactive Async [2]

## Dependencies
- [Reactive Async](https://github.com/phaller/reactive-async)
- [Heros](https://github.com/Sable/heros)
- [FlowTwist](https://github.com/johanneslerch/FlowTwist)

## How to run experimental evaluation
```
> sbt
> run ifds-solver static-analysis-lib taint-analysis num-threads N path-to-jre
```

Argument | Options
--- | ---
__ifds-solver__ | "ra" or "heros"
__static-analysis-lib__ | "soot" (OPAL currently not supported)
__taint-analysis__ | "fw", "bidi", "gen", or "seq"
__num-threads__ | number of threads to use for the IFDS solver. The number of threads used by SOOT is not affected by this number.
__N__ | Number of iterations to run.
__path-to-jre__ | Path to JRE folder. The easiest way to get this path is to run the command `/usr/libexec/java_home` on Mac OS X and append _'/jre'_.

Taint analysis explanation:
- seq is the forwards IFDS algorithm on the Class.forName experimental setup in FlowTwist
- fw is the forwards IFDS algorithm on the Class.forName experimental setup in FlowTwist
- bidi is is the bidirectional IFDS algorithm on the Class.forName experimental setup in FlowTwist
- gen is the bidirectional IFDS algoirthm on the GenericCallerSensitive experimental setup in FlowTwist

Currently the supported taint analyses are:
- ra soot seq _(does not actually use RA, should probably be rewritten to "seq soot fw" which better reflects which analysis is run)_
- ra soot fw
- ra soot bidi
- ra soot gen
- heros soot fw
- heros soot bidi
- heros soot gen

These arguments and which analysis is run is controlled by `src/main/java/flow/twist/mains/Starter.java` which is the main class of the project.

### Output
The output of running the experimental evaluation is N files with the following names:


ifds-solver\_static-analysis-lib\_taint-analysis\_num-threads_n

where n is between 0 and N and contains the output of running the experimental evaluation for
iteration n. Since the taint analysis is run N times, N files are created.

For example, if `run ra soot fw 8 3 ...` is run, 3 files named ra\_soot\_fw\_8\_0, ra\_soot\_fw\_8\_1, ra\_soot\_fw\_8\_2 are created.

__To be written:__ how to extract running times in CSV format.

## How to run tests
Run all tests:
```
> sbt
> test
```
or, for each individual test suite:
```
> sbt
> test-only IFDS.SimpleTestNoOPAL
> test-only IFDS.SimpleTestRANoOPAL
> test-only IFDS.SimpleTestBiDiNoOPAL
> test-only IFDS.SimpleTestBiDiRANoOPAL
```
where
- SimpleTestNoOPAL is the sequential IFDS solver
- SimpleTestRANoOPAL is the RA IFDS solver
- SimpleTestBiDiNoOPAL is the sequential bidirectional IFDS solver
- SimpleTestBiDiRANoOPAL is the bidirectional RA IFDS solver

## File structure
The classes for each taint analysis can be found in `src/main/java/flow/twist/mains/*`

The main class is `src/main/java/flow/twist/mains/Starter.java`

The main analysis classes are `src/main/java/flow/twist/AbstractAnalysis.java` and `src/main/java/flow/twist/AbstractMainAnalysis.java`

The Heros IFDS solver can be found in `src/main/java/heros/solver/IDESolver.java`

The Reactive Async IFDS solver can be found in `src/main/scala/IFDS/RAIFDSSolver.scala`

### References
[1] http://dl.acm.org/citation.cfm?id=199462

[2] http://dl.acm.org/citation.cfm?id=2998396

