package gdsc.insangjinsolutionchallenge.domain.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final ExampleRepository exampleRepository;

    @Transactional
    public Example saveExample(RequestExampleDto requestExampleDto) {
        Example example = Example.builder()
                .title(requestExampleDto.getTitle())
                .content(requestExampleDto.getContent())
                .multipleChoice(requestExampleDto.getMultipleChoice())
                .imgPath(requestExampleDto.getImgPath())
                .correct(requestExampleDto.getCorrect())
                .score(requestExampleDto.getScore())
                .category(requestExampleDto.getCategory())
                .grade(requestExampleDto.getGrade())
                .build();
        exampleRepository.save(example);
        return example;
    }

    /* 문제 조회 메서드 */
    @Transactional(readOnly = true)
    public ResponseExampleDto findExample(Long exampleId) {
        Example example = findById(exampleId);
        return ResponseExampleDto.builder()
                .id(example.getId())
                .content(example.getContent())
                .multipleChoice(example.getMultipleChoice())
                .title(example.getTitle())
                .imgPath(example.getImgPath())
                .correct(example.getCorrect())
                .correctPercentage(example.getCorrectPercentage())
                .score(example.getScore())
                .category(example.getCategory())
                .grade(example.getGrade())
                .createAt(example.getCreateAt())
                .updateAt(example.getUpdateAt())
                .build();
    }

    /* 학년과 유형별 문제 조회 메서드 */
    @Transactional(readOnly = true)
    public List<ResponseExampleListDto> findExampleByGradeAndCategory(String grade, String category) {
        List<Example> examples = exampleRepository.findByGradeAndCategory(grade, category);

        List<ResponseExampleListDto> responseExampleListDtos = examples.stream()
                .map(example -> ResponseExampleListDto.builder()
                        .id(example.getId())
                        .title(example.getTitle())
                        .multipleChoice(example.getMultipleChoice())
                        .category(example.getCategory())
                        .content(example.getContent())
                        .grade(example.getGrade())
                        .score(example.getScore())
                        .correct(example.getCorrect())
                        .correctPercentage(example.getCorrectPercentage())
                        .imgPath(example.getImgPath())
                        .build())
                .toList();
        return responseExampleListDtos;
    }

    @Transactional(readOnly = true)
    public List<ResponseExampleListDto> findExamples() {
        List<Example> examples = exampleRepository.findAll();
        List<ResponseExampleListDto> responseExampleListDtos = examples.stream()
                .map(example -> ResponseExampleListDto.builder()
                        .id(example.getId())
                        .title(example.getTitle())
                        .multipleChoice(example.getMultipleChoice())
                        .category(example.getCategory())
                        .content(example.getContent())
                        .grade(example.getGrade())
                        .score(example.getScore())
                        .correct(example.getCorrect())
                        .correctPercentage(example.getCorrectPercentage())
                        .imgPath(example.getImgPath())
                        .build())
                .toList();

        return responseExampleListDtos;
    }


    @Transactional
    public String updateExample(Long exampleId, RequestExampleDto requestExampleDto) throws IOException {
        Example example = findById(exampleId);
        example.update(requestExampleDto);
        return "수정 완료!";
    }

    @Transactional
    public String deleteExample(Long exampleId) {
        exampleRepository.delete(findById(exampleId));
        return "삭제 완료!";
    }

    private Example findById(Long exampleId) {
        return exampleRepository.findById(exampleId)
                .orElseThrow(() -> new IllegalArgumentException("문제 번호를 확인해 주세요"));
    }
}
