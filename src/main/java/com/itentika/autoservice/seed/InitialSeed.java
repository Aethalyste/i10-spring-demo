package com.itentika.autoservice.seed;

import com.itentika.autoservice.dao.EmployeeRepository;
import com.itentika.autoservice.dao.PositionRepository;
import com.itentika.autoservice.domain.Employee;
import com.itentika.autoservice.domain.Position;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Component
public class InitialSeed {
    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;

    public InitialSeed(PositionRepository positionRepository, EmployeeRepository employeeRepository) {
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
    }

    @EventListener
    @Transactional
    public void seed(ContextRefreshedEvent event) {
        Position adminPosition = new Position(1L, "Administrator");
        Position masterPosition = new Position(2L, "Master");

        this.positionRepository.save(adminPosition);
        this.positionRepository.save(masterPosition);
        this.positionRepository.flush();

        Employee adminEmployee = new Employee("Alice", "+44331", adminPosition);
        Employee masterEmployee = new Employee("Bob", "+44332", masterPosition);

        this.employeeRepository.save(adminEmployee);
        this.employeeRepository.save(masterEmployee);
        this.employeeRepository.flush();
    }
}
