#!/bin/bash

# Variables
BUILD_DIR="build/libs"
APP_NAME="spring-cds.jar" # Replace with your application's JAR file name
CLASSLIST_FILE="classlist.txt"
CDS_ARCHIVE="app-cds.jsa"

# Step 1: Build the application
./gradlew clean build

# Step 2: Check if the application JAR exists
if [ ! -f "$BUILD_DIR/$APP_NAME" ]; then
  echo "Error: Application JAR ($APP_NAME) not found in $BUILD_DIR!"
  exit 1
fi

# Move to the build directory
cd "$BUILD_DIR" || exit 1

# Step 3: Generate the class list
echo "Generating class list..."
java -Xlog:class+load:file=$CLASSLIST_FILE -jar $APP_NAME \
    --spring.profiles.active=prod \
    --spring.main.web-application-type=none \
    --exit-on-startup > /dev/null 2>&1

if [ $? -ne 0 ]; then
  echo "Error: Failed to generate class list."
  exit 1
fi
echo "Class list generated: $CLASSLIST_FILE"

# Step 4: Generate the CDS archive
echo "Creating CDS archive..."
java -Xshare:dump \
    -XX:SharedClassListFile=$CLASSLIST_FILE \
    -XX:SharedArchiveFile=$CDS_ARCHIVE \
    -cp $APP_NAME

if [ $? -ne 0 ]; then
  echo "Error: Failed to create CDS archive."
  exit 1
fi
echo "CDS archive created: $CDS_ARCHIVE"

# Step 5: Run the application with CDS
echo "Starting application with CDS..."
java -Xshare:on \
    -XX:SharedArchiveFile=$CDS_ARCHIVE \
    -jar $APP_NAME

if [ $? -ne 0 ]; then
  echo "Error: Failed to start the application."
  exit 1
fi

echo "Application started successfully with CDS!"
