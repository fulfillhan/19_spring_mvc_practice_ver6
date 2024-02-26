package com.application.practiceVer6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.practiceVer6.dao.BoardDAO;
import com.application.practiceVer6.dto.BoardDTO;

@Service
public class BoardServiceImple implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createBoard(BoardDTO boardDTO) {
		//passwordEncoder.encode(boardDTO.getPasswd());
		boardDTO.setPasswd(passwordEncoder.encode(boardDTO.getPasswd()));
		boardDAO.createBoard(boardDTO);
	}

	@Override
	public List<BoardDTO> getBoardList() {
		
		return boardDAO.getBoardList();
	}

	@Override
	public BoardDTO getBoardDetail(long boardId) {
		//아이디 메뉴 넘기기, 
		return boardDAO.getBoardDetail(boardId);
	}

	@Override
	public boolean isAuthorized(BoardDTO boardDTO) {
		boolean isAuthorized = false;
		
		//암호화된 비번 가지고와서 비교하기
		 boolean checkPasswd = passwordEncoder.matches(boardDTO.getPasswd(), boardDAO.getEncodedPasswd(boardDTO.getBoardId()));
		 if(checkPasswd) {
			 isAuthorized = true;
		 }
		return isAuthorized;
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) {
		boardDAO.updateBoard(boardDTO);
	}

	@Override
	public void deleteBoard(long boardId) {
		boardDAO.deleteBoard(boardId);
	}
}
