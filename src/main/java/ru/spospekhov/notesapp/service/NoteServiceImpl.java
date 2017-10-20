package ru.spospekhov.notesapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spospekhov.notesapp.dao.NoteDao;
import ru.spospekhov.notesapp.dao.NoteDaoArrayImpl;
import ru.spospekhov.notesapp.model.Note;
import ru.spospekhov.notesapp.model.Status;

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

    @Transactional
    public void completeNote(Integer id) {
        noteDao.completeNote(id);
    }

    @Transactional
    public Note getNote(Integer id) {
        return noteDao.getNote(id);
    }

    @Transactional

    public void updateNote(Note note) {
        noteDao.updateNote(note);
    }

    @Transactional
    public List<Note> getListNotes(String status) {
        return noteDao.getListNotes(status);
    }
}
