package com.theIronYard.repository;

import com.theIronYard.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findById(Integer id);

    void deleteById(Integer id);
}
