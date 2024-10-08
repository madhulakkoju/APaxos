
JAR_FILE="target/apaxos-1.jar"

MAIN_CLASS="org.cse535.Main"

java -cp "$JAR_FILE" "$MAIN_CLASS" "s1"
java -cp "$JAR_FILE" "$MAIN_CLASS" "s2"
java -cp "$JAR_FILE" "$MAIN_CLASS" "s3"
java -cp "$JAR_FILE" "$MAIN_CLASS" "s4"
java -cp "$JAR_FILE" "$MAIN_CLASS" "s5"



VIEW_SERVER="org.cse535.node.ViewServer"

java -cp "$JAR_FILE" "$VIEW_SERVER"

read -r name


