package ru.spospekhov.notesapp.service;

import ru.spospekhov.notesapp.model.Note;
import ru.spospekhov.notesapp.model.Status;

import java.util.List;

/**
 * Created by shy on 18.10.2017.
 */
public interface NoteService {
    public void addNote(Note note);

    public List<Note> listNote();

    public void removeNote(Integer id);

    public void completeNote(Integer id);

    public Note getNote(Integer id);

    public void updateNote(Note note);

    public List<Note> getListNotes(Status status);
}
