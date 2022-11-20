package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    public void writeAllCompanies(PrintWriter out) {
        for (var company : getCompanies()) {
            out.println(company);
        }
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        var out = response.getWriter();

        var params = request.getParameterMap();
        var search = params.getOrDefault("search", new String[] {""})[0];

        if (search.isEmpty()) {
            writeAllCompanies(out);
        } else {
            var count = 0;
            for (var company : getCompanies()) {
                if (company.contains(search)) {
                    out.println(company);
                    count++;
                }
            }
            if (count == 0) out.write("Companies not found");
        }
        // END
    }
}
