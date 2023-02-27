package exercise.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URISyntaxException;
import java.util.List;

import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            try {
                showUsers(request, response);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        try {
            showUser(request, response, id);
        } catch (URISyntaxException e) {
            throw new RuntimeException();
        }
    }

    private List getUsers() throws JsonProcessingException, IOException, URISyntaxException {
        // BEGIN
        var objectMapper = new ObjectMapper();
        var fileURI = Objects.requireNonNull(getClass().getClassLoader().getResource("users.json")).toURI();
        var file = new File(fileURI);
        return objectMapper.readValue(file, List.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, URISyntaxException {

        // BEGIN
        if (getUsers().isEmpty()) {
            return;
        }

        var body = new StringBuilder();
        body.append("<table>");
        List<Map> users = getUsers();
        for (var user : users) {
            var html = """
                    <tr>
                        <td>%s</td>
                        <td>
                            <a href="/users/%s">%s %s</a>
                        </td>
                    </tr>""";
            var row = String.format(html, user.get("id"), user.get("id"), user.get("firstName"), user.get("lastName"));
            body.append(row);
        }
        body.append("</table>");

        response.setContentType("text/html;charset=UTF-8");
        var out = response.getWriter();
        out.println(body);
        out.close();
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
            throws IOException, URISyntaxException {

        // BEGIN
        var isUserExist = false;
        var out = response.getWriter();
        List<Map> users = getUsers();
        var body = new StringBuilder();
        body.append("<table>");
        for (var user : users) {
            if (user.get("id").equals(id)) {
                var html = """
                    <tr>
                        <td>%s</td>
                        <td>
                            <a href="/users/%s">%s %s</a>
                        </td>
                        <td>%s</td>
                    </tr>""";
                var row = String.format(html,
                        user.get("id"),
                        user.get("id"),
                        user.get("firstName"),
                        user.get("lastName"),
                        user.get("email"));
                body.append(row);
                isUserExist = true;
                break;
            }
        }
        body.append("</table>");
        if (!isUserExist) {
            response.sendError(404);
        }
        out.close();
        // END
    }
}
