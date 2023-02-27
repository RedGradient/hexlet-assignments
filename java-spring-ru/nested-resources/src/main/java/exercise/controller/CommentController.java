package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getComment(@PathVariable Long postId,
                              @PathVariable Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found")
        );
    }

    @PostMapping(path = "/{postId}/comments")
    public void createComment(@PathVariable Long postId) {
        var post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found")
        );
        var newComment = new Comment();
        newComment.setPost(post);
        newComment.setContent("Test comment");
        commentRepository.save(newComment);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public void updateComment(@PathVariable Long postId,
                              @PathVariable Long commentId,
                              @RequestBody Comment patchComment) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found");
        }
        patchComment.setId(commentId);
        var post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found")
        );
        patchComment.setPost(post);
        commentRepository.save(patchComment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long postId,
                              @PathVariable Long commentId) {
        var comment = commentRepository.findByIdAndPostId(commentId, postId).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found")
        );
        commentRepository.delete(comment);
    }
    // END
}
