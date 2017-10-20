package ru.spospekhov.notesapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spospekhov.notesapp.model.Note;
import ru.spospekhov.notesapp.model.Status;
import ru.spospekhov.notesapp.service.NoteService;

import java.util.Collections;
import java.util.List;


/**
 * Created by shy on 18.10.2017.
 */
@Controller
public class NoteController {
    private static final int MAX_ROWS_PER_PAGE = 10;

    private static List<Note> listNoteForForm = null;
    private static Status savedStatus = null;

    @Autowired
    private NoteService noteService;

    @RequestMapping("createNote")
    public ModelAndView createNote(@ModelAttribute Note note) {
        return new ModelAndView("noteForm");
    }

    @RequestMapping("editNote")
    public ModelAndView editNote(@RequestParam Integer id, @ModelAttribute Note note) {
        note = noteService.getNote(id);

        return new ModelAndView("noteForm", "noteObject", note);
    }

    @RequestMapping(value="/")
    public ModelAndView listOfNotes(@RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("note");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("savedStatus", savedStatus);

        if (listNoteForForm == null || listNoteForForm.isEmpty()) {
            listNoteForForm = noteService.listNote();
        }

        return pagination(modelAndView, page);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNote(@ModelAttribute("note") Note note,
                             BindingResult result) {

        noteService.addNote(note);

        refreshModel();

        return "redirect:/";
    }

    @RequestMapping(value = "saveNote")
    public ModelAndView saveNote(@ModelAttribute Note note) throws Exception{

        if (note.getId() == null || note.getId() == 0) {
            noteService.addNote(note);
        } else {
            noteService.updateNote(note);
        }

        refreshModel();

        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId) {

        noteService.removeNote(noteId);

        refreshModel();

        return "redirect:/";
    }

    @RequestMapping("/complete/{noteId}")
    public String completeNote(@PathVariable("noteId") Integer noteId) {

        noteService.completeNote(noteId);
        refreshModel();

        return "redirect:/";
    }

    @RequestMapping("filterNote")
    public ModelAndView filterNote(@RequestParam("filterNote") String status, @RequestParam(required = false) Integer page){
        ModelAndView modelAndView = new ModelAndView("note");
        modelAndView.addObject("note", new Note());
        savedStatus = Status.valueOf(status);
        modelAndView.addObject("savedStatus", savedStatus);
        if (Status.ALL == savedStatus) {
            listNoteForForm = noteService.listNote();
        } else {
            listNoteForForm = noteService.getListNotes(status);
        }

        return pagination(modelAndView, page);
    }

    @RequestMapping("sortNote")
    public ModelAndView sortNote( @RequestParam(required = false) Integer page){
        ModelAndView modelAndView = new ModelAndView("note");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("savedStatus", savedStatus);
        if (listNoteForForm == null) listNoteForForm = noteService.listNote();
        Collections.reverse(listNoteForForm);
        return pagination(modelAndView, page);
    }

    private void refreshModel() {
        if (savedStatus != null) {
            listNoteForForm = noteService.getListNotes(savedStatus.name());
        } else {
            listNoteForForm = noteService.listNote();
        }
    }

    private ModelAndView pagination(ModelAndView modelAndView, Integer page) {
        PagedListHolder<Note> pagedListHolder = new PagedListHolder<>(listNoteForForm);
        pagedListHolder.setPageSize(MAX_ROWS_PER_PAGE);
        modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            page=1;
        }
        modelAndView.addObject("page", page);
        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            modelAndView.addObject("noteList", pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            modelAndView.addObject("noteList", pagedListHolder.getPageList());
        }
        return modelAndView;
    }
}
