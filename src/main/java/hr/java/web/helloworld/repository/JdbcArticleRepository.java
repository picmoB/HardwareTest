package hr.java.web.helloworld.repository;

import hr.java.web.helloworld.domain.Hardware;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
@AllArgsConstructor
public class JdbcArticleRepository implements ArticleRepository {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Hardware> getAllArticles() {
        return jdbcTemplate.query("SELECT * FROM HARDWARE", new HardwareMapper());
    }

    @Override
    public List<Hardware> getArticlesByName(String name) {
        return jdbcTemplate.query("SELECT * FROM HARDWARE WHERE NAME = ?", new HardwareMapper(), name);
    }

    @Override
    public List<Hardware> getArticlesById(Integer id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM HARDWARE WHERE ID = ?", new HardwareMapper(), map));
    }

    @Override
    public int saveNewArticle(Hardware hardware) {
        final String sql = "SELECT ID FROM FINAL TABLE (INSERT INTO HARDWARE (name, type, price, code) VALUES (?, ?, ?, ?)) ARTICLE";
        Integer generatedId = jdbcTemplate.queryForObject(sql, Integer.class, hardware.getName(), hardware.getType(), hardware.getPrice(), hardware.getCode());
        hardware.setId(generatedId);
        return hardware;
    }

    @Override
    public Optional<Hardware> updateArticle(Hardware hardware, Integer id) {
        if (articleByIdExists(id)) {
            final String SQL = "UPDATE HARDWARE SET NAME = ?, type = ?, price = ?, code = ? WHERE ID = ?";
            //jdbcTemplate.update(SQL, new Object[]{hardware.getName(), hardware.getType(), hardware.getPrice(), hardware.getCode(), id});
            jdbcTemplate.update(con ->  {
                PreparedStatement ps = con.prepareStatement(SQL);
                ps.setString(1, hardware.getName());
                ps.setString(2, hardware.getType());
                ps.setString(3, hardware.getPrice().toString());
                ps.setBigDecimal(4, hardware.getCode());
                ps.setInt(5, id);
                return ps;
            });
            hardware.setId(id);
            return Optional.of(hardware);
        } else  {
            return Optional.empty();
        }
    }

    @Override
    public boolean articleByIdExists(Integer id) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT (*) FROM HARDWARE WHERE ID = ?", Integer.class, id);
        return count > 0;
    }

    @Override
    public boolean deleteArticleById(Integer id) {
        if (articleByIdExists(id)) {
            jdbcTemplate.update("DELETE FROM HARDWARE WHERE ID = ?", id);
            return true;
        } else {
            return false;
        }
    }

    private static class HardwareMapper implements RowMapper<Hardware> {
        public Hardware mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hardware hardware = new Hardware();
            hardware.setId(rs.getInt("id"));
            hardware.setName(rs.getString("name"));
            hardware.setType(rs.getString("type"));
            hardware.setCode(rs.getBigDecimal("code"));

            return hardware;
        }
    }
}
