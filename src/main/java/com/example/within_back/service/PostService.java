package com.example.within_back.service;

import com.example.within_back.dto.PostReqDto;
import com.example.within_back.dto.PostResDto;
import com.example.within_back.entity.*;
import com.example.within_back.repository.*;
import com.example.within_back.dto.CommentReqDto;
import com.example.within_back.dto.CommentsResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    private ArrayList<String> positionList = new ArrayList<>(List.of("보병", "기갑", "포병", "방공", "정보", "공병", "정보", "공병", "정보통신", "항공", "화학", "병기", "병참", "수송", "인사행정", "헌병", "재정", "정훈", "의무", "법무", "군종"));
    private ArrayList<String> hobbyList = new ArrayList<>(List.of("축구", "농구", "야구", "헬스", "뜀걸음", "노래", "게임", "영화", "드라마", "만화", "요리", "독서"));

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UnitRepository unitRepository;

//    public int addLikes(Long postId) {
//        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 post id입니다."));
//        post.setLiked(post.getLiked() + 1);
//        postRepository.save(post);
//        return post.getLiked();
//    }

    public ArrayList<PostResDto> getPosts(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 board id 입니다."));
        List<Post> data = board.getPosts();
        ArrayList<PostResDto> result = new ArrayList<>();
        for (Post post : data) {
            int commentCount = commentRepository.countByPostId(post.getId());
            int likeCount = likeRepository.countByPostId(post.getId());
            PostResDto temp = new PostResDto(post, likeCount, commentCount);
            result.add(temp);
        }
        return result;
    }
    //게시판 게시물 조회 (GET 방식)


    @Transactional
    public Long save(PostReqDto postReqDto, String category,Long userId){
        User author = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 user id 입니다."));
        Long unitId = author.getUnit().getId();
        Board board = boardRepository.findByUnitIdAndCategory(unitId, category);
        return postRepository.save(postReqDto.toEntity(board,author)).getId();
    } //게시물 작성 (POST 방식)

    public PostResDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        int commentCount = commentRepository.countByPostId(postId);
        int likeCount = likeRepository.countByPostId(post.getId());
        return new PostResDto(post, likeCount, commentCount);
    } //게시글 조회

    public ArrayList<CommentsResDto> getComments(Long postId) {
        ArrayList<Comment> data = commentRepository.findByPostIdOrderByCreatedAt(postId);

        ArrayList<CommentsResDto> result = new ArrayList<>();

        for(Comment comment : data) {
            CommentsResDto temp = new CommentsResDto(comment);
            result.add(temp);
        }

        return result;
    }

    @Transactional
    public Long writeComment(Long postId, Long authorId, CommentReqDto commentReqDto) {
        User Author = userRepository.findById(authorId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 user id 입니다."));
        Post post = postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 post id 입니다."));
        return commentRepository.save(commentReqDto.toEntity(post, Author)).getId();
    }

    @Transactional
    public Long createUnit(String unitName){
        Unit unit = unitRepository.save(new Unit(unitName));

        boardRepository.save(new Board("칭찬", "칭찬", "부대 미담을 공유해주세요", unit));
        boardRepository.save(new Board("건의", "건의", "건의 내용을 공유해주세요", unit));
        boardRepository.save(new Board(unitName, unitName, unitName + " 소속 장병들을 위한 게시판입니다", unit));

        boardRepository.save(new Board("ISFJ", "ISFJ", "ISFJ들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ISFP", "ISFP", "ISFP들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ISTJ", "ISTJ", "ISTJ들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ISTP", "ISTP", "ISTP들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("INFJ", "INFJ", "INFJ들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("INFP", "INFP", "INFP들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("INTJ", "INTJ", "INTJ들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("INTP", "INTP", "INTP들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ESFJ", "ESFJ", "ESFJ들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ESFP", "ESFP", "ESFP들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ESTJ", "ESTJ", "ESTJ들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ESTP", "ESTP", "ESTP들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ENFJ", "ENFJ", "ENFJ들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ENFP", "ENFP", "ENFP들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ENTJ", "ENTJ", "ENTJ들을 위한 게시판입니다", unit));
        boardRepository.save(new Board("ENTP", "ENTP", "ENTP들을 위한 게시판입니다", unit));

        for(String position : positionList){
            boardRepository.save(new Board(position, position, position + "들을 위한 게시판입니다", unit));
        }

        for(String hobby : hobbyList){
            boardRepository.save(new Board(hobby, hobby, hobby + "을(를) 좋아하는 사람들을 위한 게시판입니다", unit));
        }

        return unit.getId();
    }
    public String getBoardName(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 board id 입니다."));
        return board.getBoardName();
    }

    @Transactional
    public int likes(Long postId, Long userId) {
        likeRepository.likes(postId, userId);
        return likeRepository.countByPostId(postId);
    }
    @Transactional
    public int unlikes(Long postId, Long userId) {
        likeRepository.unlikes(postId, userId);
        return likeRepository.countByPostId(postId);
    }

    public boolean isLiked(Long postId, Long userId) {
        Likes likes = likeRepository.findByPostIdAndUserId(postId, userId);
        return !Objects.isNull(likes);
    }
}