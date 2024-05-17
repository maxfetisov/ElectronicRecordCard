package diploma.electronicrecordcard.startup;

import diploma.electronicrecordcard.service.initialization.InitializationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    InitializationService initializationService;

    @Override
    public void run(String... args) {
        initializationService.createAdmin();
    }
}
