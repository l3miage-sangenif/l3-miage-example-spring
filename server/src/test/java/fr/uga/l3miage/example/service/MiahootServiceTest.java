package fr.uga.l3miage.example.service;

import fr.uga.l3miage.example.component.ExampleComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class MiahootServiceTest {

    @MockBean
    private ExampleComponent exampleComponent;
    @Autowired
    private ExampleService exampleService;
}
