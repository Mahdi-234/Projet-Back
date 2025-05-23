package com.Mahdi.hadiji.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Mahdi.hadiji.Entite.Repartition;

public interface RepartitionRepository extends JpaRepository<Repartition,Long> 
{
    List<Repartition> getByElementConstitutifId(Long elementId);


}
