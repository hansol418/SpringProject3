package com.busanit501.springproject3.lhj.service;

import com.busanit501.springproject3.lhj.dto.BoardReplyDTO;

import java.util.List;

public interface BoardReplyService {
    BoardReplyDTO getBoardReplyById(Long id);
    BoardReplyDTO createBoardReply(BoardReplyDTO boardReplyDTO);
    BoardReplyDTO updateBoardReply(Long id, BoardReplyDTO boardReplyDTO);
    void deleteBoardReply(Long id);
    List<BoardReplyDTO> getAllBoardReplies();
}
