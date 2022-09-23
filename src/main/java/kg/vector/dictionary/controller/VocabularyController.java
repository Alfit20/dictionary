package kg.vector.dictionary.controller;

import kg.vector.dictionary.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class VocabularyController {
    private final VocabularyService vocabularyService;


    @GetMapping
    public String getWord(Model model) {
        model.addAttribute("word", vocabularyService.getOneRandomWord());
        model.addAttribute("words", vocabularyService.getWords());
        model.addAttribute("count", vocabularyService.getSizeWords());
        model.addAttribute("learn", vocabularyService.numberOfLearnedWords());
        return "index";
    }



    @PutMapping("/{id}")
    @ResponseBody
    public void learnWord(@PathVariable Long id) {
        vocabularyService.learnWord(id);
    }

    @PostMapping("/change")
    public String change() {
        return "redirect:/";
    }

    @PostMapping("/upload")
    public String uploadNewWords(@RequestParam("file") MultipartFile file) {
        vocabularyService.uploadFile(file);
        return "redirect:/";
    }

    @GetMapping("/dictionary")
    public String getDictionary(Model model) {
        model.addAttribute("all", vocabularyService.getAllWordInDictionaries());
        return "dictionary";
    }
}
