package classes;

import classes.data.DriverView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LoginDAO {

    public static DriverView validate(String user, String password) {

        try {
            String responce = getResponce(user);

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonObject o = (JsonObject)parser.parse(responce);

            DriverView driverView =  gson.fromJson(o, DriverView.class);
            return driverView;

        } catch (Exception ignore) {
            return null;
        }

//        try {
//            Client client = Client.create();
//            WebResource resource = client.resource("http://localhost:8080/rest/driver/view?login="
//                    + user
//                    + "&password=" + password);
//
//            // Get response as String
//            String string = resource.path("1")
//                    .accept(MediaType.APPLICATION_XML)
//                    .get(String.class);
//            System.out.println(string);
//
//            // Get response as Customer
//            DriverView driverView = resource.path("1")
//                    .accept(MediaType.APPLICATION_XML)
//                    .get(DriverView.class);
//
//            System.out.println(driverView);
//
//            return  driverView;
//
//        } catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }

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
}