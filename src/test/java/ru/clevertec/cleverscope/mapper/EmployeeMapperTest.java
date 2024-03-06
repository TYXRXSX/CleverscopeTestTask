package ru.clevertec.cleverscope.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cleverscope.dto.DepartmentDto;
import ru.clevertec.cleverscope.dto.EmployeeDto;
import ru.clevertec.cleverscope.dto.RequisiteDto;
import ru.clevertec.cleverscope.entity.Department;
import ru.clevertec.cleverscope.entity.Employee;
import ru.clevertec.cleverscope.entity.Requisite;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeMapperTest {

    @Mock
    private DepartmentMapper departmentMapper;

    @Mock
    private RequisiteMapper requisiteMapper;

    @InjectMocks
    private EmployeeMapper employeeMapper = EmployeeMapper.INSTANCE;

    @Test
    public void entityToDto_shouldMapEntityToDto() {
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        Requisite requisite = new Requisite();
        requisite.setId(1L);
        requisite.setBankAccount("test");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartment(department);
        employee.setRequisite(requisite);

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("IT");

        RequisiteDto requisiteDto = new RequisiteDto();
        requisiteDto.setId(1L);
        requisiteDto.setBankAccount("test");

        when(departmentMapper.entityToDto(department)).thenReturn(departmentDto);
        when(requisiteMapper.entityToDto(requisite)).thenReturn(requisiteDto);

        EmployeeDto employeeDto = employeeMapper.entityToDto(employee);

        assertEquals(employee.getId(), employeeDto.getId());
        assertEquals(employee.getName(), employeeDto.getName());
        assertEquals(departmentDto, employeeDto.getDepartment());
        assertEquals(requisiteDto, employeeDto.getRequisite());
    }

    @Test
    public void dtoToEntity_shouldMapDtoToEntity() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("IT");

        RequisiteDto requisiteDto = new RequisiteDto();
        requisiteDto.setId(1L);
        requisiteDto.setBankAccount("test");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("John Doe");
        employeeDto.setDepartment(departmentDto);
        employeeDto.setRequisite(requisiteDto);

        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        Requisite requisite = new Requisite();
        requisite.setId(1L);
        requisite.setBankAccount("test");

        when(departmentMapper.dtoToEntity(departmentDto)).thenReturn(department);
        when(requisiteMapper.dtoToEntity(requisiteDto)).thenReturn(requisite);

        Employee employee = employeeMapper.dtoToEntity(employeeDto);

        assertEquals(employeeDto.getId(), employee.getId());
        assertEquals(employeeDto.getName(), employee.getName());
        assertEquals(department, employee.getDepartment());
        assertEquals(requisite, employee.getRequisite());
    }
}