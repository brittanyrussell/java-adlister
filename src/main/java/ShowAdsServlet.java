import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowAdsServlet", urlPatterns = "/ads")
public class ShowAdsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ad> Ad = DaoFactory.getAdsDao().all();
        response.setContentType("text/html");
//        String html = "";
//
//        for (Ad a: ad) {
//            System.out.println("a.getTitle() = " + a.getTitle());
//            System.out.println("a.getDescription() = " + a.getDescription());
//            html += "<h2>" + a.getId() + "</h2>";
//            html += "<h2>" + a.getTitle() + "</h2>";
//            html += "<h2>" + a.getDescription() + "</h2>";
//
//        }

        request.setAttribute("Ad", Ad);
        request.getRequestDispatcher("ads/index.jsp").forward(request, response);
    }


}
