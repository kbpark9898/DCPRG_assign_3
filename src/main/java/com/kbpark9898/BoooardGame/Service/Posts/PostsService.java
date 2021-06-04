package com.kbpark9898.BoooardGame.Service.Posts;


import com.kbpark9898.BoooardGame.Domain.Posts;
import com.kbpark9898.BoooardGame.Repository.PostsRepository;
import com.kbpark9898.BoooardGame.web.dto.PostsListResponseDto;
import com.kbpark9898.BoooardGame.web.dto.PostsResponseDto;
import com.kbpark9898.BoooardGame.web.dto.PostsSaveRequestDto;
import com.kbpark9898.BoooardGame.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto){
        System.out.println(requestDto.toEntity().getTitle());
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="
        + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
    public PostsResponseDto findByID(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id)
        );
        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

}
