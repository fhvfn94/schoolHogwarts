package com.hog.hog.repository;

import com.hog.hog.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    @Transactional
    Avatar getAvatarByStudentId(Long id);

}
