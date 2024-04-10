package gdsc.insangjinsolutionchallenge.domain.everlearning;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class EverLearningController {

    private final WebClient webClient = WebClient.create();

    @GetMapping("/everlearning/web/{pageNum}")
    public Mono<String> getLectureListWeb(@PathVariable("pageNum") int pageNum){
        //인증키를 URL에서 사용이 가능하도록 인코딩 된 인증키 값을 넣어야함
        String serviceKey = "VbXK1XrLoX1KFDCaxda0f%2FHxQxg1vLh8p0dQVeTrLAar27RnZl6tkQIwd2QL3n8b0tXCuRmzRXmCW%2FOS6aDK9Q%3D%3D";
        String pageNo = String.valueOf(pageNum);
        String numOfRows = "10";
        String url = String.format("https://apis.data.go.kr/7010000/everlearning/getLectureList?ServiceKey=%s&pageNo=%s&numOfRows=%s",
                serviceKey, pageNo, numOfRows);
        log.info("url: {}", url);
        return webClient.get()
                .uri(url) //API 엔드 포인트, 요청 URL설정
                .retrieve() //요청 보내기 메서드
                .bodyToMono(String.class); // 응답 데이터를 Mono<T>로 변환
    }

    @GetMapping("/everlearning/app/{pageNum}")
    public Mono<String> getLectureListApp(@PathVariable("pageNum") int pageNum){
        //인증키를 URL에서 사용이 가능하도록 인코딩 된 인증키 값을 넣어야함
        String serviceKey = "VbXK1XrLoX1KFDCaxda0f%2FHxQxg1vLh8p0dQVeTrLAar27RnZl6tkQIwd2QL3n8b0tXCuRmzRXmCW%2FOS6aDK9Q%3D%3D";
        String pageNo = String.valueOf(pageNum);
        String numOfRows = "15";
        String url = String.format("https://apis.data.go.kr/7010000/everlearning/getLectureList?ServiceKey=%s&pageNo=%s&numOfRows=%s",
                serviceKey, pageNo, numOfRows);
        log.info("url: {}", url);
        return webClient.get()
                .uri(url) //API 엔드 포인트, 요청 URL설정
                .retrieve() //요청 보내기 메서드
                .bodyToMono(String.class); // 응답 데이터를 Mono<T>로 변환
    }
}
