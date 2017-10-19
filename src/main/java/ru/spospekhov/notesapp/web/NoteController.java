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

import java.util.List;


/**
 * Created by shy on 18.10.2017.
 */
@Controller
public class NoteController {
    private static final int MAX_ROWS_PER_PAGE = 10;

    private List<Note> listNoteForForm = null;
    private Status savedStatus = null;

    @Autowired
    private NoteService noteService;

    @RequestMapping("createNote")
    public ModelAndView createNote(@ModelAttribute Note note) {
        return new ModelAndView("noteForm");
    }

    @RequestMapping("editUser")
    public ModelAndView editUser(@RequestParam int id, @ModelAttribute Note note) {
        note = noteService.getNote(id);
        return new ModelAndView("noteForm", "noteObject", note);
    }

    @RequestMapping(value="/")
    public ModelAndView listOfNotes(@RequestParam(required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView("note");
        modelAndView.addObject("note", new Note());
        modelAndView.addObject("savedStatus", savedStatus);

        if (listNoteForForm == null) {
            listNoteForForm = noteService.listNote();
        }
        //List<Note> notes = noteService.listNote();
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

    /*@RequestMapping("/index")
    public String listNotes(Map<String, Object> map) {

        map.put("note", new Note());
        map.put("noteList", noteService.listNote());

        return "note";
    }*/


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNote(@ModelAttribute("note") Note note,
                             BindingResult result) {

        noteService.addNote(note);

        refreshModel();

        return "redirect:/";
    }

    @RequestMapping("saveNote")
    public ModelAndView saveNote(@ModelAttribute Note note) {
        if(note.getId() == 0){
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
    public ModelAndView searchUser(@RequestParam("filterNote") String status, @RequestParam(required = false) Integer page){
        ModelAndView modelAndView = new ModelAndView("note");
        modelAndView.addObject("note", new Note());
        savedStatus = Status.valueOf(status);
        modelAndView.addObject("savedStatus", savedStatus);

        if (Status.ALL == savedStatus) {
            listNoteForForm = noteService.listNote();
        } else {
            listNoteForForm = noteService.getListNotes(savedStatus);
        }


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

        //modelAndView.addObject("noteList", listNoteForForm);
        return modelAndView;
    }

    private void refreshModel() {
        if (savedStatus != null) {
            listNoteForForm = noteService.getListNotes(savedStatus);
        } else {
            listNoteForForm = noteService.listNote();
        }
    }
}
