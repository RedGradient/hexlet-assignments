package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;



public class ArticlesServlet extends HttpServlet {

    private static final int LIMIT = 10;

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                try {
                    showArticles(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                try {
                    showArticle(request, response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException, SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        List<Map<String, String>> articles = new ArrayList<>();

        var string_page = request.getParameter("page");
        int page;
        try {
            page = Integer.parseInt(string_page);
        } catch (NumberFormatException e) {
            page = 1;
        }

        var statement = connection.createStatement();

        var offset = LIMIT * (page - 1);
        var query = String.format("SELECT * FROM articles ORDER BY id LIMIT %s OFFSET %s;", LIMIT, offset);

        var rs = statement.executeQuery(query);
        while (rs.next()) {
            articles.add(Map.of(
                    "id", rs.getString("id"),
                    "title", rs.getString("title"),
                    "body", rs.getString("body")
            ));
        }

        request.setAttribute("articles", articles);
        request.setAttribute("page", page);

        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException, SQLException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN

        int article_id;
        try {
            var pathParts = request.getPathInfo().split("/");
            var string_id = pathParts[pathParts.length - 1];
            article_id = Integer.parseInt(string_id);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        var query = String.format("SELECT * FROM articles WHERE id=%s;", article_id);
        var statement = connection.createStatement();
        var rs = statement.executeQuery(query);

        Map<String, String> article;
        if (rs.next()) {
            article = Map.of(
                    "id", rs.getString("id"),
                    "title", rs.getString("title"),
                    "body", rs.getString("body")
            );
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("article", article);

        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
