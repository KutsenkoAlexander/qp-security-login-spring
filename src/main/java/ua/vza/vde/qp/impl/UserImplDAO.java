package ua.vza.vde.qp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;
import ua.vza.vde.qp.dao.IUserDAO;
import ua.vza.vde.qp.obj.User;
import ua.vza.vde.qp.utils.UserRowMapper;

import javax.sql.DataSource;

/**
 * Created by velenteenko on 25.06.15.
 */
@Component
public class UserImplDAO implements IUserDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplateDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUserFioByLogin(String userLogin) {
        String sql = "SELECT id_user, details FROM users where username=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userLogin}, new UserRowMapper());
    }
}
