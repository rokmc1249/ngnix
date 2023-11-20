package com.sparta.team2project.notify;

import com.sparta.team2project.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotifyRepository extends JpaRepository<Notify, Long> {
    List<Notify> findByReceiverAndIsRead(Users users,Boolean isRead);

    Notify findByReceiverAndId(Users users, Long id);
}
