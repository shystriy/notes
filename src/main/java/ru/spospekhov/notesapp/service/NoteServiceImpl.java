package ru.spospekhov.notesapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spospekhov.notesapp.dao.NoteDao;
import ru.spospekhov.notesapp.model.Note;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shy on 18.10.2017.
 */
@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Transactional
    public void addNote(Note note) {
        noteDao.addNote(note);
    }

    @Transactional
    public List<Note> listNote() {
        return noteDao.listNote();
    }

    @Transactional
    public void removeNote(Integer id) {
        noteDao.removeNote(id);
    }
}
