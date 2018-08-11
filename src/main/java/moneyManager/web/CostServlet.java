package moneyManager.web;

import moneyManager.model.Cost;
import moneyManager.repository.CostRepository;
import moneyManager.repository.mock.InMemoryCostRepositoryImpl;
import moneyManager.util.CostsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by Vladimir on 02.08.2018.
 */
public class CostServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CostServlet.class);

    private CostRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryCostRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");

        Cost cost = new Cost(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("price")));

        LOG.info(cost.isNew() ? "Create {}" : "Update {}", cost);
        repository.save(cost);
        response.sendRedirect("costs");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            LOG.info("getAll");
            request.setAttribute("costs", CostsUtil.getWithExceeded(repository.getAll(), CostsUtil.DEFAULT_SUM_PER_DAY));
            request.getRequestDispatcher("/costs.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = getId(request);
            LOG.info("Delete {}", id);
            repository.delete(id);
            response.sendRedirect("costs");

        } else if ("create".equals(action) || "update".equals(action)) {
            final Cost cost = action.equals("create") ?
                    new Cost(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                    repository.get(getId(request));
            request.setAttribute("cost", cost);
            request.getRequestDispatcher("cost.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
