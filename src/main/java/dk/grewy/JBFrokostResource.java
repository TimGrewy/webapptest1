package dk.grewy;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lav et build via batch scriptet buildAndDeploy.bat (antaget at stierne er rigtige - ellers ret dem!)
 *
 */
@Path("frokost")
public class JBFrokostResource {
    private static String fileName = "c://A//Frokost.txt";

    @GET
    @Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
    @Path("")
    public String getFrokost() throws Exception {
        return Files.lines(Paths.get(fileName), Charset.forName("ISO-8859-1")).collect(Collectors.joining());
    }

    @POST
    @Path("")
    public void saveFrokost(String value) throws Exception {
        Files.write(Paths.get(fileName), (value + "\n").getBytes("ISO-8859-1"), StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
    }

}
