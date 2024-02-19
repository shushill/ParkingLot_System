package greenstitch.sushil.assignment.service;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClientRequest;

import java.time.Duration;

@Service
public class ShellService {


    private final WebClient webClient;

    public ShellService(){
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    public String createparkinglot(int numcar) {

        String requestBody = String.format("{ \"numcars\": %d }", numcar);

        String response = webClient.post()
                .uri("/api/v1/create/parkinglot")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(20));
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }

    public String parkcar(String reg, String rang) {

        if(rang == null){
            return "Colour cant be empty\n";
        }
        if(reg==null){
            return "Registration number cant be empty\n";
        }

        String requestBody = String.format("{ \"colour\": \"%s\",\"regnum\": \"%s\" }", rang, reg);

        String response = webClient.post()
                .uri("/api/v1/park/newcar")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(20));
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }

    public String deleteparkedcar(int num) {

        String uri_data = String.format("/api/v1/park/leave/%d", num);

        String response = webClient.delete()
                .uri(uri_data)
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(20));
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }

    public String availablecars() {

        String uri_data = String.format("/api/v1/park/status");

        String response = webClient.get()
                .uri(uri_data)
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(20));
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();


        return response;
    }

    public String showcarsofcolours(String rang) {

        String uri_data = String.format("/api/v1/park/%s/detail", rang);

        String response = webClient.get()
                .uri(uri_data)
                .httpRequest(httpRequest -> {
                    HttpClientRequest reactorRequest = httpRequest.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofSeconds(20));
                })
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }




}

