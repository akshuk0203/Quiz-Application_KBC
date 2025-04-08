JAVAC = javac
CLASSPATH = /usr/share/java/mysql-connector-java-9.0.0.jar

SRC_DIRS = Model View Controller additional_classes
JAVA_FILES = $(foreach dir, $(SRC_DIRS), $(wildcard $(dir)/*.java)) Main.java
CLASS_DIR = bin

all:
	@mkdir -p $(CLASS_DIR)
	$(JAVAC) -cp $(CLASSPATH) -d $(CLASS_DIR) $(JAVA_FILES)

clean:
	@rm -rf $(CLASS_DIR)

run: all
	java -cp $(CLASS_DIR):$(CLASSPATH) Main

