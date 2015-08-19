package classes;

import classes.data.DriverView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.*;

public class LoginDAO {
    static Client client = Client.create();
    public static DriverView validate(String user, String password) {

        try {
            String responce = getResponceJSON(user, password);

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject o = (JsonObject)parser.parse(responce);

            return gson.fromJson(o, DriverView.class);

        } catch (Exception ignore) {
            return null;
        }

    }


    private static String getResponce(String login) {
        switch (login) {
            case "1" :{
                return "{\"driver\":{\"id\":1,\"name\":\"Vasily\",\"lastName\":\"Petrov\",\"hoursWorked\":120,\"status\":\"DRIVING\",\"city\":{\"id\":1,\"name\":\"Moscow\"},\"orderRoute\":1},\"friends\":[{\"id\":1,\"name\":\"Vasily\",\"lastName\":\"Petrov\",\"hoursWorked\":120,\"status\":\"DRIVING\",\"city\":{\"id\":1,\"name\":\"Moscow\"},\"orderRoute\":1},{\"id\":2,\"name\":\"Dmitriy\",\"lastName\":\"Zabrodin\",\"hoursWorked\":128,\"status\":\"WORK\",\"city\":{\"id\":1,\"name\":\"Moscow\"},\"orderRoute\":1}],\"route\":[{\"orderId\":1,\"city\":{\"id\":1,\"name\":\"Moscow\"},\"baggageStatus\":\"LOADING\",\"baggage\":{\"id\":1,\"name\":\"banana\",\"weight\":1200,\"status\":\"PRODUCED\"},\"isDone\":\"NOT_DONE\",\"number\":1,\"truck\":{\"id\":\"AB12345\",\"dutySize\":1,\"capacity\":4,\"status\":\"OK\",\"city\":{\"id\":1,\"name\":\"Moscow\"}}},{\"orderId\":1,\"city\":{\"id\":2,\"name\":\"Saint-Petersburg\"},\"baggageStatus\":\"UNLOADING\",\"baggage\":{\"id\":1,\"name\":\"banana\",\"weight\":1200,\"status\":\"PRODUCED\"},\"isDone\":\"NOT_DONE\",\"number\":2,\"truck\":{\"id\":\"AB12345\",\"dutySize\":1,\"capacity\":4,\"status\":\"OK\",\"city\":{\"id\":1,\"name\":\"Moscow\"}}}],\"truckId\":\"AB12345\",\"orderId\":1}";
            }
            case "2" :{
                return "{\"driver\":{\"id\":2,\"name\":\"Dmitriy\",\"lastName\":\"Zabrodin\",\"hoursWorked\":128,\"status\":\"WORK\",\"city\":{\"id\":1,\"name\":\"Moscow\"},\"orderRoute\":1},\"friends\":[{\"id\":2,\"name\":\"Dmitriy\",\"lastName\":\"Zabrodin\",\"hoursWorked\":128,\"status\":\"WORK\",\"city\":{\"id\":1,\"name\":\"Moscow\"},\"orderRoute\":1},{\"id\":1,\"name\":\"Vasily\",\"lastName\":\"Petrov\",\"hoursWorked\":120,\"status\":\"DRIVING\",\"city\":{\"id\":1,\"name\":\"Moscow\"},\"orderRoute\":1}],\"route\":[{\"orderId\":1,\"city\":{\"id\":1,\"name\":\"Moscow\"},\"baggageStatus\":\"LOADING\",\"baggage\":{\"id\":1,\"name\":\"banana\",\"weight\":1200,\"status\":\"PRODUCED\"},\"isDone\":\"NOT_DONE\",\"number\":1,\"truck\":{\"id\":\"AB12345\",\"dutySize\":1,\"capacity\":4,\"status\":\"OK\",\"city\":{\"id\":1,\"name\":\"Moscow\"}}},{\"orderId\":1,\"city\":{\"id\":2,\"name\":\"Saint-Petersburg\"},\"baggageStatus\":\"UNLOADING\",\"baggage\":{\"id\":1,\"name\":\"banana\",\"weight\":1200,\"status\":\"PRODUCED\"},\"isDone\":\"NOT_DONE\",\"number\":2,\"truck\":{\"id\":\"AB12345\",\"dutySize\":1,\"capacity\":4,\"status\":\"OK\",\"city\":{\"id\":1,\"name\":\"Moscow\"}}}],\"truckId\":\"AB12345\",\"orderId\":1}";
            }
            case "3" :{
                return "{\"driver\":{\"id\":3,\"name\":\"Ivan\",\"lastName\":\"Bunin\",\"hoursWorked\":121,\"status\":\"REST\",\"city\":{\"id\":1,\"name\":\"Moscow\"}}}";
            }
            default:
                return null;
        }
    }

    private static String getResponceJSON(String user, String password) {

        String url = "http://localhost:8080/webservice/rest/driver/view?login="
                    + user
                    + "&password=" + password;

        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if(response.getStatus()!=200){
//            throw new RuntimeException("HTTP Error: "+ response.getStatus());
            return null;
        }
        String result = response.getEntity(String.class);
        System.out.println("Response from the Server: ");
        System.out.println(result);
        return result;
    }

    public static boolean changeDriverStatus(String id, String status){
        String url = "http://localhost:8080/webservice/rest/driver/status?" +
                "id=" + id
                + "&status=" + status;
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.get(ClientResponse.class);
        System.out.println(response);
        if(response.getStatus()!=200){
            return false;
        }
        return true;
    }
    public static boolean changeBaggageStatus(String id, String status){
        String url = "http://localhost:8080/webservice/rest/baggage/status?" +
                "id="+ id
                + "&status=" + status;
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.get(ClientResponse.class);
        System.out.println(response);
        if(response.getStatus()!=200){
            return false;
        }
        return true;
    }
}