import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/sum-calculations/new", "/sum-calculations"})
public class SumCalculationsController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(req.getServletPath().equals("/sum-calculations/new")) {
      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/sumCalculations/form.jsp");
      Integer previousSum = null;
      for(Cookie cookie : req.getCookies()) {
        if(cookie.getName().equals("sum")) {
          previousSum = Integer.parseInt(cookie.getValue());
          req.setAttribute("previousSum", previousSum);
          break;
        }
      }
      dispatcher.forward(req, resp);
    }
    else if(req.getServletPath().equals("/sum-calculations")) {
      resp.sendRedirect(("/sum-calculations/new"));
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if(req.getServletPath().equals("/sum-calculations")) {
      int firstNumber = Integer.parseInt(req.getParameter("firstNumber"));
      int secondNumber = Integer.parseInt(req.getParameter("secondNumber"));
      Integer sum = firstNumber + secondNumber;

      if(req.getParameter("sumWithPrevious").equals("1")) {
        Integer previousSum = null;
        for(Cookie cookie : req.getCookies()) {
          if(cookie.getName().equals("sum")) {
            previousSum = Integer.parseInt(cookie.getValue());
            sum += previousSum;
            break;
          }
        }
      }
      req.setAttribute("sum", sum);

      Cookie sumCookie = new Cookie("sum", sum.toString());
      sumCookie.setMaxAge(60*60);
      resp.addCookie(sumCookie);

      RequestDispatcher dispatcher = req.getRequestDispatcher("/views/sumCalculations/show.jsp");
      dispatcher.forward(req, resp);
    }
  }
}
