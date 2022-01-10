package domain;

import json.*;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    private ArrayList<Tuple<String, Integer>> exams = new ArrayList<Tuple<String, Integer>>();


    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);

        for (Tuple<String, Integer> exam : exams) {
            this.exams.add(exam);
        }

    }


    @Override
    public JsonObject toJsonObject() {
        ArrayList<JsonObject> examsJsonArrayList = new ArrayList<JsonObject>();

        for (int i = 0; i < exams.size(); i++) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add(new JsonPair("course", new JsonString(exams.get(i).key)));
            jsonObject.add(new JsonPair("mark", new JsonNumber(exams.get(i).value)));
            if (exams.get(i).value > 2) {
                jsonObject.add(new JsonPair("passed", new JsonBoolean(true)));
            } else {
                jsonObject.add(new JsonPair("passed", new JsonBoolean(false)));
            }

            examsJsonArrayList.add(jsonObject);
        }

        Json[] examsJsonArray = examsJsonArrayList.toArray(new Json[0]);

        JsonObject jsonObject = new JsonObject(
                new JsonPair("name", new JsonString(this.name)),
                new JsonPair("surname", new JsonString(this.surname)),
                new JsonPair("year", new JsonNumber(this.year)),
                new JsonPair("exams", new JsonArray(examsJsonArray))
                );

        return jsonObject;
    }
}