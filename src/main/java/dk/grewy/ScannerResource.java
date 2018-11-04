package dk.grewy;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Lav et build via batch scriptet buildAndDeploy.bat (antaget at stierne er rigtige - ellers ret dem!)
 *
 */
@Path("scanner")
public class ScannerResource {
    //private static String fileName = "////gnas//downloads//WebScanner//TargetTitles.txt";
    private static String fileName = "c://A//TargetTitles.txt";

    public List<String> getList() throws Exception {
        System.out.println("getList");
        return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
    }

    @GET
    @Produces("application/json")
    @Path("list")
    public Response list() throws Exception {
        System.out.println("list");
        List<String> collect = getList();
        return Response.ok(collect).header("Access-Control-Allow-Origin", "*").build();
    }

    @POST
    @Path("title")
    public Response addTitle(String value) throws Exception {
        System.out.println("addTitle");
        if (getList().contains(value)) {
            return Response.noContent().build();
        }
        Files.write(Paths.get(fileName), (value + "\n").getBytes("UTF-8"), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        return Response.ok(value).header("Access-Control-Allow-Origin", "*").build();
    }

    @OPTIONS
    //@Path("title")
    @Path("{path : .*}")
    public Response options() throws Exception {
        System.out.println("options");
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods","GET, POST, OPTIONS, DELETE, PUT")
                .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
                .build();
    }

    @DELETE
    @Path("title/{value}")
    public Response deleteTitle(@PathParam("value") String value) throws Exception {
        System.out.println("deleteTitle: '" + value + "'");
        List<String> list = getList();
        boolean removed = removeFromList(list, value);
        System.out.println("Removed: " + removed + ". New list: " + list);
        Files.write(Paths.get(fileName), list, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        return Response.noContent()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods","DELETE, GET, POST, OPTIONS, PUT")
                .header("Access-Control-Allow-Headers","origin, content-type, accept, authorization")
                .build();
    }

    private boolean removeFromList(List<String> list, String value) {
        for (Iterator<String> iter = list.listIterator(); iter.hasNext(); ) {
            String a = iter.next();
            if (a.trim().equals(value)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }
}