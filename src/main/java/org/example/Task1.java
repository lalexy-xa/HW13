package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

public class Task1 {
    private static HttpClient client = HttpClient.newHttpClient();
    private static String url = "https://jsonplaceholder.typicode.com/users";
    public static void createObject() throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("src/main/java/org/example/file.json")))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }

    public static void updateObject(int objId) throws IOException, InterruptedException{
        HttpRequest requestUpdate = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url + "/%d", objId)))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get("src/main/java/org/example/file.json")))
                .build();

        HttpResponse<String> responseUpdate =
                client.send(requestUpdate, HttpResponse.BodyHandlers.ofString());

        System.out.println(responseUpdate.body());
    }

    public static void deleteUser(int objId) throws IOException, InterruptedException{
        HttpRequest requestDelete = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url + "/%d", objId)))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> responseDelete =
                client.send(requestDelete, HttpResponse.BodyHandlers.ofString());

        System.out.println(responseDelete.statusCode());
    }

    public static void getUsers() throws IOException, InterruptedException{
        HttpRequest requestGet = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> responseGET =
                client.send(requestGet, HttpResponse.BodyHandlers.ofString());

        System.out.println(responseGET.body());
    }

    public static void getUserById(int objId) throws IOException, InterruptedException{
        HttpRequest requestGetById = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url + "/%d", objId)))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> responseGetById =
                client.send(requestGetById, HttpResponse.BodyHandlers.ofString());

        System.out.println(responseGetById.body());
    }

    public static void getUserByUsername(String name) throws IOException, InterruptedException{
        HttpRequest requestGetById = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url + "?username=%s", name)))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> responseGetById =
                client.send(requestGetById, HttpResponse.BodyHandlers.ofString());

        System.out.println(responseGetById.body());
    }
}
