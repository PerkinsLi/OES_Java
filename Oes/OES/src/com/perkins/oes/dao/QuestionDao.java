package com.perkins.oes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.perkins.oes.AppContext;
import com.perkins.oes.Constants;
import com.perkins.oes.entity.Question;
import com.perkins.oes.model.JDBCAbstractCallBack;
import com.perkins.oes.model.JDBCTemplate;
import com.perkins.oes.util.DButil;
import com.perkins.oes.util.DateUtil;
import com.perkins.oes.util.StringUtil;

public class QuestionDao {

    private JDBCTemplate<Question> jdbcTemplate;

    public void setJdbcTemplate(JDBCTemplate<Question> jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Question getQuestionID(final String QuestionID) {
        String sql = "SELECT * FROM question WHERE question_id = ? ";
        return jdbcTemplate.queryOne(sql, new JDBCAbstractCallBack<Question>() {

            @Override
            public Question toObject(ResultSet rs) throws SQLException {
                Question question = new Question();
                question.setQuestionID(rs.getString("question_id"));
                return question;
            }

            @Override
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, QuestionID);
            }
        });

    }

    public int insert(final Question question) {
        int id = 0;
        String sql = "INSERT INTO question "
                + "(title, a, b, c, d, right_answer, create_date, update_date, status, question_id)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        final String createDate = DateUtil.getNowDate();
        final String updateDate = DateUtil.getNowDate();
        final String status = "YES";
        id = jdbcTemplate.insert(sql, new JDBCAbstractCallBack<Question>() {
            @Override
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, question.getTitle());
                stmt.setString(2, question.getA());
                stmt.setString(3, question.getB());
                stmt.setString(4, question.getC());
                stmt.setString(5, question.getD());
                stmt.setString(6, question.getRightAnswer());
                stmt.setString(7, createDate);
                stmt.setString(8, updateDate);
                stmt.setString(9, status);
                stmt.setString(10, question.getQuestionID());
            }
        });
        return id;

    }

    public int getCount(String searchText) {
        int count = 0;
        String sql = null;
        if (StringUtil.IsEmpty(searchText)) {
            sql = "SELECT COUNT(id) AS questionCount FROM question WHERE status='YES'";
        } else {
            StringBuffer buffer = new StringBuffer();
            buffer.append("SELECT COUNT(id) AS questionCount FROM question WHERE status='YES' ");
            buffer.append(" AND question_id LIKE '%" + searchText + "%' OR title LIKE '%" + searchText + "%' ");
            sql = buffer.toString();
        }
        count = jdbcTemplate.getCount(sql, new JDBCAbstractCallBack<Question>() {
            @Override
            public int getCount(ResultSet rs) throws SQLException {
                int count = rs.getInt("questionCount");
                return count;
            }
        });

        return count;
    }

    public List<Question> findQuestionPage(String searchText, String sort, int offset, int pagesize) {
        String sql = null;
        if (StringUtil.IsEmpty(searchText)) {
            sql = "SELECT * FROM question WHERE status='YES' ORDER BY question_id " + sort + " LIMIT " +
                    offset + " , " + pagesize;
        } else {
            StringBuffer buffer = new StringBuffer();
            buffer.append("SELECT * FROM question WHERE status='YES' ");
            buffer.append(" AND question_id LIKE '%" + searchText + "%' OR title LIKE '%" + searchText + "%' ");
            buffer.append(" ORDER BY question_id " + sort + " LIMIT " + offset + " , " + pagesize);
            sql = buffer.toString();
        }
        return jdbcTemplate.queryList(sql, new JDBCAbstractCallBack<Question>() {

            @Override
            public Question toObject(ResultSet rs) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setTitle(rs.getString("title"));
                question.setA(rs.getString("a"));
                question.setB(rs.getString("b"));
                question.setC(rs.getString("c"));
                question.setD(rs.getString("d"));
                question.setQuestionID(rs.getString("question_id"));
                question.setRightAnswer(rs.getString("right_answer"));
                question.setCreateDate(rs.getDate("create_date"));
                question.setUpdateDate(rs.getDate("update_date"));
                return question;
            }
        });
    }

    public void deleteQuestion(int[] arr) {
        String sql = "UPDATE question SET status='NO' WHERE id=?";
        Connection con = (Connection) AppContext.getAppContext().getObject(Constants.APP_CONTEXT_CONN);
        boolean needMyClose = false;
        if (con == null) {
            con = DButil.getConnection();
            AppContext.getAppContext().addObjects(Constants.APP_CONTEXT_CONN, con);
            needMyClose = true;
        }
        PreparedStatement stmt = null;
        try {
            for (int i = 0; i < arr.length; i++) {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, arr[i]);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButil.close(null, stmt, null);
            if (needMyClose) {
                DButil.close(null, null, con);
            }
        }

    }

    public Question findById(final int id) {
        String sql = "SELECT * FROM question WHERE id = ?";
        return jdbcTemplate.queryOne(sql, new JDBCAbstractCallBack<Question>() {
            @Override
            public Question toObject(ResultSet rs) throws SQLException {
                Question question = new Question();
                question.setId(rs.getInt("id"));
                question.setTitle(rs.getString("title"));
                question.setA(rs.getString("a"));
                question.setB(rs.getString("b"));
                question.setC(rs.getString("c"));
                question.setD(rs.getString("d"));
                question.setQuestionID(rs.getString("question_id"));
                question.setRightAnswer(rs.getString("right_answer"));
                question.setCreateDate(rs.getDate("create_date"));
                question.setUpdateDate(rs.getDate("update_date"));
                return question;
            }

            @Override
            public void setParams(PreparedStatement stmt) throws SQLException {
                super.setParams(stmt);
                stmt.setInt(1, id);
            }
        });

    }

    public int updateQuestion(final Question question) {
        int id = 0;
        String sql = "UPDATE question SET title = ?, a = ?, b = ?, c = ?,"
                + " d = ?, right_answer = ?, update_date = ? WHERE id=? ";
        final String updateDate = DateUtil.getNowDate();
        id = jdbcTemplate.update(sql, new JDBCAbstractCallBack<Question>() {
            @Override
            public void setParams(PreparedStatement stmt) throws SQLException {
                stmt.setString(1, question.getTitle());
                stmt.setString(2, question.getA());
                stmt.setString(3, question.getB());
                stmt.setString(4, question.getC());
                stmt.setString(5, question.getD());
                stmt.setString(6, question.getRightAnswer());
                stmt.setString(7, updateDate);
                stmt.setInt(8, question.getId());
            }
        });
        return id;

    }

}
