package com.example.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class GitHubClient {
    public static void main(String[] args) throws IOException {
        //Create an HttpClient object
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //instantiate the response handler
        ResponseHandler<String> responseHandler = new MyResponseHandler();

        //Create an HttpGet object
        HttpGet httpget = new HttpGet("https://api.github.com/");

        //Execute the Get request by passing the response handler object and HttpGet object
        String httpResponse = httpclient.execute(httpget, responseHandler);

        System.out.println(httpResponse);
    }
}

class MyResponseHandler implements ResponseHandler<String> {

    public String handleResponse(final HttpResponse response) throws IOException {

        // Get the status of the response
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            if(entity == null) {
                return "";
            } else {
                return EntityUtils.toString(entity);
            }

        } else {
            return ""+status;
        }
    }
}
