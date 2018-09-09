package prv.dudekre.warehouse;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchTest {
    private static final String DOMAIN_PACKAGES = "prv.dudekre.warehouse.domain..";
    private static final String APP_PACKAGES = "prv.dudekre.warehouse.app..";
    private static final String INFRA_PACKAGES = "prv.dudekre.warehouse.infra..";

    private static JavaClasses classes = new ClassFileImporter().importPackages("prv.dudekre.warehouse");

    @Test
    public void appShouldNotDependOnInfra() {
        noClasses()
                .that().resideInAnyPackage(APP_PACKAGES)
                .should().dependOnClassesThat().resideInAnyPackage(INFRA_PACKAGES)
                .check(classes);
    }

    @Test
    public void domainShouldNotDependOnAppNorInfra() {
        noClasses().that().resideInAnyPackage(DOMAIN_PACKAGES)
                .should().dependOnClassesThat().resideInAnyPackage(APP_PACKAGES)
                .orShould().dependOnClassesThat().resideInAnyPackage(INFRA_PACKAGES)
                .check(classes);
    }
}
