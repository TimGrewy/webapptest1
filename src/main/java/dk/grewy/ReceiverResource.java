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

/**
 * Lav et build via batch scriptet buildAndDeploy.bat (antaget at stierne er rigtige - ellers ret dem!)
 *
 */
@Path("")
public class ReceiverResource {

    private static final String ON_XML = "<YAMAHA_AV cmd=\"PUT\">\n" +
            "   <Zone_2>\n" +
            "      <Power_Control>\n" +
            "         <Power>On</Power>\n" +
            "      </Power_Control>\n" +
            "   </Zone_2>\n" +
            "</YAMAHA_AV>";
    private static final String OFF_XML = "<YAMAHA_AV cmd=\"PUT\">\n" +
            "   <Zone_2>\n" +
            "      <Power_Control>\n" +
            "         <Power>Standby</Power>\n" +
            "      </Power_Control>\n" +
            "   </Zone_2>\n" +
            "</YAMAHA_AV>";
    private static final String PARTYMODE_ON_XML = "<YAMAHA_AV cmd=\"PUT\">" +
                "<System>" +
                    "<Party_Mode>" +
                        "<Mode>On</Mode>" +
                    "</Party_Mode>" +
                "</System>" +
            "</YAMAHA_AV>";
    private static final String PARTYMODE_OFF_XML = "<YAMAHA_AV cmd=\"PUT\">" +
                "<System>" +
                    "<Party_Mode>" +
                        "<Mode>Off</Mode>" +
                    "</Party_Mode>" +
                "</System>" +
            "</YAMAHA_AV>";

    private static String url = "http://10.0.0.2/YamahaRemoteControl/ctrl";



    @GET
    @Produces("text/html")
    @Path("on")
    public String turnOn () throws Exception {
        return doHttpToReciever(ON_XML);
    }

    @GET
    @Produces("text/html")
    @Path("off")
    public String turnOff () throws Exception {
        return doHttpToReciever(OFF_XML);
    }
    @GET
    @Produces("text/html")
    @Path("partyon")
    public String turnPartyOn () throws Exception {
        return doHttpToReciever(PARTYMODE_ON_XML);
    }

    @GET
    @Produces("text/html")
    @Path("partyoff")
    public String turnPartyOff () throws Exception {
        return doHttpToReciever(PARTYMODE_OFF_XML);
    }

    private String doHttpToReciever(String xml) throws IOException {
        String statusLine;CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(xml);
        httpPost.setEntity(entity);
        CloseableHttpResponse response2 = httpclient.execute(httpPost);

        try {
            statusLine = response2.getStatusLine()+ "";
            HttpEntity entity2 = response2.getEntity();
            EntityUtils.consume(entity2);
        } finally {
            response2.close();
        }
        return statusLine;
    }

}
