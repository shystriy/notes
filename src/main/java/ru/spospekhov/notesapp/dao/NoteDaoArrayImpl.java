package ru.spospekhov.notesapp.dao;

import org.springframework.stereotype.Repository;
import ru.spospekhov.notesapp.model.Note;
import ru.spospekhov.notesapp.model.Status;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ========== ItCorp v. 1.0 class library ==========
 * <p/>
 * http://www.it.ru/
 * <p/>
 * &copy; Copyright 1990-2017, by ItCorp.
 * <p/>
 * ========== NoteDaoArrayImpl.java ==========
 * <p/>
 * $Revision:  $<br/>
 * $Author:  $<br/>
 * $HeadURL:  $<br/>
 * $Id:  $
 * <p/>
 * 19.10.2017 15:41: Original version (spospekhov)<br/>
 */
//@Repository
public class NoteDaoArrayImpl implements NoteDao{
    private List<Note> listNotes;

    @PostConstruct
    private void fillList() {
        listNotes = new ArrayList<Note>();
        for (int i = 0; i < 15; i++) {
            Note note = new Note();
            note.setText("Заметка " + i);
            note.setStatus(Status.NOT_COMPLETE.name());
            note.setCreatedDate(new Date());
            note.setId(i);
            listNotes.add(note);
        }
    }
    public void addNote(Note note) {
        listNotes.add(note);
    }

    public List<Note> listNote() {
        return listNotes;
    }

    public void removeNote(Integer id) {
        Note forRemove = null;
        for (Note note : listNotes) {
            if (note.getId() == id) {
                forRemove = note;
                break;
            }
        }
        if (forRemove != null) {
            listNotes.remove(forRemove);
        }
    }

    public void completeNote(Integer id) {
        for (Note note : listNotes) {
            if (note.getId() == id) {
                note.setStatus(Status.COMPLETE.name());
                break;
            }
        }
    }

    public Note getNote(Integer id) {
        for (Note note : listNotes) {
            if (note.getId() == id) {
                return note;
            }
        }
        return null;
    }

    public void updateNote(Note note) {
        for (Note upNote : listNotes) {
            if (upNote.getId() == note.getId()) {
                upNote.setText(note.getText());
                upNote.setStatus(note.getStatus());
                break;
            }
        }
    }

    @Override
    public List<Note> getListNotes(String status) {
        List<Note> result = new ArrayList<>();
        for (Note upNote : listNotes) {
            if (upNote.getStatus().equals(status)) {
                result.add(upNote);
            }
        }
        return result;
    }
}
