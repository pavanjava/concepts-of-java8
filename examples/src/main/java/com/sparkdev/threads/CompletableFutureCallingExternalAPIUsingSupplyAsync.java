package com.sparkdev.threads;

import com.sparkdev.model.Order;
import com.sparkdev.model.Product;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureCallingExternalAPIUsingSupplyAsync {
    public static void main(String[] args) {
        Order order = new Order();
        order.setOrderInstant(Instant.now());
        order.setOrderAmount(3000);

        // basically the idea is to get the Person object and set it to Person class & similarly with User
        // then set them to Order object
        // because of the additional library requirement for serialization & deserialization (json to pojo)[lib like jakson are required] i am leaving it for our own workout.
        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(() -> callAPI("https://dummyjson.com/products/1")).thenAccept(System.out::println),
                CompletableFuture.supplyAsync(() -> callAPI("https://dummyjson.com/users/1")).thenAccept(System.out::println)
        ).join();

        System.out.println(order.toString());
    }

    static String callAPI(final String URL){
        HttpResponse httpResponse = null;
        String output = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet(URL);
            getRequest.addHeader("accept", "application/json");

            httpResponse = httpClient.execute(getRequest);

            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpResponse.getStatusLine().getStatusCode());
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            
            try {
                output = EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
}
