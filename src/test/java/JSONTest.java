import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.sample.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

import java.util.Stack;

/**
 * Created by Rakesh Komulwad on 5/23/2014.
 */
public class JSONTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONTest.class);
    @Test
    public void emptyTest() {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setName("Rakesh");
        user.setEmail("test@test.com");
        try {
            String json = mapper.writeValueAsString(user);
            LOGGER.debug("JSON--"+json);
            assertNotNull(json);
        } catch (JsonProcessingException e) {
            fail(e.getMessage());
        }
    }
}
