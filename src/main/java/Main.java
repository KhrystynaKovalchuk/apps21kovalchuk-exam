import domain.Student;
import json.*;

public class Main {
    public static void main(String[] args) {
        JsonObject jsonObject = new JsonObject(
                new JsonPair("name", new JsonString("Andrii")),
                new JsonPair("surname", new JsonString("Rodionov")),
                new JsonPair("year", new JsonNumber(2)),
                new JsonPair("marks",
                        new JsonArray(
                                new JsonNumber(3), new JsonNumber(4), new JsonNumber(2)
                        )
                )
        );

        JsonObject jsonObjectProjection = jsonObject.projection("surname", "age", "marks", "year");

        Student student = new Student(
                "Andrii",
                "Rodionov",
                3,
                new Tuple<>("OOP", 3),
                new Tuple<>("English", 5),
                new Tuple<>("Math", 2)
        );

        student.toJsonObject();
    }
}
