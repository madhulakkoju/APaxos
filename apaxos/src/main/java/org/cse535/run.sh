




echo "Running the Java application with the following parameters: "
# Define the Java class and parameters
JAVA_CLASS="org.cse535.Main"  # Full class name
JAR_FILE="./target/apaxos-1.jar"    # Relative path to the JAR file

# Check if the JAR file exists
if [ ! -f "$JAR_FILE" ]; then
    echo "Error: JAR file '$JAR_FILE' not found!"
    exit 1
fi

echo "Running Java application '$JAVA_CLASS' with parameter"

# Execute the Java application
java -cp "$JAR_FILE" "$JAVA_CLASS" "s1"


read -r name

echo "$name"


# Capture the exit status
EXIT_STATUS=$?

# Exit with the same status as the Java application
exit $EXIT_STATUS
