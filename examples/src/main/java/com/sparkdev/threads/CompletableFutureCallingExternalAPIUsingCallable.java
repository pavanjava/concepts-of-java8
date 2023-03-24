package com.sparkdev.threads;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureCallingExternalAPIUsingCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {

            HttpResponse httpResponse = null;

            try {
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet getRequest = new HttpGet("https://dummyjson.com/products/1");
                getRequest.addHeader("accept", "application/json");

                httpResponse = httpClient.execute(getRequest);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return httpResponse;
        }).thenApply(httpResponse -> {
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + httpResponse.getStatusLine().getStatusCode());
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            String output;
            try {
                output = EntityUtils.toString(httpEntity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return output;
        });
        if (!completableFuture.isDone()) {
            System.out.println(completableFuture.get());
        }

    }
}
