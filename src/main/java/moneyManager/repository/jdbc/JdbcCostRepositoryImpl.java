package moneyManager.repository.jdbc;

import moneyManager.Profiles;
import moneyManager.model.Cost;
import moneyManager.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public abstract class JdbcCostRepositoryImpl<T> implements CostRepository {
    private static final RowMapper<Cost> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Cost.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertCost;

    protected abstract T toDbDateTime(LocalDateTime ldt);

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.insertCost = new SimpleJdbcInsert(dataSource)
                .withTableName("costs")
                .usingGeneratedKeyColumns("id");
    }

    @Repository
    @Profile(Profiles.POSTGRES)
    public static class Java8JdbcCostRepositoryImpl extends JdbcCostRepositoryImpl<LocalDateTime> {
        @Override
        protected LocalDateTime toDbDateTime(LocalDateTime ldt) {
            return ldt;
        }
    }

    @Repository
    @Profile(Profiles.HSQLDB)
    public static class TimestampJdbcCostRepositoryImpl extends JdbcCostRepositoryImpl<Timestamp> {

        @Override
        protected Timestamp toDbDateTime(LocalDateTime ldt) {
            return Timestamp.valueOf(ldt);
        }
    }

    @Override
    @Transactional
    public Cost save(Cost cost, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", cost.getId())
                .addValue("description", cost.getDescription())
                .addValue("price", cost.getPrice())
                .addValue("date_time", toDbDateTime(cost.getDateTime()))
                .addValue("user_id", userId);

        if (cost.isNew()) {
            Number newId = insertCost.executeAndReturnKey(map);
            cost.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update(
                    "UPDATE costs SET description=:description, price=:price, date_time=:date_time WHERE id=:id AND user_id=:user_id", map) == 0) {
                return null;
            }
        }
        return cost;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM costs WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Cost get(int id, int userId) {
        List<Cost> costs = jdbcTemplate.query("SELECT * FROM costs WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(costs);
    }

    @Override
    public List<Cost> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM costs WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Cost> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return jdbcTemplate.query("SELECT * FROM costs WHERE user_id=? AND date_time BETWEEN ? AND ? ORDER BY date_time DESC",
                ROW_MAPPER, userId, toDbDateTime(startDate), toDbDateTime(endDate));
    }
}
