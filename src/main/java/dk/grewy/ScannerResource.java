package dk.grewy;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Lav et build via batch scriptet buildAndDeploy.bat (antaget at stierne er rigtige - ellers ret dem!)
 *
 */
@Path("scanner")
public class ScannerResource {


    @GET
    @Produces("application/json")
    @Path("list")
    public List<Object> test() throws Exception {
        String fileName = "////gnas//downloads//WebScanner//TargetTitles.txt";
        return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
    }



}
