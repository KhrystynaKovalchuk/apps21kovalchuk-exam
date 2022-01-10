package json;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {

    private ArrayList<JsonPair> pairs = new ArrayList<JsonPair>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair : jsonPairs) {
            pairs.add(pair);
        }
    }

    @Override
    public String toJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        for (int i = 0; i < this.pairs.size(); i++) {
            if (i == this.pairs.size() - 1) {
                json.append("'" + this.pairs.get(i).key + "': ");
                json.append(this.pairs.get(i).value.toJson());
            } else {
                json.append("'" + this.pairs.get(i).key + "': ");
                json.append(this.pairs.get(i).value.toJson() + ", ");
            }

        }

        json.append("}");

        return json.toString();
    }

    public void add(JsonPair jsonPair) {
        int added = 0;
        for (int i = 0; i < this.pairs.size(); i++) {
            if (this.pairs.get(i).key.equals(jsonPair.key)) {
                this.pairs.set(i, jsonPair);
                added = 1;
            }
        }

        if (added != 1) {
             this.pairs.add(jsonPair);
        }
    }


    public Json find(String name) {
        for (int i = 0; i < this.pairs.size(); i++) {
            if (this.pairs.get(i).key.equals(name)) {
                return this.pairs.get(i).value;
            }
        }

        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject projectionJsonObject = new JsonObject();
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < this.pairs.size(); j++) {
                if (names[i].equals(this.pairs.get(j).key)) {
                    projectionJsonObject.add(this.pairs.get(j));
                }
            }
        }

        return projectionJsonObject;
    }
}
