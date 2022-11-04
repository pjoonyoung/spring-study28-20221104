package com.joonyoung.study28.dao;

import java.util.ArrayList;

import com.joonyoung.study28.dto.ContentDto;

public interface IDao {
	
	public void deleteDao(String mid);
		
	public void writeDao(String mwriter, String mcontent);
	
	public ArrayList<ContentDto> listDao();
}
