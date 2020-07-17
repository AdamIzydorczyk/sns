package tk.aizydorczyk.sns.operation.domain.contra

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification
import tk.aizydorczyk.sns.operation.domain.config.SeleniumConfiguration

@RunWith(SpringRunner.class)
@SpringBootTest(classes = [SeleniumConfiguration.class, SignInPage.class])
class ContraSeleniumTest extends Specification {

    @Autowired
    private SignInPage signInPage

    @Test
    def "A"() {
        given:
        signInPage.open()
        when:
        signInPage.loginAsDefaultUser()
        then:
        true == true
    }

    @Test
    def "B"() {
        given:
        signInPage.open()
        when:
        signInPage.loginAsDefaultUser()
        then:
        true == true
    }
}
