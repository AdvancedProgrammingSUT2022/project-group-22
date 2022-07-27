package server.controllers;

import server.Main;
import server.enums.*;
import server.models.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class RegisterMenuController {
    private static Database database = Database.getInstance();
    private static RegisterMenuController instance = null;

    public static RegisterMenuController getInstance() {
        return instance == null ? new RegisterMenuController() : instance;
    }

    public static void loadUsers() throws FileNotFoundException, IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
        JsonReader reader = new JsonReader(new FileReader("src/main/resources/civilization/json/Users.json"));
        List<User> users = gson.fromJson(reader, new TypeToken<List<User>>() {
        }.getType());

        if (users != null) {
            for (User user : users) {
                database.addUser(user);
            }
        }

        reader.close();
    }
    public static User loadUser(String json) throws FileNotFoundException, IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
        //JsonReader reader = new JsonReader(new FileReader("src/main/resources/civilization/json/Users.json"));
        User user = gson.fromJson(json, new TypeToken<User>() {
        }.getType());
        return user;
    }

    public static void saveUsers() throws IOException {
        try (Writer writer = new FileWriter("src/main/resources/civilization/json/Users.json")) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
            Gson gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
            gson.toJson(database.getUsers(), writer);
            writer.close();
        }
    }

    public static Response signUp(String username, String nickname, String password) {
        Response response = new Response();
        User user;
        if (Database.getInstance().getUserByUsername(username) != null) {
            //Menu.showPopUp("user with username " + username + " already exists");
            response.setStatus(400);
            response.addData("message", "user with username " + username + " already exists");
            return response;
        }
        if (Database.getInstance().getUserByNickname(nickname) != null) {
            //Menu.showPopUp("user with nickname " + nickname + " already exists");
            response.setStatus(400);
            response.addData("message", "user with nickname " + nickname + " already exists");
            return response;
        }
        Random random = new Random();
        int i = random.nextInt(Avatar.values().length);
        Database.getInstance().addUser((user = new User(username, password, nickname, Avatar.values()[i].getUrl(), null, 0,
                LocalDateTime.of(1900, 01, 01, 00, 00, 00).format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) )));
        Database.getInstance().addLoggedInUser(user);
//        try {
//            RegisterMenuController.saveUsers();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        response.setStatus(200);
        response.addData("message","user created and logged in successfully");
        response.addData("jwt",JwtController.createJWT(user));
        return response;
    }

    public static Response login(String username, String password) {
        Response response = new Response();
        User user;
        if ((user = Database.getInstance().getUserByUsername(username)) == null) {
            //Menu.showPopUp("no account with this username exists");
            response.setStatus(400);
            response.addData("message","no account with this username exists");
            return response;
        }
        if (!user.getPassword().equals(password)) {
            //Menu.showPopUp("incorrect password");
            response.setStatus(400);
            response.addData("message","incorrect password");
            return response;
        }
        user.setLastLoginTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")));
        Database.getInstance().addLoggedInUser(user);
        response.setStatus(200);
        response.addData("message","logged in successfully");
        response.addData("jwt",JwtController.createJWT(user));
        return response;
//        try {
//            RegisterMenuController.saveUsers();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}

class LocalDateSerializer implements JsonSerializer<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss");

    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}

class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss");

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDateTime));
    }
}

class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss").withLocale(Locale.ENGLISH));
    }
}

class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss").withLocale(Locale.ENGLISH));
    }
}

