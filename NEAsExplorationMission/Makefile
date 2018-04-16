CLASSPATH = lib/mariadb-java-client-2.2.2.jar:lib/commons-csv-1.5.jar:lib/annotations-java8.jar:lib/jooq-3.10.5.jar
OUT = out/production/NEAsExplorationMission
SRC = $(wildcard src/com/exploration/jooq/*.java src/com/exploration/jooq/tables/*.java src/com/exploration/jooq/tables/records/*.java src/com/exploration/*.java)

all: build

build: $(SRC)
	mkdir -p $(OUT)
	javac -classpath $(CLASSPATH) -d $(OUT) $(SRC)

run: $(OUT)
	java -classpath $(OUT):$(CLASSPATH) com.exploration.Main

clean:
	rm -rf out
