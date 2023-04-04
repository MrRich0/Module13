package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class HttpUtil {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static String URL = "https://jsonplaceholder.typicode.com/users";
    private static String URL_USER_NAME = "https://jsonplaceholder.typicode.com/users?username=";
    private static  String POSTS_URL = "https://jsonplaceholder.typicode.com/posts/";



    public User sendPost(User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(GSON.toJson(user)))
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        return GSON.fromJson(response.body(),User.class);
    }

    public User sendPut(String inputId,User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/" + inputId))
                .header("Content-Type","application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(GSON.toJson(user)))
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println("KOD " + response.statusCode());
        return GSON.fromJson(response.body(),User.class);
    }

    public void sendDelete(String inputId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/" + inputId))
                .DELETE()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println("KOD " + response.statusCode());
    }

    public void sendGetAll() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void sendGetById(String inputId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/" + inputId))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void sendGetByUserName(String inputUserName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_USER_NAME + inputUserName))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public void findByIdPostMaxComments(String inputId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/" + inputId + "/posts"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        Type type = TypeToken.
                getParameterized(List.class, Post.class).
                getType();
        List<Post> posts = GSON.fromJson(response.body(),type);
        int max = 0;
        for (int i = 0; i < posts.size(); i++) {
            if (max < posts.get(i).getId())
                max = posts.get(i).getId();
        }

        HttpRequest requestComments = HttpRequest.newBuilder()
                .uri(URI.create(POSTS_URL + max + "/comments"))
                .headers("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> responseComments = CLIENT.send(requestComments,HttpResponse.BodyHandlers.ofString());
        Type type1 = TypeToken.
                getParameterized(List.class, Comment.class).
                getType();
        List<Comment> comments = GSON.fromJson(responseComments.body(),type1);

        writeInJson(inputId, max, comments);
    }

    private static void writeInJson(String inputId, int max, List<Comment> comments) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(comments);
        OutputStream fos = new FileOutputStream("user-" + inputId + "-post-"+ max +"-comments.json");
        fos.write(json.getBytes());
    }

    public void sendTodo(String inputId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + "/" + inputId + "/todos?completed=false"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
