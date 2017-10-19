package ru.spospekhov.notesapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.spospekhov.notesapp.model.Note;
import ru.spospekhov.notesapp.service.NoteService;

import java.util.Map;


/**
 * Created by shy on 18.10.2017.
 */
@Controller
public class NoteController {
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

    @RequestMapping("/index")
    public String listNotes(Map<String, Object> map) {

        map.put("note", new Note());
        map.put("noteList", noteService.listNote());

        return "note";
    }

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addNote(@ModelAttribute("note") Note note,
                             BindingResult result) {

        noteService.addNote(note);

        return "redirect:/index";
    }

    @RequestMapping("saveNote")
    public ModelAndView saveNote(@ModelAttribute Note note) {
        if(note.getId() == 0){
            noteService.addNote(note);
        } else {
            noteService.updateNote(note);
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") Integer noteId) {

        noteService.removeNote(noteId);

        return "redirect:/index";
    }

    @RequestMapping("/complete/{noteId}")
    public String completeNote(@PathVariable("noteId") Integer noteId) {

        noteService.completeNote(noteId);

        return "redirect:/index";
    }
}
