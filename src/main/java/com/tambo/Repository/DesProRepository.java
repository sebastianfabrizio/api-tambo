package com.tambo.Repository;

import com.tambo.Model.Des_pro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesProRepository extends JpaRepository<Des_pro, Integer> {
}

