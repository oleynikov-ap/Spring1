import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;


@WebServlet(name = "webapp", urlPatterns = "/web_app")
public class Servlet implements javax.servlet.Servlet {

    private ArrayList<Product> productList = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(Servlet.class);

    private ServletConfig servletConfig;

    public void setProductList() {
        for (int i = 0; i < 10; i++) {
            productList.add(new Product(i, "Продукт №" + i, (float) (Math.round(100000 * Math.random()) * 0.01)));
        }
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        setProductList();
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        LOGGER.info("New request");
        for (int i = 0; i < productList.size(); i++)
            servletResponse.getWriter().printf("<h1>%s</h1>", productList.get(i));
    }

    @Override
    public String getServletInfo() {
        return "ServletInfo";
    }

    @Override
    public void destroy() {
        LOGGER.info("Servlet {} destroyed", getServletInfo());
    }
}
