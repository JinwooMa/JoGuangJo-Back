package com.jgj.byl_process.domain.boards.qna.service;

import com.jgj.byl_process.domain.boards.qna.controller.dto.request.QnaCommentRequest;
import com.jgj.byl_process.domain.boards.qna.entity.QnaBoard;
import com.jgj.byl_process.domain.boards.qna.entity.QnaComment;
import com.jgj.byl_process.domain.boards.qna.repository.QnaBoardRepository;
import com.jgj.byl_process.domain.boards.qna.repository.QnaCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class QnaCommentServiceImpl implements QnaCommentService{

    @Autowired
    QnaCommentRepository qnaCommentRepository;

    @Autowired
    QnaBoardRepository qnaBoardRepository;

    @Override
    public List<QnaComment> qnaCommentList(Long qnaBoardId) {
        return qnaCommentRepository.findAll(qnaBoardId);
    }
    @Override
    public void qnaCommentRegister(QnaCommentRequest commentRequest) {
        Optional<QnaBoard> maybeQnaBoard = qnaBoardRepository.findById(commentRequest.getQnaBoardId());

        QnaComment qnaComment = new QnaComment();
        qnaComment.setQnaBoard(maybeQnaBoard.get());
        qnaComment.setWriter(commentRequest.getWriter());
        qnaComment.setComment(commentRequest.getComment());

        qnaCommentRepository.save(qnaComment);
    }

    @Override
    public QnaComment modify(Long qnaCommentId, QnaCommentRequest qnaCommentRequest) {
        QnaComment qnaComment = qnaCommentRepository.findById(qnaCommentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글 존재안함." + qnaCommentId));

        qnaComment.update(qnaCommentRequest.getComment());

        qnaCommentRepository.save(qnaComment);
        return qnaComment;
    }

    @Override
    public void remove(Long qnaCommentId) {
        qnaCommentRepository.deleteById(qnaCommentId);
    }
}
