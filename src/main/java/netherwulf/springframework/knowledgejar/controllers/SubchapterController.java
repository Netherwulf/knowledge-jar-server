package netherwulf.springframework.knowledgejar.controllers;

import netherwulf.springframework.knowledgejar.api.v1.model.SubchapterDTO;
import netherwulf.springframework.knowledgejar.api.v1.model.SubchapterListDTO;
import netherwulf.springframework.knowledgejar.services.SubchapterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(SubchapterController.BASE_URL)
public class SubchapterController {
    public static final String BASE_URL = "/api/v1/chapters";

    private final SubchapterService subchapterService;

    public SubchapterController(SubchapterService subchapterService) {
        this.subchapterService = subchapterService;
    }

    @GetMapping("/{id}/subchapters")
    @ResponseStatus(HttpStatus.OK)
    public SubchapterListDTO getSubchaptersByChapterId(@PathVariable Long id) {
        return subchapterService.getAllSubchaptersByChapterId(id);
    }

    @GetMapping("/{chapterId}/subchapters/{subchapterId}")
    @ResponseStatus(HttpStatus.OK)
    public SubchapterDTO getSubchapterByChapterIdAndSubchapterId(@PathVariable Long chapterId, @PathVariable Long subchapterId) {
        return subchapterService.getByChapterIdAndSubchapterId(chapterId, subchapterId);
    }
}
