package com.uba.check_book.repository;

import com.uba.check_book.entity.CheckBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckBookRepository extends JpaRepository<CheckBook,Long> {
}
