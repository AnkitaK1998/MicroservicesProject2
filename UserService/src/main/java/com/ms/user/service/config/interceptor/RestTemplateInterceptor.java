package com.ms.user.service.config.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.io.IOException;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    private OAuth2AuthorizedClientManager manager;

    private Logger logger= LoggerFactory.getLogger(RestTemplateInterceptor.class);

    public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
        this.manager = manager;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("my-internal-client").principal("internal").build()).getAccessToken().getTokenValue();

        String token="eyJraWQiOiJZSjg4OWVHWXhXXzNDNmFVSEN6am1iZVZhY1RfSTh3eG1NdHY5aU52QXBvIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULnozcG5qd0ZualRzWnFLNjlVVnlCOHhldlFPUkY1c2J2bGFtYnJGYW1JbUkub2FyMTBtMzV4Y3pCaWdmWTM1ZDciLCJpc3MiOiJodHRwczovL2Rldi05NzI5MjQ5OS5va3RhLmNvbS9vYXV0aDIvZGVmYXVsdCIsImF1ZCI6ImFwaTovL2RlZmF1bHQiLCJpYXQiOjE2ODE5ODI4MjAsImV4cCI6MTY4MTk4NjQyMCwiY2lkIjoiMG9hOTZpcjBybjhJa3RZUGk1ZDciLCJ1aWQiOiIwMHU5Nmo0OXp3bFhTN3c3MzVkNyIsInNjcCI6WyJvZmZsaW5lX2FjY2VzcyIsImVtYWlsIiwib3BlbmlkIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2ODE5ODI4MTcsInN1YiI6InNoaXZhbmlAZ21haWwuY29tIiwibXljbGFpbSI6WyJFdmVyeW9uZSIsIk5vcm1hbCJdfQ.nL40dUxA3uzm7OZtNQsH9byEBiY0-pCJm5UIx5xztG_vaWZftnASBxEWAl83B7QxQJPjz6BlQm5rpyoYP4KVaP19y6ArfTMMnRkTAg9aQSFBb4l_e8f28cUMEmDNCakEMuwLNlIsrxHb0r6zhNpAuT2eFTJogEWGBq4CdMIEgPLcLYR2OnUE8TyMUn92oRgvXD7IJap-R41q5MItzxshxgU8V71jgDDalObwOi5AZYgaHbznPq87kIu0hCMbjdA7ChdWmKWr7rg4vEr_VuzE1iSkfDdf4iDoAnP12S4R3_p_0Sl6Ho1q49yYcI05n26A8A0YakNurXU6CrJBk2QqQg";
        logger.info("Rest Template interceptor: Token :  {} ",token);

        request.getHeaders().add("Authorization","Bearer "+token);
        logger.info("Rest Template interceptor: Token :  {} ",token);

        return execution.execute(request,body);
    }
}