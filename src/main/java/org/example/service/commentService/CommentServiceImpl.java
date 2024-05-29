package org.example.service.commentService;

import org.example.base.repository.BaseRepository;
import org.example.base.service.BaseServiceImpl;
import org.example.model.Comment;
import org.example.repository.commentRepository.CommentRepository;
import org.hibernate.SessionFactory;

public class CommentServiceImpl extends BaseServiceImpl<Comment, Long, CommentRepository> implements CommentService {

    public CommentServiceImpl(CommentRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
