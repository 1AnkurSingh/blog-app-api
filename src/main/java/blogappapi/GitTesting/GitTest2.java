package blogappapi.GitTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GitTest2   {
    public static void main(String[] args) {
        try {
            String username = "your-github-username";
            String apiUrl = "https://api.github.com/users/" + username;

            // Create a URL object with the API URL
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            String userInfo = response.toString();
            System.out.println(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
