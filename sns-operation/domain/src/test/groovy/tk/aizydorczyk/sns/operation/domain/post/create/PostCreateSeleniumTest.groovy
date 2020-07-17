package tk.aizydorczyk.sns.operation.domain.post.create

import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification
import tk.aizydorczyk.sns.operation.domain.config.SeleniumConfiguration

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeleniumConfiguration.class)
class PostCreateSeleniumTest extends Specification {

    @Autowired
    private WebDriver driver

    @Test
    def "A"() {
        given:
        driver.get("https://www.google.pl")
        when:
        final String findButtonName = driver.findElement(By.name("btnK")).getAttribute("value")
        then:
        findButtonName == "Szukaj w Google"
    }
}
