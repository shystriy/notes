package ru.spospekhov.notesapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ru.spospekhov.notesapp.model.Note;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shy on 18.10.2017.
 */
@Repository
public class NoteDaoImpl implements NoteDao{
    @Autowired
    private SessionFactory sessionFactory;

    public void addNote(Note note) {
        sessionFactory.getCurrentSession().save(note);
    }


    @SuppressWarnings("unchecked")
    public List<Note> listNote() {
        return sessionFactory.getCurrentSession().createQuery("from Note").list();
    }

    public void removeNote(Integer id) {
        Note note = (Note) sessionFactory.getCurrentSession().load(
                Note.class, id);
        if (null != note) {
            sessionFactory.getCurrentSession().delete(note);
        }
    }

    public void completeNote(Integer id) {
        Note note = (Note) sessionFactory.getCurrentSession().load(
                Note.class, id);
        if (null != note) {
            note.setStatus("Выполнена");
            sessionFactory.getCurrentSession().persist(note);
        }
    }
}
