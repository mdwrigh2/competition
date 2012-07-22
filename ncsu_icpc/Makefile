JAVAC = javac
SRC = $(CURDIR)/src/ncsu/icpc
BIN = $(CURDIR)/bin

all: prims graph_example

prims: $(SRC)/Prims.java $(SRC)/GraphExample.java
	$(JAVAC) -d $(BIN) -cp $(BIN) $(SRC)/GraphExample.java $(SRC)/Prims.java

graph_example: $(SRC)/GraphExample.java
	$(JAVAC) -d $(BIN) -cp $(BIN) $(SRC)/GraphExample.java
