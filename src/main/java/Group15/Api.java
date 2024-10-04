package Group15;

import javafx.application.Platform;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Api {
    public static void getWorkout()
    {
        new Thread(() -> {
            String URL = "https://www.dnd5eapi.co/api/";
            try
            {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<Boolean> response = restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<Boolean>()
                        {
                        });
                Boolean bool = response.getBody();
                Platform.runLater(() ->
                {
                });
            }
            catch (Exception e)
            {
                System.out.println("Exception occurred: " + e.getMessage());
            }
        }).start();
    }
    
}
