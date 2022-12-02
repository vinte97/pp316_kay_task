package com.example.demo;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @created 02.12.2022 14:28
 */

@Component
public class Main {
    private final String url = "http://94.198.50.185:7081/api/users";
    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders headers = new HttpHeaders();
    static String result = "";

    public static void main(String[] args) {
        Main app = new Main();
        app.create();
        app.change();
        app.delete(3L);
        if (result.length() != 18) {
            System.out.println("Error");
        }
        System.out.println(result);
    }



    public Main() {
        String session = getAllUsers();
        headers.set("cookie", session);
    }


    private String getAllUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return String.join(";", Objects.requireNonNull(response.getHeaders().get("Set-cookie")));
    }

    public void create() {
        User user = new User("James", "Brown", (byte) 40);
        user.setId(3L);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String request = restTemplate.postForEntity(url, entity, String.class).getBody();
        System.out.println(restTemplate.postForEntity(url, entity, String.class));
        System.out.println(request);
        result = result + request;
        System.out.println(result);
        new ResponseEntity<>(request, HttpStatus.OK);
    }


    private void change() {
        User user = new User("Thomas", "Shelby", (byte) 40);
        user.setId(3L);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        String request = restTemplate.postForEntity(url, entity, String.class).getBody();
        System.out.println(restTemplate.postForEntity(url, entity, String.class));
        System.out.println(request);
        result = result + request;
        System.out.println(result);
        new ResponseEntity<>(request, HttpStatus.OK);
    }

    private void delete(@PathVariable Long id) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        String request = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
        System.out.println(request);
        result = result + request;
        new ResponseEntity<>(request, HttpStatus.OK);
    }
}
