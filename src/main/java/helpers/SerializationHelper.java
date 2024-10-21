package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SerializationHelper extends PropertiesManager {

    /**
     * Serializes DTO to JSON
     * @param DTO : DTO to be converted into JSON
     * @return : JSON (String)
     */
    public static String serializeDtoToJson(Object DTO) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json =  gson.toJson(DTO);
        log.trace("DTO converted to JSON: \n" + json);
        return json;
    }

    /**
     * De-serializes a Json to a DTO
     * @param json : Json to be de-serialized
     * @param classOfT : class of the DTO we want it to be de-serialized to
     * @param <T> : generic type of DTO class
     * @return : the DTO in which Json has been de-serialized to
     */
    public static <T> T deSerializeJsonToDto(String json, Class<T> classOfT) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, classOfT);
    }

}
