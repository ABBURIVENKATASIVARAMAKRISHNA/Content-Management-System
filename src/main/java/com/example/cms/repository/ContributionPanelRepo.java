package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.ContributionPanel;
import com.example.cms.entity.User;

public interface ContributionPanelRepo extends JpaRepository<ContributionPanel, Integer> {




	boolean existsByPanelIdAndList(int panelId, User user);

}
