FROM openjdk:17
COPY . /usr/src/todoTask
WORKDIR /usr/src/todoTask

RUN ./gradlew build -x test
CMD [ "./gradlew", "bootRun" ]

ENTRYPOINT ["java", "-jar", "./build/libs/todo-task-1.0.0-SNAPSHOT.jar"]

