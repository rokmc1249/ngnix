package com.sparta.team2project.posts.controller;

import com.sparta.team2project.commons.dto.MessageResponseDto;
import com.sparta.team2project.commons.exceptionhandler.CustomException;
import com.sparta.team2project.commons.exceptionhandler.ErrorCode;
import com.sparta.team2project.commons.security.UserDetailsImpl;

import com.sparta.team2project.pictures.dto.PicturesMessageResponseDto;
import com.sparta.team2project.pictures.dto.PicturesResponseDto;
import com.sparta.team2project.pictures.dto.UploadResponseDto;
import com.sparta.team2project.posts.dto.PostMessageResponseDto;
import com.sparta.team2project.posts.dto.PostResponseDto;
import com.sparta.team2project.posts.dto.TotalRequestDto;
import com.sparta.team2project.posts.dto.UpdateRequestDto;

import com.sparta.team2project.posts.dto.*;

import com.sparta.team2project.posts.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "게시글 관련 API", description = "게시글 관련 API입니다.")
@RequestMapping("/api")
public class PostsController {

    private final PostsService postsService;

    //게시글 생성
    @Operation(summary = "게시글 생성 ", description = "게시글 생성 api 입니다.")
    @PostMapping("/posts") // 게시글 생성
    public ResponseEntity<PostMessageResponseDto> createPost(@RequestBody TotalRequestDto totalRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(postsService.createPost(totalRequestDto,userDetails.getUsers()));
    }
  
    // 단일 게시물 조회
    @Operation(summary = "게시글 상세 조회 ", description = "게시글 상세 조회 api 입니다.")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId){return ResponseEntity.ok(postsService.getPost(postId));}


    // 게시글 전체 조회
    @Operation(summary = "게시글 전체 조회 ", description = "게시글 전체 조회 api 입니다.")
    @GetMapping("/posts")
    public ResponseEntity<Slice<PostResponseDto>> getAllPosts(@RequestParam int page,@RequestParam int size) {
        return ResponseEntity.ok(postsService.getAllPosts(page,size));
    }

    // 사용자별 게시글 전체 조회
    @Operation(summary = "사용자별 게시글 전체 조회 ", description = "사용자별 게시글 전체 조회 api 입니다.")
    @GetMapping("/user/posts")
    public ResponseEntity<List<PostResponseDto>> getUserPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(postsService.getUserPosts(userDetails.getUsers()));
    }

    // 게시물 검색 조회
    @Operation(summary = "게시글 검색 조회 ", description = "키워드로 게시글 검색 조회  api 입니다.")
    @GetMapping("/search")
    public ResponseEntity<List<PostResponseDto>> getKeywordPost(@RequestParam String keyword){return ResponseEntity.ok(postsService.getKeywordPosts(keyword));}

    // 랭킹 목록 조회
    @Operation(summary = " 좋아요 순 게시글 조회 ", description = "TOP3 좋아요 순 게시글 조회 api 입니다.")
    @GetMapping("/posts/rank")
    public ResponseEntity<List<PostResponseDto>> getRankPosts(){return ResponseEntity.ok(postsService.getRankPosts());}

    // 사용자가 좋아요 누른 게시글 조회
    @Operation(summary = " 사용자가 좋아요 한 게시글 조회 ", description = "사용자가 좋아요 한 게시글 조회 api 입니다.")
    @GetMapping("/posts/like")
    public ResponseEntity<Page<PostResponseDto>> getUserLikePosts(@RequestParam int page, @RequestParam int size,@AuthenticationPrincipal UserDetailsImpl userDetails){return ResponseEntity.ok(postsService.getUserLikePosts(userDetails.getUsers(),page,size));}

    // 사용자가 좋아요 누른 게시글 id만 조회
    @Operation(summary = " 사용자가 좋아요 한 게시글 id만 조회 ", description = "사용자가 좋아요 한 게시글 id만 조회 api 입니다.")
    @GetMapping("/postlike/id")
    public ResponseEntity<List<Long>> getUserLikePostsId(@AuthenticationPrincipal UserDetailsImpl userDetails){return ResponseEntity.ok(postsService.getUserLikePostsId(userDetails.getUsers()));}

    // 좋아요 기능
    @Operation(summary = " 좋아요 기능 ", description = "좋아요 클릭시 1 좋아요 또 누르면 1 취소하는 api 입니다.")
    @GetMapping("/posts/like/{postId}")
    public ResponseEntity<LikeResponseDto> like(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(postsService.like(postId,userDetails.getUsers()));
    }

    // 게시글 수정
    @Operation(summary = " 게시글 수정 ", description = "게시글 수정 api 입니다.")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<MessageResponseDto> updatePost(@PathVariable Long postId, @RequestBody UpdateRequestDto updateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(postsService.updatePost(postId,updateRequestDto,userDetails.getUsers()));
    }

    //게시글 삭제
    @Operation(summary = "게시글 조회 삭제", description = "게시글 삭제 api 입니다.")
    @DeleteMapping("/posts/{postId}") // 게시글 삭제
    public ResponseEntity<MessageResponseDto> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(postsService.deletePost(postId,userDetails.getUsers()));
    }

    // 사진 등록 API 메서드
    @PostMapping("/posts/{postId}/postsPictures")
    public PostsPicturesUploadResponseDto uploadPictures(@PathVariable("postId") Long postId,
                                            @RequestPart("file") List<MultipartFile> files,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        // 업로드할 사진이 3개를 초과하면 예외 출력
        if(files.size() > 3){
            throw new CustomException(ErrorCode.EXCEED_PICTURES_LIMIT);
        }
        // 그 외의 경우 업로드 수행
        else{
            return postsService.uploadPostsPictures(postId, files, userDetails.getUsers());
        }
    }

    @GetMapping("/posts/{postId}/postsPictures")
    public PostsPicturesUploadResponseDto getPostsPictures(@PathVariable("postId") Long postId){
        return postsService.getPostsPictures(postId);
    }

    @GetMapping("/postsPictures/{postsPicturesId}")
    public PostsPicturesResponseDto getPostsPicture(@PathVariable("postsPicturesId") Long postsPicturesId){
        return postsService.getPostsPicture(postsPicturesId);
    }

    @PutMapping("/postsPictures/{postsPicturesId}")
    public PostsPicturesMessageResponseDto updatePictures(@PathVariable("postsPicturesId") Long postsPicturesId,
                                                     @RequestParam("file") MultipartFile file,
                                                     @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return postsService.updatePictures(postsPicturesId, file, userDetails.getUsers());
    }

    @DeleteMapping("/postsPictures/{postsPicturesId}")
    public MessageResponseDto deletePictures(@PathVariable("postsPicturesId") Long postsPicturesId,
                                             @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return postsService.deletePictures(postsPicturesId, userDetails.getUsers());
    }

}
