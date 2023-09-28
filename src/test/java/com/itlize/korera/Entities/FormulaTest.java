package com.itlize.korera.Entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FormulaTest {

  private Formula formula;

    @BeforeEach
    void setUp() {
        Project project = new Project(); 
        Resource resource = new Resource(); 
        ColumnTypeEnum columnType = ColumnTypeEnum.NUMBER; 

        formula = new Formula("Test Field", columnType, "Test Value", project, resource);
    }

    @Test
    void testFormulaId() {
        formula.setFormulaId(1L);
        assertEquals(1L, formula.getFormulaId());
    }

    @Test
    void testFieldName() {
        formula.setFieldName("Updated Field");
        assertEquals("Updated Field", formula.getFieldName());
    }

    @Test
    void testFieldType() {
        ColumnTypeEnum updatedType = ColumnTypeEnum.NUMBER;
        formula.setFieldType(updatedType);
        assertEquals(updatedType, formula.getFieldType());
    }

    @Test
    void testFieldValue() {
        formula.setFieldValue("Updated Value");
        assertEquals("Updated Value", formula.getFieldValue());
    }

    @Test
    void testProject() {
        Project updatedProject = new Project(); 
        formula.setProject(updatedProject);
        assertEquals(updatedProject, formula.getProject());
    }

    @Test
    void testResource() {
        Resource updatedResource = new Resource(); 
        formula.setResource(updatedResource);
        assertEquals(updatedResource, formula.getResource());
    }

    @Test
    void testToString() {
        assertNotNull(formula.toString());
    }
  
}
