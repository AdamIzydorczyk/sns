package tk.aizydorczyk.sns.performance

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import tk.aizydorczyk.sns.performance.PostControllerPerformanceTest._;

class PostControllerPerformanceTest extends Simulation {
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(URL)
    .acceptHeader(CONTENT_TYPE)
    .contentTypeHeader(CONTENT_TYPE)
    .header("userUuid", USER_HEADER)
    .userAgentHeader(AGENT_HEADER)

  val scn: ScenarioBuilder = scenario(SCENARIO_NAME)
    .repeat(REPEAT) {
      exec(http(REQUEST_NAME).post(API_URL).body(StringBody("""{"content":"OK OK"}""")))
    }

  setUp(
    scn.inject(atOnceUsers(USERS_AT_ONCE))
  ).protocols(httpProtocol)
    .assertions(global.requestsPerSec.gte(MIN_REQUESTS_COUNT))
}

object PostControllerPerformanceTest {
  private val URL = "http://localhost:8083"
  private val CONTENT_TYPE = "application/json;charset=UTF-8"
  private val AGENT_HEADER = "Gatling"
  private val USER_HEADER = UUID.randomUUID().toString
  private val SCENARIO_NAME = "SnsPerformanceTest"
  private val API_URL = s"/sns/operation/posts"
  private val REQUEST_NAME = s"POST $API_URL"
  private val REPEAT = 10
  private val USERS_AT_ONCE = 3500
  private val MIN_REQUESTS_COUNT = 20
}
