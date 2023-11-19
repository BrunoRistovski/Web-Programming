package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns = "/bookDetails")
@AllArgsConstructor
public class bookDetails extends HttpServlet {

    private SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context =  new WebContext(webExchange);
        bookService.addAuthorToBook(Long.parseLong( req.getParameter("author")),(String) getServletContext().getAttribute("book"));
        context.setVariable("bookDetail",bookService.findBookByIsbn((String) getServletContext().getAttribute("book")));
        springTemplateEngine.process("bookDetails.html",context,resp.getWriter());
    }
}
