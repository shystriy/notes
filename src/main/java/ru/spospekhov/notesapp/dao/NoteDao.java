package ru.spospekhov.notesapp.dao;

import ru.spospekhov.notesapp.model.Note;

import java.util.List;

/**
 * Created by shy on 18.10.2017.
 */
public interface NoteDao {
    public void addNote(Note note);

    public List<Note> listNote();

    public void removeNote(Integer id);

    public void completeNote(Integer id);

    public Note getNote(Integer id);

    public void updateNote(Note note);
}
