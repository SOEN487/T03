package com.example.client;

import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Scanner;

public class CustomerClient {

    // Pretend we are in a bar
    public static void main(String[] args) throws InterruptedException {
        // 2 TAs and 1 Professor walk into a bar to celebrate the end of COVID
        createCustomer("Nick", 24);
        createCustomer("Ali", 30);
        createCustomer("Hamed", 26);

        // Since everyone in a bar needs to be at least 18 years old, the waiter decides to check all their IDs and ages
        System.out.println(getCustomers());

        // They don't drink, so they eat food at the bar for 5 seconds instead
        Thread.sleep(5000);

        // Nick has to wake up early the next day, so he leaves early, while another TA ends up joining the others
        replaceCustomer(0, "Kosta", 28);

        // They talk altogether for another 5 seconds
        Thread.sleep(5000);

        // Because it's getting late, the waiter is looking around to see who is left in the bar
        System.out.println(getCustomers());

        // It has gotten late, so now the waiter asks everyone to leave and waits for them all to leave
        for (int i = 1; i <= 3; i++) {
            deleteCustomer(i);
            Thread.sleep(1000);
        }

        // The waiter now wants to make sure no one is in the bar
        System.out.println(getCustomers());
    }

    /**
     * Gets the list of customers by calling the API
     * @return string representation of all the customers currently in the restaurant
     */
    private static String getCustomers() {

        // Create closeable http client to execute requests with
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Creating the request to execute
            HttpGet httpget = new HttpGet("http://localhost:8080/restaurant/customer");

            // Executing the request using the http client and obtaining the response
            CloseableHttpResponse response = client.execute(httpget);
            return readResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get customers";
        }
    }

    /**
     * Gets a particular customer by id
     * @param id of the customer
     * @return string representation of the customer
     */
    private static String getACustomer(int id) {
        // Create closeable http client to execute requests with
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Creating the request to execute
            HttpGet httpget = new HttpGet(String.format(""));

            // Executing the request using the http client and obtaining the response
            CloseableHttpResponse response = client.execute(httpget);
            return readResponse(response);

        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to get a customer";
        }
    }

    /**
     * Reads the response and converts it into a string
     * @param response response from http request
     * @return string of the response
     * @throws IOException
     */
    public static String readResponse(CloseableHttpResponse response) throws IOException {
        // Handling the IO Stream from the response using scanner
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder stringResponse = new StringBuilder();
        while (sc.hasNext()) {
            stringResponse.append(sc.nextLine());
            stringResponse.append("\n");
        }
        response.close();
        return stringResponse.toString();
    }

    /**
     * Method for creating a customer using the REST API
     * @param name of customer
     * @param age of customer
     */
    private static void createCustomer(String name, int age) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format("http://localhost:8080/restaurant/customer/%s/%d",
                    name, age));
            CloseableHttpResponse httpresponse = client.execute(httpPost);
            httpresponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for replacing customer using the PUT method
     * @param id of the customer to replace
     * @param name of the new customer
     * @param age of the new customer
     */
    private static void replaceCustomer(int id, String name, int age) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(String.format("http://localhost:8080/restaurant/customer/%d/%s/%d",
                    id, name, age));
            CloseableHttpResponse httpresponse = client.execute(httpPut);
            httpresponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that leverages the REST API to delete a customer given an ID
     * @param id of the customer to delete
     */
    private static void deleteCustomer(int id) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete httpDelete = new HttpDelete(String.format("http://localhost:8080/restaurant/customer/%d", id));
            CloseableHttpResponse deleteResponse = client.execute(httpDelete);
            deleteResponse.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
