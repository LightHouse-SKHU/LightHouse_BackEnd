package gdsc.insangjinsolutionchallenge.domain.post;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "게시판 API", description = "게시판관련 API.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @PostMapping("/save")
    public String save(Principal principal, @RequestBody @Valid RequestPostDto requestPostDto) {
        return postService.save(Long.valueOf(principal.getName()), requestPostDto);
    }

    @GetMapping("/find/list/all")
    public List<ResponsePostListDto> findAll(Principal principal) {
        return postService.findAll(Long.valueOf(principal.getName()));
    }

    //좋아요 많은 순서로 정렬
    @GetMapping("/find/list/like")
    public List<ResponsePostLikeListDto> findAllByLikeCount() {
        return postService.findAllByLikeCount();
    }

    // 검색어로 검색하는 기능
    @GetMapping("/find")
    public ResponseEntity<List<ResponsePostListDto>> searchPosts(@RequestParam("searchTerm") String searchTerm) {
        List<ResponsePostListDto> searchResults = postService.findSearchTerm(searchTerm);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponsePostDto find(Principal principal, @PathVariable("id") Long id) {
        return postService.find(Long.valueOf(principal.getName()), id);
    }

    @PatchMapping("/update/{id}")
    public String update(Principal principal, @PathVariable("id") Long id, @RequestBody RequestPostDto requestPostDto) {
        return postService.update(Long.valueOf(principal.getName()), id, requestPostDto);
    }

    @DeleteMapping("delete/{id}")
    public String delete(Principal principal, @PathVariable("id") Long id) {
        return postService.delete(Long.valueOf(principal.getName()), id);
    }
}
