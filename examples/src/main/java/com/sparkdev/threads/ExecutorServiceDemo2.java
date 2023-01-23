package com.sparkdev.threads;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * This example demonstrate how to call external apis with the help of callable and wait for both of the APIs to finish,
 * using the invokeAny and invokeAll methods.
 */
class UserService implements Callable<String>{

    @Override
    public String call() throws Exception {
        HttpResponse httpResponse = null;

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet("https://dummyjson.com/users");
            getRequest.addHeader("accept", "application/json");

            httpResponse = httpClient.execute(getRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
    }
}

class ProductService implements Callable<String>{

    @Override
    public String call() throws Exception {
        HttpResponse httpResponse = null;

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet getRequest = new HttpGet("https://dummyjson.com/products");
            getRequest.addHeader("accept", "application/json");

            httpResponse = httpClient.execute(getRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }

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
    }
}
public class ExecutorServiceDemo2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        System.out.println(Instant.now());
        List<Callable<String>> callables = new ArrayList<>();
        callables.add(new ProductService());
        callables.add(new UserService());
        // get any one of the output from any of the two callable services defined above
        String output = executorService.invokeAny(callables);
        System.out.println(output);

        // get all the output from both the callable services defined above
        List<Future<String>> futures = executorService.invokeAll(callables);
        for(Future<String> stringFuture: futures){
            System.out.println(stringFuture.get());
        }

        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(Instant.now());
    }
}
