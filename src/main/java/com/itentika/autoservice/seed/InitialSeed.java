package com.itentika.autoservice.seed;

import com.itentika.autoservice.dao.EmployeeRepository;
import com.itentika.autoservice.dao.ItemPriceRepository;
import com.itentika.autoservice.dao.ItemPriceUnitRepository;
import com.itentika.autoservice.dao.PositionRepository;
import com.itentika.autoservice.domain.Employee;
import com.itentika.autoservice.domain.ItemPrice;
import com.itentika.autoservice.domain.ItemPriceUnit;
import com.itentika.autoservice.domain.Position;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialSeed {
    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;
    private final ItemPriceRepository itemPriceRepository;
    private final ItemPriceUnitRepository itemPriceUnitRepository;

    public InitialSeed(
            PositionRepository positionRepository,
            EmployeeRepository employeeRepository,
            ItemPriceRepository itemPriceRepository,
            ItemPriceUnitRepository itemPriceUnitRepository
    ) {
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
        this.itemPriceRepository = itemPriceRepository;
        this.itemPriceUnitRepository = itemPriceUnitRepository;
    }

    @EventListener
    @Transactional
    public void seed(ContextRefreshedEvent event) {
        Position adminPosition = new Position(1L, "Administrator");
        Position masterPosition = new Position(2L, "Master");

        this.positionRepository.save(adminPosition);
        this.positionRepository.save(masterPosition);
        this.positionRepository.flush();

        this.employeeRepository.save(new Employee("Alice", "+44331", adminPosition));
        this.employeeRepository.save(new Employee("Bob", "+44332", masterPosition));
        this.employeeRepository.flush();

        ItemPriceUnit unitItems = new ItemPriceUnit(1L, "шт.");
        ItemPriceUnit unitLitres = new ItemPriceUnit(2L, "л.");
        ItemPriceUnit unitPeopleHours = new ItemPriceUnit(3L, "Чел.ч.");

        this.itemPriceUnitRepository.save(unitItems);
        this.itemPriceUnitRepository.save(unitLitres);
        this.itemPriceUnitRepository.save(unitPeopleHours);
        this.itemPriceUnitRepository.flush();

        this.itemPriceRepository.save(new ItemPrice("Замена колеса", 500D, unitPeopleHours));
        this.itemPriceRepository.save(new ItemPrice("Замена радиатора", 750D, unitPeopleHours));
        this.itemPriceRepository.save(new ItemPrice("Масло SHELL", 900D, unitLitres));
        this.itemPriceRepository.save(new ItemPrice("радиатор", 25000D, unitItems));
        this.itemPriceRepository.save(new ItemPrice("Свечи SHELL", 1500D, unitItems));
        this.itemPriceRepository.flush();

    }
}
