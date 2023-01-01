package com.example.kirikiri.service;

import com.example.kirikiri.domain.BoardVO;


import java.util.List;

public interface BoardService {
    //전체 게시물 조회
    public List<BoardVO> getListAll();

    //특정 게시판 조회
    public List<BoardVO> getListByCategory(String categoryName);

    //특정 게시판 조회
    public List<BoardVO> getListByDetailCategory(String detailCategoryName);

    //게시물 추가
    public void add(BoardVO boardVO);

    //게시물 삭제
    public void delete(Long boardId);

    //게시물 수정
    public void edit(BoardVO boardVO);

    //게시물 조회
    public BoardVO getBoard(Long boardId);

    // 작성한 게시글 조회
    public List<BoardVO> getWrittenBoard(String userId, Integer page);
}
