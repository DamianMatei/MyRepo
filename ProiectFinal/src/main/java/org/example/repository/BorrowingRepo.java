package org.example.repository;

import org.example.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRepo extends JpaRepository<Borrowing,Integer> {
}
