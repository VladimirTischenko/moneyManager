package moneyManager.web;

import moneyManager.util.CostsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vladimir on 02.08.2018.
 */
public class CostServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CostServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("getAll");
        request.setAttribute("costs", CostsUtil.getWithExceeded(CostsUtil.COSTS, CostsUtil.DEFAULT_SUM_PER_DAY));
        request.getRequestDispatcher("/costs.jsp").forward(request, response);
    }
}
