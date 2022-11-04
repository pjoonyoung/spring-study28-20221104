package com.joonyoung.study28.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.joonyoung.study28.dto.ContentDto;

public class ContentDao implements IDao {
	
	JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public void deleteDao(final String mid) {
		// TODO Auto-generated method stub
		
		
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				
				String sql = "DELETE FROM board WHERE mid=?";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, mid);
				
				return pstmt;
			}
		});
		
		
	}

	@Override
	public void writeDao(final String mwriter, final String mcontent) {
		// TODO Auto-generated method stub
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				
				String sql = "INSERT INTO board (mid, mwriter, mcontent) VALUES(BOARD_SEQ.nextval,?,?)";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, mwriter);
				pstmt.setString(2, mcontent);
				
				return pstmt;
			}
		});
	}

	@Override
	public ArrayList<ContentDto> listDao() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM board ORDER BY mid DESC";// 게시판 번호로 내림차순(최신글이 가장 위로)
		
		ArrayList<ContentDto> dtos = (ArrayList<ContentDto>) template.query(sql, new BeanPropertyRowMapper(ContentDto.class));
		
		return dtos;
	}
	
	
}
