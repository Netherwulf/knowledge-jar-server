package netherwulf.springframework.knowledgejar.controllers;

import netherwulf.springframework.knowledgejar.api.v1.model.ChapterDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.ChapterListDTO;
import netherwulf.springframework.knowledgejar.services.ChapterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ChapterController.BASE_URL)
public class ChapterController {
    public static final String BASE_URL = "/api/v1/chapters";

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ChapterListDTO getAllChapters() {
        return chapterService.getAllChapters();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChapterDTO getChapterById(@PathVariable Long id) {
        return chapterService.getChapterById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChapterDTO createNewChapter(@RequestBody ChapterDTO chapterDTO) {
        return chapterService.createNewChapter(chapterDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChapterDTO updateChapter(@PathVariable Long id, @RequestBody ChapterDTO chapterDTO) {
        return chapterService.saveChapterByDTO(id, chapterDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChapterDTO patchChapter(@PathVariable Long id, @RequestBody ChapterDTO chapterDTO) {
        return chapterService.patchChapter(id, chapterDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteChapter(@PathVariable Long id) {
        chapterService.deleteChapterById(id);
    }
}
