package civilization.controllers;

import civilization.App;
import civilization.enums.*;
import civilization.models.*;
import civilization.views.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;

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

    public static Boolean signUp(String username, String nickname, String password) {
        User user;
        if (Database.getInstance().getUserByUsername(username) != null) {
            Menu.showPopUp("user with username " + username + " already exists");
            return false;
        }
        if (Database.getInstance().getUserByNickname(nickname) != null) {
            Menu.showPopUp("user with nickname " + nickname + " already exists");
            return false;
        }
        Random random = new Random();
        int i = random.nextInt(Avatar.values().length);
        ImageView temp = new ImageView(new Image(App.class.getResource(Avatar.values()[i].getUrl()).toExternalForm()));
        Database.getInstance().addUser((user = new User(username, password, nickname, temp, null, 0,
                LocalDateTime.of(1900, 01, 01, 00, 00, 00), LocalDateTime.of(1900, 01, 01, 00, 00, 00))));
        Database.getInstance().setLoggedInUser(user);
//        try {
//            RegisterMenuController.saveUsers();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return true;
    }

    public static Boolean login(String username, String password) {
        User user;
        if ((user = Database.getInstance().getUserByUsername(username)) == null) {
            Menu.showPopUp("no account with this username exists");
            return false;
        }
        if (!user.getPassword().equals(password)) {
            Menu.showPopUp("incorrect password");
            return false;
        }
        Database.getInstance().setLoggedInUser(user);
        Database.getInstance().getLoggedInUser().setLastLoginTime(LocalDateTime.now());
//        try {
//            RegisterMenuController.saveUsers();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return true;
    }
}

class LocalDateSerializer implements JsonSerializer<LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-d-yyyy");

    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}

class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d uuuu, HH:mm:ss");

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
                DateTimeFormatter.ofPattern("MMM-d-yyyy").withLocale(Locale.ENGLISH));
    }
}

class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(),
                DateTimeFormatter.ofPattern("MMM d uuuu, HH:mm:ss").withLocale(Locale.ENGLISH));
    }
}
