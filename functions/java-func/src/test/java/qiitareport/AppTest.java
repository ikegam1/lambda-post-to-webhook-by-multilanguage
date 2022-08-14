package qiitareport;

import java.util.HashMap;
import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AppTest {
  @Test
  public void successfulResponse() {
    App app = new App();
    Map<String, String> map = new HashMap<String, String>();
    map.put("q","java");
    Context ctx = new TestContext();
    String result = app.handleRequest(map, ctx);
    assertEquals("200", result);
  }
}

