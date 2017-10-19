package ru.spospekhov.notesapp.dao;

import ru.spospekhov.notesapp.model.Note;
import ru.spospekhov.notesapp.model.Status;

import java.util.List;

/**
 * Created by shy on 18.10.2017.
 */
public interface NoteDao {
    void addNote(Note note);

    List<Note> listNote();

    void removeNote(Integer id);

    void completeNote(Integer id);

    Note getNote(Integer id);

    void updateNote(Note note);

    List<Note> getListNotes(Status status);
}
