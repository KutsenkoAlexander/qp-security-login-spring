package ua.vza.vde.qp.utils;

import org.springframework.jdbc.core.RowMapper;
import ua.vza.vde.qp.obj.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kycenko on 28.09.15.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("id_user"));
        user.setFio(rs.getString("details"));
        return user;
    }
}
