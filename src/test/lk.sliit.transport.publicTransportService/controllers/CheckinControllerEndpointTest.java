package lk.sliit.transport.publicTransportService.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lk.sliit.transport.publicTransportService.dtos.TripDTO;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by dinukshakandasamanage on 11/19/17.
 */

@RunWith(SpringRunner.class)
public class CheckinControllerEndpointTest {

    @Test
    public void checkinShouldThrowExceptionForInvalidToken() throws Exception {
        TripDTO tripDTO = new TripDTO();

        HttpPost request = new HttpPost("http://localhost:8080/checkin/");

        Gson gson = new Gson();
        String json = gson.toJson(tripDTO);
        StringEntity entity = new StringEntity(json);

        request.setEntity(entity);
        request.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        InputStream in = httpResponse.getEntity().getContent();
        String body = IOUtils.toString(in, "UTF-8");
        Map<String, Object> map = gson.fromJson(body, new TypeToken<Map<String, Object>>() {
        }.getType());

        assertEquals(HttpStatus.NOT_FOUND.value(), httpResponse.getStatusLine().getStatusCode());
        assertEquals("Invalid Data provided!", map.get("message"));
    }

    @Test
    public void validateTokenShouldReturnOKForInvalidToken() throws Exception {

        HttpGet request = new HttpGet("http://localhost:8080/checkin?token=abc");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        InputStream in = httpResponse.getEntity().getContent();
        String body = IOUtils.toString(in, "UTF-8");

        assertEquals(HttpStatus.OK.value(), httpResponse.getStatusLine().getStatusCode());
        assertEquals("false", body);
    }

    @Test
    public void findPassengerByTokenShouldThrowExceptionForInvalidToken() throws Exception {

        HttpGet request = new HttpGet("http://localhost:8080/checkin/passenger?token=abc");

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Gson gson = new Gson();

        InputStream in = httpResponse.getEntity().getContent();
        String body = IOUtils.toString(in, "UTF-8");

        Map<String, Object> map = gson.fromJson(body, new TypeToken<Map<String, Object>>() {
        }.getType());


        assertEquals(HttpStatus.NOT_FOUND.value(), httpResponse.getStatusLine().getStatusCode());
        assertEquals("The Token Reference was not found", map.get("message").toString());
    }

}