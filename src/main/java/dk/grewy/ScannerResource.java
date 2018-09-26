package dk.grewy;

import javax.ws.rs.*;
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

    @GET
    @Produces("application/json")
    @Path("list")
    public List<String> list() throws Exception {
        return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
    }

    @POST
    @Path("title")
    public void addTitle(String value) throws Exception {
        if (list().contains(value)) {
            return;
        }
        Files.write(Paths.get(fileName), (value + "\n").getBytes("UTF-8"), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
    }


    @DELETE
    @Path("title/{value}")
    public void deleteTitle(@PathParam("value") String value) throws Exception {
        System.out.println("Deleting: '" + value + "'");
        List<String> list = list();
        boolean removed = removeFromList(list, value);
        System.out.println("Removed: " + removed + ". New list: " + list);
        Files.write(Paths.get(fileName), list, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
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
