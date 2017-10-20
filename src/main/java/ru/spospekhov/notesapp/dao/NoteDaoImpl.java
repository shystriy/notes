package ru.spospekhov.notesapp.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.spospekhov.notesapp.model.Note;
import ru.spospekhov.notesapp.model.Status;

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
        return sessionFactory.getCurrentSession().createQuery("from Note N order by N.createdDate desc").list();
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
            note.setStatus(Status.COMPLETE.name());
            sessionFactory.getCurrentSession().persist(note);
        }
    }

    public Note getNote(Integer id) {
        Note note = (Note) sessionFactory.getCurrentSession().load(
                Note.class, id);
        if (null != note) {
            return note;
        } else {
            return new Note();
        }
    }

    public void updateNote(Note note) {
        Note editNote = (Note) sessionFactory.getCurrentSession().load(
                Note.class, note.getId());
        if (null != editNote) {
            note.setText(note.getText());
            note.setStatus(note.getStatus());
            sessionFactory.getCurrentSession().persist(note);
        }
    }

    @Override
    public List<Note> getListNotes(String status) {
        String hql = "from Note N where N.status = :status_ order by N.createdDate desc";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("status_", status);

        return query.list();
    }
}
