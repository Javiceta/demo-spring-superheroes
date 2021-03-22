/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Superheroe;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author R79061236
 */

@Repository
public interface SuperheroeRepository extends JpaRepository<Superheroe, Long>, SuperheroeRepositoryCustom {
    
}
