package com.venkatsai.codelink.service;

import com.venkatsai.codelink.model.Project;
import com.venkatsai.codelink.model.User;
import com.venkatsai.codelink.repositories.ProjectRepository;
import com.venkatsai.codelink.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private UserRepository userRepository;

    public Project createProject(Long userId, Project project) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            project.setUser(user.get());
            List<Project> projectList = user.get().getProjects();
            projectList.add(project);
            user.get().setProjects(projectList);
            userRepository.save(user.get());
            return project;
        }
        return null;
    }

    public List<Project> getProjects(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return user.get().getProjects();
        }
        return null;
    }
}
